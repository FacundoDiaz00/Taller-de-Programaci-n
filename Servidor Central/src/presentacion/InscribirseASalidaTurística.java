package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class InscribirseASalidaTurística extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InscribirseASalidaTurística frame = new InscribirseASalidaTurística();
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
	public InscribirseASalidaTurística() {
		setBounds(100, 100, 583, 318);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel botones = new JPanel();
		getContentPane().add(botones, BorderLayout.SOUTH);
		botones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton cancelarButton = new JButton("Cancelar");
		botones.add(cancelarButton);
		
		JButton aceptarButton = new JButton("Aceptar");
		botones.add(aceptarButton);
		
		JPanel elementos = new JPanel();
		getContentPane().add(elementos, BorderLayout.NORTH);
		elementos.setLayout(new BoxLayout(elementos, BoxLayout.Y_AXIS));
		
		JPanel departamentoPanel = new JPanel();
		elementos.add(departamentoPanel);
		departamentoPanel.setLayout(new BoxLayout(departamentoPanel, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("Departamento:");
		departamentoPanel.add(lblNewLabel);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		departamentoPanel.add(horizontalStrut);
		
		JComboBox comboBox = new JComboBox();
		departamentoPanel.add(comboBox);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		departamentoPanel.add(horizontalStrut_1);
		
		Component verticalStrut1 = Box.createVerticalStrut(10);
		elementos.add(verticalStrut1);
		
		JPanel actividadPanel = new JPanel();
		elementos.add(actividadPanel);
		actividadPanel.setLayout(new BoxLayout(actividadPanel, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_1 = new JLabel("Actividad Turistica:");
		actividadPanel.add(lblNewLabel_1);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		actividadPanel.add(horizontalStrut_2);
		
		JComboBox comboBox_1 = new JComboBox();
		actividadPanel.add(comboBox_1);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		actividadPanel.add(horizontalStrut_3);
		
		Component verticalStrut2 = Box.createVerticalStrut(10);
		elementos.add(verticalStrut2);
		
		JPanel infoSalida = new JPanel();
		infoSalida.setBorder(new TitledBorder(null, "Salidas turisticas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		elementos.add(infoSalida);
		infoSalida.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		infoSalida.add(panel, BorderLayout.WEST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8, BorderLayout.NORTH);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(40);
		panel_8.add(horizontalStrut_6);
		
		JLabel lblNewLabel_4 = new JLabel("Seleccione una salida");
		panel_8.add(lblNewLabel_4);
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(40);
		panel_8.add(horizontalStrut_7);
		
		JList list = new JList();
		panel_2.add(list, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		infoSalida.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		Component horizontalStrut_18 = Box.createHorizontalStrut(20);
		panel_3.add(horizontalStrut_18);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre");
		panel_3.add(lblNewLabel_3);
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		panel_3.add(horizontalStrut_8);
		
		textField = new JTextField();
		textField.setEditable(false);
		panel_3.add(textField);
		textField.setColumns(10);
		
		Component horizontalStrut_13 = Box.createHorizontalStrut(20);
		panel_3.add(horizontalStrut_13);
		
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		Component horizontalStrut_19 = Box.createHorizontalStrut(20);
		panel_4.add(horizontalStrut_19);
		
		JLabel lblNewLabel_5 = new JLabel("Lugar salida");
		panel_4.add(lblNewLabel_5);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		panel_4.add(horizontalStrut_9);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		panel_4.add(textField_1);
		textField_1.setColumns(10);
		
		Component horizontalStrut_14 = Box.createHorizontalStrut(20);
		panel_4.add(horizontalStrut_14);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
		
		Component horizontalStrut_20 = Box.createHorizontalStrut(20);
		panel_5.add(horizontalStrut_20);
		
		JLabel lblNewLabel_6 = new JLabel("Fecha alta");
		panel_5.add(lblNewLabel_6);
		
		Component horizontalStrut_10 = Box.createHorizontalStrut(20);
		panel_5.add(horizontalStrut_10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		panel_5.add(textField_2);
		textField_2.setColumns(10);
		
		Component horizontalStrut_15 = Box.createHorizontalStrut(20);
		panel_5.add(horizontalStrut_15);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));
		
		Component horizontalStrut_21 = Box.createHorizontalStrut(20);
		panel_6.add(horizontalStrut_21);
		
		JLabel lblNewLabel_7 = new JLabel("Capacidad de turistas");
		panel_6.add(lblNewLabel_7);
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(20);
		panel_6.add(horizontalStrut_11);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		panel_6.add(textField_3);
		textField_3.setColumns(10);
		
		Component horizontalStrut_16 = Box.createHorizontalStrut(20);
		panel_6.add(horizontalStrut_16);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));
		
		Component horizontalStrut_22 = Box.createHorizontalStrut(20);
		panel_7.add(horizontalStrut_22);
		
		JLabel lblNewLabel_8 = new JLabel("Fecha y hora de salida");
		panel_7.add(lblNewLabel_8);
		
		Component horizontalStrut_12 = Box.createHorizontalStrut(20);
		panel_7.add(horizontalStrut_12);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		panel_7.add(textField_4);
		textField_4.setColumns(10);
		
		Component horizontalStrut_17 = Box.createHorizontalStrut(20);
		panel_7.add(horizontalStrut_17);
		

		
		Component verticalStrut4 = Box.createVerticalStrut(10);
		elementos.add(verticalStrut4);

		
		JPanel turista = new JPanel();
		elementos.add(turista);
		turista.setLayout(new BoxLayout(turista, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_2 = new JLabel("Turista: ");
		turista.add(lblNewLabel_2);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		turista.add(horizontalStrut_4);
		
		JComboBox comboBox_2 = new JComboBox();
		turista.add(comboBox_2);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		turista.add(horizontalStrut_5);
		

		


		

	}

}
