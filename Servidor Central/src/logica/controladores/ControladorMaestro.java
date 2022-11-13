package logica.controladores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.TurismoUyException;
import logica.datatypes.EstadoActividadTuristica;
import logica.datatypes.Imagen;
import logica.manejadores.ManejadorPersistenciaJPA;

/**
 * @author Equipo taller prog 16
 */

public class ControladorMaestro implements IControladorMaestro {
	private static String[][] datosDepartamentos = {
			{ "Canelones", "División Turismo de la Intendencia", "https://www.imcanelones.gub.uy/es" },
			{ "Maldonado", "División Turismo de la Intendencia", "https://www.maldonado.gub.uy/" },
			{ "Rocha",
					"La Organización de Gestión del Destino (OGD) Rocha es un ámbito de articulación público – privada en el sector turístico que integran la Corporación Rochense de Turismo y la Intendencia de Rocha a través de su Dirección de Turismo.",
					"www.turismorocha.gub.uy" },
			{ "Treinta y Tres", "División Turismo de la Intendencia", "https://treintaytres.gub.uy/" },
			{ "Cerro Largo", "División Turismo de la Intendencia", "https://www.gub.uy/intendencia-cerro-largo/" },
			{ "Rivera",
					"Promociona e implementa proyectos e iniciativas sostenibles de interés turístico con la participación institucional pública – privada en bien del desarrollo socioeconómico de la comunidad.",
					"www.rivera.gub.uy/social/turismo/" },
			{ "Artigas", "División Turismo de la Intendencia", "http://www.artigas.gub.uy" },
			{ "Salto", "División Turismo de la Intendencia", "https://www.salto.gub.uy" },
			{ "Paysandú", "División Turismo de la Intendencia", "https://www.paysandu.gub.uy" },
			{ "Río Negro", "División Turismo de la Intendencia", "https://www.rionegro.gub.uy" },
			{ "Soriano", "División Turismo de la Intendencia", "https://www.soriano.gub.uy" },
			{ "Colonia",
					"La propuesta del Departamento de Colonia divide en cuatro actos su espectáculo anual. Cada acto tiene su magia. Desde su naturaleza y playas hasta sus tradiciones y el patrimonio mundial. Todo el año se disfruta.",
					"https://colonia.gub.uy/turismo/" },
			{ "San José", "División Turismo de la Intendencia", "https://sanjose.gub.uy" },
			{ "Flores", "División Turismo de la Intendencia", "https://flores.gub.uy" },
			{ "Florida", "División Turismo de la Intendencia", "http://www.florida.gub.uy" },
			{ "Lavalleja", "División Turismo de la Intendencia", "http://www.lavalleja.gub.uy" },
			{ "Durazno", "División Turismo de la Intendencia", "https://durazno.uy" },
			{ "Tacuarembó", "División Turismo de la Intendencia", "https://tacuarembo.gub.uy" }, { "Montevideo",
					"División Turismo de la Intendencia", "https://montevideo.gub.uy/areas-tematicas/turismo" } };

	private static int[][] fechasNacUsuarios = new int[][] { { 23, 2, 1927 }, { 21, 04, 1926 }, { 31, 12, 1937 },
			{ 15, 4, 1990 }, { 30, 07, 1971 }, { 19, 02, 2004 }, { 01, 05, 1999 }, { 11, 04, 1976 }, { 17, 03, 1976 },
			{ 07, 02, 1922 }, { 14, 9, 1970 }, { 27, 06, 1965 }, { 31, 12, 1990 } };

