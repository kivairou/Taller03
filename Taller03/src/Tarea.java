package src;

import java.time.LocalDate;

public abstract class Tarea {
	protected String proyectoId;
    protected String id;
    protected String tipo;
    protected String descripcion;
    protected String estado;
    protected Usuario responsable;
    protected String complejidad; // Baja/Media/Alta
    protected LocalDate fechaCreacion;

    public Tarea(String proyectoId, String id, String tipo, String descripcion, String estado, Usuario responsable, String complejidad, LocalDate fechaCreacion) {
        this.proyectoId = proyectoId; 
        this.id = id; 
        this.tipo = tipo;
        this.descripcion = descripcion; 
        this.estado = estado; 
        this.responsable = responsable;
        this.complejidad = complejidad; 
        this.fechaCreacion = fechaCreacion;
    }

    public String getProyectoId() { 
    	return proyectoId; 
    	}
    
    public String getId() { 
    	return id; 
    	}
    
    public String getTipo() { 
    	return tipo; 
    	}
    
    public String getDescripcion() { 
    	return descripcion; 
    	}
    
    public String getEstado() { 
    	return estado; 
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

    public void setEstado(String e) { 
    	this.estado = e; 
    	}
    
    public void setResponsable(String r) { 
    	this.responsable = r; 
    	}

    // Visitor accept
    public abstract void accept(IVisitorTarea visitor);
}
