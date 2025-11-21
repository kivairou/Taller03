package src;

import java.time.LocalDate;

public abstract class Tarea {
	
    protected String id;
    protected String descripcion;
    protected String estado;
    protected String responsable;
    protected String complejidad; // Baja/Media/Alta
    protected LocalDate fechaCreacion;

    public Tarea(String id, String descripcion, String estado, String responsable, String complejidad, LocalDate fechaCreacion) {
        
        this.id = id; 
        this.descripcion = descripcion; 
        this.estado = estado; 
        this.responsable = responsable;
        this.complejidad = complejidad; 
        this.fechaCreacion = fechaCreacion;
    }

    
    public String getId() { 
    	return id; 
    	}
   
 
    public String getEstado() { 
    	return estado; 
    	}
    
    public void setEstado(String estado) {
    	this.estado = estado;
    }
    
    public String getResponsable() { 
    	return responsable; 
    	}
    
    public String getComplejidad() { 
    	return complejidad; 
    	}
    
    public LocalDate getFechaCreacion() { 
    	return fechaCreacion; 
    	}

    // Visitor accept
    public abstract void accept(IVisitorTarea visitor);
}