	private static String[][] datosUsuarios = new String[][] {
			{ "lachiqui", "Rosa María", "Martínez", "mirtha.legrand.ok@hotmail.com.ar", "awdrg543", "argentina", null,
					null },
			{ "isabelita", "Elizabeth", "Windsor", "isabelita@thecrown.co.uk", "r5t6y7u8", "inglesa", null, null },
			{ "anibal", "Aníbal", "Lecter", "anibal@fing.edu.uy", "edrft543", "lituana", null, null },
			{ "waston", "Emma", "Waston", "e.waston@gmail.com", "poiuy987", "inglesa", null, null },
			{ "elelvis", "Elvis", "Lacio", "suavemente@hotmail.com", "45idgaf67", "estadounidense", null, null },
			{ "eleven11", "Eleven", "Once", "eleven11@gmail.com", "xdrgb657", "española", null, null },
			{ "bobesponja", "Bob", "Esponja", "bobesponja@nickelodeon.com", "sbsplol1", "japonesa", null, null },
			{ "tony", "Antonio", "Pacheco", "eltony@manya.org.uy", "okmnji98", "uruguaya", null, null },
			{ "chino", "Álvaro", "Recoba", "chino@trico.org.uy", "qsxcdw43", "uruguaya", null, null },
			{ "mastropiero", "Johann Sebastian", "Mastropiero", "johann.sebastian@gmail.com", "qpwoei586", "austríaca",
					null, null },
			{ "washington", "Washington", "Rocha", "washington@turismorocha.gub.uy", "asdfg654", null,
					"Hola! me llamo Washington y soy el encargado del portal de turismo del departamento de Rocha - Uruguay",
					"http://turismorocha.gub.uy/" },
			{ "eldiez", "Pablo", "Bengoechea", "eldiez@socfomturriv.org.uy", "ytrewq10", null,
					"Pablo es el presidente de la Sociedad de Fomento Turístico de Rivera (conocida como Socfomturriv)",
					"http://wwww.socfomturriv.org.uy" },
			{ "meche", "Mercedes", "Venn", "meche@colonia.gub.uy", "mnjkiu89", null,
					"Departamento de Turismo del Departamento de Colonia", "https://colonia.gub.uy/turismo/" },

	};

	private static String[][] datosActividadesStrings = new String[][] {
			{ "Degusta", "Festival gastronómico de productos locales en Rocha", "Rocha", "Rocha", "washington", },
			{ "Teatro con Sabores",
					"En el mes aniversario del Club Deportivo Unión de Rocha te invitamos a una merienda deliciosa.",
					"Rocha", "Rocha", "washington", },
			{ "Tour por Colonia del Sacramento",
					"Con guía especializado y en varios idiomas. Varios circuitos posibles.", "Colonia del Sacramento",
					"Colonia", "meche", },
			{ "Almuerzo en el Real de San Carlos", "Restaurante en la renovada Plaza de Toros con menú internacional",
					"Colonia del Sacramento", "Colonia", "meche", },
			{ "Almuerzo en Valle del Lunarejo",
					"Almuerzo en la Posada con ticket fijo. Menú que incluye bebida y postre casero.", "Tranqueras",
					"Rivera", "eldiez", },
			{ "Cabalgata en Valle del Lunarejo", "Cabalgata por el área protegida. Varios recorridos para elegir.",
					"Tranqueras", "Rivera", "eldiez", },
			{ "Bus turístico Colonia", "Recorrida por los principales atractivos de la ciudad",
					"Colonia del Sacramento", "Colonia", "meche", },
			{ "Colonia Premium Tour", "Visita lugares exclusivos y relevantes", "Colonia del Sacramento", "Colonia",
					"meche", },
			{ "Deportes náuticos sin uso de motor", "kitsurf - windsurf - kayakismo - canotaje en Rocha", "Rocha",
					"Rocha", "washington", },
			{ "Descubre Rivera",
					"Rivera es un departamento de extraordinaria riqueza natural patrimonial y cultural con una ubicación geográfica privilegiada",
					"Rivera", "Rivera", "eldiez", } };

	private static int[][] datosActividadesInteger = new int[][] { { 3, 800, 20, 7, 2022 }, { 3, 500, 21, 7, 2022 },
			{ 2, 400, 1, 8, 2022 }, { 2, 800, 1, 8, 2022 }, { 2, 300, 1, 8, 2022 }, { 2, 150, 1, 8, 2022 },
			{ 3, 600, 1, 9, 2022 }, { 4, 2600, 3, 9, 2022 }, { 3, 1200, 5, 9, 2022 }, { 2, 650, 16, 9, 2022 }

	};

