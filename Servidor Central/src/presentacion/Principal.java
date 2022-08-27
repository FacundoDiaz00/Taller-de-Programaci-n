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

public class Principal {
	IControladorUsuario CUS;
	IControladorActividadTuristica CAD;

	private JFrame frmEstacionDeTrabajo;

	private AltaDeUsuario frmIntAltaUsuario;
	private ConsultaDeUsuario frmIntConsultaDeUsuario; //Lo dejo de esta forma asi queda igual al ejemplo y no tenemos que tocar el metodo initialize
	private AltaDeActividadTuristica frmIntAltaActividadTuristica;
	private ConsultaDeActividadTuristica frmIntConsultaDeActividadTuristica;
	private AltaDePaquete frmIntAltaPaquete;
	private AgregarActividadAPaquete frmIntAgregarActividadAPaquete;
	private AltaDeSalidaTuristica frmIntAltaSalidaTuristica;

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
		frmIntConsultaDeUsuario = new ConsultaDeUsuario(this);
		frmIntAltaSalidaTuristica = new AltaDeSalidaTuristica();
		frmIntAltaActividadTuristica = new AltaDeActividadTuristica(CAD);
		frmIntConsultaDeActividadTuristica = new ConsultaDeActividadTuristica(CAD);
		frmInscribirseASalidaTurística = new InscribirseASalidaTurística();
		frmIntAltaUsuario = new AltaDeUsuario(CUS);
		frmIntAltaPaquete = new AltaDePaquete();
		frmIntAgregarActividadAPaquete = new AgregarActividadAPaquete();
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

		frmIntAltaUsuario = new AltaDeUsuario(CUS);
		
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
	
	private void cargarDatosDePrueba() {
		IControladorActividadTuristica icat = Fabrica.getInstancia().getIControladorActividadTuristica();
		IControladorUsuario iuser = Fabrica.getInstancia().getIControladorUsuario();
		IControladorPaquete ipack = Fabrica.getInstancia().getIControladorPaquete();

		//Cargo los departamentos

		//Cargo los usuarios

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



		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error a la hora de cargar los datos de prueba." +
					" Sugerimos que esta operación sea la primera que se haga cuando se ejecuta el sistema.", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
}







