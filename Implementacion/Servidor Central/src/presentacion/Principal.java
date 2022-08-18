package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal {

	private JFrame frmEstacionDeTrabajo;

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
		mnUsuario.add(registrarUsuarioJMenuItem);
		
		JMenuItem consultarUsuarioJMenuItem = new JMenuItem("Consultar Usuario");
		mnUsuario.add(consultarUsuarioJMenuItem);
		
		JMenuItem modificarUsuarioJMenuItem = new JMenuItem("Modificar Usuario");
		mnUsuario.add(modificarUsuarioJMenuItem);
		
		JMenu mnNewMenu_3 = new JMenu("Actividades/Salidas turisticas");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem registrarActividadJMenuItem = new JMenuItem("Registrar Actividad Turística");
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
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Registrar Paquete");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Consulta de Paquete");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Agregar Actividad Turística a Paquete");
		mnNewMenu.add(mntmNewMenuItem_1);
	}

}
