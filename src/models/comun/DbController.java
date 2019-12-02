package src.models.comun;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DbController {
	
	private Connection con;
	
	private static DbController instance;
	 
	private DbController() { 
		DbSqlite db = DbSqlite.getInstance();
		con = db.getConnection();
	}

	public static DbController getInstance() {
		if (instance == null) {
			instance = new DbController();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return con;
	}
	
	/**
	 * Ejecuta la consulta SQL que se pasa por parametro
	 * 
	 * @param sql - Tiene que ser una consulta SQL valida y correcta
	 * @return Devuelve si fue bien o mal la ejecución de la consulta. Puede devolver false; si la consulta no está bien formulada
	 */
	private boolean doExecute(String sql) {
		try {
			
			Statement statemnt = this.con.createStatement(); 
			statemnt.execute(sql);
			statemnt.close();
		 
		} catch (SQLException e) { 
			e.printStackTrace();
			System.out.println(e);
			return false;
		}
		
		return true;
	}
	
	/**
	 * <strong>EJERCICIO</strong>
	 * 
	 * @param sql
	 * @return - Debe devolver un array de DbObject o array vacio
	 */
	private List<DbObject> doExecuteQuery(String sql, DbObject obj ){
		ArrayList<DbObject> dev = new ArrayList<DbObject>();
		
		try {
			Statement statemnt = this.con.createStatement(); 
			ResultSet res = statemnt.executeQuery(sql);
			
			while(res.next()) { 
				DbObject nObj = obj.getDbObject(res);
				dev.add(nObj);
			} 
			statemnt.close();
		} catch (SQLException e) { 
			e.printStackTrace();
			System.out.println(e);
			return null;
		} 
		
		return dev;
	}
	
	/**
	 * Guarda un objeto nuevo en la base de datos
	 * <strong>NOTA: No comprueba si el objecto existe o tiene campos duplicados</strong>
	 * @param obj - Tiene que ser un objecto valido; con los datos correctos de las funciones: getTable, getCampos, getValues
	 * @return Devuelve si fue bien o mal el salvado del objecto. Puede devolver false; si la consulta no está bien formulada
	 */
	private boolean doSave(DbObject obj) {
		String TABLA = obj.getTable();
		String campos = obj.getCampos();
		String valor = obj.getValues();
		 
		String sql = "INSERT INTO "+TABLA+" ("+campos+") VALUES ("+valor+");";
		System.out.println(sql);
		
		boolean check = this.doExecute(sql); 
		if (check) {
			System.out.println("Objeto "+TABLA+" - Insertada");
		}else {
			System.out.println("FALLO "+TABLA+" - Insertada");
		}
		return check;
	}
	
	private boolean doUpdate(DbObject obj) {
		/*
		 * Modificaciones es el cuerpo de la consulta del update.
		 * Se construye con el string campos y el values de cada objecto; 
		 * tenemos que presuponer que ambos campos son iguales y tienen el
		 * mismo tamaño. Siendo esto así; podemos hacer un split por el campo
		 * de separación ','; y recoger la posición del array para concatenarlo y 
		 * poner un igual en medio 
		 */
		
		String TABLA = obj.getTable();
		String campos = obj.getCampos();
		String valores = obj.getValues();
				
		String modificaciones = "";
		String[] arCampos  = campos.split(",");
		String[] arValores = valores.split(",");
		
		for (int i = 0; i < arCampos.length; i++) {
			String campo = arCampos[i];
			String valor = arValores[i];
			
			// Nos genera un string equivalente a: 'campo'=valor 
			// y lo concatena a modificaciones
			modificaciones = modificaciones + campo+"="+valor;
			//Posible solucion a evitar que tenga coma en el ultimo elemento.
			if (i <= arCampos.length) {
				modificaciones = modificaciones+",";
			}
		}
		// Comprobamos que el ultimo caracter es una , y la eliminamos. 
		// Para evitar problemas con la consulta.
		if (modificaciones.charAt(modificaciones.length()-1) == ',') {
			modificaciones = modificaciones.substring(0, modificaciones.length()-1);
		}
		// Otra posible solución a tener coma al final del elemento
		//modificaciones = modificaciones.substring(0, modificaciones.length()-1);  
		
		String sql = "UPDATE "+TABLA+" SET "+modificaciones+" WHERE  id ="+obj.getId();
		System.out.println(sql);
		
		boolean check = this.doExecute(sql); 
		if (check) {
			System.out.println("Objeto "+TABLA+" - Actualizado");
		}else {
			System.out.println("FALLO "+TABLA+" - Actualizado");
		}
		return check;
	}
	
	/**
	 * Guarda en la base de datos el objecto DBOject que le pasemos;
	 * <strong>NOTA: Tambien actualiza un objecto, diferencia el objecto nuevo de uno de la base de datos por el campo id==null; o la función isNew()</strong>
	 * @param obj - Tiene que ser un objecto valido; con los datos correctos de las funciones: getTable, getCampos, getValues
	 * @return Devuelve si fue bien o mal el salvado del objecto. Puede devolver false; si la consulta no está bien formulada
	 */
	public boolean saveDb(DbObject obj) {  
		
		if (obj.isNew()) {
			return this.doSave(obj);
		}
		 
		return this.doUpdate(obj);
	}

	public boolean deleteDb(DbObject obj) {  
		
		String TABLA = obj.getTable(); 
		
		String sql = "DELETE FROM "+TABLA+" where id = "+obj.getId();
		System.out.println(sql); 
		
		boolean check = this.doExecute(sql);
		if (check) {
			System.out.println("Objeto "+TABLA+" - Borrado");
		}else {
			System.out.println("FALLO "+TABLA+" - Borrado");
		}
		
		return check;
	}
	
	/**
	 * 
	 * @param obj Modelo de Datos para recoger la TABLA
	 * @return Devuelve un Array de DbObject o <strong>NULL</strong> en caso de error
	 */
	public List<DbObject> list(DbObject obj) {
		  
		String sql = "SELECT * FROM "+obj.getTable(); 
		List<DbObject> dev = this.doExecuteQuery(sql, obj);
		
		return dev;
	} 

	/**
	 * <strong>EJERCICIO</strong>
	 *  Dado un ID de un modelo; devuelve el elemento de la base de datos.
	 *  
	 * @param dbObject - una clase que extienda de DbObject
	 * @param id - ID que tenemos que buscar en la tabla del objeto
	 * @return - En caso de encontrar el registro; debe devolver un objeto de ese tipo. <br/> En caso de no encontrarlo; debe devovler un NULL. <br/> En caso de Exception; devolver null.
	 */
	public DbObject getByid(DbObject obj, Integer id) {

		String sql = "SELECT * FROM "+obj.getTable()+" WHERE id="+id; 
		List<DbObject> dev = this.doExecuteQuery(sql, obj);
		
		if (dev == null || dev.isEmpty()) {
			return null;
		}
		
		return dev.get(0);
	}
	
	/**
	* @param campo - campo por el que buscar
	* @param valor - valor que tenemos que buscar en la tabla del objeto
	* @return - En caso de encontrar el registro; debe devolver una lista de
	*   objeto de ese tipo. <br/> En caso de no encontrarlo; lista vacía. <br/>
	*   caso de Exception; devolver null.
	*/	
	public List<DbObject> getByCampo(String campo, String valor ,DbObject obj){
		String sql = "SELECT * FROM "+obj.getTable()+" where " + campo + " = "+ valor;
		List<DbObject> dev = DbController.getInstance().doExecuteQuery(sql, obj);
		
		
		return dev;
	}
	
	

}
