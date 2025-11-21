package src;

public class VisitorTarea implements IVisitorTarea{

	@Override
	public void visitar(Bug b) {
		System.out.println("Afecta criticidad del proyecto");
		
	}

	@Override
	public void visitar(Feature f) {
		System.out.println("Impacta en la estimción de tiempo");
		
	}

	@Override
	public void visitar(Documentacion d) {
		System.out.println("Mejora la calidad del proyecto");
		
	}

}
