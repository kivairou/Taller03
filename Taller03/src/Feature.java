package src;

import java.time.LocalDate;

public class Feature extends Tarea{

	public Feature(String id, String descripcion, String estado, String responsable, String complejidad,
			LocalDate fechaCreacion) {
		super(id, descripcion, estado, responsable, complejidad, fechaCreacion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(IVisitorTarea visitor) {
		// TODO Auto-generated method stub
		
	}

}
