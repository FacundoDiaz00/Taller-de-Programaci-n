package logica.controladores;

/**
 * @author Equipo taller prog 16
 */

public class Fabrica {
    private static Fabrica instancia;
    private IControladorActividadTuristica cAT;
    private IControladorPaquete cP;
    private IControladorUsuario cU;

    private Fabrica() {
    }

    public static Fabrica getInstancia(){
        if(instancia == null){
            instancia = new Fabrica();
        }
        return instancia;
    }

    public IControladorActividadTuristica getIControladorActividadTuristica() {
        if(cAT == null){
            cAT = new ControladorActividadTuristica();
        }
        return cAT;
    }

    public IControladorPaquete getIControladorPaquete() {
        if(cP == null){
            cP = new ControladorPaquete();
        }
        return cP;
    }

    public IControladorUsuario getIControladorUsuario() {
        if(cU == null){
            cU = new ControladorUsuario();
        }
        return cU;
    }
}
