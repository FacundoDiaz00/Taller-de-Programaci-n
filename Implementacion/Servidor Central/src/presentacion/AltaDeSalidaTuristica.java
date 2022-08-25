package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JButton;

public class AltaDeSalidaTuristica extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Create the frame.
	 */
	public AltaDeSalidaTuristica() {
		setTitle("Registrar Salida Turistica.");
		setBounds(100, 100, 428, 391);
		getContentPane().setLayout(null);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos de la Salida Turistica a Registrar.");
		lblIngreseLosDatos.setBounds(12, 12, 385, 15);
		getContentPane().add(lblIngreseLosDatos);
		
		JLabel lblDepart = new JLabel("Departamento :");
		lblDepart.setBounds(12, 50, 112, 15);
		getContentPane().add(lblDepart);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(150, 45, 175, 24);
		getContentPane().add(comboBox);
		
		JLabel lblActividadTurisica = new JLabel("Actividad Turisica:");
		lblActividadTurisica.setBounds(12, 85, 128, 15);
		getContentPane().add(lblActividadTurisica);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(150, 80, 175, 24);
		getContentPane().add(comboBox_1);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 120, 70, 15);
		getContentPane().add(lblNombre);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(12, 190, 47, 15);
		getContentPane().add(lblHora);
		
		JLabel lblFecha = new JLabel("Fecha de la Salida :");
		lblFecha.setBounds(12, 155, 138, 15);
		getContentPane().add(lblFecha);
		
		JLabel lblLugar = new JLabel("Lugar:");
		lblLugar.setBounds(12, 225, 70, 15);
		getContentPane().add(lblLugar);
		
		JLabel lblCantidadMaximaDe = new JLabel("Cantidad Maxima de turistas:");
		lblCantidadMaximaDe.setBounds(12, 259, 208, 15);
		getContentPane().add(lblCantidadMaximaDe);
		
		textField = new JTextField();
		textField.setBounds(157, 153, 30, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("/");
		label.setBounds(190, 155, 4, 15);
		getContentPane().add(label);
		
		textField_1 = new JTextField();
		textField_1.setBounds(199, 153, 30, 19);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_1 = new JLabel("/");
		label_1.setBounds(231, 155, 4, 15);
		getContentPane().add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(238, 153, 60, 19);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblddmmyyyy = new JLabel("(dd/mm/yyyy)");
		lblddmmyyyy.setBounds(303, 155, 112, 15);
		getContentPane().add(lblddmmyyyy);
		
		textField_3 = new JTextField();
		textField_3.setBounds(80, 118, 245, 19);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(66, 185, 58, 24);
		getContentPane().add(comboBox_2);
		
		textField_4 = new JTextField();
		textField_4.setBounds(66, 223, 163, 19);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(227, 257, 60, 20);
		getContentPane().add(spinner);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(12, 320, 117, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(280, 320, 117, 25);
		getContentPane().add(btnCancelar);

	}
}
