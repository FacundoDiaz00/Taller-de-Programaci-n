package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import excepciones.ActividadTuristicaYaRegistradaException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;

public class AltaDeActividadTuristica extends JInternalFrame {

	private JComboBox<String> comboProveedores;
	private JComboBox<String> comboDepartamentos;
	private JTextField nombre;
	private JTextField costo;
	private JTextField descripcion;
	private JTextField duracion;
	private IControladorActividadTuristica icat;
	private JTextField ciudad;
	private JTextField fDeAlta;

	/**
	 * Create the frame.
	 */
	public AltaDeActividadTuristica(IControladorActividadTuristica icat) {
		Fabrica fabrica = Fabrica.getInstancia();
		this.icat = fabrica.getIControladorActividadTuristica();
		setTitle("Alta de Actividad Turística");
		setBounds(100, 100, 409, 328);
		getContentPane().setLayout(null);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);

		JLabel lblNewLabel_2 = new JLabel("Proveedores:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(7, 12, 120, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("Departamentos:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(7, 37, 120, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(6, 63, 121, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Descripción:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(7, 89, 120, 14);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Duración:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setBounds(7, 111, 120, 14);
		getContentPane().add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Costo:");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1.setBounds(0, 139, 121, 14);
		getContentPane().add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Ciudad:");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1.setBounds(7, 165, 120, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Fecha de alta:");
		lblNewLabel_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1_1_1.setBounds(7, 191, 120, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1);

		comboProveedores = new JComboBox<String>();
		comboProveedores.setBounds(145, 7, 212, 24);
		getContentPane().add(comboProveedores);

		comboProveedores.addPopupMenuListener(new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent event) {
				// Esto es lo que actualiza la lista cada vez que se abre.
				actualizarComboProveedores();
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent event) {

			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent event) {

			}

		});

		comboDepartamentos = new JComboBox();
		comboDepartamentos.setBounds(145, 32, 212, 24);
		getContentPane().add(comboDepartamentos);

		nombre = new JTextField();
		nombre.setBounds(131, 61, 258, 20);
		getContentPane().add(nombre);
		nombre.setColumns(10);

		descripcion = new JTextField();
		descripcion.setColumns(10);
		descripcion.setBounds(131, 87, 258, 20);
		getContentPane().add(descripcion);

		duracion = new JTextField();
		duracion.setColumns(10);
		duracion.setBounds(131, 109, 258, 20);
		getContentPane().add(duracion);

		costo = new JTextField();
		costo.setColumns(10);
		costo.setBounds(131, 137, 258, 20);
		getContentPane().add(costo);
		// this.comboProveedores = comboProveedores;

		comboDepartamentos.addPopupMenuListener(new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent event) {
				// Esto es lo que actualiza la lista cada vez que se abre.
				actualizarComboDepartamentos();
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent event) {

			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent event) {

			}
		});

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				agregarAT(event);
			}
		});

		ciudad = new JTextField();
		ciudad.setColumns(10);
		ciudad.setBounds(131, 163, 258, 20);
		getContentPane().add(ciudad);
		ciudad.setEnabled(true);

		fDeAlta = new JTextField();
		fDeAlta.setColumns(10);
		fDeAlta.setBounds(131, 189, 258, 20);
		getContentPane().add(fDeAlta);
		btnNewButton.setBounds(272, 259, 117, 25);
		getContentPane().add(btnNewButton);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		btnCancelar.setBounds(10, 259, 117, 25);
		getContentPane().add(btnCancelar);
	}

	private void agregarAT(ActionEvent action) {

		try {
			// FIXME llenar esta list con las categorias
			List<String> categorias = new ArrayList<String>();

			String prov;
			if (comboProveedores.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			prov = comboProveedores.getSelectedItem().toString();

			String dpto;
			if (comboDepartamentos.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un departamento", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			dpto = comboDepartamentos.getSelectedItem().toString();

			String nom = nombre.getText().toString();
			String desc = descripcion.getText().toString();
			int dur = Integer.parseInt(duracion.getText().toString());
			float cost = Float.parseFloat(costo.getText().toString());
			String ciu = ciudad.getText().toString();

			String FAlta = fDeAlta.getText().toString();
			DateTimeFormatter JEFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate fecha = LocalDate.parse(FAlta, JEFormatter);

			if (nom.isBlank() || desc.isBlank() || duracion.getText().toString().isBlank() || ciu.isBlank()
					|| costo.getText().toString().isBlank()) {
				JOptionPane.showMessageDialog(null,
						"Los campos nombre, descripción, duración, ciudad y costo son obligatorios", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			icat.altaActividadTuristica(prov, dpto, nom, desc, dur, cost, ciu, fecha, null, categorias);
			limpiarFormulario();
			setVisible(false);
			JOptionPane.showMessageDialog(null, "Se ha dado de alta la Actividad Turística con éxito", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (ActividadTuristicaYaRegistradaException e) {
			JOptionPane.showMessageDialog(null,
					"Ya existe una actividad turística con este nombre registrada en el sistema", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(null,
					"Fecha alta inválido, es un campo obligatorio y su formato es dd/mm/yyyy", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Hay campos numéricos con datos inválido", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void actualizarComboProveedores() {
		List<String> provs = Fabrica.getInstancia().getIControladorUsuario().obtenerIdProveedores();
		comboProveedores.setModel(new DefaultComboBoxModel(provs.toArray()));
	}

	public void actualizarComboDepartamentos() {
		List<String> deptos = Fabrica.getInstancia().getIControladorActividadTuristica().obtenerIdDepartamentos();
		comboDepartamentos.setModel(new DefaultComboBoxModel(deptos.toArray()));
	}

	private void limpiarFormulario() {
		nombre.setText("");
		descripcion.setText("");
		comboProveedores.setModel(new DefaultComboBoxModel<>(new String[0]));
		comboDepartamentos.setModel(new DefaultComboBoxModel<>(new String[0]));
		duracion.setText("");
		costo.setText("");
		ciudad.setText("");
		fDeAlta.setText("");
	}
}
