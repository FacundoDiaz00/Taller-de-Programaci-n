package presentacion;

import java.awt.EventQueue;

import excepciones.UsuarioYaRegistradoException;
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
import java.time.DateTimeException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import logica.controladores.Fabrica;

public class AltaDeUsuario extends JInternalFrame {

	private final String OPCION_PROVEEDOR = "Proveedor";
	private final String OPCION_TURISTA = "Turista";

	private JTextField nombre;
	private JTextField nickname;
	private JTextField Fnacimiento;
	private JTextField apellido;
	private JTextField correo;
	private JComboBox tipoUsuario;
	private IControladorUsuario icu;
	private JTextField nacionalidad;
	private JTextField descripcion;
	private JTextField url;

	/**
	 * Create the frame.
	 */
	public AltaDeUsuario(IControladorUsuario icu) {
		Fabrica f = Fabrica.getInstancia();
		this.icu = f.getIControladorUsuario();
		setTitle("Registrar Usuario");
		setBounds(100, 100, 409, 328);
		getContentPane().setLayout(null);
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
		
		JLabel lblNewLabel = new JLabel("Nickname:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(40, 40, 77, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(53, 65, 64, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Apellido:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(54, 90, 63, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		nombre = new JTextField();
		nombre.setBounds(131, 61, 258, 20);
		getContentPane().add(nombre);
		nombre.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Correo:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setBounds(64, 115, 53, 14);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Fecha nacimiento:");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1.setBounds(7, 140, 110, 14);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo de Usuario:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(0, 12, 120, 14);
		getContentPane().add(lblNewLabel_2);
		
		nickname = new JTextField();
		nickname.setColumns(10);
		nickname.setBounds(131, 35, 258, 20);
		getContentPane().add(nickname);
		
		Fnacimiento = new JTextField();
		Fnacimiento.setColumns(10);
		Fnacimiento.setBounds(131, 137, 258, 20);
		getContentPane().add(Fnacimiento);
		
		apellido = new JTextField();
		apellido.setColumns(10);
		apellido.setBounds(131, 87, 258, 20);
		getContentPane().add(apellido);
		
		correo = new JTextField();
		correo.setColumns(10);
		correo.setBounds(131, 109, 258, 20);
		getContentPane().add(correo);
		
		
		tipoUsuario = new JComboBox();
		tipoUsuario.setBounds(131, 7, 258, 24);
		getContentPane().add(tipoUsuario);
		tipoUsuario.addItem(OPCION_PROVEEDOR);
		tipoUsuario.addItem(OPCION_TURISTA);
		this.tipoUsuario = tipoUsuario;
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarUsuario(e);
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
		btnCancelar.setBounds(20, 259, 117, 25);
		getContentPane().add(btnCancelar);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Nacionalidad:");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1.setBounds(17, 165, 97, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		nacionalidad = new JTextField();
		nacionalidad.setColumns(10);
		nacionalidad.setBounds(131, 163, 258, 20);
		getContentPane().add(nacionalidad);
		nacionalidad.setEnabled(false);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("URL:");
		lblNewLabel_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1_1.setBounds(20, 218, 97, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1);
		
		descripcion = new JTextField();
		descripcion.setColumns(10);
		descripcion.setBounds(131, 189, 258, 20);
		getContentPane().add(descripcion);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Descripción:");
		lblNewLabel_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1_1_1.setBounds(20, 192, 97, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1);
		
		url = new JTextField();
		url.setColumns(10);
		url.setBounds(131, 215, 258, 20);
		getContentPane().add(url);
		
	   tipoUsuario.addActionListener(new ActionListener() {     
		     public void actionPerformed(ActionEvent e) {
		        if(tipoUsuario.getSelectedItem().toString() == "Proveedor") {
		        	nacionalidad.setEnabled(false);
		        	url.setEnabled(true);
		        	descripcion.setEnabled(true);
		        }else {
		        	nacionalidad.setEnabled(true);
		        	url.setEnabled(false);
		        	descripcion.setEnabled(false);
		        }
		     }
		   });

	}
	private void agregarUsuario(ActionEvent action) {
		String fecha = Fnacimiento.getText();
		LocalDate fechaNac;
		try {
			DateTimeFormatter JEFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			fechaNac = LocalDate.parse(fecha, JEFormatter);
			//Todo falta verificar los campos obligatorios
			if(tipoUsuario.getSelectedItem().toString().equals(OPCION_PROVEEDOR)) {
				icu.altaProveedor(nickname.getText(), nombre.getText(), apellido.getText(), correo.getText(), descripcion.getText(),url.getText(), fechaNac);
			}else {
				icu.altaTurista(nickname.getText(), nombre.getText(), apellido.getText(), correo.getText(), fechaNac, nacionalidad.getText());
			}
			limpiarFormulario();
			setVisible(false);
			JOptionPane.showMessageDialog (null, "El usuario se ha creado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);

		} catch (UsuarioYaRegistradoException e) {
			JOptionPane.showMessageDialog(null, "Ya existe un usuario con este nickname o con este correo", "Error", JOptionPane.ERROR_MESSAGE);
			//throw new RuntimeException(e); //Todo ¿porque ponen esto?
		} catch (DateTimeParseException e){
			JOptionPane.showMessageDialog(null, "Fecha nacimiento invalida, es un campo obligatorio y su formato es dd/mm/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
			//throw new RuntimeException(e);
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al crear el usuario", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
    private void limpiarFormulario() {
        nombre.setText("");
        apellido.setText("");
        nickname.setText("");
        correo.setText("");
        Fnacimiento.setText("");
        nacionalidad.setText("");
        url.setText("");
        descripcion.setText("");
    }
}
