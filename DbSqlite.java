import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; 

public class DbSqlite {
	
	final String path = "./db/";
	final String dbname = "miTienda.db";

	private static DbSqlite instance;
	private DbSqlite(){
		this.init();
	}
	public static DbSqlite getInstance(){
		if(instance == null){
			instance = new DbSqlite();
		}
		return instance;
	}
	
	private void checkDbFiles() {
		boolean fileExists = false;
		System.out.println("Comprobando Base de Datos");
		File PATH = new File(path);
		if(!PATH.exists()) {
			PATH.mkdirs();
		}
		File DB = new File(PATH,dbname);
		if(DB.exists()) {
			System.out.println("Base de Datos ya Creada");
			this.init();
			return;
			
		}
		try {
			System.out.println("Creando base de datos");
			if(DB.createNewFile()) {
				fileExists = true;
	
			}
		}catch(IOException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}finally {
			if(fileExists) {
				System.out.println("Base de Datos, Creada");
				this.init();
			}
		}
	}
	
	public void init() {
		Connection conn = null;
        try {
            //db parameters
            String url = "jdbc:sqlite:"+path+dbname;
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.checkDbFiles();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
	}

	public static void main(String[] args) {
		  DbSqlite db = DbSqlite.getInstance();
		
	}
	
}
