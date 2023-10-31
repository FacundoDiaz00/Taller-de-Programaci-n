package logica.controladores;

/**
 * @author Equipo taller prog 16
 */

public class Fabrica {
    private static Fabrica instancia;

    private Fabrica() {
    }

    public static Fabrica getInstancia() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    public IControladorActividadTuristica getIControladorActividadTuristica() {
        return new ControladorActividadTuristica();
    }

    public IControladorPaquete getIControladorPaquete() {
        return new ControladorPaquete();
    }

    public IControladorUsuario getIControladorUsuario() {
        return new ControladorUsuario();
    }

    public IControladorMaestro getIControladorMaestro() {
        return new ControladorMaestro();
    }
}
