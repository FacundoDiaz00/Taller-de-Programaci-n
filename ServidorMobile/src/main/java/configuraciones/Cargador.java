package configuraciones;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cargador {

    private static final String UBICACION_ARCHIVO_PROPIEDADES = "/.tProgProyecto/configuraciones/";
    private static final String ARCHIVO_CONFIGURACIONES_SERVIDOR_CENTRAL = "mobileConfiguracion.properties";

    private static String dirrecionDelCentral;


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

            dirrecionDelCentral = prop.getProperty("dirrecionDelServidorCentral");
            if (dirrecionDelCentral == null){
                log.severe("No se pudo leer la dirrecion del servidor central. Se termina el programa");
                return false;
            }
                      
            log.setLevel(Level.INFO);
            
            log.info("CONFIGURACIONES:");
            log.info("DIRRECION A SERVIDOR CENTRAL: " + dirrecionDelCentral);
            

        }catch (IOException e){
            log.setLevel(Level.ALL);
            log.severe(" No se pudo leer el archivo de configuraciones del servidor ");
            return false;
        }
        return true;
    }

    public static String getDirrecionDelCentral() {
        return dirrecionDelCentral;
    }

}
