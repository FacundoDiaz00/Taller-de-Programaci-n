package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import excepciones.PaqueteYaRegistradoException;
import logica.controladores.IControladorPaquete;

import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class AltaDePaquete extends JInternalFrame{
	private IControladorPaquete cp;
	
	private JTextField txtNombre;
	private JTextArea descrp;
	private JSpinner perVal;
	private JSpinner desc;
	private JSpinner anior;
	private JSpinner mesr;
	private JSpinner diar;
	public AltaDePaquete(IControladorPaquete cp) {
		this.cp = cp;		
		
		setTitle("Registrar Paquete de Actividades.");
		setBounds(100, 100, 461, 415);
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
		txtNombre.setText("");
		txtNombre.setBounds(75, 48, 114, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		perVal = new JSpinner();
		perVal.setBounds(153, 78, 49, 20);
		getContentPane().add(perVal);
		
		JLabel lbldias = new JLabel("(Dias)");
		lbldias.setBounds(206, 80, 70, 15);
		getContentPane().add(lbldias);
		
		desc = new JSpinner();
		desc.setBounds(100, 108, 56, 20);
		getContentPane().add(desc);
		
		JLabel label = new JLabel("%");
		label.setBounds(163, 110, 70, 15);
		getContentPane().add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 160, 416, 118);
		getContentPane().add(scrollPane);
		
		descrp = new JTextArea();
		scrollPane.setViewportView(descrp);
		descrp.setWrapStyleWord(true);
		descrp.setLineWrap(true);
		descrp.setColumns(10);
		
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(txtNombre.getText().isBlank() || descrp.getText().isBlank()) {
						JOptionPane.showMessageDialog(null, "Los campos nombre y descripción son obligatorios","Registro de Paquete",JOptionPane.WARNING_MESSAGE );
						return;
					}
					LocalDate f = LocalDate.of((int)anior.getValue(),(int)mesr.getValue(),(int)diar.getValue());
					cp.altaPaquete(txtNombre.getText().toString(),descrp.getText().toString(), (int)perVal.getValue(), (int)desc.getValue(),f);
					JOptionPane.showMessageDialog(null, "Operacion realizada con exito.","Registro de Paquete",JOptionPane.INFORMATION_MESSAGE );
					setVisible(false);
					limpiarForm();
				}catch(PaqueteYaRegistradoException e){
					JOptionPane.showMessageDialog(null, "Error. Ya existe un Paquete de Actividades con el nombre elegido.","Registro de Paquete",JOptionPane.WARNING_MESSAGE );

				}
				
			}
		});
		btnConfirmar.setBounds(311, 341, 117, 25);
		getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarForm();
				setVisible(false);
			}
		});
		btnCancelar.setBounds(12, 341, 117, 25);
		getContentPane().add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("Fecha De Registro:");
		lblNewLabel.setBounds(12, 300, 134, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel label_1 = new JLabel("/");
		label_1.setBounds(206, 300, 11, 15);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("/");
		label_2.setBounds(268, 300, 11, 15);
		getContentPane().add(label_2);
		
		diar = new JSpinner();
		diar.setBounds(153, 298, 47, 20);
		diar.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		getContentPane().add(diar);
		
		
		mesr = new JSpinner();
		mesr.setBounds(216, 298, 47, 20);
		mesr.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		getContentPane().add(mesr);
		
		anior = new JSpinner();
		anior.setBounds(278, 298, 64, 20);
		anior.setModel(new SpinnerNumberModel(2022, 1900, 2100, 1));
		getContentPane().add(anior);
		
		JLabel lblddmmyyyy = new JLabel("(dd/mm/yyyy)");
		lblddmmyyyy.setBounds(347, 300, 92, 15);
		getContentPane().add(lblddmmyyyy);
		
	}
	private void limpiarForm() {
        txtNombre.setText("");
        descrp.setText("");
		perVal.setValue(0);
		desc.setValue(0);
		diar.setValue(1);
		mesr.setValue(1);
		anior.setValue(2001);
    }
}
