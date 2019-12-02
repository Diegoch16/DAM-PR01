package src.models.comun;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DbObject {

	public abstract Integer getId();

	public abstract boolean isNew();

	/**
	 * 
	 * @return Devuelve el nombre de la tabla de la base de datos del Objecto.
	 */
	public abstract String getTable();

	/**
	 * 
	 * @return Devuelve el String/Array de los campos de la base de datos
	 */
	public abstract String getCampos();

	public abstract String getValues();

	public abstract DbObject getDbObject(ResultSet res) throws SQLException;

	// CREATE - UPDATE
	public void save() {
		DbController.getInstance().saveDb(this);
	}

	// DELETE
	public void delete() {
		DbController.getInstance().deleteDb(this);
	}

	// READ
	public List<DbObject> list() {
		return DbController.getInstance().list(this);
	}

	// READ BY
	public DbObject getByid(Integer id) {
		return DbController.getInstance().getByid(this, id);
	}

	private boolean isNullOrEmpty(Object value) {
		if (value == null) {
			return true;
		}

		if (value instanceof String) {
			if (((String) value).trim().isEmpty()) {
				return true;
			}
		}

		return false;
	}

	public String getCorrectCampos(String data, String campo, Object value) {
		if (isNullOrEmpty(data)) {
			data = "";
		}
		if (isNullOrEmpty(value)) {
			return data;
		}

		if (data.isEmpty()) {
			return "" + campo;
		}

		return data + "," + campo;
	}

	public String getCorrectValues(String data, Object value) {
		if (isNullOrEmpty(data)) {
			data = "";
		}
		if (isNullOrEmpty(value)) {
			return data;
		}

		if (data == null || data.isEmpty()) {
			return "'" + value + "'";
		}

		return data + ",'" + value + "'";
	}
	
	public List<DbObject> getByCampos(String campo, String valor){
		List <DbObject> tabla = DbController.getInstance().getByCampo(campo, valor, this);
		return tabla;
	}

}
