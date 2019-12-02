package src;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Calendar;

public class Backup { 

	public String getFormatDay(int day) {
		return (day > 10) ? "" + day : "0" + day;
	}

	public void doBackup() {
		// Directorio Raiz de los Backups
		File dirBackup = new File(Config.PATH_BACKUP);

		// Directorio de Backups de este Mes
		Calendar cal = Calendar.getInstance();
		String month = getFormatDay(cal.get(Calendar.MONTH));
		String format = "" + cal.get(Calendar.YEAR) + month;
		dirBackup = new File(dirBackup, format);
		if (!dirBackup.exists()) {
			dirBackup.mkdirs();
		}

		// Movemos la base de datos
		File origen = new File(Config.PATH_DB + Config.DBNAME);
		format = "db-" + getFormatDay(cal.get(Calendar.DAY_OF_MONTH)) + ".db";
		File destino = new File(dirBackup, format);
		
		if (destino.exists()) {
			return;
		}
		
		try {
			Files.copy(origen.toPath(), destino.toPath());
		} catch (IOException e) { 
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	private static Backup instance;

	private Backup() {
	}

	public static Backup getInstance() {
		if (instance == null) {
			instance = new Backup();
		}
		return instance;
	}
 

}
