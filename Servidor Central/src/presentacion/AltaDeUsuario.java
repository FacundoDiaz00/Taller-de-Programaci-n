package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import excepciones.UsuarioYaRegistradoException;
import logica.controladores.IControladorUsuario;

public class AltaDeUsuario extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;

	private static final String OPCION_PROVEEDOR = "Proveedor";
	private static final String OPCION_TURISTA = "Turista";

	private JTextField nombre;
	private JTextField nickname;
	private JTextField fechaNacimiento;
	private JTextField apellido;
	private JTextField correo;
	private JComboBox tipoUsuario;
	private JTextField nacionalidad;
	private JTextField descripcion;
	private JTextField url;
	private JTextField contra;
	private JTextField confirmarContra;
	
	private IControladorUsuario contrUsuario;
	/**
	 * Create the frame.
	 */
	public AltaDeUsuario(IControladorUsuario contrUsuario) {
		this.contrUsuario = contrUsuario;
		setTitle("Registrar Usuario");
		setBounds(100, 100, 450, 409);
		getContentPane().setLayout(null);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);

		JLabel lblNewLabel = new JLabel("Nickname:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(40, 38, 116, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel1 = new JLabel("Nombre:");
		lblNewLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel1.setBounds(53, 64, 103, 14);
		getContentPane().add(lblNewLabel1);

		JLabel lblNewLabel11 = new JLabel("Apellido:");
		lblNewLabel11.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel11.setBounds(54, 90, 102, 14);
		getContentPane().add(lblNewLabel11);

		nombre = new JTextField();
		nombre.setBounds(166, 61, 258, 20);
		getContentPane().add(nombre);
		nombre.setColumns(10);

		JLabel lblNewLabel111 = new JLabel("Correo:");
		lblNewLabel111.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel111.setBounds(64, 118, 92, 14);
		getContentPane().add(lblNewLabel111);

		JLabel lblNewLabel1111 = new JLabel("Fecha nacimiento:");
		lblNewLabel1111.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel1111.setBounds(7, 199, 149, 14);
		getContentPane().add(lblNewLabel1111);

		JLabel lblNewLabel2 = new JLabel("Tipo de Usuario:");
		lblNewLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel2.setBounds(0, 12, 156, 14);
		getContentPane().add(lblNewLabel2);

		nickname = new JTextField();
		nickname.setColumns(10);
		nickname.setBounds(166, 35, 258, 20);
		getContentPane().add(nickname);

		fechaNacimiento = new JTextField();
		fechaNacimiento.setColumns(10);
		fechaNacimiento.setBounds(166, 196, 258, 20);
		getContentPane().add(fechaNacimiento);

		apellido = new JTextField();
		apellido.setColumns(10);
		apellido.setBounds(166, 87, 258, 20);
		getContentPane().add(apellido);

		correo = new JTextField();
		correo.setColumns(10);
		correo.setBounds(166, 115, 258, 20);
		getContentPane().add(correo);

		tipoUsuario = new JComboBox();
		tipoUsuario.setBounds(166, 7, 258, 24);
		getContentPane().add(tipoUsuario);
		tipoUsuario.addItem(OPCION_PROVEEDOR);
		tipoUsuario.addItem(OPCION_TURISTA);
		tipoUsuario.setSelectedIndex(0);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				agregarUsuario(event);
			}
		});
		btnAceptar.setBounds(272, 340, 117, 25);
		getContentPane().add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		btnCancelar.setBounds(17, 340, 117, 25);
		getContentPane().add(btnCancelar);

		JLabel lblNewLabel11111 = new JLabel("Nacionalidad:");
		lblNewLabel11111.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel11111.setBounds(20, 225, 136, 14);
		getContentPane().add(lblNewLabel11111);

		nacionalidad = new JTextField();
		nacionalidad.setColumns(10);
		nacionalidad.setBounds(166, 222, 258, 20);
		getContentPane().add(nacionalidad);
		nacionalidad.setEnabled(false);

		JLabel lblNewLabel111111 = new JLabel("URL:");
		lblNewLabel111111.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel111111.setBounds(20, 280, 136, 14);
		getContentPane().add(lblNewLabel111111);

		descripcion = new JTextField();
		descripcion.setColumns(10);
		descripcion.setBounds(166, 248, 258, 20);
		getContentPane().add(descripcion);

		JLabel lblNewLabel1111111 = new JLabel("Descripción:");
		lblNewLabel1111111.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel1111111.setBounds(20, 251, 136, 14);
		getContentPane().add(lblNewLabel1111111);

		url = new JTextField();
		url.setColumns(10);
		url.setBounds(166, 277, 258, 20);
		getContentPane().add(url);
		
		contra = new JPasswordField();
		contra.setBounds(166, 141, 258, 20);
		getContentPane().add(contra);
		contra.setColumns(10);
		
		confirmarContra = new JPasswordField();
		confirmarContra.setColumns(10);
		confirmarContra.setBounds(166, 169, 258, 20);
		getContentPane().add(confirmarContra);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasea.setBounds(17, 144, 139, 14);
		getContentPane().add(lblContrasea);
		
		JLabel lblConfirmarContrasea = new JLabel("Confirmar Contraseña:");
		lblConfirmarContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmarContrasea.setBounds(17, 172, 139, 14);
		getContentPane().add(lblConfirmarContrasea);

		tipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (OPCION_PROVEEDOR.equals(tipoUsuario.getSelectedItem().toString())) {
					nacionalidad.setEnabled(false);
					url.setEnabled(true);
					descripcion.setEnabled(true);
				} else {
					nacionalidad.setEnabled(true);
					url.setEnabled(false);
					descripcion.setEnabled(false);
				}
			}
		});

	}

	private void agregarUsuario(ActionEvent action) {
		String fecha = fechaNacimiento.getText();
		LocalDate fechaNac;
		try {
			if (tipoUsuario.getSelectedItem().toString().equals(OPCION_TURISTA)
					&& (nombre.getText().isBlank() || apellido.getText().isBlank() || nickname.getText().isBlank()
							|| fechaNacimiento.getText().isBlank() || nacionalidad.getText().isBlank() || contra.getText().isBlank()) ) {
				JOptionPane.showMessageDialog(null,
						"Los campos nombre, apellido, nickname, correo, fecha de nacimiento y nacionalidad son obligatorios",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (tipoUsuario.getSelectedItem().toString().equals(OPCION_PROVEEDOR)
					&& (descripcion.getText().isBlank() || nombre.getText().isBlank() || apellido.getText().isBlank()
							|| nickname.getText().isBlank() || fechaNacimiento.getText().isBlank() || contra.getText().isBlank())) {
				JOptionPane.showMessageDialog(null,
						"Los campos nombre, apellido, nickname, correo, contaseña, confirmar contraseña, fecha de nacimiento y descripción son obligatorios",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}			
			if (contra.getText().compareTo(confirmarContra.getText()) != 0) {
				JOptionPane.showMessageDialog(null, "La confirmación debe coincidir con la contraseña",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			DateTimeFormatter jeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			fechaNac = LocalDate.parse(fecha, jeFormatter);
			// TODO falta verificar los campos obligatorios
			if (tipoUsuario.getSelectedItem().toString().equals(OPCION_PROVEEDOR)) {
				contrUsuario.altaProveedor(nickname.getText(), nombre.getText(), apellido.getText(), correo.getText(), contra.getText(), fechaNac,
						null, descripcion.getText(), url.getText());
			} else {
				contrUsuario.altaTurista(nickname.getText(), nombre.getText(), apellido.getText(), correo.getText(), contra.getText(), fechaNac,
						null, nacionalidad.getText());
			}
			limpiarFormulario();
			setVisible(false);
			JOptionPane.showMessageDialog(null, "El usuario se ha creado con éxito", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (UsuarioYaRegistradoException exception) {
			JOptionPane.showMessageDialog(null, "Ya existe un usuario con este nickname o con este correo", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (DateTimeParseException exception) {
			JOptionPane.showMessageDialog(null,
					"Fecha nacimiento inválida, es un campo obligatorio y su formato es dd/mm/yyyy", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException exception) {
			JOptionPane.showMessageDialog(null, "Hay campos numéricos con datos inválidos", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarFormulario() {
		nombre.setText("");
		apellido.setText("");
		nickname.setText("");
		correo.setText("");
		contra.setText("");
		confirmarContra.setText("");
		fechaNacimiento.setText("");
		nacionalidad.setText("");
		url.setText("");
		descripcion.setText("");
	}
}
