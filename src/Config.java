package src;

public class Config {

	public static final String PATH        = "c:/miTienda/";
	public static final String PATH_DB     = PATH+"db/";
	public static final String PATH_BACKUP = PATH+"Backup/";
	public static final String DBNAME      = "database.db"; 
	
	public static final String DBMYSQL_HOST   = "127.0.0.1"; // localhost 
	public static final String DBMYSQL_PORT   = "3306";
	public static final String DBMYSQL_DBNAME = "mitienda";
	public static final String DBMYSQL_USER   = "root";
	public static final String DBMYSQL_PASS   = "1234";
	
	public static final String DBSQLITE = "jdbc:sqlite:"+PATH_DB+DBNAME;
	public static final String DBMYSQL  = "jdbc:mysql://"+DBMYSQL_HOST+":"+DBMYSQL_PORT+"/"+DBMYSQL_DBNAME;
	
 
	private Config() {};
}
