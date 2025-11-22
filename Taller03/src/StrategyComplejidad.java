package src;

import java.util.ArrayList;

public class StrategyComplejidad implements PrioridadStrategy {

	@Override
	public ArrayList<Tarea> tareasOrdenada(ArrayList<Tarea> tareas) {

		tareas.sort((t1, t2) -> {
			int comparador1 = nivel(t1.getComplejidad());
			int comparador2 = nivel(t2.getComplejidad());
			return Integer.compare(comparador2, comparador1);
		});
		return tareas;
	}

	private int nivel(String complejidad) {
		return switch (complejidad) {
		case "alta" -> 3;
		case "media" -> 2;
		case "baja" -> 1;
		default -> 0;
		};
	}

}
