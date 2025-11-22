package src;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ISistema {
	Usuario logeo(String user, String contraseña);

	void menuAdmin(Administrador admin) throws IOException;
	void menuColaborador(Colaborador colab);
	void leerArchivos() throws FileNotFoundException;

}
