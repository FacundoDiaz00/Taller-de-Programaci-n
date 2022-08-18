package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;

public class AltaDeUsuario extends JInternalFrame {
	private JTextField textField;

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
	public AltaDeUsuario() {
		setTitle("Registrar Usuario");
		setBounds(100, 100, 409, 418);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel inputs = new JPanel();
		getContentPane().add(inputs, BorderLayout.NORTH);
		inputs.setLayout(new BoxLayout(inputs, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		inputs.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel labelNinkName = new JLabel("NickName");
		labelNinkName.setSize(100, 100);
		panel.add(labelNinkName, BorderLayout.WEST);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		inputs.add(panel_1);
		
		JPanel botonera = new JPanel();
		botonera.setBackground(Color.RED);
		getContentPane().add(botonera, BorderLayout.SOUTH);

	}

}
