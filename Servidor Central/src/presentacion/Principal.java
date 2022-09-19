package presentacion;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorPaquete;
import logica.controladores.IControladorUsuario;

public class Principal {
	private IControladorUsuario CUS;
	private IControladorActividadTuristica CAD;
	private IControladorPaquete CP;

	private JFrame frmEstacionDeTrabajo;

	private AltaDeUsuario frmIntAltaUsuario;
	private ConsultaDeUsuario frmIntConsultaDeUsuario; // Lo dejo de esta forma
														// asi queda igual al
														// ejemplo y no tenemos
														// que tocar el metodo
														// initialize
	private AltaDeActividadTuristica frmIntAltaActividadTuristica;
	private ConsultaDeActividadTuristica frmIntConsultaDeActividadTuristica;
	private AltaDePaquete frmIntAltaPaquete;
	private AgregarActividadAPaquete frmIntAgregarActividadAPaquete;
	private AltaDeSalidaTuristica frmIntAltaSalidaTuristica;
	private ConsultaDeSalidaTuristica frmIntConsultaDeSalidaTuristica;
	private InscribirseASalidaTurística frmInscribirseASalidaTurística;

	private ModificarUsuario frmModificarUsuario;

	private ConsultaDePaquete frmIntConsultaDePaquete;

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
		frmModificarUsuario = new ModificarUsuario(CUS);
		frmIntAltaPaquete.setNormalBounds(new Rectangle(100, 100, 425, 350));
		frmIntConsultaDePaquete = new ConsultaDePaquete(this, CP);

		frmEstacionDeTrabajo.getContentPane().setLayout(null);
		frmIntAltaUsuario.setVisible(false);
		frmIntAltaPaquete.setVisible(false);
		frmIntConsultaDeUsuario.setVisible(false);
		frmIntAltaActividadTuristica.setVisible(false);
		frmIntConsultaDeActividadTuristica.setVisible(false);
		frmIntAgregarActividadAPaquete.setVisible(false);
		frmInscribirseASalidaTurística.setVisible(false);
		frmIntAltaSalidaTuristica.setVisible(false);
		frmIntConsultaDePaquete.setVisible(false);
		frmIntConsultaDeSalidaTuristica.setVisible(false);
		frmModificarUsuario.setVisible(false);

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
		frmEstacionDeTrabajo.getContentPane().add(frmIntConsultaDePaquete);
		frmEstacionDeTrabajo.getContentPane().add(frmIntConsultaDeSalidaTuristica);
		frmEstacionDeTrabajo.getContentPane().add(frmModificarUsuario);

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
		frmEstacionDeTrabajo.getContentPane()
				.setLayout(new BoxLayout(frmEstacionDeTrabajo.getContentPane(), BoxLayout.X_AXIS));

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
		modificarUsuarioJMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmModificarUsuario.setVisible(true);
			}
		});
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
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmIntConsultaDePaquete.setVisible(true);
			}
		});
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
	 * Exponemos estos métodos para poder llamarlo desde otros módulos Se puede
	 * pasar null como parámetro y que no se seleccione nada desde antes
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
			frmIntConsultaDeSalidaTuristica.seleccionYaHecha(nombreSalida);
		}
		frmIntConsultaDeSalidaTuristica.setVisible(true);
	}

	public void mostrarConsultaDePaquete(String nombrePaquete) {
		if (nombrePaquete != null) {
			frmIntConsultaDePaquete.seleccionYaHecha(nombrePaquete);
		}
		frmIntConsultaDePaquete.setVisible(true);
	}

	private void cargarDatosDePrueba() {
		try {
			Fabrica.getInstancia().getIControladorMaestro().generarDatosDePrueba();
			JOptionPane.showMessageDialog(null, "Los datos de prueba han sido cargado con exito", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Ha ocurrido un error a la hora de cargar los datos de prueba."
							+ " Sugerimos que esta operación sea la primera que se haga cuando se ejecuta el sistema.",
					"Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}
}
