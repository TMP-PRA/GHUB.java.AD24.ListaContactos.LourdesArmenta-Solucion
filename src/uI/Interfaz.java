/**
 * The `Interfaz` class represents the user interface for managing a list of contacts.
 * It provides options for adding, modifying, and deleting contacts, as well as displaying the list of contacts.
 * The user can interact with the interface by selecting options from a menu.
 * 
 * This class uses the `ListadeContactos` class to store and manage the contacts.
 * It also uses the `Email` class to validate email addresses.
 * 
 * To use this class, create an instance of `Interfaz` and call the `lectura` method to start the user interface.
 * The user can then select options from the menu until they choose to exit.
 * 
 * Example usage:
 * ```
 * Interfaz interfaz = new Interfaz();
 * interfaz.lectura();
 * ```
 * 
 * @see ListadeContactos
 * @see Email
 */
package uI;
import java.util.Scanner;

import negocios.ListadeContactos;
import datos.Email;

public class Interfaz {
	static Scanner entrada = new  Scanner(System.in);
	static ListadeContactos ldc = new ListadeContactos();
	public static void lectura() {
		System.out.println("Bienvenido");
		System.out.println("Por favor, escoja una de las siguientes opciones");
		System.out.println("Para acceder a la opcion, escriba el número");
		imprimirMenu();			
	}
	private static void imprimirMenu() {

		int opcion = 0;
		do{
		
			System.out.println("Por favor, escoja una de las siguientes opciones");
			System.out.println("Para acceder a la opcion, escriba el número");
			System.out.println("****** MENU PRINCIPAL ******");
			System.out.println("1.- Ingresar Nuevo Contacto");
			System.out.println("2.- Mostrar Contactos");
			System.out.println("3.- Modificar Contacto");
			System.out.println("4.- Eliminar Contacto");
			System.out.println("5.- Salir");
			System.out.println("*****************************");
			opcion = entrada.nextInt();
			switch  (opcion) {
			case 1:
				entrada.nextLine(); // solo limpia la cadena Scanner
				validarAlta(); // si los datos que ingresa el usuario tiene el formato correcto
				break;
			case 2:
				// mostrar todos los contactos
				mostrarContactos();
				break;
			case 3:
				//modificar un contacto
				entrada.nextLine(); // solo limpia la cadena Scanner
				validarCambio();
				break;
			case 4:
				//Eliminar un contactoentrada.nextLine(); // solo limpia la cadena Scanner
				entrada.nextLine(); // solo limpia la cadena Scanner
				validarBaja();
				break;
			case 5:
				salir();
				break;
			
			default:
				System.out.println("Opción invalida");
				break;		
			} //fin de switch
		} while (opcion !=5 ); //fin de do
	} //fin de método
	private static void salir() {
		System.out.println("Sesion Finalizada");
		System.out.println("Adios!");
		System.exit(0);
	}
	private static void validarAlta() {
		try {
			String nombres,apellidos,direccion,
			       correo,telefono,celular;
			while (true) {
				
				System.out.println("Ingresar un nuevo contacto");
				System.out.println("Por favor ingrese la información del contacto");
				System.out.println("El nombre y apellido no puede repetirse");
				System.out.println("y el celular debe tener los caracteres válidos");
				System.out.println("Los nombres y apellidos no pueden repetirse");
				
				System.out.print("NOMBRES:");
				
				nombres = entrada.nextLine();
				
				System.out.print("APELLIDOS:");
				apellidos = entrada.nextLine();
	
				if (ldc.buscarContacto(nombres, apellidos)!=null) //se puede agregar el contacto
				{
					System.out.println("Ya existe un contacto con ese nombre y apellido");
					System.out.println("Si desea agregar nuevamente un contacto");
					System.out.println("Ingrese un nuevo nombre y apellido");
				}
				else
				{
					break;
				}
			}
			System.out.print("DOMICILIO:");
			direccion = entrada.nextLine();
			try {
				System.out.print("CORREO:");
				correo = entrada.nextLine();
				new Email(correo);
			}
			catch (Exception e) {
				System.out.println("Correo no válido");
				System.out.println("Si desea agregar nuevamente un contacto");
				System.out.println("Seleccione la opción 1");
				return;
			}
			
			
			System.out.print("TELÉFONO:");
			telefono = entrada.nextLine();
			System.out.print("CELULAR:");
			celular = entrada.nextLine();

			if (ldc.agregarContacto(nombres, apellidos, direccion,
					correo, telefono, celular))
			{
				System.out.println("Exito Agregado a la lista de contacto");	
			}
			else
			{
				System.out.println("No se Agregó");
				System.out.println("Si desea agregar nuevamente un contacto");
				System.out.println("Seleccione la opción 1");	
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	    imprimirMenu();
	}
	private static void mostrarContactos() {
		System.out.println("Total de elementos: "+ldc.tamanio());
		ldc.mostrarTodosLosContactos();
		imprimirMenu();
		
	}
	private static void validarCambio() {
		try {
			String nombres,apellidos,direccion,
			       correo,telefono,celular;
			while (true) {
				System.out.println("Modificar un contacto");
				System.out.println("Por favor ingrese la información del contacto");
				System.out.println("El nombre y apellido deben existir");
				System.out.print("NOMBRES:");
				nombres = entrada.nextLine();
				System.out.print("APELLIDOS:");
				apellidos = entrada.nextLine();
				if (ldc.buscarContacto(nombres, apellidos) == null) 
				{
					System.out.println("Contacto No Existe");				
				}
				else
				{
					break;
				}
			}
			System.out.print("DOMICILIO:");
			direccion = entrada.nextLine();
			try {
				System.out.print("CORREO:");
				correo = entrada.nextLine();
				new Email(correo);
			}
			catch (Exception e) {
				System.out.println("Correo no válido");
				System.out.println("Si desea agregar nuevamente un contacto");
				System.out.println("Seleccione la opción 1");
				return;
			}
			
			System.out.print("TELÉFONO:");
			telefono = entrada.nextLine();
			System.out.print("CELULAR:");
			celular = entrada.nextLine();
			if (ldc.modificarContacto(nombres, apellidos, direccion, correo, telefono, celular))
			{
				System.out.println("Modificación Exitosa");
				System.out.println("Lista de Contactos");
				ldc.mostrarTodosLosContactos();
			}
			else
			{
				System.out.println("No se puedo Modificar Contacto");
				System.out.println("Si desea hacerlos, intente nuevamente ");
				System.out.println("Seleccione la opcion 3");	
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		imprimirMenu();
	}
	private static void validarBaja() {
		try {
			
			while (true) {
				System.out.println("Eliminar un contacto");
				System.out.println("Por favor ingrese la información del contacto");
				System.out.println("El nombre y apellido deben existir");
				//Contacto contacto = new Contacto();
				System.out.print("NOMBRES:");
				nombres = entrada.nextLine();
				System.out.print("APELLIDOS:");
				apellidos = entrada.nextLine();

				if (ldc.eliminarContacto(nombres, apellidos)) 
				{
					System.out.println("Contacto Eliminada");
					System.out.println("Lista de Contactos");
					ldc.mostrarTodosLosContactos();
					break;
					
					
				}
				else
				{
					
					System.out.println("No Existe un Contacto con ese nombre y apellido");
					System.out.println("Si desea eleminar un contacto");
					System.out.println("Ingrese un Contacto nombre y apellido válido");
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		imprimirMenu();
	}
	


}
	

	

