package logica.controladores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.AltaInscripcionPosteriorAFechaSalidaException;
import excepciones.DeparamentoYaRegistradoException;
import excepciones.FechaAltaActividadPosteriorAFechaAltaSalidaException;
import excepciones.FechaAltaSalidaPosteriorAFechaSalidaException;
import excepciones.FechaAltaSalidaTuristicaPosteriorAFechaInscripcion;
import excepciones.InscripcionYaRegistradaException;
import excepciones.PaqueteYaRegistradoException;
import excepciones.SalidaYaRegistradaException;
import excepciones.SuperaElMaximoDeTuristasException;
import excepciones.UsuarioYaRegistradoException;
import logica.datatypes.Imagen;

/**
 * @author Equipo taller prog 16
 */


public class ControladorMaestro implements IControladorMaestro {
	public void generarDatosDePrueba() throws DeparamentoYaRegistradoException, UsuarioYaRegistradoException, ActividadTuristicaYaRegistradaException, SalidaYaRegistradaException, FechaAltaActividadPosteriorAFechaAltaSalidaException, FechaAltaSalidaPosteriorAFechaSalidaException, InscripcionYaRegistradaException, SuperaElMaximoDeTuristasException, FechaAltaSalidaTuristicaPosteriorAFechaInscripcion, AltaInscripcionPosteriorAFechaSalidaException, PaqueteYaRegistradoException {
		IControladorActividadTuristica icat = Fabrica.getInstancia().getIControladorActividadTuristica();
		IControladorUsuario iuser = Fabrica.getInstancia().getIControladorUsuario();
		IControladorPaquete ipack = Fabrica.getInstancia().getIControladorPaquete();
		
		// FIXME
		var categorias = new ArrayList<String>();
		var img = new Imagen(null, null);

		//Cargo Departamentos
		icat.altaDepartamento("Canelones", "División Turismo de la Intendencia", "https://www.imcanelones.gub.uy/es");
		icat.altaDepartamento("Maldonado", "División Turismo de la Intendencia", "https://www.maldonado.gub.uy/");
		icat.altaDepartamento("Rocha", "La Organización de Gestión del Destino" +
												  " (OGD) Rocha es un ámbito de articulación" +
												  " público – privada en el sector turístico que integran la Corporación Rochense de Turismo" +
												  "y la Intendencia de Rocha a trav´es de su Dirección de Turismo.", "http://www.turismorocha.gub.uy/");
		icat.altaDepartamento("Treinta y Tres" ,"División Turismo de la Intendencia", "https://treintaytres.gub.uy/" );
		icat.altaDepartamento("Cerro Largo","División Turismo de la Intendencia", "https://www.gub.uy/intendencia-cerro-largo/" );
		icat.altaDepartamento("Rivera","Promociona e implementa proyectos e iniciativas " +
												  "sostenibles de interés turístico con la participación institucional pública – privada en " +
												  "bien del desarrollo socioeconómico de la comunidad", "http://www.rivera.gub.uy/social/turismo/" );
		icat.altaDepartamento("Artigas","División Turismo de la Intendencia", "http://www.artigas.gub.uy/" );
		icat.altaDepartamento("Salto","División Turismo de la Intendencia", "https://www.salto.gub.uy/" );
		icat.altaDepartamento("Paysandú","División Turismo de la Intendencia", "https://www.paysandu.gub.uy/" );
		icat.altaDepartamento("Río Negro","División Turismo de la Intendencia", "https://www.rionegro.gub.uy/" );
		icat.altaDepartamento("Soriano","División Turismo de la Intendencia", "https://www.soriano.gub.uy/" );
		icat.altaDepartamento("Colonia","La propuesta del Departamento de Colonia" +
													"divide en cuatro actos su espect´aculo anual." +
													"Cada acto tiene su magia. Desde su naturaleza" +
													"y playas hasta sus tradiciones y el patrimonio" +
													"mundial. Todo el año se disfruta.", "https://colonia.gub.uy/turismo/" );
		icat.altaDepartamento("San José","División Turismo de la Intendencia", "https://sanjose.gub.uy/" );
		icat.altaDepartamento("Flores","División Turismo de la Intendencia", "https://flores.gub.uy/" );
		icat.altaDepartamento("Florida","División Turismo de la Intendencia", "http://www.florida.gub.uy/" );
		icat.altaDepartamento("Lavalleja","División Turismo de la Intendencia", "http://www.lavalleja.gub.uy/" );
		icat.altaDepartamento("Durazno","División Turismo de la Intendencia", "https://durazno.uy/" );
		icat.altaDepartamento("Tacuarembó ","División Turismo de la Intendencia", "https://tacuarembo.gub.uy/" );
		icat.altaDepartamento("Montevideo","División Turismo de la Intendencia", "https://montevideo.gub.uy/areas-tematicas/turismo" );


		//Cargo Turistas
		iuser.altaTurista("lachiqui", "Rosa María", "Martínez", "mirtha.legrand.ok@hotmail.com.ar", LocalDate.of(1927, 2, 23), img, "argentina");
		iuser.altaTurista("isabelita", "Elizabeth", "Windsor", "isabelita@thecrown.co.uk", LocalDate.of(1926, 4, 21), img, "inglesa");
		iuser.altaTurista("anibal", "Aníbal", "Lecter", "anibal@fing.edu.uy", LocalDate.of(1937, 12, 31), img, "lituana");
		iuser.altaTurista("waston", "Emma", "Waston", "e.waston@gmail.com", LocalDate.of(1990, 4, 15), img, "inglesa");
		iuser.altaTurista("elelvis", "Elvis", "Lacio", "suavemente@hotmail.com", LocalDate.of(1971, 7, 30), img, "estadounidense");
		iuser.altaTurista("eleven11", "Eleven", "Once", "eleven11@gmail.com", LocalDate.of(2004, 2, 19), img, "española");
		iuser.altaTurista("bobesponja", "Bob", "Esponja", "bobesponja@nickelodeon.com", LocalDate.of(1999, 5, 1), img, "japonesa");
		iuser.altaTurista("tony", "Antonio", "Pacheco", "eltony@manya.org.uy", LocalDate.of(1976, 4, 11), img, "uruguaya");
		iuser.altaTurista("chino", "Álvaro", "Recoba", "chino@trico.org.uy", LocalDate.of(1976, 3, 17), img, "uruguaya");
		iuser.altaTurista("mastropiero", "Johann Sebastian", "Mastropiero", "johann.sebastian@gmail.com", LocalDate.of(1922, 2, 7), img, "austríaca");

		//Cargo Proveedores
		iuser.altaProveedor("washington", "Washington", "Rocha", "washington@turismorocha.gub.uy", LocalDate.of(1970,9,14), img, 
				"Hola! me llamo Washington y soy el encargado del portal de turismo del departamento de Rocha Uruguay","http://turismorocha.gub.uy/");
		iuser.altaProveedor("eldiez", "Pablo", "Bengoechea", "eldiez@socfomturriv.org.uy", LocalDate.of(1965,6,27), img,
				"Pablo es el presidente de la Sociedad de Fomento Turístico de Rivera (conocida como Socfomturriv)","http://wwww.socfomturriv.org.uy/");
		iuser.altaProveedor("meche", "Mercedes", "Venn", "meche@colonia.gub.uy", LocalDate.of(1990,12,31), img,
				"Departamento de Turismo del Departamento de Colonia","https://colonia.gub.uy/turismo/");

		
		
		//Actividades
		icat.altaActividadTuristica("washington" , "Rocha", "Degusta",
				"Festival gastronómico de productos locales en Rocha", 3, 800, "Rocha", LocalDate.of(2022,7,20), img, categorias);
		icat.altaActividadTuristica("washington" , "Rocha", "Teatro con Sabores",
				"En el mes aniversario del Club Deportivo Unión de Rocha te invitamos a una merienda deliciosa.", 3, 500, "Rocha", LocalDate.of(2022,7,21), img, categorias);
		icat.altaActividadTuristica("meche" , "Colonia", "Tour por Colonia del Sacramento",
				"Con guía especializado y en varios idiomas. Varios circuitos posibles.", 2, 400, "Colonia del Sacramento", LocalDate.of(2022,8,1), img, categorias);
		icat.altaActividadTuristica("meche" , "Colonia", "Almuerzo en el Real de San Carlos",
				"Restaurante en la renovada Plaza de Toros con men´u internacional", 2, 800, "Colonia del Sacramento", LocalDate.of(2022,8,1), img, categorias);
		icat.altaActividadTuristica("eldiez" , "Rivera", "Almuerzo en Valle del Lunarejo",
				"Almuerzo en la Posada con ticket fijo. Menú que incluye bebida y postre casero.", 2, 300, "Tranqueras", LocalDate.of(2022,8,1), img, categorias);
		icat.altaActividadTuristica("eldiez" , "Rivera", "Cabalgata en Valle del Lunarejo",
				"Cabalgata por el área protegida. Varios recorridos para elegir.", 2, 150, "Tranqueras", LocalDate.of(2022,8,1), img, categorias);

		//Salidas
		icat.altaSalidaTuristica("Degusta", "Degusta Agosto", LocalDateTime.of(2022,8,20,17,0),
				LocalDate.of(2022,7,21), "Sociedad Agropecuaria de Rocha", 20, img);
		icat.altaSalidaTuristica("Degusta", "Degusta Setiembre", LocalDateTime.of(2022,9,3,17,0),
				LocalDate.of(2022,7,22), "Sociedad Agropecuaria de Rocha", 20, img);
		icat.altaSalidaTuristica("Teatro con Sabores", "Teatro con Sabores 1", LocalDateTime.of(2022,9,4,18,0),
				LocalDate.of(2022,7,23), "Club Deportivo Unión", 30, img);
		icat.altaSalidaTuristica("Teatro con Sabores", "Teatro con Sabores 2", LocalDateTime.of(2022,9,11,18,0),
				LocalDate.of(2022,7,23), "Club Deportivo Unión", 30, img);
		icat.altaSalidaTuristica("Tour por Colonia del Sacramento", "Tour Colonia del Sacramento 11-09", LocalDateTime.of(2022,9,11,10,0),
				LocalDate.of(2022,8,5), "Encuentro en la base del Faro", 5, img);
		icat.altaSalidaTuristica("Tour por Colonia del Sacramento", "Tour Colonia del Sacramento 18-09", LocalDateTime.of(2022,9,18,10,0),
				LocalDate.of(2022,8,5), "Encuentro en la base del Faro", 5, img);
		icat.altaSalidaTuristica("Almuerzo en el Real de San Carlos", "Almuerzo 1", LocalDateTime.of(2022,9,18,12,0),
				LocalDate.of(2022,8,4), "Restaurante de la Plaza de Toros", 5, img);
		icat.altaSalidaTuristica("Almuerzo en el Real de San Carlos", "Almuerzo 2", LocalDateTime.of(2022,9,25,12,0),
				LocalDate.of(2022,8,4), "Restaurante de la Plaza de Toros", 5, img);
		icat.altaSalidaTuristica("Almuerzo en Valle del Lunarejo", "Almuerzo 3", LocalDateTime.of(2022,9,10,12,0),
				LocalDate.of(2022,8,15), "Posada Del Lunarejo", 4, img);
		icat.altaSalidaTuristica("Almuerzo en Valle del Lunarejo", "Almuerzo 4", LocalDateTime.of(2022,9,11,12,0),
				LocalDate.of(2022,8,15), "Posada Del Lunarejo", 4, img);
		icat.altaSalidaTuristica( "Cabalgata en Valle del Lunarejo","Cabalgata 1", LocalDateTime.of(2022,9,10,16,0),
				LocalDate.of(2022,8,15), "Posada Del Lunarejo", 4, img);
		icat.altaSalidaTuristica( "Cabalgata en Valle del Lunarejo","Cabalgata 2", LocalDateTime.of(2022,9,11,16,0),
				LocalDate.of(2022,8,15), "Posada Del Lunarejo", 4, img);


		//Inscripciones
		icat.altaInscripcionSalidaTuristica("Degusta Agosto", "lachiqui" , 3, LocalDate.of(2022, 8, 15));
		icat.altaInscripcionSalidaTuristica("Degusta Agosto", "elelvis" , 5, LocalDate.of(2022, 8, 16));
		icat.altaInscripcionSalidaTuristica("Tour Colonia del Sacramento 18-09", "lachiqui" , 3, LocalDate.of(2022, 8, 18));
		icat.altaInscripcionSalidaTuristica("Tour Colonia del Sacramento 18-09", "isabelita" , 1, LocalDate.of(2022, 8, 19));
		icat.altaInscripcionSalidaTuristica("Almuerzo 2", "mastropiero" , 2, LocalDate.of(2022, 8, 19));
		icat.altaInscripcionSalidaTuristica("Teatro con Sabores 1", "anibal" , 1, LocalDate.of(2022, 8, 19));
		icat.altaInscripcionSalidaTuristica("Teatro con Sabores 2", "chino" , 10, LocalDate.of(2022, 8, 20));
		icat.altaInscripcionSalidaTuristica("Teatro con Sabores 2", "bobesponja" , 2, LocalDate.of(2022, 8, 20));
		icat.altaInscripcionSalidaTuristica("Teatro con Sabores 2", "anibal" , 1, LocalDate.of(2022, 8, 21));
		icat.altaInscripcionSalidaTuristica("Degusta Setiembre", "tony" , 11, LocalDate.of(2022, 8, 21));

		//Paquete
		ipack.altaPaquete("Disfrutar Rocha", "Actividades para hacer en familia y disfrutar arte y gastronomía", 60, 20,LocalDate.of(2022, 8, 10), img); //todo falta la fecha de alta
		ipack.altaPaquete("Un día en Colonia", "Paseos por el casco histórico y se puede terminar con Almuerzo en la Plaza de Toro", 45, 15, LocalDate.of(2022, 8, 10), img); //todo falta la fecha de alta

		//Agregar Actividad a Paquete
		ipack.agregarActividadAPaquete("Degusta", "Disfrutar Rocha");
		ipack.agregarActividadAPaquete("Teatro con Sabores", "Disfrutar Rocha");
		ipack.agregarActividadAPaquete("Tour por Colonia del Sacramento", "Un día en Colonia");
		ipack.agregarActividadAPaquete("Almuerzo en el Real de San Carlos", "Un día en Colonia");
	}
}
