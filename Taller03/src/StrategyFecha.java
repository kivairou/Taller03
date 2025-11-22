package src;

import java.util.ArrayList;
import java.util.Comparator;

public class StrategyFecha implements PrioridadStrategy {

	@Override
	public ArrayList<Tarea> tareasOrdenada(ArrayList<Tarea> tareas) {

		tareas.sort(Comparator.comparing(t -> t.getFechaCreacion()));
		return tareas;
	}

}
