package src;

public class Usuario {
	private String username;
    private String password;
    private String rol; // "Administrador" o "Colaborador"

    public Usuario(String u, String p, String rol) {
        this.username = u;
        this.password = p;
        this.rol = rol;
    }

    public String getUsername() { 
    	return username; 
    	}
    
    public String getPassword() { 
    	return password; 
    	}
    
    public String getRol() { 
    	return rol; 
    }
    
    public boolean isAdministrador() { 
    	return rol.equalsIgnoreCase("Administrador"); 
    	}
}
