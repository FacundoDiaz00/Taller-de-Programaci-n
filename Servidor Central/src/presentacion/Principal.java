package presentacion;

import java.awt.EventQueue;

import javax.swing.*;

import excepciones.DeparamentoYaRegistradoException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorPaquete;
import logica.controladores.IControladorUsuario;

import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Rectangle;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Principal {
	IControladorUsuario CUS;
	IControladorActividadTuristica CAD;
	IControladorPaquete CP;

	private JFrame frmEstacionDeTrabajo;

	private AltaDeUsuario frmIntAltaUsuario;
	private ConsultaDeUsuario frmIntConsultaDeUsuario; //Lo dejo de esta forma asi queda igual al ejemplo y no tenemos que tocar el metodo initialize
	private AltaDeActividadTuristica frmIntAltaActividadTuristica;
	private ConsultaDeActividadTuristica frmIntConsultaDeActividadTuristica;
	private AltaDePaquete frmIntAltaPaquete;
	private AgregarActividadAPaquete frmIntAgregarActividadAPaquete;
	private AltaDeSalidaTuristica frmIntAltaSalidaTuristica;
	private ConsultaDeSalidaTuristica	frmIntConsultaDeSalidaTuristica;
	private InscribirseASalidaTurística frmInscribirseASalidaTurística;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmEstacionDeTrabajo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
        Fabrica fabrica = Fabrica.getInstancia();
        CUS = fabrica.getIControladorUsuario();
		CAD = fabrica.getIControladorActividadTuristica();
		CP = fabrica.getIControladorPaquete();
		
		frmIntConsultaDeUsuario = new ConsultaDeUsuario(this, CUS);
		frmIntAltaSalidaTuristica = new AltaDeSalidaTuristica(CAD);
		frmIntAltaActividadTuristica = new AltaDeActividadTuristica(CAD);
		frmIntConsultaDeActividadTuristica = new ConsultaDeActividadTuristica(this, CAD);
		frmInscribirseASalidaTurística = new InscribirseASalidaTurística(CAD, CUS);
		frmIntConsultaDeSalidaTuristica = new ConsultaDeSalidaTuristica(CAD);
		frmIntAltaUsuario = new AltaDeUsuario(CUS);
		frmIntAltaPaquete = new AltaDePaquete(CP);
		frmIntAgregarActividadAPaquete = new AgregarActividadAPaquete(CP, CAD);
		frmIntAltaPaquete.setNormalBounds(new Rectangle(100, 100, 425, 350));

		frmEstacionDeTrabajo.getContentPane().setLayout(null);
		frmIntAltaUsuario.setVisible(false);
		frmIntAltaPaquete.setVisible(false);
		frmIntConsultaDeUsuario.setVisible(false);
		frmIntAltaActividadTuristica.setVisible(false);
		frmIntConsultaDeActividadTuristica.setVisible(false);
		frmIntAgregarActividadAPaquete.setVisible(false);
		frmInscribirseASalidaTurística.setVisible(false);
		frmIntAltaSalidaTuristica.setVisible(false);
		frmIntConsultaDeSalidaTuristica.setVisible(false);
		
		frmEstacionDeTrabajo.getContentPane().setLayout(null);
		frmEstacionDeTrabajo.getContentPane().add(frmIntAltaPaquete);
		frmEstacionDeTrabajo.getContentPane().add(frmIntConsultaDeUsuario);
		frmEstacionDeTrabajo.getContentPane().add(frmIntAltaUsuario);
		frmEstacionDeTrabajo.getContentPane().add(frmIntConsultaDeUsuario);
		frmEstacionDeTrabajo.getContentPane().add(frmIntAltaActividadTuristica);
		frmEstacionDeTrabajo.getContentPane().add(frmIntConsultaDeActividadTuristica);
		frmEstacionDeTrabajo.getContentPane().add(frmIntAgregarActividadAPaquete);
		frmEstacionDeTrabajo.getContentPane().add(frmInscribirseASalidaTurística);
		frmEstacionDeTrabajo.getContentPane().add(frmIntAltaSalidaTuristica);
		frmEstacionDeTrabajo.getContentPane().add(frmIntConsultaDeSalidaTuristica);
		
		frmIntAltaUsuario.setVisible(false);
		frmEstacionDeTrabajo.getContentPane().add(frmIntAltaUsuario);







	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEstacionDeTrabajo = new JFrame();
		frmEstacionDeTrabajo.setTitle("Estacion de trabajo");
		frmEstacionDeTrabajo.setBounds(100, 100, 940, 600);
		frmEstacionDeTrabajo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmEstacionDeTrabajo.setJMenuBar(menuBar);
		
		JMenu sistemaMenu = new JMenu("Sistema");
		menuBar.add(sistemaMenu);
		frmEstacionDeTrabajo.getContentPane().setLayout(new BoxLayout(frmEstacionDeTrabajo.getContentPane(), BoxLayout.X_AXIS));
		
		JDesktopPane desktopPane = new JDesktopPane();
		frmEstacionDeTrabajo.getContentPane().add(desktopPane);
		
		
		JMenuItem salirJMenuItem = new JMenuItem("Salir");
		salirJMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmEstacionDeTrabajo.setVisible(false);
				frmEstacionDeTrabajo.dispose();
			}
		});
		salirJMenuItem.addMouseListener(new MouseAdapter() {
		});
		salirJMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
		sistemaMenu.add(salirJMenuItem);
		
		JMenu mnUsuario = new JMenu("Usuarios");
		menuBar.add(mnUsuario);
		
		JMenuItem registrarUsuarioJMenuItem = new JMenuItem("Registrar Usuario");
		registrarUsuarioJMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmIntAltaUsuario.setVisible(true);
			}
		});
		mnUsuario.add(registrarUsuarioJMenuItem);
		
		JMenuItem consultarUsuarioJMenuItem = new JMenuItem("Consultar Usuario");
		consultarUsuarioJMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmIntConsultaDeUsuario.setVisible(true);
			}
		
		});
		mnUsuario.add(consultarUsuarioJMenuItem);
		
		JMenuItem modificarUsuarioJMenuItem = new JMenuItem("Modificar Usuario");
		mnUsuario.add(modificarUsuarioJMenuItem);
		
		JMenu mnNewMenu_3 = new JMenu("Actividades/Salidas turisticas");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem registrarActividadJMenuItem = new JMenuItem("Registrar Actividad Turística");
		registrarActividadJMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmIntAltaActividadTuristica.setVisible(true);
			}
		});
		mnNewMenu_3.add(registrarActividadJMenuItem);
		
		JMenuItem consultarActividadTuristicaJMenuItem = new JMenuItem("Consultar Actividad Turística");
		consultarActividadTuristicaJMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmIntConsultaDeActividadTuristica.setVisible(true);
			}
		});
		mnNewMenu_3.add(consultarActividadTuristicaJMenuItem);
		
		JMenuItem registrarSalidaJMenuItem = new JMenuItem("Registrar Salida Turística");
		registrarSalidaJMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmIntAltaSalidaTuristica.setVisible(true);
			}
		});
		mnNewMenu_3.add(registrarSalidaJMenuItem);
		
		JMenuItem consultarSalidaTuristicaJMenuItem = new JMenuItem("Consultar Salida Turística");
		consultarSalidaTuristicaJMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmIntConsultaDeSalidaTuristica.setVisible(true);
			}
		});
		mnNewMenu_3.add(consultarSalidaTuristicaJMenuItem);
		
		
		JMenuItem inscribirseASalidaTuristicaJMenuItem = new JMenuItem("Inscribirse a Salida Turistica");
		inscribirseASalidaTuristicaJMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmInscribirseASalidaTurística.setVisible(true);
			}
		});
		mnNewMenu_3.add(inscribirseASalidaTuristicaJMenuItem);
		
		JMenu mnNewMenu = new JMenu("Paquetes");
		menuBar.add(mnNewMenu);
		
		JMenuItem altaPaqueteJMenuItem = new JMenuItem("Registrar Paquete");
		altaPaqueteJMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmIntAltaPaquete.setVisible(true);
			}
		});
		mnNewMenu.add(altaPaqueteJMenuItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Consulta de Paquete");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Agregar Actividad Turística a Paquete");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmIntAgregarActividadAPaquete.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu datosInicoMenu = new JMenu("Datos de inicio");
		menuBar.add(datosInicoMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cargar datos de prueba");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatosDePrueba();
			}
		});
		datosInicoMenu.add(mntmNewMenuItem);
	}

	/*
	 * Exponemos estos métodos para poder llamarlo desde otros módulos
	 * Se puede pasar null como parámetro y que no se seleccione nada desde antes
	 */
	public void mostrarConsultaDeUsuario(String nickname) {
		if (nickname != null) {
			frmIntConsultaDeUsuario.seleccionYaHecha(nickname);			
		}
		frmIntConsultaDeUsuario.setVisible(true);
	}
	
	public void mostrarConsultaDeActividadTuristica(String nombreActividad) {
		if (nombreActividad != null) {
			frmIntConsultaDeActividadTuristica.seleccionYaHecha(nombreActividad);			
		}
		frmIntConsultaDeActividadTuristica.setVisible(true);
	}
	
	public void mostrarConsultaDeSalidaTuristica(String nombreSalida) {
		if (nombreSalida != null) {
			// FIXME: descomentar e implementar las operaciones basandose en lo de arriba
			// frmIntConsultaDeSalidaTuristica.seleccionYaHecha(nombreSalida);			
		}
		// frmIntConsultaDeSalidaTuristica.setVisible(true);
	}
	
	public void mostrarConsultaDePaquete(String nombrePaquete) {
		if (nombrePaquete != null) {
			// FIXME: descomentar e implementar las operaciones basandose en lo de arriba
			// frmIntConsultaDePaquete.seleccionYaHecha(nombrePaquete);			
		}
		// frmIntConsultaDePaquete.setVisible(true);
	}
	
		
	private void cargarDatosDePrueba() {
		IControladorActividadTuristica icat = Fabrica.getInstancia().getIControladorActividadTuristica();
		IControladorUsuario iuser = Fabrica.getInstancia().getIControladorUsuario();
		IControladorPaquete ipack = Fabrica.getInstancia().getIControladorPaquete();
		try{

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
			iuser.altaTurista("lachiqui", "Rosa María", "Martínez", "mirtha.legrand.ok@hotmail.com.ar", LocalDate.of(1927, 2, 23),"argentina");
			iuser.altaTurista("isabelita", "Elizabeth", "Windsor", "isabelita@thecrown.co.uk", LocalDate.of(1926, 4, 21),"inglesa");
			iuser.altaTurista("anibal", "Aníbal", "Lecter", "anibal@fing.edu.uy", LocalDate.of(1937, 12, 31),"lituana");
			iuser.altaTurista("waston", "Emma", "Waston", "e.waston@gmail.com", LocalDate.of(1990, 4, 15),"inglesa");
			iuser.altaTurista("elelvis", "Elvis", "Lacio", "suavemente@hotmail.com", LocalDate.of(1971, 7, 30),"estadounidense");
			iuser.altaTurista("eleven11", "Eleven", "Once", "eleven11@gmail.com", LocalDate.of(2004, 2, 19),"española");
			iuser.altaTurista("bobesponja", "Bob", "Esponja", "bobesponja@nickelodeon.com", LocalDate.of(1999, 5, 1),"japonesa");
			iuser.altaTurista("tony", "Antonio", "Pacheco", "eltony@manya.org.uy", LocalDate.of(1976, 4, 11),"uruguaya");
			iuser.altaTurista("chino", "Álvaro", "Recoba", "chino@trico.org.uy", LocalDate.of(1976, 3, 17),"uruguaya");
			iuser.altaTurista("mastropiero", "Johann Sebastian", "Mastropiero", "johann.sebastian@gmail.com", LocalDate.of(1922, 2, 7),"austríaca");

			//Cargo Proveedores
			iuser.altaProveedor("washington", "Washington", "Rocha", "washington@turismorocha.gub.uy",
					"Hola! me llamo Washington y soy el encargado del portal de turismo del departamento de Rocha Uruguay","http://turismorocha.gub.uy/", LocalDate.of(1970,9,14));
			iuser.altaProveedor("eldiez", "Pablo", "Bengoechea", "eldiez@socfomturriv.org.uy",
					"Pablo es el presidente de la Sociedad de Fomento Turístico de Rivera (conocida como Socfomturriv)","http://wwww.socfomturriv.org.uy/", LocalDate.of(1965,6,27));
			iuser.altaProveedor("meche", "Mercedes", "Venn", "meche@colonia.gub.uy",
					"Departamento de Turismo del Departamento de Colonia","https://colonia.gub.uy/turismo/", LocalDate.of(1990,12,31));

			//Actividades
			icat.altaActividadTuristica("washington" , "Rocha", "Degusta",
					"Festival gastronómico de productos locales en Rocha", 3, 800, "Rocha", LocalDate.of(2022,7,20));
			icat.altaActividadTuristica("washington" , "Rocha", "Teatro con Sabores",
					"En el mes aniversario del Club Deportivo Unión de Rocha te invitamos a una merienda deliciosa.", 3, 500, "Rocha", LocalDate.of(2022,7,21));
			icat.altaActividadTuristica("meche" , "Colonia", "Tour por Colonia del Sacramento",
					"Con guía especializado y en varios idiomas. Varios circuitos posibles.", 2, 400, "Colonia del Sacramento", LocalDate.of(2022,8,1));
			icat.altaActividadTuristica("meche" , "Colonia", "Almuerzo en el Real de San Carlos",
					"Restaurante en la renovada Plaza de Toros con men´u internacional", 2, 800, "Colonia del Sacramento", LocalDate.of(2022,8,1));
			icat.altaActividadTuristica("eldiez" , "Rivera", "Almuerzo en Valle del Lunarejo",
					"Almuerzo en la Posada con ticket fijo. Menú que incluye bebida y postre casero.", 2, 300, "Tranqueras", LocalDate.of(2022,8,1));
			icat.altaActividadTuristica("eldiez" , "Rivera", "Cabalgata en Valle del Lunarejo",
					"Cabalgata por el área protegida. Varios recorridos para elegir.", 2, 150, "Tranqueras", LocalDate.of(2022,8,1));

			//Salidas
			icat.altaSalidaTuristica("Degusta", "Degusta Agosto", LocalDateTime.of(2022,8,20,17,0),
					LocalDate.of(2022,7,21), "Sociedad Agropecuaria de Rocha", 20);
			icat.altaSalidaTuristica("Degusta", "Degusta Setiembre", LocalDateTime.of(2022,9,3,17,0),
					LocalDate.of(2022,7,22), "Sociedad Agropecuaria de Rocha", 20);
			icat.altaSalidaTuristica("Teatro con Sabores", "Teatro con Sabores 1", LocalDateTime.of(2022,9,4,18,0),
					LocalDate.of(2022,7,23), "Club Deportivo Unión", 30);
			icat.altaSalidaTuristica("Teatro con Sabores", "Teatro con Sabores 2", LocalDateTime.of(2022,9,11,18,0),
					LocalDate.of(2022,7,23), "Club Deportivo Unión", 30);
			icat.altaSalidaTuristica("Tour por Colonia del Sacramento", "Tour Colonia del Sacramento 11-09", LocalDateTime.of(2022,9,11,10,0),
					LocalDate.of(2022,8,5), "Encuentro en la base del Faro", 5);
			icat.altaSalidaTuristica("Tour por Colonia del Sacramento", "Tour Colonia del Sacramento 18-09", LocalDateTime.of(2022,9,18,10,0),
					LocalDate.of(2022,8,5), "Encuentro en la base del Faro", 5);
			icat.altaSalidaTuristica("Almuerzo en el Real de San Carlos", "Almuerzo 1", LocalDateTime.of(2022,9,18,12,0),
					LocalDate.of(2022,8,4), "Restaurante de la Plaza de Toros", 5);
			icat.altaSalidaTuristica("Almuerzo en el Real de San Carlos", "Almuerzo 2", LocalDateTime.of(2022,9,25,12,0),
					LocalDate.of(2022,8,4), "Restaurante de la Plaza de Toros", 5);
			icat.altaSalidaTuristica("Almuerzo en Valle del Lunarejo", "Almuerzo 3", LocalDateTime.of(2022,9,10,12,0),
					LocalDate.of(2022,8,15), "Posada Del Lunarejo", 4);
			icat.altaSalidaTuristica("Almuerzo en Valle del Lunarejo", "Almuerzo 4", LocalDateTime.of(2022,9,11,12,0),
					LocalDate.of(2022,8,15), "Posada Del Lunarejo", 4);
			icat.altaSalidaTuristica( "Cabalgata en Valle del Lunarejo","Cabalgata 1", LocalDateTime.of(2022,9,10,16,0),
					LocalDate.of(2022,8,15), "Posada Del Lunarejo", 4);
			icat.altaSalidaTuristica( "Cabalgata en Valle del Lunarejo","Cabalgata 2", LocalDateTime.of(2022,9,11,16,0),
					LocalDate.of(2022,8,15), "Posada Del Lunarejo", 4);


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
			ipack.altaPaquete("Disfrutar Rocha", "Actividades para hacer en familia y disfrutar arte y gastronomía", 60, 20,LocalDate.of(2022, 8, 10)); //todo falta la fecha de alta
			ipack.altaPaquete("Un día en Colonia", "Paseos por el casco histórico y se puede terminar con Almuerzo en la Plaza de Toro", 45, 15, LocalDate.of(2022, 8, 10)); //todo falta la fecha de alta

			//Agregar Actividad a Paquete
			ipack.agregarActividadAPaquete("Degusta", "Disfrutar Rocha");
			ipack.agregarActividadAPaquete("Teatro con Sabores", "Disfrutar Rocha");
			ipack.agregarActividadAPaquete("Tour por Colonia del Sacramento", "Un día en Colonia");
			ipack.agregarActividadAPaquete("Almuerzo en el Real de San Carlos", "Un día en Colonia");

			JOptionPane.showMessageDialog(null, "Los datos de prueba han sido cargado con exito", "Error", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error a la hora de cargar los datos de prueba." +
					" Sugerimos que esta operación sea la primera que se haga cuando se ejecuta el sistema.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}
}







