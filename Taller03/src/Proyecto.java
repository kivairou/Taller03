package src;

import java.util.ArrayList;
import java.util.List;

public class Proyecto {
	private String id;
	private String nombre;
	private String responsable;
	private List<Tarea> tareas = new ArrayList<>();
	private PrioridadStrategy estrategia;

	public Proyecto(String id, String nombre, String responsable) {
		this.id = id;
		this.nombre = nombre;
		this.responsable = responsable;
		this.estrategia = null;
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

	public void agregarTarea(Tarea t) {
		tareas.add(t);
	}

	public void setEstrategia(PrioridadStrategy estrategia) {
		this.estrategia = estrategia;
	}

	public ArrayList<Tarea> getTareasOrdenadas() {
		if (estrategia == null) {
			return new ArrayList<>(tareas);
			// lista sin ordenar
		} else {
			return estrategia.tareasOrdenada(new ArrayList<>(tareas));
			// lista ordenada port estrategia
		}
	}

	public void borrarTarea(String tid) {
		for (int i = 0; i < tareas.size(); i++) {
			if (tareas.get(i).getId().equals(tid)) {
				tareas.remove(i);
				break; // si los IDs son únicos, puedes salir aquí
			}
		}
	}
}

