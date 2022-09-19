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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import excepciones.UsuarioYaRegistradoException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;

public class AltaDeUsuario extends JInternalFrame {

	private final String opcionProveedor = "Proveedor";
	private final String opcionTurista = "Turista";

	private JTextField nombre;
	private JTextField nickname;
	private JTextField fechaNacimiento;
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

		Fabrica fabrica = Fabrica.getInstancia();
		this.icu = fabrica.getIControladorUsuario();
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

		fechaNacimiento = new JTextField();
		fechaNacimiento.setColumns(10);
		fechaNacimiento.setBounds(131, 137, 258, 20);
		getContentPane().add(fechaNacimiento);

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
		tipoUsuario.addItem(opcionProveedor);
		tipoUsuario.addItem(opcionTurista);
		tipoUsuario.setSelectedIndex(0);
		this.tipoUsuario = tipoUsuario;

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				agregarUsuario(event);
			}
		});
		btnAceptar.setBounds(272, 259, 117, 25);
		getContentPane().add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
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
			public void actionPerformed(ActionEvent event) {
				if (tipoUsuario.getSelectedItem().toString() == "Proveedor") {
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
			if (tipoUsuario.getSelectedItem().toString().equals(opcionTurista)
					&& (nombre.getText().isBlank() || apellido.getText().isBlank() || nickname.getText().isBlank()
							|| fechaNacimiento.getText().isBlank() || nacionalidad.getText().isBlank())) {
				JOptionPane.showMessageDialog(null,
						"Los campos nombre, apellido, nickname, correo, fecha de nacimiento y nacionalidad son obligatorios",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (tipoUsuario.getSelectedItem().toString().equals(opcionProveedor)
					&& (descripcion.getText().isBlank() || nombre.getText().isBlank() || apellido.getText().isBlank()
							|| nickname.getText().isBlank() || fechaNacimiento.getText().isBlank())) {
				JOptionPane.showMessageDialog(null,
						"Los campos nombre, apellido, nickname, correo, fecha de nacimiento y descripción son obligatorios",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			DateTimeFormatter JEFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			fechaNac = LocalDate.parse(fecha, JEFormatter);
			// Todo falta verificar los campos obligatorios
			if (tipoUsuario.getSelectedItem().toString().equals(opcionProveedor)) {
				icu.altaProveedor(nickname.getText(), nombre.getText(), apellido.getText(), correo.getText(), fechaNac,
						null, descripcion.getText(), url.getText());
			} else {
				icu.altaTurista(nickname.getText(), nombre.getText(), apellido.getText(), correo.getText(), fechaNac,
						null, nacionalidad.getText());
			}
			limpiarFormulario();
			setVisible(false);
			JOptionPane.showMessageDialog(null, "El usuario se ha creado con éxito", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (UsuarioYaRegistradoException e) {
			JOptionPane.showMessageDialog(null, "Ya existe un usuario con este nickname o con este correo", "Error",
					JOptionPane.ERROR_MESSAGE);
			// throw new RuntimeException(e); //Todo ¿porque ponen esto?
		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(null,
					"Fecha nacimiento inválida, es un campo obligatorio y su formato es dd/mm/yyyy", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Hay campos numéricos con datos inválidos", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarFormulario() {
		nombre.setText("");
		apellido.setText("");
		nickname.setText("");
		correo.setText("");
		fechaNacimiento.setText("");
		nacionalidad.setText("");
		url.setText("");
		descripcion.setText("");
	}
}
