package src.menus;

import java.util.List;
import src.models.Clientes;
import src.models.comun.DbObject;

public class MenuClientes extends Menu {
	
	
	public String toString() {
		
		return 
				"¿Qué acción quiere realizar? \n"+
				"1.Crear nuevo cliente \n"+
				"2.Ver clientes\n"+
				"3.Ver detalles de un cliente\n"+
				"4.Actualizar un cliente \n"+
				"5.Borrar un cliente \n"+			
				
				"0.Volver al menú principal"	;
	}
	
	@Override
	public Menu siguienteMenu(String opcion) {
				
				switch (opcion) {
				case "1":
					crearCliente();
					break;
				case "2":
					verCliente();
					break;
				case "3":
					verDetallesCliente();
					break;	
				case "4": 
					actualizarCliente();
					break;
				case "5": 
					borrarCliente();
					break;					
				case "0":
					return new MenuPrincipal();
				default:
					System.out.println("Opción no válida");
					break;
				}
		return new MenuClientes();		
	}

	private void verDetallesCliente() { 
		Clientes cli = MenuController.eligeCliente();
		if (cli == null) {
			return;
		}
		System.out.println("Nombre: \t"+cli.getNombre());
		System.out.println("DNI: \t"+cli.getDni());
		System.out.println("Dirección: \t"+cli.getDireccion());
		System.out.println("Telefono: \t"+cli.getTelefono());
		System.out.println("Email: \t"+cli.getEmail());
		
	}
	
	private void crearCliente() {
		Clientes cli = new Clientes();
		System.out.println("Introduzca el nombre del cliente");
		cli.setNombre(MenuController.campoValido("^[^,]+$"));
		System.out.println("Introduzca el dni del cliente");
		cli.setDni(MenuController.campoValido("^[^,]+$"));
		System.out.println("Introduzca dirección: ");
		cli.setDireccion(MenuController.campoValido("^[^,]+$"));
		System.out.println("Introduzca telefono: ");
		cli.setTelefono(MenuController.campoValido("^[^,]+$"));
		System.out.println("Introduzca email: ");
		cli.setEmail(MenuController.campoValido("^[A-Za-z0-9+_.-]+@(.+)$"));
		cli.save();		
		
	}
	private void verCliente() {
		Clientes cli = new Clientes();		
		List<DbObject> listaCat = cli.list();
		System.out.println("Tabla Categoria: ");
		System.out.println("ID \t Nombre");
		for(DbObject obj : listaCat) {
			cli = (Clientes)obj;
			System.out.print(cli.getId() +"\t");
			System.out.print(cli.getNombre()+"\n");			
		}		
	}
	
	private void actualizarCliente() {
		
		Clientes  cli = MenuController.eligeCliente();
		if (cli == null) {
			System.out.println("El cliente introducido no existe");
			return;
		}		
		System.out.println("Introduzca el nombre del cliente Actual: "+cli.getNombre());
		cli.setNombre(MenuController.campoValido("^[^,]+$"));
		System.out.println("Introduzca el dni del cliente Actual: "+cli.getDni());
		cli.setDni(MenuController.campoValido("\\d{8}[A-HJ-NP-TV-Z]"));
		System.out.println("Introduzca dirección: Actual:"+cli.getDireccion());
		cli.setDireccion(MenuController.campoValido("^[^,]+$"));
		System.out.println("Introduzca telefono: Actual: "+cli.getTelefono());
		cli.setTelefono(MenuController.campoValido("^[^,]+$"));
		System.out.println("Introduzca email: Actual: "+cli.getEmail());
		cli.setEmail(MenuController.campoValido("^[A-Za-z0-9+_.-]+@(.+)$"));
		cli.save();			
	}
	private void borrarCliente() {
		Clientes cli = MenuController.eligeCliente();	
		if (cli == null) {
			System.out.println("No existe el cliente a borrar");
			return;
		}
		cli.delete();		
	}
	
	

}
