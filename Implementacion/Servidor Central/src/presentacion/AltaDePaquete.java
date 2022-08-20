package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;

public class AltaDePaquete extends JInternalFrame{
	private JTextField txtPepe;
	private JTextField textField;
	public AltaDePaquete() {
		getContentPane().setLayout(null);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos del Paquete a registrar.");
		lblIngreseLosDatos.setBounds(12, 20, 318, 15);
		getContentPane().add(lblIngreseLosDatos);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 50, 70, 15);
		getContentPane().add(lblNombre);
		
		JLabel lblPeriodoDeValidez = new JLabel("Periodo de validez:");
		lblPeriodoDeValidez.setBounds(12, 80, 144, 15);
		getContentPane().add(lblPeriodoDeValidez);
		
		JLabel lblDescuento = new JLabel("Descuento:");
		lblDescuento.setBounds(12, 110, 151, 15);
		getContentPane().add(lblDescuento);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(12, 140, 101, 15);
		getContentPane().add(lblDescripcion);
		
		txtPepe = new JTextField();
		txtPepe.setText("Pepe");
		txtPepe.setBounds(75, 48, 114, 19);
		getContentPane().add(txtPepe);
		txtPepe.setColumns(10);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(153, 78, 49, 20);
		getContentPane().add(spinner);
		
		JLabel lbldias = new JLabel("(Dias)");
		lbldias.setBounds(206, 80, 70, 15);
		getContentPane().add(lbldias);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(100, 108, 56, 20);
		getContentPane().add(spinner_1);
		
		JLabel label = new JLabel("%");
		label.setBounds(163, 110, 70, 15);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(12, 160, 416, 106);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(12, 278, 117, 25);
		getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(311, 278, 117, 25);
		getContentPane().add(btnCancelar);
	}
}
