package presentacion;

import java.awt.EventQueue;

import javax.swing.*;

import excepciones.DeparamentoYaRegistradoException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorUsuario;

import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Rectangle;

public class Principal {
	IControladorUsuario CUS;
	IControladorActividadTuristica CAD;

	private JFrame frmEstacionDeTrabajo;

	private AltaDeUsuario frmIntAltaUsuario;
	private ConsultaDeUsuario frmIntConsultaDeUsuario; //Lo dejo de esta forma asi queda igual al ejemplo y no tenemos que tocar el metodo initialize
	private AltaDeActividadTuristica frmIntAltaActividadTuristica;
	private AltaDePaquete frmIntAltaPaquete;
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

		frmIntAltaUsuario = new AltaDeUsuario(CUS);
		frmIntConsultaDeUsuario = new ConsultaDeUsuario(this);
		frmIntAltaActividadTuristica = new AltaDeActividadTuristica(CAD);
		frmIntAltaPaquete = new AltaDePaquete();
		frmIntAltaPaquete.setNormalBounds(new Rectangle(100, 100, 425, 350));

		frmIntAltaUsuario.setVisible(false);
		frmIntAltaPaquete.setVisible(false);
		frmEstacionDeTrabajo.getContentPane().setLayout(null);
		frmIntConsultaDeUsuario = new ConsultaDeUsuario(this);
		frmIntConsultaDeUsuario.setVisible(false);
		frmIntAltaActividadTuristica.setVisible(false);
		
		frmEstacionDeTrabajo.getContentPane().setLayout(null);
		frmEstacionDeTrabajo.getContentPane().add(frmIntAltaPaquete);
		frmEstacionDeTrabajo.getContentPane().add(frmIntConsultaDeUsuario);
		frmEstacionDeTrabajo.getContentPane().add(frmIntAltaUsuario);
		frmEstacionDeTrabajo.getContentPane().add(frmIntConsultaDeUsuario);
		frmEstacionDeTrabajo.getContentPane().add(frmIntAltaActividadTuristica);


		/*ToDo eliminar esto cuando tengamos los datos de prueba*/

		try {
			CAD.altaDepartamento("Montevideo", "Capital" , "www.algo.com");
		} catch (DeparamentoYaRegistradoException e){
			JOptionPane.showMessageDialog(frmEstacionDeTrabajo, "Error al crear el departamento hadcode en Principal", "Por dios...", JOptionPane.ERROR_MESSAGE);
		}


		/*------------------*/




	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEstacionDeTrabajo = new JFrame();
		frmEstacionDeTrabajo.setTitle("Estacion de trabajo");
		frmEstacionDeTrabajo.setBounds(100, 100, 700, 500);
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
		mnNewMenu_3.add(consultarActividadTuristicaJMenuItem);
		
		JMenuItem registrarSalidaJMenuItem = new JMenuItem("Registrar Salida Turística");
		mnNewMenu_3.add(registrarSalidaJMenuItem);
		
		JMenuItem consultarSalidaTuristicaJMenuItem = new JMenuItem("Consultar Salida Turística");
		mnNewMenu_3.add(consultarSalidaTuristicaJMenuItem);
		
		JMenuItem inscribirseASalidaTuristicaJMenuItem = new JMenuItem("Inscribirse a Salida Turistica");
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
		mnNewMenu.add(mntmNewMenuItem_1);
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

	/*
	 * Exponemos estos métodos para poder llamarlo desde otros módulos
	 */
	public void mostrarAltaDeUsuario() {
		
	}
}
