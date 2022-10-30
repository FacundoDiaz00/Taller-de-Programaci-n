package configuraciones;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cargador {

    private static final String UBICACION_ARCHIVO_PROPIEDADES = "/tProgProyecto/configuraciones/";
    private static final String ARCHIVO_CONFIGURACIONES_SERVIDOR_CENTRAL = "servidorConfiguracion.properties";

    private static String dirrecionAHacerDeploy;
    private static String dirrectorioImagenes;


    public static Boolean cargarPropiedades(){
        String homeUsuario = System.getProperty("user.home");

        //Lectura propiedades servidor

        Logger log = Logger.getLogger("logger");

        try {
            InputStream input = new FileInputStream(homeUsuario + UBICACION_ARCHIVO_PROPIEDADES + ARCHIVO_CONFIGURACIONES_SERVIDOR_CENTRAL);
            Properties prop = new Properties();
            prop.load(input);
            input.close();

            // Obtengo las configuraciones

            dirrecionAHacerDeploy = prop.getProperty("dirrecionAPublicar");
            if(dirrecionAHacerDeploy == null){
                log.severe(" No se pudo leer la dirrecion a hacer deploy. Se termina el programa");
                return false;
            }
            dirrectorioImagenes = prop.getProperty("dirrectorioImagenes");
            if(dirrectorioImagenes == null){
                log.severe(" No se pudo leer el dirrectorio de imagenes. Se termina el programa");
                return false;
            }

            String nivelLogger = prop.getProperty("nivelLogger");
            if(nivelLogger == null){
                log.severe(" No se pudo leer el nivel de logger. Se termina el programa");
                return false;
            }

            try {

                log.setLevel(Level.parse(nivelLogger));
            } catch (IllegalArgumentException e) {
                log.setLevel(Level.INFO);
                log.severe("El nivel de logeo es invalido, se continua con el nivel INFO");
            }

            log.info("CONFIGURACIONES:");
            log.info("DIRRECION A PUBLICAR: " + dirrecionAHacerDeploy);
            log.info("DIRRECION A CARPETA IMAGENES: " + dirrectorioImagenes);

        }catch (IOException e){
            log.setLevel(Level.ALL);
            log.severe(" No se pudo leer el archivo de configuraciones del servidor ");
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
