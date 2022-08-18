package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;

import logica.controladores.IControladorUsuario;

import javax.swing.JTextPane;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;

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
		
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consultar un Usuario");
        setBounds(30, 30, 400, 280);
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1);
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
        
        JPanel panel_2 = new JPanel();
        panel_1.add(panel_2);
        panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        panel_2.add(lblNewLabel_1);
        
        JComboBox comboBox_1 = new JComboBox();
        Iterable<String> usuarios = IControladorUsuario.obtenerIdUsuarios();
        comboBox_1.setModel(new DefaultComboBoxModel());
        panel_2.add(comboBox_1);
        
        JScrollPane scrollPane = new JScrollPane();
        panel_1.add(scrollPane);
    }
}
