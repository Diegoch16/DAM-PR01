package src.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import src.models.comun.DbObject;

public class Factura extends DbObject{

	private Integer id;
	private Date created;
	private Date fecha;
	private Integer serie; 
	private Integer id_cliente;
	
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
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getSerie() {
		return serie;
	}
	public void setSerie(Integer serie) {
		this.serie = serie;
	}
	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	@Override
	public boolean isNew() {
		return (this.id == null);
	}
	
	@Override
	public String getTable() {
		return "facturas";
	}
	@Override
	public String getCampos() {
		String campos = "";
		campos = getCorrectCampos(campos, "fecha"     , this.fecha);
		campos = getCorrectCampos(campos, "serie"     , this.serie);
		campos = getCorrectCampos(campos, "id_cliente", this.id_cliente); 
		return campos;
	}
	@Override
	public String getValues() {
		String value = "";
		value = getCorrectValues(value, this.fecha);
		value = getCorrectValues(value, this.serie);
		value = getCorrectValues(value, this.id_cliente); 
		return value;		
	}
	
	@Override
	public DbObject getDbObject(ResultSet res) throws SQLException {
		Factura item = new Factura();
		item.setId( res.getInt("id") ); 
		int created = res.getInt("created");
		Date date = new Date(created);		
		item.setCreated( date );
		
		created = res.getInt("fecha");
		date = new Date(created);		
		item.setFecha( date );
		 
		item.setSerie( res.getInt("serie") );
		item.setId_cliente( res.getInt("id_cliente") ); 
				
		return item;
	}
	
	@Override
	public String toString() {
		return this.getValues();
	}
	
	
}
