package logica.controladores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.PaqueteYaRegistradoException;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTPaqueteDetalles;
import logica.datatypes.Imagen;
import logica.entidades.ActividadTuristica;
import logica.entidades.Paquete;
import logica.manejadores.ManejadorActividadTuristica;
import logica.manejadores.ManejadorPaquete;

/**
 * @author Equipo taller prog 16
 */

public class ControladorPaquete implements IControladorPaquete {

	public ControladorPaquete() {

	}

	public void altaPaquete(String nombre, String descripcion, int periodovalidez, float descuento, LocalDate fechaR,
			Imagen img) throws PaqueteYaRegistradoException {

		ManejadorPaquete manejadosPaq = ManejadorPaquete.getInstancia();

		if (manejadosPaq.existePaquete(nombre)) {
			throw new PaqueteYaRegistradoException("Ya existe en el sistema un paquete con el nombre: " + nombre);
		}
		Paquete paq = new Paquete(nombre, descripcion, periodovalidez, descuento, fechaR, img);
		manejadosPaq.addPaquete(paq);
	}

	public void comprarPaquete(String nickTurista, String nombrePaquete, int cantTuristas) {
		// TODO
	}

	@Override
	public List<DTPaqueteDetalles> obtenerDTPaquetesDetalles() {
		ArrayList<DTPaqueteDetalles> dtsPacks = new ArrayList<>();
		ManejadorPaquete manejadorPaq = ManejadorPaquete.getInstancia();
		for (Paquete pack : manejadorPaq.getPaquetes()) {
			dtsPacks.add(pack.obtenerDTPaqueteDetalle());
		}
		return dtsPacks;
	}

	public DTPaqueteDetalles obtenerDTPaqueteDetalle(String nombrePaquete) {
		// TODO
		return null;
	}

	public List<DTPaquete> obtenerDTPaquetes() {
		// TODO
		return null;
	}

	@Override
	public List<String> obtenerIdPaquetes() {
		ManejadorPaquete manejadorPaq = ManejadorPaquete.getInstancia();
		return new ArrayList<String>(manejadorPaq.obtenerIdPaquetes());
	}

	@Override
	public List<String> obtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete(String nombreDep, String nombrePaq) {
		var cat = new ControladorActividadTuristica();

		// TODO: filtrar las no aceptadas
		List<String> actividadesDep = cat.obtenerIdActividadesTuristicas(nombreDep);

		ManejadorPaquete manejadorPaq = ManejadorPaquete.getInstancia();
		Paquete paq = manejadorPaq.getPaquete(nombrePaq);

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
	public void agregarActividadAPaquete(String nombreAct, String nombrePaq)
			throws ActividadTuristicaYaRegistradaException {
		// TODO: mirar DCOM

		ManejadorPaquete manejadorPaq = ManejadorPaquete.getInstancia();
		Paquete paq = manejadorPaq.getPaquete(nombrePaq);

		if (paq.obtenerIdActividadesIncluidas().contains(nombreAct))
			throw new ActividadTuristicaYaRegistradaException("El paquete ya incluye esta actividad");

		ManejadorActividadTuristica mat = ManejadorActividadTuristica.getInstancia();
		ActividadTuristica act = mat.getActividad(nombreAct);

		paq.agregarActividad(act);
		act.agregarPaquete(paq);
	}

	@Override
	public List<String> obtenerIdPaquetesSinComprar() {
		// TODO Auto-generated method stub
		return null;
	}

}
