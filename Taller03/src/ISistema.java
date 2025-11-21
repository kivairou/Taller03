package src;

import java.io.FileNotFoundException;

public interface ISistema {
	Usuario logeo(String user, String contraseña);
	void menuAdmin(Administrador admin);
	void menuColaborador(Colaborador colab);
	void leerArchivos() throws FileNotFoundException;
	
}
