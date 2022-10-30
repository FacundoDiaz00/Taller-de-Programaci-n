package configuraciones;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Cargador {

    private static final String UBICACION_ARCHIVO_PROPIEDADES = "/tProgProyecto/configuraciones/";
    private static final String ARCHIVO_CONFIGURACIONES_SERVIDOR_CENTRAL = "servidorConfiguracion.properties";

    private static String dirrecionAHacerDeploy;
    private static String dirrectorioImagenes;


    public static Boolean cargarPropiedades(){
        String homeUsuario = System.getProperty("user.home");

        //Lectura propiedades servidor

        try {
            InputStream input = new FileInputStream(homeUsuario + UBICACION_ARCHIVO_PROPIEDADES + ARCHIVO_CONFIGURACIONES_SERVIDOR_CENTRAL);
            Properties prop = new Properties();
            prop.load(input);
            input.close();

            // Obtengo las configuraciones

            dirrecionAHacerDeploy = prop.getProperty("dirrecionAPublicar");
            dirrectorioImagenes = prop.getProperty("dirrectorioImagenes");

            System.out.println("CONFIGURACIONES:");
            System.out.println("DIRRECION A PUBLICAR: " + dirrecionAHacerDeploy);
            System.out.println("DIRRECION A CARPETA IMAGENES: " + dirrectorioImagenes);

        } catch (IOException e){
            System.out.println("[CRITICO] No se pudo leer el archivo de configuraciones del servidor ");
            return false;
        }
        return true;
    }

    public static String getDirrecionAHacerDeploy() {
        return dirrecionAHacerDeploy;
    }

    public static String getDirrectorioImagenes() {
        return dirrectorioImagenes;
    }
}
