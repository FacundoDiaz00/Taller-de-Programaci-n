package logica.controladores;

import java.util.List;
import java.util.ArrayList;

import logica.entidades.ActividadTuristica;
import logica.entidades.Paquete;
import logica.manejadores.ManejadorActividadTuristica;
import logica.manejadores.ManejadorPaquete;

/**
 * @author Equipo taller prog 16
 */

public class ControladorPaquete implements IControladorPaquete{
    public boolean altaTurista(String nombre, String descripcion, int periodovalidez, float descuento) {
        
    	ManejadorPaquete mp = ManejadorPaquete.getInstancia();
        
        if(mp.existePaquete(nombre)) {
            //TODO: throw exeption.
        	return false;
        }
        Paquete paq = new Paquete(nombre, descripcion, periodovalidez, descuento);
        mp.addPaquete(paq);
        return true; 
    }

	@Override
	public List<String> obtenerIdPaquetes() {
		ManejadorPaquete mp = ManejadorPaquete.getInstancia();	
		return new ArrayList<String>(mp.obtenerIdPaquetes());
	}

	@Override
	public List<String> obtenerIdDepartamentos() {
		var cat = new ControladorActividadTuristica();
		return cat.obtenerIdDepartamentos();
	}

	@Override
	public List<String> obtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete(String nombreDep, String nombrePaq) {
		var cat = new ControladorActividadTuristica();
		List<String> actividadesDep = cat.obtenerIdActividadesTuristicas(nombreDep);

		ManejadorPaquete mp = ManejadorPaquete.getInstancia();
		Paquete paq = mp.getPaquete(nombrePaq);

		List<String> actividadesPaq = new ArrayList<String>(paq.obtenerIdActividadesIncluidas());

		ArrayList<String> actividadesNoPaq = new ArrayList<String>();

		for (String actividadDep : actividadesDep) {
			if (!actividadesPaq.contains(actividadDep)) {
				actividadesNoPaq.add(actividadDep);
			}
		}

		return actividadesNoPaq;
	}

	@Override
	public void agregarActividadAPaquete(String nombreAct, String nombrePaq) {
		ManejadorPaquete mp = ManejadorPaquete.getInstancia();
		Paquete paq = mp.getPaquete(nombrePaq);
		ManejadorActividadTuristica mat = ManejadorActividadTuristica.getInstancia();
		ActividadTuristica act = mat.getActividad(nombreAct);
		paq.agregarActividad(act);
		act.agregarPaquete(paq);
	}
}
