package Main;
import java.util.ArrayList;
import java.util.List;

public class Proyecto {
	private String id;
	private String nombre;
	private String responsable;
	private List<Tarea> tareas = new ArrayList<>();

	public Proyecto(String id, String nombre, String responsable) {
		this.id = id;
		this.nombre = nombre;
		this.responsable = responsable;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getResponsable() {
		return responsable;
	}

	public List<Tarea> getTareas() {
		return tareas;
	}

	public void addTarea(Tarea t) {
		tareas.add(t);
	}

	public void removeTarea(String tid) {
		for (int i = 0; i < tareas.size(); i++) {
			if (tareas.get(i).getId().equals(tid)) {
				tareas.remove(i);
				break; // si los IDs son únicos, puedes salir aquí
			}
		}
	}
}
