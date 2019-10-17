import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; 


public class DbSqlite {

	public static void main(String[] args) {
		String path = "C:/DAM2/AD/Practicas/Pr01/";
		Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:"+path+"miTienda.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

}