	private static String[] datosCategorias = { "Aventura y Deporte", "Campo y Naturaleza", "Cultura y Patrimonio",
			"Gastronomia", "Turismo Playas" };

	private static String[][] actividadesCategorias = new String[][] { { "Gastronomia" }, // A1
			{ "Cultura y Patrimonio", "Gastronomia" }, // A2
			{ "Cultura y Patrimonio" }, // A3
			{ "Gastronomia" }, // A4
			{ "Campo y Naturaleza", "Gastronomia" }, // A5
			{ "Campo y Naturaleza" }, // A6
			{ "Cultura y Patrimonio" }, // A7
			{ "Cultura y Patrimonio" }, // A8
			{ "Cultura y Patrimonio", "Aventura y Deporte" }, // A9
			{ "Cultura y Patrimonio" } // A10
	};

	private static String[] actividadesConfirmadas = new String[] { "Degusta", "Teatro con Sabores",
			"Tour por Colonia del Sacramento", "Almuerzo en el Real de San Carlos", "Almuerzo en Valle del Lunarejo",
			"Cabalgata en Valle del Lunarejo" };

	private static String[] actividadesRechazadas = new String[] { "Colonia Premium Tour", "Descubre Rivera" };
	private static String[][] datosSalidasStrings = new String[][] {
			{ "Degusta", "Degusta Agosto", "Sociedad Agropecuaria de Rocha" },
			{ "Degusta", "Degusta Setiembre", "Sociedad Agropecuaria de Rocha" },
			{ "Teatro con Sabores", "Teatro con Sabores 1", "Club Deportivo Unión" },
			{ "Teatro con Sabores", "Teatro con Sabores 2", "Club Deportivo Unión" },
			{ "Tour por Colonia del Sacramento", "Tour Colonia del Sacramento 11-09", "Encuentro en la base del Faro" },
			{ "Tour por Colonia del Sacramento", "Tour Colonia del Sacramento 18-09", "Encuentro en la base del Faro" },
			{ "Almuerzo en el Real de San Carlos", "Almuerzo 1", "Restaurante de la Plaza de Toros" },
			{ "Almuerzo en el Real de San Carlos", "Almuerzo 2", "Restaurante de la Plaza de Toros" },
			{ "Almuerzo en Valle del Lunarejo", "Almuerzo 3", "Posada Del Lunarejo" },
			{ "Almuerzo en Valle del Lunarejo", "Almuerzo 4", "Posada Del Lunarejo" },
			{ "Cabalgata en Valle del Lunarejo", "Cabalgata 1", "Posada del Lunarejo" },
			{ "Cabalgata en Valle del Lunarejo", "Cabalgata 2", "Posada del Lunarejo" },
			{ "Degusta", "Degusta Octubre", "Sociedad Agropecuaria de Rocha" },
			{ "Degusta", "Degusta Noviembre", "Sociedad Agropecuaria de Rocha" },
			{ "Teatro con Sabores", "Teatro con Sabores 3", "Club Deportivo Unión" },
			{ "Tour por Colonia del Sacramento", "Tour Colonia del Sacramento 30-10", "Encuentro en la base del Faro" },
			{ "Cabalgata en Valle del Lunarejo", "Cabalgata Extrema", "Posada del Lunarejo" },
			{ "Almuerzo en el Real de San Carlos", "Almuerzo en el Real 1", "Restaurante de la Plaza de Toros" },

			{ "Degusta", "Degusta Diciembre", "Sociedad Agropecuaria de Rocha" },
			{ "Teatro con Sabores", "Teatro con Sabores 4", "Club Deportivo Unión" } };

