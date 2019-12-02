package src.menus;


import java.util.List;

import src.models.Clientes;
import src.models.Factura;
import src.models.FacturaLinea;
import src.models.Producto;
import src.models.comun.DbObject;

public class MenuFacturas extends Menu {
	public String toString() {
		// TODO Auto-generated method stub
		return 
				"¿Qué acción quiere realizar? \n"+
				"1.Crear nueva factura \n"+
				"2.Añadir una linea a una factura existente \n"+
				"3.Ver facturas\n"+
				"4.Ver lineas de una factura\n"+
				"5.Editar una factura \n"+
				"6.Editar una linea de una factura \n"+
				"7.Borrar una factura \n"+	
				"8.Borrar una linea de una factura \n"+
				
				"0.Volver al menú principal"	;
		
	}
	
	public Menu siguienteMenu(String opcion) {
				
				switch (opcion) {
				case "1":
					crearFactura();
					break;
				case "2":
					crearLineaFactura();
					break;
				case "3": 
					verFacturas();
					break;
				case "4": 
					verLineasFactura();
					break;
				case "5":
					editarFactura();
					break;
				case "6":
					editarLineaFactura();
					break;
				case "7":	
					borrarFactura();
					break;		
				case "8":
					borrarLineaFactura();
					break;
				case "0":
					return new MenuPrincipal();
				default:
					System.out.println("Opción no válida");
					break;
				
				}
		return new MenuFacturas();		
	}
	
	
	
	private void editarLineaFactura() {
		FacturaLinea fl = MenuController.eligeLineaFactura();
		
		if( fl == null) {
			System.out.println("Linea de factura no encontrada");
			return;
		}
		Factura fac = MenuController.eligeFactura();
		if( fac == null) {
			return;
		}
		
		fl.setId_factura(fac.getId());
		Producto prod = MenuController.eligeProducto();		
		fl.setNombre(prod.getNombre());
		fl.setPrecio(Integer.parseInt(MenuController.campoValido("^\\d+$")));
		
		
	}

	private void borrarLineaFactura() {
		Factura fac = MenuController.eligeFactura();
		List<DbObject> listFl = new FacturaLinea().getByCampos("id_factura",fac.getId()+"");
		for(DbObject obj: listFl) {
			System.out.println(obj.getId()+" "+obj);
		}
		FacturaLinea fl = MenuController.eligeLineaFactura();
		fl.delete();
		
	}

	private void borrarFactura() {
		Factura fac = MenuController.eligeFactura();
		List<DbObject> listFl = new FacturaLinea().getByCampos("id_factura",fac.getId()+"");
		for(DbObject obj: listFl) {
			obj.delete();
		}
		
		fac.delete();
		
	}

	private void editarFactura() {
		
		Factura fac = MenuController.eligeFactura();
		if (fac == null) {
			System.out.println("La factura no existe");			
			return ;
		}
		
		System.out.println("Elija un cliente al que asignar la factura");
		Clientes cli = MenuController.eligeCliente();
		if (cli== null) {
			System.out.println("El cliente no existe");
			return;
		}
		
		System.out.println("Introduzca la fecha de la factura, (formato yyyy/MM/dd) ");
		fac.setFecha(MenuController.validarFecha());		
		System.out.println("Introduzca la serie de la factura,  ");
		fac.setSerie(Integer.parseInt(MenuController.campoValido("^\\d+$")));
		fac.setId_cliente(cli.getId());
	}
		
	

	private void verLineasFactura() {
		Factura fac = MenuController.eligeFactura();
		
		List<DbObject> listFl = new FacturaLinea().getByCampos("id_factura",fac.getId()+"");
		for(DbObject obj: listFl) {
			System.out.println(obj.getId()+" "+obj);
		}
				
				
		
	}

	private void verFacturas() {
		List<DbObject> facList = new Factura().list();
		
		for(DbObject obj: facList ) {
			System.out.println(obj.getId()+ " " +obj.toString());
		}
		
	}

	
	private void crearLineaFactura() {
		Factura fac = MenuController.eligeFactura();
		if( fac == null) {
			return;
		}
		FacturaLinea fl = new FacturaLinea();
		fl.setId_factura(fac.getId());
		Producto prod = MenuController.eligeProducto();		
		fl.setNombre(prod.getNombre());
		fl.setPrecio(Integer.parseInt(MenuController.campoValido("^\\d+$")));
		
		fl.save();
	}

	private void crearFactura() {
		
		Factura fac = new Factura();
		

		System.out.println("Elija un cliente al que asignar la factura");
		Clientes cli = MenuController.eligeCliente();
		if (cli== null) {
			System.out.println("El cliente no existe");
			return;
		}
		
		System.out.println("Introduzca la fecha de la factura, (formato yyyy/MM/dd) ");
		fac.setFecha(MenuController.validarFecha());		
		System.out.println("Introduzca la serie de la factura,  ");
		fac.setSerie(Integer.parseInt(MenuController.campoValido("^\\d+$")));
		fac.setId_cliente(cli.getId());
			
		fac.save();
		
	}
	
	



}
