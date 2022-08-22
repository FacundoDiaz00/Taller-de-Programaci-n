package logica.controladores;

import java.util.List;

/**
 * @author Equipo taller prog 16
 */


public interface IControladorPaquete {
    public boolean altaTurista(String nombre, String descripcion, int periodovalidez, float descuento);
    
    public List<String> obtenerIdPaquetes();
    
    public List<String> obtenerIdDepartamentos();
    
    public List<String> obtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete(String nombreDep, String nombrePaq);
    
    public void agregarActividadAPaquete(String nombreAct, String nombrePaq);

}