	private static int[][] datosSalidasInteger = new int[][] { { 20, 8, 2022, 17, 20, 21, 07, 2022 },
			{ 03, 9, 2022, 17, 20, 22, 07, 2022 }, { 04, 9, 2022, 18, 30, 23, 07, 2022 },
			{ 11, 9, 2022, 18, 30, 23, 07, 2022 }, { 11, 9, 2022, 10, 5, 05, 8, 2022 },
			{ 18, 9, 2022, 10, 5, 05, 8, 2022 }, { 18, 9, 2022, 12, 5, 04, 8, 2022 },
			{ 25, 9, 2022, 12, 5, 04, 8, 2022 }, { 10, 9, 2022, 12, 4, 15, 8, 2022 },
			{ 11, 9, 2022, 12, 4, 15, 8, 2022 }, { 10, 9, 2022, 16, 4, 15, 8, 2022 },
			{ 11, 9, 2022, 16, 4, 15, 8, 2022 }, { 30, 10, 2022, 17, 20, 22, 9, 2022 },
			{ 05, 11, 2022, 17, 20, 02, 10, 2022 }, { 11, 11, 2022, 18, 30, 25, 8, 2022 },
			{ 30, 10, 2022, 10, 10, 07, 9, 2022 }, { 30, 10, 2022, 16, 4, 15, 9, 2022 },
			{ 30, 10, 2022, 12, 10, 10, 10, 2022 },

			{ 02, 12, 2022, 17, 20, 07, 11, 2022 }, { 03, 12, 2022, 18, 30, 07, 11, 2022 } };

	private static boolean[] salidaTieneImg = new boolean[] { true, true, true, true, true, true, false, false, false,
			false, true, false, true, true, false, true, true, false,

			true, false };

	private static String[][] datosPaquetesString = new String[][] {
			{ "Disfrutar Rocha", "Actividades para hacer en familia y disfrutar arte y gastronomía" },
			{ "Un día en Colonia",
					"Paseos por el casco histórico y se puede terminar con Almuerzo en la Plaza de Toros" },
			{ "Valle Del Lunarejo", "Visite un área protegida con un paisaje natural hermoso" },
			{ "Rocha de Fiesta", "Para cerrar el año a lo grande en nuestro departamento más oceánico" } };

	private static int[][] datosPaquetesInteger = new int[][] { { 60, 20, 10, 8, 2022 }, { 45, 15, 01, 8, 2022 },
			{ 60, 15, 15, 9, 2022 }, { 45, 30, 07, 11, 2022 } };

	private static String[][] asigPaqueteAct = new String[][] { { "Disfrutar Rocha", "Degusta" },
			{ "Disfrutar Rocha", "Teatro con Sabores" }, { "Un día en Colonia", "Tour por Colonia del Sacramento" },
//            { "Un día en Colonia", "Almuerzo en el Real de San Carlos" },
			{ "Valle Del Lunarejo", "Almuerzo en Valle del Lunarejo" },
			{ "Valle Del Lunarejo", "Cabalgata en Valle del Lunarejo" }, { "Rocha de Fiesta", "Degusta" } };

	private static int[][] datosCompras = new int[][] { { 2, 15, 8, 2022, 14, 10, 2022, 2080 },
			{ 5, 20, 8, 2022, 04, 10, 2022, 1700 }, { 1, 15, 9, 2022, 30, 10, 2022, 340 },
			{ 10, 1, 9, 2022, 31, 10, 2022, 10400 }, { 2, 18, 9, 2022, 2, 11, 2022, 680 },
			{ 6, 2, 9, 2022, 17, 10, 2022, 2040 } };

	private static String[][] asigPaqueteTurista = new String[][] { { "lachiqui", "Disfrutar Rocha" },
			{ "lachiqui", "Un día en Colonia" }, { "waston", "Un día en Colonia" }, { "elelvis", "Disfrutar Rocha" },
			{ "elelvis", "Un día en Colonia" }, { "mastropiero", "Un día en Colonia" } };

