package src.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import src.models.comun.DbObject;

public class FacturaLinea extends DbObject {

	private Integer id;
	private Date created;
	private Integer id_factura;
	private String nombre;
	private Integer precio; // 100 = 1,00
	
	public Integer getId() {
		return id;
	}
	private void setId(Integer id) {
		this.id = id;
	}
	public Date getCreated() {
		return created;
	}
	private void setCreated(Date created) {
		this.created = created;
	}
	public Integer getId_factura() {
		return id_factura;
	}
	public void setId_factura(Integer id_factura) {
		this.id_factura = id_factura;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	
	@Override
	public boolean isNew() {
		return (this.id == null);
	}
	
	@Override
	public String getTable() {
		return "facturas_linea";
	}
	@Override
	public String getCampos() {
		String campos = "";
		campos = getCorrectCampos(campos, "id_factura", this.id_factura);
		campos = getCorrectCampos(campos, "nombre"    , this.nombre);
		campos = getCorrectCampos(campos, "precio"    , this.precio); 
		return campos;
	}
	@Override
	public String getValues() {
		String value = "";
		value = getCorrectValues(value, this.id_factura);
		value = getCorrectValues(value, this.nombre);
		value = getCorrectValues(value, this.precio); 
		return value;		
	}
	
	@Override
	public DbObject getDbObject(ResultSet res) throws SQLException {
		FacturaLinea item = new FacturaLinea();
		item.setId( res.getInt("id") ); 
		int created = res.getInt("created");
		Date date = new Date(created);		
		item.setCreated( date ); 
		item.setId_factura(res.getInt("id_factura"));
		item.setNombre(res.getString("nombre"));
		item.setPrecio(res.getInt("precio")); 
				
		return item;
	}
	
	@Override
	public String toString() {
		return this.getValues();
	}
	
}
