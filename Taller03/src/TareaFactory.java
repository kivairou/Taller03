package src;

import java.time.LocalDate;

public class TareaFactory {

	public static Tarea crearTarea(String tipo, String id, String descripcion, String estado, String responsable, String complejidad, LocalDate fecha) {
	
		return switch(tipo.toLowerCase()) {
			case "bug" -> new Bug(id, descripcion, estado, responsable, complejidad, fecha);
			case "feature" -> new Feature(id, descripcion, estado, responsable, complejidad, fecha);
			case "documentacion" -> new Documentacion(id, descripcion, estado, responsable, complejidad, fecha);
			default -> throw new IllegalArgumentException("Tipo de tarea no válido: "+ tipo);
		};
		
	}	
	
}
	