	private static String[][] datosInscripcion = new String[][] { { "Degusta Agosto", "lachiqui", null },
			{ "Degusta Agosto", "elelvis", null }, { "Tour Colonia del Sacramento 18-09", "lachiqui", null },
			{ "Tour Colonia del Sacramento 18-09", "isabelita", null }, { "Almuerzo 2", "mastropiero", null },
			{ "Teatro con Sabores 1", "chino", null }, { "Teatro con Sabores 2", "chino", null },
			{ "Teatro con Sabores 2", "bobesponja", null }, { "Teatro con Sabores 2", "anibal", null },
			{ "Degusta Setiembre", "tony", null }, { "Degusta Noviembre", "lachiqui", "Disfrutar Rocha" }, // I11
			{ "Teatro con Sabores 3", "lachiqui", "Disfrutar Rocha" },
			{ "Degusta Setiembre", "elelvis", "Disfrutar Rocha" },
			{ "Teatro con Sabores 1", "elelvis", "Disfrutar Rocha" },
			{ "Tour Colonia del Sacramento 11-09", "lachiqui", "Un día en Colonia" },
			{ "Almuerzo 1", "lachiqui", null }, // I16
			{ "Tour Colonia del Sacramento 18-09", "waston", "Un día en Colonia" }, { "Almuerzo 2", "waston", null },
			{ "Tour Colonia del Sacramento 30-10", "elelvis", "Un día en Colonia" },
			{ "Almuerzo en el Real 1", "elelvis", null },
			{ "Tour Colonia del Sacramento 30-10", "mastropiero", "Un día en Colonia" },
			{ "Almuerzo en el Real 1", "mastropiero", null } };

	private static int[][] datosIntegerInscripcion = new int[][] { { 3, 2400, 15, 8, 2022 }, { 5, 4000, 16, 8, 2022 },
			{ 3, 1200, 18, 8, 2022 }, { 1, 400, 19, 8, 2022 }, { 2, 1600, 19, 8, 2022 }, { 1, 500, 19, 8, 2022 },
			{ 10, 5000, 20, 8, 2022 }, { 2, 1000, 20, 8, 2022 }, { 1, 500, 21, 8, 2022 }, { 11, 8800, 21, 8, 2022 },
			{ 2, 1280, 3, 10, 2022 }, { 2, 800, 3, 10, 2022 }, { 5, 3200, 2, 9, 2022 }, { 5, 2000, 2, 9, 2022 },
			{ 5, 1700, 3, 9, 2022 }, { 5, 4000, 3, 9, 2022 }, // I16
			{ 1, 340, 5, 9, 2022 }, { 1, 800, 5, 9, 2022 }, // 18
			{ 2, 680, 2, 10, 2022 }, { 2, 1600, 11, 10, 2022 }, // 20
			{ 4, 1360, 12, 10, 2022 }, { 4, 3200, 12, 10, 2022 },// 22

	};

	// Seguidores
	private static String[][] seguidoresYSeguidos = new String[][] { { "lachiqui", "isabelita" }, // U1
			{ "lachiqui", "mastropiero" }, { "lachiqui", "washington" }, { "lachiqui", "eldiez" },
			{ "lachiqui", "meche" },

			{ "isabelita", "lachiqui" }, // U2

			{ "anibal", "waston" }, // U3
			{ "anibal", "eleven11" }, { "anibal", "bobesponja" }, { "anibal", "meche" },

			{ "waston", "isabelita" }, // U4
			{ "waston", "washington" },

			{ "elelvis", "bobesponja" }, // U5
			{ "elelvis", "tony" }, { "elelvis", "eldiez" },

			{ "eleven11", "lachiqui" }, // U6
			{ "eleven11", "waston" }, { "eleven11", "mastropiero" },

			{ "bobesponja", "anibal" }, // U7
			{ "bobesponja", "eleven11" },

			{ "tony", "chino" }, // U8
			{ "tony", "eldiez" },

			{ "chino", "elelvis" }, // U9
			{ "chino", "mastropiero" }, { "chino", "washington" }, { "chino", "meche" },

			{ "washington", "mastropiero" }, { "washington", "waston" },

			{ "eldiez", "tony" },

			{ "meche", "lachiqui" }, { "meche", "isabelita" }, { "meche", "waston" }, { "meche", "eleven11" },

	};
// Videos actividades

