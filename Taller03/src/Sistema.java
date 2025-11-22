package src;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Sistema implements ISistema {

	private static Sistema instancia;
	private ArrayList<Usuario> usuarios;
	private ArrayList<Proyecto> proyectos;

	private static Scanner scan;

	public Sistema() {

		usuarios = new ArrayList<>();
		proyectos = new ArrayList<>();

	}

	public static Sistema getInstancia() {
		if (instancia == null) {
			instancia = new Sistema();
		}
		return instancia;
	}

	@Override
	public Usuario logeo(String user, String contraseña) {
		for (Usuario u : usuarios) {
			if (u.getUsername().equalsIgnoreCase(user) && u.getPassword().equalsIgnoreCase(contraseña)) {
				return u;
			}
		}
		return null;
	}

	@Override
	public void menuAdmin(Administrador admin) throws IOException {

		int opcion;
		scan = new Scanner(System.in);

		do {
			System.out.println("\n===== Menú Administrador =====");
			System.out.println("1) Ver lista de proyectos y tareas");
			System.out.println("2) Agregar o eliminar un proyecto");
			System.out.println("3) Agregar o eliminar una tarea en un proyecto");
			System.out.println("4) Asignar prioridades con Strategy");
			System.out.println("5) Generar reporte de proyectos");
			System.out.println("0) Salir");
			System.out.print("Ingrese una opción: ");

			opcion = scan.nextInt();
			scan.nextLine();

			switch (opcion) {

			case 1:
				verProyectosyTareas();
				break;

			case 2:
				int opcion2;

				do {
					System.out.println("\1) Agregar");
					System.out.println("2) Eliminar");
					System.out.println("0) Salir");
					System.out.print("Ingrese una opción: ");

					opcion2 = scan.nextInt();
					scan.nextLine();

					switch (opcion2) {

					case 1:
						agregarProyecto();
						break;

					case 2:
						eliminarProyecto();
						break;
					}

				} while (opcion2 != 0);
				break;

			case 3:

				int opcion3;

				do {
					System.out.println("\1) Agregar");
					System.out.println("2) Eliminar");
					System.out.println("0) Salir");
					System.out.print("Ingrese una opción: ");

					opcion3 = scan.nextInt();
					scan.nextLine();

					switch (opcion3) {

					case 1:
						agregarTarea();
						break;

					case 2:
						eliminarTarea();
						break;
					}

				} while (opcion3 != 0);
				break;

			case 4:
				asignarPrioridad();
				break;

			case 5:
				generarReporte();
				break;
			}

		} while (opcion != 0);
	}

	private void eliminarProyecto() {
		
		System.out.print("\nID Proyecto a eliminar: ");
		String id = scan.nextLine();
		
		Proyecto buscarProyecto = null;
		
		for(Proyecto p: proyectos) {
			if(p.getId().equalsIgnoreCase(id)) {
				buscarProyecto = p;
				break;
			}
		}
		
		if(buscarProyecto != null) {
			proyectos.remove(buscarProyecto);
			System.out.println("Proyecto eliminado");
		} else {
			System.out.println("Producto no encontrado");
		}
		
	}

	private void generarReporte() throws IOException {

		try (FileWriter reporte = new FileWriter("reportes.txt")) {
			for (Proyecto p : proyectos) {
				reporte.write("Proyecto: " + p.getId() + " | " + p.getNombre() + " | Responsable: " + p.getResponsable()
						+ "\n");

				for (Tarea t : p.getTareas()) {
					reporte.write("   - Tarea: " + t.getId() + " | Tipo: " + t.getClass().getSimpleName()
							+ " | Descripcion: " + t.getDescripcion() + " | Estado: " + t.getEstado()
							+ " | Responsable: " + t.getResponsable() + " | Complejidad: " + t.getComplejidad()
							+ " | Fecha: " + t.getFechaCreacion() + "\n");
				}

				reporte.write("\n");
			}
			System.out.println("\nReporte generado en 'reportes.txt'");

		} catch (Exception e) {
			System.out.println("Error al generar reporte: " + e.getMessage());
		}

	}

	private void asignarPrioridad() {
		
		int opcion;
		
		do {
			System.out.println("\n=== Prioridades ===");
			System.out.println("1) Estrategia por fecha de creación");
			System.out.println("2) Estrategia por impacto");
			System.out.println("3) Estrategia por complejidad");
			System.out.println("0) Salir");
			System.out.print("Ingrese una opción: ");
			
			opcion = scan.nextInt();
			scan.nextLine();
			
			PrioridadStrategy estrategia = null;
			
			switch(opcion) {
			
				case 1 -> estrategia = new StrategyFecha();
				case 2 -> estrategia = new StrategyImpacto();
				case 3 -> estrategia = new StrategyComplejidad();
				
			}
			
			if(estrategia != null) {
				for(Proyecto p: proyectos) {
					p.setEstrategia(estrategia);
					System.out.println("\nProyecto: "+ p.getNombre());
					for(Tarea t: p.getTareasOrdenadas()) {
						System.out.println(t.getId() + " | " + t.getDescripcion()+ " | "+ t.getComplejidad());
					}
				}
			}
		
		}while(opcion!=0);
	}

	private void eliminarTarea() {

		System.out.print("\nID Proyecto: ");
		String idProyecto = scan.nextLine();

		Proyecto buscarProyecto = null;

		// revisar si existe el proyecto
		for (Proyecto p : proyectos) {
			if (p.getId().equalsIgnoreCase(idProyecto)) {
				buscarProyecto = p;
				break;
			}
		}

		if (buscarProyecto != null) {

			System.out.print("ID Tarea: ");
			String idTarea = scan.nextLine();

			Tarea buscarTarea = null;

			// revisar si existe la tarea
			for (Tarea t : buscarProyecto.getTareas()) {
				if (t.getId().equalsIgnoreCase(idTarea)) {
					buscarTarea = t;
					break;
				}
			}

			if (buscarTarea != null) {
				buscarProyecto.borrarTarea(idTarea);
				System.out.println("Tarea eliminada con exito");
			} else {
				System.out.println("No se encontró el ID de la tarea");

			}

		} else {
			System.out.println("No se encontró el ID del proyecto");
		}

	}

	private void agregarTarea() {

		System.out.print("\nID Proyecto: ");
		String idProyecto = scan.nextLine();

		Proyecto buscarProyecto = null;

		// revisar que exista el proyecto
		for (Proyecto p : proyectos) {
			if (p.getId().equalsIgnoreCase(idProyecto)) {
				buscarProyecto = p;
				break;
			}
		}

		if (buscarProyecto != null) {

			System.out.print("ID Tarea: ");
			String idTarea = scan.nextLine();

			// revisar que no exista la tarea
			for (Tarea t : buscarProyecto.getTareas()) {
				if (t.getId().equalsIgnoreCase(idTarea)) {
					System.out.println("Ya existe una tarea con ese ID");
					return;
				}
			}

			System.out.print("Tipo (Bug|Feature|Documentacion): ");
			String tipo = scan.nextLine();

			System.out.print("Descripcion: ");
			String descripcion = scan.nextLine();

			System.out.print("Estado: ");
			String estado = scan.nextLine();

			System.out.print("Responsable: ");
			String responsable = scan.nextLine();

			Usuario buscarUsuario = null;

			// revisar si existe el usuario
			for (Usuario u : usuarios) {
				if (u instanceof Colaborador && u.getUsername().equalsIgnoreCase(responsable)) {
					buscarUsuario = u;
					break;
				}
			}

			if (buscarUsuario == null) {
				System.out.println("Responsable no existe o no es Colaborador");
				return;
			}

			System.out.print("Complejidad (Alta|Media|Baja): ");
			String complejidad = scan.nextLine();

			System.out.print("Fecha (YYYY-MM-DD): ");
			LocalDate fecha = LocalDate.parse(scan.nextLine());

			// revisar que el responsable no tenga otra tarea el mismo dia
			for (Tarea t : buscarProyecto.getTareas()) {
				if (t.getResponsable().equalsIgnoreCase(responsable) && t.getFechaCreacion().equals(fecha)) {
					System.out.println("El responsable ya tiene una tarea asignada en la fecha " + fecha);
					return;
				}
			}

			Tarea nuevaTarea = TareaFactory.crearTarea(tipo, idTarea, descripcion, estado, responsable, complejidad,
					fecha);
			buscarProyecto.agregarTarea(nuevaTarea);

			System.out.println("Tarea agregada con éxito");

		} else {
			System.out.println("No se encontró ID del proyecto");
			return;
		}

	}

	private void agregarProyecto() {
		
		System.out.print("\nID: ");
		String id = scan.nextLine();
		
		for (Proyecto p : proyectos) {
	        if (p.getId().equalsIgnoreCase(id)) {
	            System.out.println("Ya existe un proyecto con ese ID");
	            return;
	        }
	    }
		
		System.out.print("Nombre: ");
		String nombre = scan.nextLine();
		
		System.out.print("Responsable: ");
		String responsable = scan.nextLine();
		
		Usuario buscarUsuario = null;

	
		for (Usuario u : usuarios) {
			if (u instanceof Administrador && u.getUsername().equalsIgnoreCase(responsable)) {
				buscarUsuario = u;
				break;
			}
		}

		if (buscarUsuario == null) {
			System.out.println("Responsable no existe o no es Administrador");
			return;
		}
		
		proyectos.add(new Proyecto(id, nombre, responsable));
		System.out.println("El proyecto se ha agregado");

	}

	private void verProyectosyTareas() {
		
		 for (Proyecto p : proyectos) {
	            System.out.println("\nProyecto: " + p.getNombre());
	            for (Tarea t : p.getTareas()) {
	                System.out.println(" - " + t.getId() + ": " + t.descripcion + " (" + t.getEstado() + ")");
	            }
	        }
		 
	}

	@Override
	public void menuColaborador(Colaborador colab) {

		int opcion;
		scan = new Scanner(System.in);

		do {
			System.out.println("\n===== Menú Colaborador =====");
			System.out.println("1) Ver proyectos disponibles");
			System.out.println("2) Ver tareas asignadas");
			System.out.println("3) Actualizar estado de una tarea");
			System.out.println("4) Aplicar Visitor sobre tareas");
			System.out.println("0) Salir");
			System.out.print("Ingrese una opción: ");

			opcion = scan.nextInt();
			scan.nextLine();

			switch (opcion) {

			case 1:
				verProyectos();
				break;

			case 2:
				verTareasAsignadas(colab);
				break;

			case 3:
				actualizarEstado(colab);
				break;

			case 4:
				aplicarVisitor(colab);
				break;
			}

		} while (opcion != 0);
	}

	private void aplicarVisitor(Colaborador colab) {

		VisitorTarea visitor = new VisitorTarea();
		for (Proyecto p : proyectos) {
			for (Tarea t : p.getTareas()) {
				if (t.getResponsable().equalsIgnoreCase(colab.getUsername())) {
					System.out.println("\n"+t.getId() + ": ");
					t.accept(visitor);
				}
			}
		}

	}

	private void actualizarEstado(Colaborador colab) {
		
		System.out.print("ID de tarea: ");
		String id = scan.nextLine();
        for(Proyecto p: proyectos) {
            for (Tarea t : p.getTareas()) {
                if (t.getId().equals(id) && t.getResponsable().equals(colab.getUsername())) {
                    System.out.print("Nuevo estado (Pendiente|En progreso|Completada): ");
                    
                    t.setEstado(scan.nextLine());
                    System.out.println("Estado actualizado.");
                    return;
                }
            }
        }
        System.out.println("Tarea no encontrada o no asignada a este Usuario.");


	}

	private void verTareasAsignadas(Colaborador colab) {
		
		for(Proyecto p : proyectos) {
            for(Tarea t : p.getTareas()) {
                if(t.getResponsable().equals(colab.getUsername())) {
                    System.out.println("\n"+ p.getNombre() + " - " + t.getId() + ": " + t.descripcion + " (" + t.getEstado() + ")");
                }
            }
        }


	}

	private void verProyectos() {
		System.out.println("\n=== Proyectos Disponibles ===");
		for (Proyecto p : proyectos) {
			System.out.println(
					"ID: " + p.getId() + " | Nombre: " + p.getNombre() + " | Responsable: " + p.getResponsable());
		}

	}

	@Override
	public void leerArchivos() throws FileNotFoundException {

		// texto usuarios
		File txtUsuarios = new File("usuarios.txt");
		scan = new Scanner(txtUsuarios);

		while (scan.hasNextLine()) {

			String linea = scan.nextLine();
			String[] partes = linea.split("\\|");

			String nombre = partes[0];
			String contraseña = partes[1];
			String rol = partes[2];

			if (rol.equalsIgnoreCase("Administrador")) {
				usuarios.add(new Administrador(nombre, contraseña));
			} else {
				usuarios.add(new Colaborador(nombre, contraseña));
			}
		}

		// texto proyectos
		File txtProyectos = new File("proyectos.txt");
		scan = new Scanner(txtProyectos);

		while (scan.hasNextLine()) {

			String linea = scan.nextLine();
			String[] partes = linea.split("\\|");

			String id = partes[0];
			String nombre = partes[1];
			String responsable = partes[2];
			proyectos.add(new Proyecto(id, nombre, responsable));
		}

		// texto tareas
		File txtTareas = new File("tareas.txt");
		scan = new Scanner(txtTareas);

		while (scan.hasNextLine()) {

			String linea = scan.nextLine();
			String[] partes = linea.split("\\|");

			String idProyecto = partes[0];
			String id = partes[1];
			String tipo = partes[2];
			String descripcion = partes[3];
			String estado = partes[4];
			String responsable = partes[5];
			String complejidad = partes[6];
			LocalDate fecha = LocalDate.parse(partes[7]);

			Tarea nuevaTarea = TareaFactory.crearTarea(tipo, id, descripcion, estado, responsable, complejidad, fecha);
			Proyecto proyecto = null;

			for (Proyecto p : proyectos) {
				if (p.getId().equalsIgnoreCase(idProyecto)) {
					proyecto = p;
					break;
				}
			}
			if (proyecto != null) {
				proyecto.agregarTarea(nuevaTarea);
			}

		}
	}

}
