package src.models.comun;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import src.Config;
 


public class DbSqlite { 
	
	private static DbSqlite instance;
	private DbSqlite(){
		this.init();
	} 
	public static DbSqlite getInstance(){
		if (instance == null){
			instance = new DbSqlite(); 
		}
		return instance;
	}
	
	private Connection conn = null;
	
	public Connection getConnection() {
		return conn;
	}
	
	private void checkDbFiles() {
		boolean fileExist = false;
		System.out.println("Comprobando Base de Datos");
		File path = new File(Config.PATH_DB);
		if (!path.exists()) {
			path.mkdirs();
		}
		File db = new File(path, Config.DBNAME);
		if (db.exists()) {
			System.out.println("Base de Datos Ya Creada");
			this.init();
			return;
		}
		try {
			System.out.println("Creando Base de Datos");
			if( db.createNewFile() ) {
				fileExist = true;				
			}
		} catch (IOException e) { 
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			if (fileExist) {
				System.out.println("Base de Datos, Creada");
				this.init();
			}
		}
		
	}
	
	public void close() {
		try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	private Connection init() { 
		
        try {
            // db parameters
        //	conn = DriverManager.getConnection(Config.DBMYSQL, Config.DBMYSQL_USER, Config.DBMYSQL_PASS);
            // create a connection to the database
            conn = DriverManager.getConnection(Config.DBSQLITE); // Para SQLITE
            
            System.out.println("Connection to SQLite has been established.");
             
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.checkDbFiles();
        }  
        return conn;
	} 

}
