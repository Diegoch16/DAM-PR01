import java.io.File;
import java.util.Calendar;

public class Backup {

	public static final String PATH = "c:/miTienda/";

	public String getFormatDay(int day) {
		return (day > 10) ? "" + day : "0" + day;
	}

	public void doBackup() {
		// Directorio Raiz de los Backups
		File dirBackup = new File(PATH + "Backup");

		// Directorio de Backups de este Mes
		Calendar cal = Calendar.getInstance();
		String month = getFormatDay(cal.get(Calendar.MONTH));
		String format = "" + cal.get(Calendar.YEAR) + month;
		dirBackup = new File(dirBackup, format);
		if (!dirBackup.exists()) {
			dirBackup.mkdirs();
		}

		// Movemos la base de datos
		File origen = new File(PATH + "database.db");
		format = "db-" + getFormatDay(cal.get(Calendar.DAY_OF_MONTH)) + ".db";
		File destino = new File(dirBackup, format);
		origen.renameTo(destino);
	}

	public static void main(String[] args) {
		// ejecutamos el backup
		Backup bck = new Backup();
		bck.doBackup();

	}

}
