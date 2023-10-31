package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.controladores.IControladorActividadTuristica;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTSalidaTuristica;

public class ConsultaDeActividadTuristica extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private IControladorActividadTuristica contrAct;
	private Principal principal;

	private String seleccionActividad;
	private boolean mostrarOtrasConsultas = true;

	private final JComboBox comboSalidas;
	private final JComboBox comboPaquetes;
	private JComboBox comboActividades;
	private JComboBox comboDepartamentos;
	private JTextArea nombre;
	private JTextArea descripcion;
	private JTextArea duracion;
	private JTextArea ciudad;
	private JTextArea costo;
	private JTextArea fechaAlta;
	private JTextArea proveedor;
	private JLabel lblCatecogr;
	private JList listCategorias;

	/**
	 * Create the frame.
	 */
	public ConsultaDeActividadTuristica(Principal principal, IControladorActividadTuristica icat) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent event) {
				limpiarFormulario();
			}
		});
		this.principal = principal;
		this.contrAct = icat;
		setTitle("Consulta de Actividad Turística");
		setBounds(100, 100, 409, 482);
		getContentPane().setLayout(null);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);

		JLabel departamentos = new JLabel("Departamentos:");
		departamentos.setHorizontalAlignment(SwingConstants.RIGHT);
		departamentos.setBounds(7, 12, 120, 14);
		getContentPane().add(departamentos);

		JLabel actividades = new JLabel("Actividades:");
		actividades.setHorizontalAlignment(SwingConstants.RIGHT);
		actividades.setBounds(7, 37, 120, 14);
		getContentPane().add(actividades);

		JLabel nombreLabel = new JLabel("Nombre:");
		nombreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nombreLabel.setBounds(6, 63, 121, 14);
		getContentPane().add(nombreLabel);

		JLabel descripcionLabel = new JLabel("Descripción:");
		descripcionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		descripcionLabel.setBounds(7, 89, 120, 14);
		getContentPane().add(descripcionLabel);

		JLabel duracionLabel = new JLabel("Duración:");
		duracionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		duracionLabel.setBounds(7, 164, 120, 14);
		getContentPane().add(duracionLabel);

		JLabel costoLabel = new JLabel("Costo:");
		costoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		costoLabel.setBounds(0, 192, 121, 14);
		getContentPane().add(costoLabel);

		JLabel ciudadLabel = new JLabel("Ciudad:");
		ciudadLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		ciudadLabel.setBounds(7, 218, 120, 14);
		getContentPane().add(ciudadLabel);

		JLabel fechaAltaLabel = new JLabel("Fecha de alta:");
		fechaAltaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		fechaAltaLabel.setBounds(7, 244, 120, 14);
		getContentPane().add(fechaAltaLabel);

		comboDepartamentos = new JComboBox<String>();
		comboDepartamentos.setBounds(145, 7, 212, 24);
		getContentPane().add(comboDepartamentos);

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

		comboActividades = new JComboBox();
		comboActividades.setBounds(145, 32, 212, 24);
		getContentPane().add(comboActividades);

		nombre = new JTextArea();
		nombre.setEditable(false);
		nombre.setBounds(145, 63, 212, 15);
		getContentPane().add(nombre);

		duracion = new JTextArea();
		duracion.setEditable(false);
		duracion.setBounds(145, 164, 212, 15);
		getContentPane().add(duracion);

		costo = new JTextArea();
		costo.setEditable(false);
		costo.setBounds(145, 192, 212, 15);
		getContentPane().add(costo);

		ciudad = new JTextArea();
		ciudad.setEditable(false);
		ciudad.setBounds(145, 218, 212, 15);
		getContentPane().add(ciudad);

		fechaAlta = new JTextArea();
		fechaAlta.setEditable(false);
		fechaAlta.setBounds(145, 244, 212, 15);
		getContentPane().add(fechaAlta);

		comboSalidas = new JComboBox();
		comboSalidas.setBounds(145, 289, 212, 24);
		getContentPane().add(comboSalidas);

		JLabel salidasTuristicasLabel = new JLabel("Salidas turísticas:");
		salidasTuristicasLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		salidasTuristicasLabel.setBounds(-12, 294, 139, 14);
		getContentPane().add(salidasTuristicasLabel);

		comboPaquetes = new JComboBox();
		comboPaquetes.setBounds(145, 314, 212, 24);
		getContentPane().add(comboPaquetes);

		JLabel paquetesLabel = new JLabel("Paquetes:");
		paquetesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		paquetesLabel.setBounds(46, 319, 81, 14);
		getContentPane().add(paquetesLabel);

		descripcion = new JTextArea();
		descripcion.setLineWrap(true);
		descripcion.setWrapStyleWord(true);
		descripcion.setEditable(false);
		descripcion.setBounds(145, 84, 212, 69);
		getContentPane().add(descripcion);

		JLabel lblProveedor = new JLabel("Proveedor:");
		lblProveedor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProveedor.setBounds(7, 269, 120, 14);
		getContentPane().add(lblProveedor);

		proveedor = new JTextArea();
		proveedor.setEditable(false);
		proveedor.setBounds(145, 270, 212, 15);
		getContentPane().add(proveedor);

		lblCatecogr = new JLabel("Categorías:");
		lblCatecogr.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCatecogr.setBounds(33, 345, 94, 15);
		getContentPane().add(lblCatecogr);

		listCategorias = new JList();
		listCategorias.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				listCategorias.clearSelection();
			}
		});
		listCategorias.setBounds(145, 350, 212, 88);
		getContentPane().add(listCategorias);

		comboDepartamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				var dep = comboDepartamentos.getSelectedItem();
				if (dep != null) {
					try {
						List<String> actividades = icat.obtenerIdActividadesTuristicas((String) dep);
						comboActividades.setModel(new DefaultComboBoxModel(actividades.toArray()));
					} catch (ObjetoNoExisteEnTurismoUy e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		comboActividades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				consultaDeActividad(event);
			}
		});

		comboSalidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ejecutarCasoConsultaSalidaTuristca((String) comboSalidas.getSelectedItem());
			}
		});

		comboPaquetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ejecutarCasoConsultaPaquete((String) comboPaquetes.getSelectedItem());
			}
		});

	}

	public void seleccionYaHecha(String nombreAct) {
		comboActividades.removeAllItems();
		comboDepartamentos.removeAllItems();

		seleccionActividad = nombreAct;
		seSeleccionoUnaActividad();
	}

	private void consultaDeActividad(ActionEvent action) {
		seleccionActividad = (String) comboActividades.getSelectedItem();
		seSeleccionoUnaActividad();
	}

	private void seSeleccionoUnaActividad() {
		String seleccion = seleccionActividad;
		mostrarOtrasConsultas = false;

		try {
			DTActividadTuristicaDetalle actividad = contrAct.obtenerDTActividadTuristicaDetalle(seleccion);

			nombre.setText(actividad.getNombre());
			ciudad.setText(actividad.getCuidad());
			costo.setText(String.valueOf(actividad.getCostoPorTurista()));
			descripcion.setText(actividad.getDescripcion());
			fechaAlta.setText(actividad.getFechaAlta().toString());
			proveedor.setText(actividad.getNicknameProveedor());
			duracion.setText(String.valueOf(actividad.getDuracion()));

			comboSalidas.removeAllItems();
			comboPaquetes.removeAllItems();

			List<DTSalidaTuristica> salidas = new ArrayList<DTSalidaTuristica>(actividad.getSalidas().values());
			for (DTSalidaTuristica salida : salidas) {
				comboSalidas.addItem(salida.getNombre());
			}

			List<DTPaquete> paquetes = new ArrayList<DTPaquete>(actividad.getPaquetes().values());
			for (DTPaquete paquete : paquetes) {
				comboPaquetes.addItem(paquete.getNombre());
			}

			DefaultListModel<String> listModelCategorias = new DefaultListModel<>();
			for (String idCat : actividad.getCategorias()) {
				listModelCategorias.addElement(idCat);
			}
			listCategorias.setModel(listModelCategorias);

		} catch (ObjetoNoExisteEnTurismoUy e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(() -> mostrarOtrasConsultas = true);
	}

	public void actualizarComboDepartamentos() {
		List<String> deps = contrAct.obtenerIdDepartamentos();
		comboDepartamentos.setModel(new DefaultComboBoxModel(deps.toArray()));
	}

	private void ejecutarCasoConsultaSalidaTuristca(String nombreSalida) {
		if (mostrarOtrasConsultas) {
			principal.mostrarConsultaDeSalidaTuristica(nombreSalida);
		}
	}

	private void ejecutarCasoConsultaPaquete(String nombrePaquete) {
		if (mostrarOtrasConsultas) {
			principal.mostrarConsultaDePaquete(nombrePaquete);
		}
	}

	private void limpiarFormulario() {
		nombre.setText("");
		descripcion.setText("");
		duracion.setText("");
		costo.setText("");
		ciudad.setText("");
		fechaAlta.setText("");
		proveedor.setText("");
		comboSalidas.setModel(new DefaultComboBoxModel<>(new String[0]));
		comboPaquetes.setModel(new DefaultComboBoxModel<>(new String[0]));
		comboActividades.setModel(new DefaultComboBoxModel<>(new String[0]));
		comboDepartamentos.setModel(new DefaultComboBoxModel<>(new String[0]));
	}
}
