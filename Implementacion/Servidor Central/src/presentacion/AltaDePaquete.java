package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import logica.controladores.Fabrica;
import logica.controladores.IControladorPaquete;

import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaDePaquete extends JInternalFrame{
	private JTextField txtNombre;
	private JTextField descrp;
	public AltaDePaquete() {
		setTitle("Registrar Paquete de Actividades.");
		setBounds(100, 100, 461, 342);
		getContentPane().setLayout(null);
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
		
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
		lblDescuento.setBounds(12, 110, 81, 15);
		getContentPane().add(lblDescuento);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(12, 140, 101, 15);
		getContentPane().add(lblDescripcion);
		
		txtNombre = new JTextField();
		txtNombre.setText("Pepe");
		txtNombre.setBounds(75, 48, 114, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JSpinner perVal = new JSpinner();
		perVal.setBounds(153, 78, 49, 20);
		getContentPane().add(perVal);
		
		JLabel lbldias = new JLabel("(Dias)");
		lbldias.setBounds(206, 80, 70, 15);
		getContentPane().add(lbldias);
		
		JSpinner desc = new JSpinner();
		desc.setBounds(100, 108, 56, 20);
		getContentPane().add(desc);
		
		JLabel label = new JLabel("%");
		label.setBounds(163, 110, 70, 15);
		getContentPane().add(label);
		
		descrp = new JTextField();
		descrp.setBounds(12, 160, 416, 106);
		getContentPane().add(descrp);
		descrp.setColumns(10);
		
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtNombre.getText().toString() != "") {
					Fabrica f = Fabrica.getInstancia();
					IControladorPaquete cp = f.getIControladorPaquete();

					boolean alta = cp.altaTurista(txtNombre.getText().toString(),descrp.getText().toString(),(int)perVal.getValue(),(int)desc.getValue());
					if(alta) {
						JOptionPane.showMessageDialog(null, "Operacion realizada con exito.","Registro de Paquete",JOptionPane.INFORMATION_MESSAGE );
						setVisible(false);
						limpiarForm();

					}else {
						JOptionPane.showMessageDialog(null, "Error. Ya existe un Paquete de Actividades con el nombre elejido.","Registro de Paquete",JOptionPane.WARNING_MESSAGE );

					}
				}else {
					//TODO: no detecta bien la entrada vacia todavia.
					JOptionPane.showMessageDialog(null, "Error. El nombre del paquete no puede ser vacio.","Registro de Paquete",JOptionPane.WARNING_MESSAGE );
				}
			}
		});
		btnConfirmar.setBounds(12, 278, 117, 25);
		getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarForm();
				setVisible(false);
			}
		});
		btnCancelar.setBounds(311, 278, 117, 25);
		getContentPane().add(btnCancelar);
		
	}
	private void limpiarForm() {
        txtNombre.setText("");
        descrp.setText("");
    }
	
}