	private static String[] videosActividades = new String[] { "https://www.youtube.com/embed/zQjSMQ6uV1g",
			"https://www.youtube.com/embed/Lxit3xvKShc", "https://www.youtube.com/embed/zVDGjURCBz8",
			"https://www.youtube.com/embed/wfyDxicM1PQ", "https://www.youtube.com/embed/5uaEdiQVEEE",
			"https://www.youtube.com/embed/dlUb22YfXDg", "https://www.youtube.com/embed/FCFoe4ibkk8", null,
			"https://www.youtube.com/embed/a7Lfx4Flb28", null };

//Actividades favoritas

	private static String[][] actFav = new String[][] { { "lachiqui", "Degusta" }, // U1
			{ "lachiqui", "Tour por Colonia del Sacramento" },

			{ "isabelita", "Tour por Colonia del Sacramento" }, // U2
			{ "isabelita", "Almuerzo en el Real de San Carlos" }, // U2

			{ "anibal", "Almuerzo en el Real de San Carlos" }, // U3
			{ "anibal", "Almuerzo en Valle del Lunarejo" }, { "anibal", "Cabalgata en Valle del Lunarejo" },

			{ "waston", "Degusta" }, // U4
			{ "waston", "Teatro con Sabores" }, { "waston", "Tour por Colonia del Sacramento" },
			{ "waston", "Almuerzo en el Real de San Carlos" },

			{ "elelvis", "Cabalgata en Valle del Lunarejo" }, // U5

			{ "eleven11", "Degusta" }, // U6
			{ "eleven11", "Teatro con Sabores" },

			{ "bobesponja", "Tour por Colonia del Sacramento" }, // U7
			{ "bobesponja", "Almuerzo en el Real de San Carlos" },

			{ "tony", "Teatro con Sabores" }, // U8

	};

