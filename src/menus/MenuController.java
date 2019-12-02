package src.menus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import src.models.Categoria;
import src.models.Clientes;
import src.models.Factura;
import src.models.FacturaLinea;
import src.models.Producto;
import src.models.comun.DbObject;

public class MenuController {
	
	private Menu menu;
	private Scanner keyboard;
	private static MenuController instance;
	
	
	
	private MenuController() {
		this.keyboard = new Scanner(System.in);
	}

	public static MenuController getInstance() {
		if (instance == null) {
			instance = new MenuController();
		}
		return instance;
	}
	public void callMenus() {
		menu = new MenuPrincipal();
		while(menu!=null) {
			navegarMenu();
		}
		keyboard.close();
		System.out.println("ByeBye");		
	}
	public  Scanner getKeyboard(){
		return this.keyboard;
	}
	

	public void navegarMenu() {
		String opcion;
		System.out.println(menu.toString());
		opcion = this.keyboard.nextLine();
		menu = menu.siguienteMenu(opcion);
	}
	
	public  DbObject seleccionarObjeto(DbObject obj) {	
		
		int id=-1;
		List<DbObject> tabla =obj.list();
		if (tabla.isEmpty()) {
			System.out.println("No hay nada en la tabla");
			return null;
		}
		
		for(DbObject dbo: tabla ) {	
			System.out.println(dbo.getId()+" "+dbo.toString());
		}
		System.out.println("Introduzca la ID del objeto de "+obj.getTable()+ " que quiera seleccionar ");
		try {
			id = Integer.parseInt(this.keyboard.nextLine());
		}catch(NumberFormatException ex){
			System.out.println("Id no válida");
			return null;
		}
		DbObject pedido = obj.getByid(id);
		
		
		return pedido; 
	}
	
	private String validarCampo(String regex) {		
		String datos;	
		datos = keyboard.nextLine();		
		
		while (!Pattern.matches(regex, datos)) {		
				System.out.println("El valor introducido para el campo no es válido");
				datos = keyboard.nextLine();										
		}
		return datos;
	}
	
	
	public static String campoValido(String regex) {
		return MenuController.getInstance().validarCampo(regex);
	}	
	
	public static Factura eligeFactura() {
		return (Factura)MenuController.getInstance().seleccionarObjeto(new Factura());
	}
	
	public static FacturaLinea eligeLineaFactura() {
		return (FacturaLinea) MenuController.getInstance().seleccionarObjeto(new FacturaLinea());
	}
	
	public static Clientes eligeCliente() {
		return (Clientes)MenuController.getInstance().seleccionarObjeto(new Clientes());
	}
	
	public static Producto eligeProducto() {
		return (Producto)MenuController.getInstance().seleccionarObjeto(new Producto());
	}
	
	public static Categoria eligeCategoria() {
		return (Categoria)MenuController.getInstance().seleccionarObjeto(new Categoria());
	}

	public static Date validarFecha() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		
		Date fecha = new Date();
		String datos;
		boolean validado = false;
		
		datos = MenuController.getInstance().getKeyboard().nextLine();
		
		while(!validado) {
			try {
				fecha = sdf.parse(datos);
				validado = true;
			} catch (ParseException e) {
				System.err.println("Error: el formato de la fecha debe ser: yyyy/MM/dd");
				e.printStackTrace();
			}
		}
		return fecha;
	}
	
}
