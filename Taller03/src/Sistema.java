package src;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;

public class Sistema implements ISistema{
	
	private static Sistema instancia;
	private ArrayList<Usuario> usuarios;
	private ArrayList<Proyecto> proyectos; 

	private static Scanner scan;

	
	public Sistema() {
		
		usuarios = new ArrayList<>();
		proyectos = new ArrayList<>();
		
	}

	public static Sistema getInstancia() {
		if(instancia == null) {
			instancia = new Sistema();
		}
		return instancia;
	}
	
	@Override
	public Usuario logeo(String user, String contraseña) {
		for(Usuario u: usuarios) {
			if(u.getUsername().equalsIgnoreCase(user) && u.getPassword().equalsIgnoreCase(contraseña)) {
				return u;
			}
		}
		return null;
	}

	@Override
	public void menuAdmin(Administrador admin) {
		
		int opcion; 
		scan = new Scanner(System.in);
		
		do {
			System.out.println("\n===== Menú Administrador =====");
			System.out.println("1) Ver lista de proyectos y tareas");
			System.out.println("2) Agregar o eliminar un proyecto");
			System.out.println("3) Agregar o eliminar una tarea en un proyecto");
			System.out.println("4) Asignar prioridades con Strategy");
			System.out.println("5) Generar reporte de proyectos");
			System.out.println("0)");
			System.out.print("Ingrese una opción: ");
			
			opcion = scan.nextInt();
			scan.nextLine();
			
			switch(opcion) {
			
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
					
					switch(opcion2) {
					
					case 1:
						agregarProyecto();
						break;
						
					case 2:
						agregarProyecto();
						break;
					}
					
				}while(opcion2!=0);
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
					
					switch(opcion3) {
					
					case 1:
						agregarTarea();
						break;
						
					case 2:
						eliminarTarea();
						break;
					}
					
				}while(opcion3!=0);
				break;
				
			case 4:
				asignarPrioridad();
				break;
				
			case 5:
				generarReporte();
				break;
			}
			
			
		}while(opcion!=0);
	}

	private void generarReporte() {
		// TODO Auto-generated method stub
		
	}

	private void asignarPrioridad() {
		// TODO Auto-generated method stub
		
	}

	private void eliminarTarea() {
		// TODO Auto-generated method stub
		
	}

	private void agregarTarea() {
		// TODO Auto-generated method stub
		
	}

	private void agregarProyecto() {
		// TODO Auto-generated method stub
		
	}

	private void verProyectosyTareas() {
		// TODO Auto-generated method stub
		
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
			
			switch(opcion) {
			
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
			
		}while(opcion!=0);
	}

	private void aplicarVisitor(Colaborador colab) {
		
		VisitorTarea visitor = new VisitorTarea();
		for(Proyecto p: proyectos) {
			for(Tarea t: p.getTareas()) {
				if(t.getResponsable().equalsIgnoreCase(colab.getUsername())) {
					System.out.println(t.getId() + ": ");
					t.accept(visitor);
				}
			}
		}
		
	}

	private void actualizarEstado(Colaborador colab) {
		// TODO Auto-generated method stub
		
	}

	private void verTareasAsignadas(Colaborador colab) {
		// TODO Auto-generated method stub
		
	}

	private void verProyectos() {
		System.out.println("\n=== Proyectos Disponibles ===");
		for(Proyecto p: proyectos) {
			System.out.println("ID: "+ p.getId() + " | Nombre: "+ p.getNombre() + " | Responsable: "+ p.getResponsable());
		}
		
	}

	@Override
	public void leerArchivos() throws FileNotFoundException{
		
		//texto usuarios
		File txtUsuarios = new File("usuarios.txt");
		scan = new Scanner(txtUsuarios);
		
		while(scan.hasNextLine()) {
			
			String linea = scan.nextLine();
			String[] partes = linea.split("\\|");
			
			String nombre = partes[0];
			String contraseña = partes[1];
			String rol = partes[2];
			
			if(rol.equalsIgnoreCase("Administrador")) {
				usuarios.add(new Administrador(nombre, contraseña));
			}
		}
		
		//texto proyectos
		File txtProyectos = new File("proyectos.txt");
		scan = new Scanner(txtProyectos);
		
		while(scan.hasNextLine()) {
			
			String linea = scan.nextLine();
			String[] partes = linea.split("\\|");
			
			String id = partes[0];
			String nombre = partes[1];
			String responsable = partes[2];
			proyectos.add(new Proyecto(id, nombre, responsable));
		}
		
		//texto tareas
		File txtTareas = new File("tareas.txt");
		scan = new Scanner(txtTareas);
		
		while(scan.hasNextLine()) {
			
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
			
			for(Proyecto p: proyectos) {
				if(p.getId().equalsIgnoreCase(idProyecto)) {
					proyecto = p;
				}
			}
			if(proyecto != null) {
				proyecto.agregarTarea(nuevaTarea);
			}
			
		}
	}

}
