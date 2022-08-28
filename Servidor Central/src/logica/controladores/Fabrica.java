package logica.controladores;

/**
 * @author Equipo taller prog 16
 */

public class Fabrica {
    private static Fabrica instancia;

    private Fabrica() {
    }

    public static Fabrica getInstancia(){
        if(instancia == null){
            instancia = new Fabrica();
        }
        return instancia;
    }

    // Todo en el ejemplo crean una instancia por cada vez que la llaman, pero no le encuentro sentido ni a esto ni a que la fabrica sea un singleton, no tiene datos que mantener

    public IControladorActividadTuristica getIControladorActividadTuristica() {
        return new ControladorActividadTuristica();
    }

    public IControladorPaquete getIControladorPaquete() {
        return new ControladorPaquete();
    }

    public IControladorUsuario getIControladorUsuario() {
        return new ControladorUsuario();
    }
}
