package main;

import configuraciones.Cargador;
import excepciones.TurismoUyException;
import logica.controladores.Fabrica;
import publicar.actividadesTuristicasService.WebServiceActividades;
import publicar.paqueteTuristicasService.WebServicePaquetes;
import publicar.usuarioTuristicasService.WebServiceUsuarios;

import java.util.logging.Logger;

public class Main {

	public static void main(String[] args) {
		boolean resultado = Cargador.cargarPropiedades();
		if (!resultado) {
			return;
		}
		try{
			Fabrica.getInstancia().getIControladorMaestro().generarDatosDePrueba();
		}catch (TurismoUyException e){
			Logger.getLogger("logger").severe("Error al generar datos de prueba");
			return;
		}

		WebServiceActividades webServiceActividades = new WebServiceActividades();
		WebServicePaquetes webServicePaquetes = new WebServicePaquetes();
		WebServiceUsuarios webServiceUsuarios = new WebServiceUsuarios();
		webServiceActividades.publicar();
		webServicePaquetes.publicar();
		webServiceUsuarios.publicar();
	}

}
