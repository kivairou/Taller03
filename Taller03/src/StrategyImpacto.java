package src;

import java.util.ArrayList;

public class StrategyImpacto implements PrioridadStrategy {

	@Override
	public ArrayList<Tarea> tareasOrdenada(ArrayList<Tarea> tareas) {

		tareas.sort((t1, t2) -> {
			int prioridad1 = obtenerPrioridad(t1);
			int prioridad2 = obtenerPrioridad(t2);
			return Integer.compare(prioridad2, prioridad1);
		});
		return tareas;

	}

	private int obtenerPrioridad(Tarea t) {

		if (t instanceof Bug)
			return 3;
		if (t instanceof Feature)
			return 2;
		if (t instanceof Documentacion)
			return 1;

		return 0;
	}

}
