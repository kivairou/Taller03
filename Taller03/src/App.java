/* 
 * Diego Seco Burgos     | 21.892.538-3 | ICCI
 * Kevin Zamora Riquelme | 21.578.521-1 | ICCI
 */

package src;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

	private static Scanner scan;

	public static void main(String[] args) throws FileNotFoundException {

		Sistema sistema = Sistema.getInstancia();
		sistema.leerArchivos();

		scan = new Scanner(System.in);

		System.out.print("Usuario: ");
		String user = scan.nextLine();

		System.out.print("Contraseña: ");
		String contra = scan.nextLine();

		Usuario login = sistema.logeo(user, contra);
		if (login != null) {
			login.mostrarMenu(sistema);
		} else {
			System.out.println("Usuario o contraseña incorrecta(s)");
		}

	}

}
