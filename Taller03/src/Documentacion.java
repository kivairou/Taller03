package src;

import java.time.LocalDate;

public class Documentacion extends Tarea {

	public Documentacion(String id, String descripcion, String estado, String responsable, String complejidad,
			LocalDate fechaCreacion) {
		super(id, descripcion, estado, responsable, complejidad, fechaCreacion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(IVisitorTarea visitor) {
		visitor.visitar(this);

	}

}

