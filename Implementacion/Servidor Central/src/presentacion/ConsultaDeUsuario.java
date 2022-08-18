package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;

import logica.controladores.IControladorUsuario;

import javax.swing.JTextPane;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultaDeUsuario extends JInternalFrame {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaDeUsuario frame = new ConsultaDeUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultaDeUsuario() {
        ArrayList<String> usuarios = IControladorUsuario.obtenerIdUsuarios();
		
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consultar un Usuario");
        setBounds(30, 30, 400, 280);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        
        JPanel panel_principal = new JPanel();
        getContentPane().add(panel_principal);
        panel_principal.setLayout(new BoxLayout(panel_principal, BoxLayout.Y_AXIS));
        
        JPanel panel_eleccion = new JPanel();
        panel_principal.add(panel_eleccion);
        panel_eleccion.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JLabel lblNewLabel_1 = new JLabel("Elija un usuario:");
        panel_eleccion.add(lblNewLabel_1);
        JComboBox comboBox_1 = new JComboBox(usuarios.toArray());
        

        panel_eleccion.add(comboBox_1);
        
        
        
        JPanel panel_consulta = new JPanel();
        panel_principal.add(panel_consulta);
        
        JPanel panel_izquierda = new JPanel();
        panel_izquierda.setVisible(false);
        panel_consulta.add(panel_izquierda);
        panel_izquierda.setLayout(new BoxLayout(panel_izquierda, BoxLayout.Y_AXIS));
        
        JPanel panel_mostrar_datos = new JPanel();
        
        panel_izquierda.add(panel_mostrar_datos);
        panel_mostrar_datos.setLayout(new BoxLayout(panel_mostrar_datos, BoxLayout.Y_AXIS));
        
        JPanel panel_3 = new JPanel();
        panel_mostrar_datos.add(panel_3);
        panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
        
        JLabel lblNewLabel = new JLabel("Nickname: ");
        panel_3.add(lblNewLabel);
        
        JTextArea txtrErgsesss = new JTextArea();
        txtrErgsesss.setEditable(false);
        txtrErgsesss.setText("...");
        panel_3.add(txtrErgsesss);
        
        JPanel panel_4 = new JPanel();
        panel_mostrar_datos.add(panel_4);
        panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
        
        JLabel lblNewLabel_2 = new JLabel("Nombre: ");
        panel_4.add(lblNewLabel_2);
        
        JTextArea txtrErgsesss_1 = new JTextArea();
        txtrErgsesss_1.setText("...");
        txtrErgsesss_1.setEditable(false);
        panel_4.add(txtrErgsesss_1);
        
        JPanel panel_5 = new JPanel();
        panel_mostrar_datos.add(panel_5);
        panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
        
        JLabel lblNewLabel_2_1 = new JLabel("Apellido: ");
        panel_5.add(lblNewLabel_2_1);
        
        JTextArea txtrErgsesss_1_1 = new JTextArea();
        txtrErgsesss_1_1.setText("...");
        txtrErgsesss_1_1.setEditable(false);
        panel_5.add(txtrErgsesss_1_1);
        
        JPanel panel_6 = new JPanel();
        panel_mostrar_datos.add(panel_6);
        panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));
        
        JLabel lblNewLabel_3 = new JLabel("Correo: ");
        panel_6.add(lblNewLabel_3);
        
        JTextArea txtrErgsesss_1_1_1 = new JTextArea();
        txtrErgsesss_1_1_1.setText("...");
        txtrErgsesss_1_1_1.setEditable(false);
        panel_6.add(txtrErgsesss_1_1_1);
        
        JPanel panel_7 = new JPanel();
        panel_mostrar_datos.add(panel_7);
        panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));
        
        JLabel lblNewLabel_4 = new JLabel("Fecha de Nacimiento: ");
        panel_7.add(lblNewLabel_4);
        
        JTextArea txtrErgsesss_1_1_1_1 = new JTextArea();
        txtrErgsesss_1_1_1_1.setText("...");
        txtrErgsesss_1_1_1_1.setEditable(false);
        panel_7.add(txtrErgsesss_1_1_1_1);
        
        JPanel panel_3_1 = new JPanel();
        panel_mostrar_datos.add(panel_3_1);
        panel_3_1.setLayout(new BoxLayout(panel_3_1, BoxLayout.X_AXIS));
        
        JLabel lblTipo = new JLabel("Tipo: ");
        panel_3_1.add(lblTipo);
        
        JTextArea txtrErgsesss_2 = new JTextArea();
        txtrErgsesss_2.setText("...");
        txtrErgsesss_2.setEditable(false);
        panel_3_1.add(txtrErgsesss_2);
        
        JPanel panel_derecha = new JPanel();
        panel_consulta.add(panel_derecha);
        panel_derecha.setLayout(new BoxLayout(panel_derecha, BoxLayout.Y_AXIS));
        
        JPanel casos = new JPanel();
        
        // HACER VISIBLE CUANDO SE SELECCIONE UN USUARIO
        casos.setVisible(false);
        panel_derecha.add(casos);
        
        casos.setLayout(new BoxLayout(casos, BoxLayout.Y_AXIS));
        
        JPanel proveedor_panel = new JPanel();
        proveedor_panel.setVisible(false);
        casos.add(proveedor_panel);
        proveedor_panel.setLayout(new BoxLayout(proveedor_panel, BoxLayout.Y_AXIS));
        
        JLabel lblNewLabel_5 = new JLabel("Actividades y salidas: ");
        proveedor_panel.add(lblNewLabel_5);
        
        JTextArea textArea = new JTextArea();
        textArea.setText("...");
        proveedor_panel.add(textArea);
        
        JPanel turista_panel = new JPanel();
        turista_panel.setVisible(false);
        casos.add(turista_panel);
        turista_panel.setLayout(new BoxLayout(turista_panel, BoxLayout.Y_AXIS));
        
        JLabel lblNewLabel_6 = new JLabel("Salidas a las que se inscribió: ");
        turista_panel.add(lblNewLabel_6);
        
        JTextArea textArea_1 = new JTextArea();
        textArea_1.setText("...");
        turista_panel.add(textArea_1);
        
        
        
        
        
        comboBox_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// actualizar items pues se seleccionó un nuevo tiem del COMBO BOX
        		

                panel_izquierda.setVisible(true);
        		panel_consulta.setVisible(true);
        		casos.setVisible(true);
        		turista_panel.setVisible(true);
        	}
        });
    }
}
