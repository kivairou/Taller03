package src;

public interface IVisitorTarea {
	
	void visitar(Bug b);
	void visitar(Feature f);
	void visitar(Documentacion d);

}
