package src.menus;

import java.util.List;
import java.util.Scanner;

import src.models.Categoria;
import src.models.comun.DbObject;

public class MenuCategorias extends Menu {

	public String toString() {
		// TODO Auto-generated method stub
		return 
				"¿Qué acción quiere realizar? \n"+
				"1.Crear nueva categoría \n"+
				"2.Ver categorías \n"+
				"3.Actualizar una categoría \n"+
				"4.Borrar una categoría \n"+				
				
				"0.Volver al menú principal"	;
	}
	
	
	
	
	@Override
	public Menu siguienteMenu(String opcion) {
				
				switch (opcion) {
				case "1":
					crearCategoria();
					break;
				case "2":
					verCategorias();
					break;

				case "3": 
					actualizarCategoria();
					break;
				case "4": 
					borrarCategoria();
					break;
					
				case "0":
					return new MenuPrincipal();
					
				default:
					System.out.println("Opción no válida");
					break;
				}			
				return new MenuCategorias();
		
	}

	private void crearCategoria() {
		Categoria cat = new Categoria();
		System.out.println("Introduzca un nombre para la categoría");
		cat.setNombre(MenuController.campoValido("^[^,]+$"));
		cat.save();
	}
	
	
	private void verCategorias() {
		Categoria cat = new Categoria();		
		List<DbObject> listaCat = cat.list();
		System.out.println("Tabla Categoria: ");
		System.out.println("ID \t Nombre");
		for(DbObject obj : listaCat) {
			cat = (Categoria)obj;
			System.out.print(cat.getId() +"\t");
			System.out.print(cat.getNombre()+"\n");			
		}		
	}
	
	private void actualizarCategoria() {
		Categoria cat = MenuController.eligeCategoria();
		if (cat == null) {
			return;
		}
		System.out.println("Introduzca un nombre para la categoría");
		cat.setNombre(MenuController.campoValido("^[^,]+$"));
		cat.save();		
	}
	private void borrarCategoria() {		
		Categoria cat = MenuController.eligeCategoria();
		if (cat == null) {
			
			return;
		}
		cat.delete();		
	}


	
	
	

}