	public void generarDatosDePrueba() throws TurismoUyException {
		IControladorActividadTuristica icat = Fabrica.getInstancia().getIControladorActividadTuristica();
		IControladorUsuario iuser = Fabrica.getInstancia().getIControladorUsuario();
		IControladorPaquete ipack = Fabrica.getInstancia().getIControladorPaquete();

		var actividadesPersistidas = ManejadorPersistenciaJPA.getInstancia().obtenerIdActividadesFinalizadas();

		// Carga de Categorias
		for (String cat : datosCategorias) {
			icat.altaCategoria(cat);
		}

		// Carga de departamentos
		for (String[] dep : datosDepartamentos) {
			icat.altaDepartamento(dep[0], dep[1], dep[2]);
		}

		// Cargo usuarios
		for (int i = 0; i < datosUsuarios.length; i++) {
			String[] usr = datosUsuarios[i];
			if (usr[6] == null) {
				// Cargo Turistas
				iuser.altaTurista(usr[0], usr[1], usr[2], usr[3], usr[4],
						LocalDate.of(fechasNacUsuarios[i][2], fechasNacUsuarios[i][1], fechasNacUsuarios[i][0]),
						new Imagen("/usuarios/" + usr[0] + ".png"), usr[5]);
			} else {
				// Cargo Proveedores
				iuser.altaProveedor(usr[0], usr[1], usr[2], usr[3], usr[4],
						LocalDate.of(fechasNacUsuarios[i][2], fechasNacUsuarios[i][1], fechasNacUsuarios[i][0]),
						new Imagen("/usuarios/" + usr[0] + ".png"), usr[6], usr[7]);
			}
		}

		// Seguidores

		for (String[] seg : seguidoresYSeguidos) {
			iuser.seguirODejarDeSeguirUsuario(seg[0], seg[1]);
		}

		// Altas de actividades
		for (int i = 0; i < datosActividadesStrings.length; i++) {
			String[] datosAct = datosActividadesStrings[i];
			int[] datosInt = datosActividadesInteger[i];
			String[] categorias = actividadesCategorias[i];

			if (actividadesPersistidas.contains(datosAct[0]))
				continue;

			try {
				icat.altaActividadTuristica(datosAct[4], datosAct[3], datosAct[0], datosAct[1], datosInt[0],
						datosInt[1], datosAct[2], LocalDate.of(datosInt[4], datosInt[3], datosInt[2]),
						new Imagen("/actividades/" + datosAct[0] + ".png"), Arrays.asList(categorias),
						videosActividades[i]);
			} catch (ActividadTuristicaYaRegistradaException e) {
				// Actividad posiblemente en persistencia
			}
		}

		// Clasificacion de actividades
		for (String act : actividadesConfirmadas) {
			if (actividadesPersistidas.contains(act))
				continue;
			icat.cambiarEstadoDeActividadTuristica(act, EstadoActividadTuristica.ACEPTADA);
		}

		for (String act : actividadesRechazadas)
			icat.cambiarEstadoDeActividadTuristica(act, EstadoActividadTuristica.RECHAZADA);

		// Favoritos
		for (String[] fav : actFav) {
			if (actividadesPersistidas.contains(fav[1]))
				continue;
			iuser.agregarOEliminarActividadDeFavoritos(fav[0], fav[1]);
		}

		List<String> salidasPersistidas = new ArrayList<String>();

		// Altas de salidas
		for (int i = 0; i < datosSalidasStrings.length; i++) {
			var salStr = datosSalidasStrings[i];
			var salInt = datosSalidasInteger[i];
			Imagen img = null;

			if (actividadesPersistidas.contains(salStr[0])) {
				salidasPersistidas.add(salStr[1]);
				continue;
			}

			if (salidaTieneImg[i])
				img = new Imagen("/salidas/" + salStr[1] + ".png");

			icat.altaSalidaTuristica(salStr[0], salStr[1],
					LocalDateTime.of(salInt[2], salInt[1], salInt[0], salInt[3], 0),
					LocalDate.of(salInt[7], salInt[6], salInt[5]), salStr[2], salInt[4], img);
		}

		// Altas de paquetes
		for (int i = 0; i < datosPaquetesString.length; i++) {
			String[] datosPaq = datosPaquetesString[i];
			int[] datosPaqInt = datosPaquetesInteger[i];

			ipack.altaPaquete(datosPaq[0], datosPaq[1], datosPaqInt[0], datosPaqInt[1],
					LocalDate.of(datosPaqInt[4], datosPaqInt[3], datosPaqInt[2]),
					(i == 3) ? null : new Imagen("/paquetes/" + datosPaq[0] + ".png"));
		}

		// Agregar Actividad a Paquete
		for (int i = 0; i < asigPaqueteAct.length; i++) {
			ipack.agregarActividadAPaquete(asigPaqueteAct[i][1], asigPaqueteAct[i][0]);
		}

		// Compras de paquetes
		for (int i = 0; i < asigPaqueteTurista.length; i++) {
			var datos = datosCompras[i];
			ipack.comprarPaquete(asigPaqueteTurista[i][0], asigPaqueteTurista[i][1], datos[0],
					LocalDate.of(datos[3], datos[2], datos[1]));
		}

		// Inscripciones
		for (int i = 0; i < datosInscripcion.length; i++) {
			var datosStr = datosInscripcion[i];
			var datosInt = datosIntegerInscripcion[i];

			if (salidasPersistidas.contains(datosStr[0]))
				continue;

			icat.altaInscripcionSalidaTuristica(datosStr[0], datosStr[1], datosInt[0],
					LocalDate.of(datosInt[4], datosInt[3], datosInt[2]), datosStr[2]);
		}

		// icat.altaActividadTuristica("washington", "San José", "Iglesia", "a
		// misa los domingos...",
		// 3, 10, "San José de Mayo", LocalDate.of(2022, 10, 10), null, null ,
		// null) ;
		// icat.cambiarEstadoDeActividadTuristica("Iglesia",
		// EstadoActividadTuristica.ACEPTADA);
		// icat.cambiarEstadoDeActividadTuristica("Iglesia",
		// EstadoActividadTuristica.FINALIZADA);
	}
}
