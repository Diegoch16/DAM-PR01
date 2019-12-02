package src.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import src.models.comun.DbObject;

public class Clientes extends DbObject {

	private Integer id;
	private Date created;
	private String nombre;
	private String dni;
	private String direccion;
	private String telefono;
	private String email;
	
	@Override
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	} 
	
	@Override
	public String getTable() {
		return "clientes";
	}
	
	@Override
	public String toString() {
		return this.getValues();
	}
	
	@Override
	public boolean isNew() {
		return (this.id == null);
	}
	
	@Override
	public String getCampos() {
		String campos = "";
		campos = getCorrectCampos(campos, "nombre"   , this.nombre);
		campos = getCorrectCampos(campos, "dni"      , this.dni);
		campos = getCorrectCampos(campos, "direccion", this.direccion);
		campos = getCorrectCampos(campos, "telefono" , this.telefono);
		campos = getCorrectCampos(campos, "email"    , this.email); 
		return campos;
	}
	
	@Override
	public String getValues() {
		String value = "";
		value = getCorrectValues(value, this.nombre);
		value = getCorrectValues(value, this.dni);
		value = getCorrectValues(value, this.direccion);
		value = getCorrectValues(value, this.telefono);
		value = getCorrectValues(value, this.email); 
		return value;	
	}
	
	@Override
	public DbObject getDbObject(ResultSet res) throws SQLException {
		Clientes item = new Clientes();
		item.setId( res.getInt("id") ); 
		int created = res.getInt("created");
		Date date = new Date(created);		
		item.setCreated( date );
		item.setNombre( res.getString("nombre") );
		item.setDni( res.getString("dni") );
		item.setDireccion( res.getString("direccion") );
		item.setTelefono( res.getString("telefono") );
		item.setEmail( res.getString("email") ); 
				
		return item;
	}
	
}
