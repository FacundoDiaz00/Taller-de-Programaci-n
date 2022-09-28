package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.datatypes.DTInscripcion;

import javax.swing.JList;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class AltaDeActividadTuristica extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private JComboBox<String> comboProveedores;
	private JComboBox<String> comboDepartamentos;
	private JTextField nombre;
	private JTextField costo;
	private JTextField descripcion;
	private JTextField duracion;
	private IControladorActividadTuristica contrAct;
	private JTextField ciudad;
	private JTextField fDeAlta;
	private JList<String> categoriasList;
	private JList<String> seleccionList;
	private DefaultListModel<String> listModelSeleccion = new DefaultListModel<>();
	private DefaultListModel<String> listModelCategorias;
	
	/**
	 * Create the frame.
	 */
	public AltaDeActividadTuristica(IControladorActividadTuristica contrAct) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				actualizarListaCategorias();
			}
		});
		Fabrica fabrica = Fabrica.getInstancia();
		this.contrAct = fabrica.getIControladorActividadTuristica();
		setTitle("Alta de Actividad Turística");
		setBounds(100, 100, 409, 409);
		getContentPane().setLayout(null);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);

		JLabel lblNewLabel2 = new JLabel("Proveedores:");
		lblNewLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel2.setBounds(7, 12, 120, 14);
		getContentPane().add(lblNewLabel2);

		JLabel lblNewLabel = new JLabel("Departamentos:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(7, 37, 120, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel1 = new JLabel("Nombre:");
		lblNewLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel1.setBounds(6, 63, 121, 14);
		getContentPane().add(lblNewLabel1);

		JLabel lblNewLabel11 = new JLabel("Descripción:");
		lblNewLabel11.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel11.setBounds(7, 89, 120, 14);
		getContentPane().add(lblNewLabel11);

		JLabel lblNewLabel111 = new JLabel("Duración:");
		lblNewLabel111.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel111.setBounds(7, 111, 120, 14);
		getContentPane().add(lblNewLabel111);

		JLabel lblNewLabel1111 = new JLabel("Costo:");
		lblNewLabel1111.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel1111.setBounds(0, 139, 121, 14);
		getContentPane().add(lblNewLabel1111);

		JLabel lblNewLabel11111 = new JLabel("Ciudad:");
		lblNewLabel11111.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel11111.setBounds(7, 165, 120, 14);
		getContentPane().add(lblNewLabel11111);

		JLabel lblNewLabel1111111 = new JLabel("Fecha de alta:");
		lblNewLabel1111111.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel1111111.setBounds(7, 191, 120, 14);
		getContentPane().add(lblNewLabel1111111);

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
		btnNewButton.setBounds(270, 340, 117, 25);
		getContentPane().add(btnNewButton);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		btnCancelar.setBounds(7, 340, 117, 25);
		getContentPane().add(btnCancelar);
		
		JLabel lblCategorias = new JLabel("Categorías");
		lblCategorias.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategorias.setBounds(7, 217, 151, 15);
		getContentPane().add(lblCategorias);
		
		JList listCategorias = new JList();
		listCategorias.setBounds(12, 233, 139, 95);
		getContentPane().add(listCategorias);
		categoriasList = listCategorias;
		
		JLabel lblSeleccion = new JLabel("Selección");
		lblSeleccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccion.setBounds(250, 217, 137, 15);
		getContentPane().add(lblSeleccion);
		
		JList listSeleccion = new JList();
		listSeleccion.setBounds(250, 233, 139, 95);
		getContentPane().add(listSeleccion);
		seleccionList = listSeleccion;
		seleccionList.setModel(listModelSeleccion);
		
		JButton btnDerecha = new JButton(">");
		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String elem = categoriasList.getSelectedValue();
				if (elem != null) {
					listModelSeleccion.addElement(elem);
					listModelCategorias.removeElement(elem);
				}
					
			}
		});
		btnDerecha.setBounds(171, 233, 59, 25);
		getContentPane().add(btnDerecha);
		
		JButton btnIzquierda = new JButton("<");
		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String elem = seleccionList.getSelectedValue();
				if (elem != null) {
					listModelCategorias.addElement(elem);
					listModelSeleccion.removeElement(elem);
				}
			}
		});
		btnIzquierda.setBounds(171, 303, 59, 25);
		getContentPane().add(btnIzquierda);
		
		JButton btnClear = new JButton("CLR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModelSeleccion.clear();
				actualizarListaCategorias();
			}
		});
		btnClear.setBounds(163, 266, 75, 25);
		getContentPane().add(btnClear);
	}

	private void agregarAT(ActionEvent action) {

		try {
			// FIXME llenar esta list con las categorias
			List<String> categorias = new ArrayList<>();

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

			String nom = nombre.getText();
			String desc = descripcion.getText();
			int dur = Integer.parseInt(duracion.getText());
			float cost = Float.parseFloat(costo.getText());
			String ciu = ciudad.getText();

			String fAlta = fDeAlta.getText();
			DateTimeFormatter jeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate fecha = LocalDate.parse(fAlta, jeFormatter);

			if (nom.isBlank() || desc.isBlank() || duracion.getText().isBlank() || ciu.isBlank()
					|| costo.getText().isBlank()) {
				JOptionPane.showMessageDialog(null,
						"Los campos nombre, descripción, duración, ciudad y costo son obligatorios", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				contrAct.altaActividadTuristica(prov, dpto, nom, desc, dur, cost, ciu, fecha, null, categorias);
			} catch (ObjetoNoExisteEnTurismoUy e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			limpiarFormulario();
			setVisible(false);
			JOptionPane.showMessageDialog(null, "Se ha dado de alta la Actividad Turística con éxito", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (ActividadTuristicaYaRegistradaException exception) {
			JOptionPane.showMessageDialog(null,
					"Ya existe una actividad turística con este nombre registrada en el sistema", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (DateTimeParseException exception) {
			JOptionPane.showMessageDialog(null,
					"Fecha alta inválido, es un campo obligatorio y su formato es dd/mm/yyyy", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException exception) {
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
	
	public void actualizarListaCategorias() {
		listModelCategorias = new DefaultListModel<>();
		for (String idCat : contrAct.obtenerIdCategorias()) {
			listModelCategorias.addElement(idCat);
		}
		categoriasList.setModel(listModelCategorias);
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
