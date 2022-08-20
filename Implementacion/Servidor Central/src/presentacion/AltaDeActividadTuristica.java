package presentacion;

import java.awt.EventQueue;
import logica.controladores.IControladorActividadTuristica;
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
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import logica.controladores.Fabrica;
import java.util.ArrayList;

public class AltaDeActividadTuristica extends JInternalFrame {
	
	
	private JTextField nombre;
	private JTextField costo;
	private JTextField descripcion;
	private JTextField duracion;
	private JComboBox tipoUsuario;
	private IControladorActividadTuristica icat;
	private JTextField ciudad;
	private JTextField FdeAlLta;

	/**
	 * Create the frame.
	 */
	public AltaDeActividadTuristica(IControladorActividadTuristica icat) {
		Fabrica f = Fabrica.getInstancia();
		this.icat = f.getIControladorActividadTuristica();
		setTitle("Alta de Actividad Turistica");
		setBounds(100, 100, 409, 328);
		getContentPane().setLayout(null);
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
		
		JLabel lblNewLabel = new JLabel("Departamentos:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(7, 37, 120, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(6, 63, 121, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Descripcion:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(7, 89, 120, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		nombre = new JTextField();
		nombre.setBounds(131, 61, 258, 20);
		getContentPane().add(nombre);
		nombre.setColumns(10);
		
		descripcion = new JTextField();
		descripcion.setColumns(10);
		descripcion.setBounds(131, 87, 258, 20);
		getContentPane().add(descripcion);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Duracion:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setBounds(7, 111, 120, 14);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Costo:");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1.setBounds(0, 139, 121, 14);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Proveedores:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(7, 12, 120, 14);
		getContentPane().add(lblNewLabel_2);
		
		costo = new JTextField();
		costo.setColumns(10);
		costo.setBounds(131, 137, 258, 20);
		getContentPane().add(costo);
		
		duracion = new JTextField();
		duracion.setColumns(10);
		duracion.setBounds(131, 109, 258, 20);
		getContentPane().add(duracion);
		
		
		JComboBox comboProveedores = new JComboBox();
		comboProveedores.setBounds(145, 7, 212, 24);
		getContentPane().add(comboProveedores);
		ArrayList<String> proveedores = icat.obtenerIdProveedores();
		comboProveedores.addItem(proveedores);
		this.tipoUsuario = comboProveedores;
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//agregarUsuario(e);
			}
		});
		btnNewButton.setBounds(272, 259, 117, 25);
		getContentPane().add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
			}
		});
		btnCancelar.setBounds(10, 259, 117, 25);
		getContentPane().add(btnCancelar);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Ciudad:");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1.setBounds(7, 165, 120, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		ciudad = new JTextField();
		ciudad.setColumns(10);
		ciudad.setBounds(131, 163, 258, 20);
		getContentPane().add(ciudad);
		ciudad.setEnabled(false);
		
		FdeAlLta = new JTextField();
		FdeAlLta.setColumns(10);
		FdeAlLta.setBounds(131, 189, 258, 20);
		getContentPane().add(FdeAlLta);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Fecha de alta:");
		lblNewLabel_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1_1_1.setBounds(7, 191, 120, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1);
		
		JComboBox comboDepartamentos = new JComboBox();
		comboDepartamentos.setBounds(145, 32, 212, 24);
		getContentPane().add(comboDepartamentos);
		
	   comboProveedores.addActionListener(new ActionListener() {     
		     public void actionPerformed(ActionEvent e) {}
		   });

	}
	/*
	private void agregarUsuario(ActionEvent action) {
	      String fecha = costo.getText();
	      DateTimeFormatter JEFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	      LocalDate fechaNac = LocalDate.parse(fecha, JEFormatter);
	      boolean existeUsuario;
		if(tipoUsuario.getSelectedItem().toString() == "Proveedor") {
			System.out.print(fechaNac);
		    existeUsuario = icu.altaProveedor(nickname.getText().toString(), nombre.getText().toString(), descripcion.getText().toString(), duracion.getText().toString(), FdeAlLta.getText().toString(),url.getText().toString(), fechaNac);
		}else {
			existeUsuario = icu.altaTurista(nickname.getText().toString(), nombre.getText().toString(), descripcion.getText().toString(), duracion.getText().toString(), fechaNac, ciudad.getText().toString());
		}

		if(existeUsuario) {
			limpiarFormulario();
            setVisible(false);
			JOptionPane.showMessageDialog (null, "El usuario se ha creado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al crear el usuario", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	*/
    private void limpiarFormulario() {
        nombre.setText("");
        descripcion.setText("");
        //nickname.setText("");
        duracion.setText("");
        costo.setText("");
    }
}
