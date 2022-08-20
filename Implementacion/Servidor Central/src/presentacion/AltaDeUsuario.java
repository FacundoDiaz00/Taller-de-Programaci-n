package presentacion;

import java.awt.EventQueue;
import logica.controladores.IControladorUsuario;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class AltaDeUsuario extends JInternalFrame {
	
	
	private JTextField nombre;
	private JTextField nickname;
	private JTextField fnacimiento;
	private JTextField apellido;
	private JTextField correo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaDeUsuario frame = new AltaDeUsuario();
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
	public AltaDeUsuario(IControladorUsuario icu) {
		setTitle("Registrar Usuario");
		setBounds(100, 100, 409, 270);
		getContentPane().setLayout(null);
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
		
		JLabel lblNewLabel = new JLabel("Nickname:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(20, 11, 77, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(20, 35, 64, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Apellido:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(21, 61, 63, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		nombre = new JTextField();
		nombre.setBounds(131, 34, 258, 20);
		getContentPane().add(nombre);
		nombre.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Correo:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setBounds(0, 87, 77, 14);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Fecha nacimiento:");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1.setBounds(0, 110, 137, 14);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo de Usuario:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(0, 136, 127, 14);
		getContentPane().add(lblNewLabel_2);
		
		nickname = new JTextField();
		nickname.setColumns(10);
		nickname.setBounds(131, 9, 258, 20);
		getContentPane().add(nickname);
		
		fnacimiento = new JTextField();
		fnacimiento.setColumns(10);
		fnacimiento.setBounds(155, 109, 234, 20);
		getContentPane().add(fnacimiento);
		
		apellido = new JTextField();
		apellido.setColumns(10);
		apellido.setBounds(131, 59, 258, 20);
		getContentPane().add(apellido);
		
		correo = new JTextField();
		correo.setColumns(10);
		correo.setBounds(131, 84, 258, 20);
		getContentPane().add(correo);
		
		JComboBox tipoUsuario = new JComboBox();
		tipoUsuario.setBounds(155, 131, 212, 24);
		getContentPane().add(tipoUsuario);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(272, 201, 117, 25);
		getContentPane().add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
			}
		});
		btnCancelar.setBounds(10, 201, 117, 25);
		getContentPane().add(btnCancelar);
	    private void limpiarFormulario() {
	        textFieldNombre.setText("");
	        textFieldApellido.setText("");
	        textFieldCI.setText("");
	    }

	}
}
