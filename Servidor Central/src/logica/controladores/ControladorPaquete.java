package logica.controladores;

import logica.datatypes.DTPaqueteDetalles;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import logica.entidades.ActividadTuristica;
import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.PaqueteYaRegistradoException;
import logica.entidades.Paquete;
import logica.manejadores.ManejadorActividadTuristica;
import logica.manejadores.ManejadorPaquete;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Equipo taller prog 16
 */

public class ControladorPaquete implements IControladorPaquete{
    public void altaPaquete(String nombre, String descripcion, int periodovalidez, float descuento, LocalDate fechaR) throws PaqueteYaRegistradoException {
        
    	ManejadorPaquete mp = ManejadorPaquete.getInstancia();
        
        if(mp.existePaquete(nombre)) {
            throw new PaqueteYaRegistradoException("Ya existe en el sistema un paquete con el nombre: "+nombre);
        }
        Paquete paq = new Paquete(nombre, descripcion, periodovalidez, descuento, fechaR);
        mp.addPaquete(paq);
    }

    @Override
    public List<DTPaqueteDetalles> obtenerDetallesPaquetes() {
        ArrayList<DTPaqueteDetalles> dtsPacks = new ArrayList<>();
        ManejadorPaquete mp = ManejadorPaquete.getInstancia();
        for (Paquete pack : mp.getPaquetes()){
            dtsPacks.add(pack.obtenerDTPaqueteDetalle());
        }
        return dtsPacks;
    }

	@Override
	public List<String> obtenerIdPaquetes() {
		ManejadorPaquete mp = ManejadorPaquete.getInstancia();	
		return new ArrayList<String>(mp.obtenerIdPaquetes());
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
	public void agregarActividadAPaquete(String nombreAct, String nombrePaq) throws ActividadTuristicaYaRegistradaException {
		ManejadorPaquete mp = ManejadorPaquete.getInstancia();
		Paquete paq = mp.getPaquete(nombrePaq);
		
		if (paq.obtenerIdActividadesIncluidas().contains(nombreAct))
			throw new ActividadTuristicaYaRegistradaException("El paquete ya incluye esta actividad");

		ManejadorActividadTuristica mat = ManejadorActividadTuristica.getInstancia();
		ActividadTuristica act = mat.getActividad(nombreAct);
				
		paq.agregarActividad(act);
		act.agregarPaquete(paq);
	}
}
