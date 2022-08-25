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

    // Todo en el ejemplo crean una instancia por cada vez que la llaman, pero no le encuentro sentido ni a esto ni a que la fabrica sea un singleton, no tiene datos que mantener

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
