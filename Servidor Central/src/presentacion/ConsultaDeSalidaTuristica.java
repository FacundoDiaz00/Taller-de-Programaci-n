package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTInscripcion;
import logica.datatypes.DTSalidaTuristicaDetalle;
import logica.datatypes.DTTuristaDetalle;

public class ConsultaDeSalidaTuristica extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private String seleccionSalida;

	private final JComboBox comboSalidas;
	private JComboBox comboActividades;
	private JComboBox comboDepartamentos;
	private IControladorActividadTuristica contrAct;
	private JTextArea nombre;
	private JTextArea fechaSalida;
	private JTextArea horaSalida;
	private JTextArea lugarSalida;
	private JTextArea maxCantTuristas;
	private JTextArea fechaAlta;

	private JList inscripcionList;
	private JTextArea nombreInscripto;
	private JTextArea fechaInscripto;
	private JTextArea cantTuristasInscripto;
	private JTextArea costoInsc;

	/**
	 * Create the frame.
	 */
	public ConsultaDeSalidaTuristica(IControladorActividadTuristica icat) {
		this.contrAct = icat;

		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent event) {
				limpiarFormulario();
			}
		});

		setTitle("Consulta de Salida Turística");
		setBounds(100, 100, 430, 512);
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
		nombreLabel.setBounds(7, 97, 121, 14);
		getContentPane().add(nombreLabel);

		JLabel fechaLabel = new JLabel("Fecha:");
		fechaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		fechaLabel.setBounds(7, 123, 120, 14);
		getContentPane().add(fechaLabel);

		JLabel hora = new JLabel("Hora:");
		hora.setHorizontalAlignment(SwingConstants.RIGHT);
		hora.setBounds(7, 149, 120, 14);
		getContentPane().add(hora);

		JLabel lugarLabel = new JLabel("Lugar de salida:");
		lugarLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lugarLabel.setBounds(7, 175, 121, 14);
		getContentPane().add(lugarLabel);

		JLabel maxTuristasLabel = new JLabel("Máxima cantidad de turistas:");
		maxTuristasLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		maxTuristasLabel.setBounds(7, 201, 212, 14);
		getContentPane().add(maxTuristasLabel);

		JLabel fechaAltaLabel = new JLabel("Fecha de alta:");
		fechaAltaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		fechaAltaLabel.setBounds(7, 227, 120, 14);
		getContentPane().add(fechaAltaLabel);

		JComboBox comboDeps = new JComboBox<String>();
		comboDeps.setBounds(145, 7, 212, 24);
		getContentPane().add(comboDeps);

		comboDeps.addPopupMenuListener(new PopupMenuListener() {

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

		this.comboDepartamentos = comboDeps;

		JComboBox comboActividades = new JComboBox();
		comboActividades.setBounds(145, 32, 212, 24);
		getContentPane().add(comboActividades);
		this.comboActividades = comboActividades;

		JTextArea nombre = new JTextArea();
		nombre.setEditable(false);
		nombre.setBounds(145, 98, 212, 15);
		getContentPane().add(nombre);
		this.nombre = nombre;

		JTextArea horaSalida = new JTextArea();
		horaSalida.setEditable(false);
		horaSalida.setBounds(145, 150, 212, 15);
		getContentPane().add(horaSalida);
		this.horaSalida = horaSalida;

		JTextArea lugarSalida = new JTextArea();
		lugarSalida.setEditable(false);
		lugarSalida.setBounds(145, 177, 212, 15);
		getContentPane().add(lugarSalida);
		this.lugarSalida = lugarSalida;

		JTextArea maxCantTuristas = new JTextArea();
		maxCantTuristas.setEditable(false);
		maxCantTuristas.setBounds(227, 201, 130, 15);
		getContentPane().add(maxCantTuristas);
		this.maxCantTuristas = maxCantTuristas;

		JTextArea fechaAlta = new JTextArea();
		fechaAlta.setEditable(false);
		fechaAlta.setBounds(145, 227, 212, 15);
		getContentPane().add(fechaAlta);
		this.fechaAlta = fechaAlta;

		JComboBox comboSalidas = new JComboBox();
		comboSalidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// salida fue seleccionada:
				seleccionSalida = comboSalidas.getSelectedItem().toString();
				mostrarDatosSalida();
			}
		});
		comboSalidas.setBounds(145, 58, 212, 24);
		getContentPane().add(comboSalidas);
		this.comboSalidas = comboSalidas;

		JLabel salidasTuristicasLabel = new JLabel("Salidas turísticas:");
		salidasTuristicasLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		salidasTuristicasLabel.setBounds(0, 63, 139, 14);
		getContentPane().add(salidasTuristicasLabel);

		JLabel inscriptosLabel = new JLabel("Inscriptos:");
		inscriptosLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		inscriptosLabel.setBounds(17, 253, 81, 14);
		getContentPane().add(inscriptosLabel);

		fechaSalida = new JTextArea();
		fechaSalida.setLineWrap(true);
		fechaSalida.setWrapStyleWord(true);
		fechaSalida.setEditable(false);
		fechaSalida.setBounds(145, 123, 212, 15);
		getContentPane().add(fechaSalida);
		this.fechaSalida = fechaSalida;

		inscripcionList = new JList();
		inscripcionList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (inscripcionList.getSelectedValue() != null) {
					String tur = inscripcionList.getSelectedValue().toString();
					turistaSeleccionado(tur);
				}
			}

			private void turistaSeleccionado(String tur) {
				try {
					IControladorUsuario icu = Fabrica.getInstancia().getIControladorUsuario();
					DTTuristaDetalle datosTur = (DTTuristaDetalle) icu.obtenerDTUsuarioDetalle(tur);
					String nomSal = seleccionSalida;
					DTInscripcion datosInsc;
					datosInsc = icat.obtenerDTInscripcion(datosTur.getNickname(), nomSal);
					nombreInscripto.setText(datosTur.getNombre());
					fechaInscripto.setText(datosInsc.getFechaInscripcion().toString());
					cantTuristasInscripto.setText(String.valueOf(datosInsc.getCantidadTuristas()));
					costoInsc.setText(String.valueOf(datosInsc.getCosto()));
				} catch (ObjetoNoExisteEnTurismoUy e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		inscripcionList.setBounds(17, 279, 137, 133);
		getContentPane().add(inscripcionList);

		JLabel nombreInscriptoLabel = new JLabel("Nombre:");
		nombreInscriptoLabel.setBounds(172, 280, 60, 15);
		getContentPane().add(nombreInscriptoLabel);

		JLabel fechaInscripcionLabel = new JLabel("Fecha:");
		fechaInscripcionLabel.setBounds(172, 307, 60, 15);
		getContentPane().add(fechaInscripcionLabel);

		JLabel cantTuristasLabel = new JLabel("Cant. Turistas:");
		cantTuristasLabel.setBounds(172, 334, 113, 15);
		getContentPane().add(cantTuristasLabel);

		nombreInscripto = new JTextArea();
		nombreInscripto.setBounds(250, 280, 157, 15);
		getContentPane().add(nombreInscripto);

		fechaInscripto = new JTextArea();
		fechaInscripto.setBounds(250, 307, 157, 15);
		getContentPane().add(fechaInscripto);

		cantTuristasInscripto = new JTextArea();
		cantTuristasInscripto.setBounds(287, 334, 120, 15);
		getContentPane().add(cantTuristasInscripto);

		JLabel costoInscLabel = new JLabel("Costo Insc.:");
		costoInscLabel.setBounds(172, 361, 113, 15);
		getContentPane().add(costoInscLabel);

		JTextArea costoInsc = new JTextArea();
		costoInsc.setBounds(287, 361, 120, 15);
		getContentPane().add(costoInsc);
		this.costoInsc = costoInsc;

		// proveedor = new JTextArea();
		// proveedor.setEditable(false);
		// proveedor.setBounds(145, 319, 212, 15);
		// getContentPane().add(proveedor);

		comboDeps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					List<String> actividades = icat
							.obtenerIdActividadesTuristicas(comboDeps.getSelectedItem().toString());
					limpiarFormularioSinDepartamento();
					comboActividades.setModel(new DefaultComboBoxModel(actividades.toArray()));
				} catch (ObjetoNoExisteEnTurismoUy e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		comboActividades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// se cargan identificadores de salidas
				String act = comboActividades.getSelectedItem().toString();
				List<String> salidas;
				try {
					salidas = icat.obtenerIdSalidasTuristicas(act);
					limpiarFormularioSinDepartamentoNiActividad();
					comboSalidas.setModel(new DefaultComboBoxModel(salidas.toArray()));
				} catch (ObjetoNoExisteEnTurismoUy e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	public void seleccionYaHecha(String salida) {
		comboDepartamentos.removeAllItems();
		comboSalidas.removeAllItems();
		comboActividades.removeAllItems();

		seleccionSalida = salida;
		mostrarDatosSalida();
	}

	private void mostrarDatosSalida() {
		DTSalidaTuristicaDetalle salida;
		try {
			salida = contrAct.obtenerDTSalidaTuristicaDetalle(seleccionSalida);
			nombre.setText(salida.getNombre());
			fechaSalida.setText(salida.getFechaHoraSalida().toString());
			horaSalida.setText(salida.getFechaHoraSalida().toString());
			lugarSalida.setText(salida.getLugarSalida());
			maxCantTuristas.setText(String.valueOf(salida.getCantMaxTuristas()));
			fechaAlta.setText(salida.getFechaAlta().toString());
			DefaultListModel<String> listModel = new DefaultListModel<>();

			for (DTInscripcion insc : salida.getInscripciones()) {
				listModel.addElement(insc.getTurista());
			}
			inscripcionList.setModel(listModel);
		} catch (ObjetoNoExisteEnTurismoUy e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void actualizarComboDepartamentos() {
		List<String> deps = contrAct.obtenerIdDepartamentos();
		comboDepartamentos.setModel(new DefaultComboBoxModel(deps.toArray()));
	}

	private void limpiarFormularioSinDepartamento() {
		nombre.setText("");
		fechaSalida.setText("");
		horaSalida.setText("");
		lugarSalida.setText("");
		maxCantTuristas.setText("");
		fechaAlta.setText("");
		nombreInscripto.setText("");
		fechaInscripto.setText("");
		cantTuristasInscripto.setText("");

		inscripcionList.setModel(new DefaultListModel<>());

		comboActividades.setModel(new DefaultComboBoxModel<>(new String[0]));
		comboSalidas.setModel(new DefaultComboBoxModel<>(new String[0]));
	}

	private void limpiarFormularioSinDepartamentoNiActividad() {
		nombre.setText("");
		fechaSalida.setText("");
		horaSalida.setText("");
		lugarSalida.setText("");
		maxCantTuristas.setText("");
		fechaAlta.setText("");
		nombreInscripto.setText("");
		fechaInscripto.setText("");
		cantTuristasInscripto.setText("");
		costoInsc.setText("");

		inscripcionList.setModel(new DefaultListModel<>());
		comboSalidas.setModel(new DefaultComboBoxModel<>(new String[0]));
	}

	private void limpiarFormulario() {
		nombre.setText("");
		fechaSalida.setText("");
		horaSalida.setText("");
		lugarSalida.setText("");
		maxCantTuristas.setText("");
		fechaAlta.setText("");
		nombreInscripto.setText("");
		fechaInscripto.setText("");
		cantTuristasInscripto.setText("");
		costoInsc.setText("");

		inscripcionList.setModel(new DefaultListModel<>());

		comboDepartamentos.setModel(new DefaultComboBoxModel<>(new String[0]));
		comboActividades.setModel(new DefaultComboBoxModel<>(new String[0]));
		comboSalidas.setModel(new DefaultComboBoxModel<>(new String[0]));
	}
}
