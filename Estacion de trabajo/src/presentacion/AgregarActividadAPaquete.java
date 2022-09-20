package presentacion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import excepciones.ActividadTuristicaYaRegistradaException;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorPaquete;

public class AgregarActividadAPaquete extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	
	private IControladorPaquete controladorPaq;
	private IControladorActividadTuristica controladorAct;

	private JComboBox comboPaquetes;
	private JComboBox comboDepartamentos;
	private JComboBox comboActividades;
	
	private String TestPMD;

	/**
	 * Create the frame.
	 */
	public AgregarActividadAPaquete(IControladorPaquete controladorPaq, IControladorActividadTuristica cat) {
		this.controladorPaq = controladorPaq;
		this.controladorAct = cat;

		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent event) {
				cancelar();
			}
		});
		setTitle("Agregar Actividad Turística a Paquete");
		setBounds(100, 100, 410, 206);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel selecciones = new JPanel();
		panel.add(selecciones, BorderLayout.NORTH);
		selecciones.setLayout(new BoxLayout(selecciones, BoxLayout.Y_AXIS));

		JPanel panelSeleccionPaquete = new JPanel();
		selecciones.add(panelSeleccionPaquete);

		JLabel lblNewLabel2 = new JLabel(" Paquete:");

		comboPaquetes = new JComboBox();
		comboPaquetes.addPopupMenuListener(new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent event) {
				actualizarComboPaquete();
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent event) {

			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent event) {

			}

		});
		panelSeleccionPaquete.setLayout(new BoxLayout(panelSeleccionPaquete, BoxLayout.X_AXIS));
		panelSeleccionPaquete.add(lblNewLabel2);

		Component gluePaquete = Box.createHorizontalStrut(120);
		panelSeleccionPaquete.add(gluePaquete);
		panelSeleccionPaquete.add(comboPaquetes);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelSeleccionPaquete.add(horizontalStrut);

		comboPaquetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				selecionarPaquete();
			}
		});

		Component verticalStrut = Box.createVerticalStrut(10);
		selecciones.add(verticalStrut);

		JPanel panelSeleccionDepartamento = new JPanel();
		selecciones.add(panelSeleccionDepartamento);
		panelSeleccionDepartamento.setLayout(new BoxLayout(panelSeleccionDepartamento, BoxLayout.X_AXIS));

		JLabel lblNewLabel21 = new JLabel("Departamento de la Actividad:");
		panelSeleccionDepartamento.add(lblNewLabel21);

		comboDepartamentos = new JComboBox();

		comboDepartamentos.addPopupMenuListener(new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent event) {
				actualizarComboDepartamentos();
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent event) {

			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent event) {

			}

		});

		comboDepartamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				seleccionDepartamento();
			}
		});

		Component glueDepartamento = Box.createHorizontalStrut(5);
		panelSeleccionDepartamento.add(glueDepartamento);
		panelSeleccionDepartamento.add(comboDepartamentos);

		Component horizontalStrut1 = Box.createHorizontalStrut(20);
		panelSeleccionDepartamento.add(horizontalStrut1);

		Component verticalStrut1 = Box.createVerticalStrut(10);
		selecciones.add(verticalStrut1);

		JPanel panelSeleccionActividad = new JPanel();
		selecciones.add(panelSeleccionActividad);
		panelSeleccionActividad.setLayout(new BoxLayout(panelSeleccionActividad, BoxLayout.X_AXIS));

		JLabel lblNewLabel211 = new JLabel("Actividad:");
		lblNewLabel211.setMinimumSize(new Dimension(100, 14));
		lblNewLabel211.setMaximumSize(new Dimension(100, 14));
		panelSeleccionActividad.add(lblNewLabel211);

		comboActividades = new JComboBox();
		comboActividades.setEnabled(false);
		comboActividades.addPopupMenuListener(new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent event) {
				actualizarComboActividades();
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent event) {

			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent event) {

			}

		});

		Component glueActividadTuristica = Box.createHorizontalStrut(120);
		panelSeleccionActividad.add(glueActividadTuristica);

		panelSeleccionActividad.add(comboActividades);

		Component horizontalStrut2 = Box.createHorizontalStrut(20);
		panelSeleccionActividad.add(horizontalStrut2);

		JPanel parteInferior = new JPanel();
		panel.add(parteInferior, BorderLayout.SOUTH);
		parteInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				cancelar();
			}
		});
		parteInferior.add(botonCancelar);

		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				agregarActividad();
			}
		});
		parteInferior.add(botonAgregar);

	}

	@Override
	public void setVisible(boolean visi) {
		if (visi) {
			limpiarSelecciones();
			actualizarComboDepartamentos();
			actualizarComboPaquete();
		}
		super.setVisible(visi);
	}

	private void seleccionDepartamento() {
		if (comboPaquetes.getSelectedItem() != null) {
			comboActividades.setEnabled(true);
			actualizarComboActividades();
		}
	}

	private void selecionarPaquete() {
		if (comboDepartamentos.getSelectedItem() != null) {
			comboActividades.setEnabled(true);
			actualizarComboActividades();
		}
	}

	private void actualizarComboPaquete() {
		List<String> idsPaquetes = controladorPaq.obtenerIdPaquetes();
		comboPaquetes.setModel(new DefaultComboBoxModel(idsPaquetes.toArray()));
		if (!idsPaquetes.isEmpty()) {
			comboPaquetes.setSelectedIndex(0);
			if (comboDepartamentos.getSelectedItem() != null) {
				actualizarComboActividades();
			}
		}
	}

	private void actualizarComboDepartamentos() {
		List<String> idsDepartamentos = controladorAct.obtenerIdDepartamentos();
		comboDepartamentos.setModel(new DefaultComboBoxModel<>(idsDepartamentos.toArray()));
		if (!idsDepartamentos.isEmpty()) {
			comboDepartamentos.setSelectedIndex(0);
			if (comboPaquetes.getSelectedItem() != null) {
				actualizarComboActividades();
			}
		}

	}

	private void actualizarComboActividades() {
		String depId = (String) comboDepartamentos.getSelectedItem();
		String packId = (String) comboPaquetes.getSelectedItem();
		// No va a estar habilitado el combo si no seleciono la actividad y el
		// pack
		List<String> idsActividades = controladorPaq.obtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete(depId,
				packId);
		comboActividades.setModel(new DefaultComboBoxModel<>(idsActividades.toArray()));
		if (!idsActividades.isEmpty()) {
			comboActividades.setSelectedIndex(0);
		}
	}

	private void limpiarSelecciones() {
		actualizarComboPaquete();
		actualizarComboDepartamentos();
	}

	private void agregarActividad() {
		String act = (String) comboActividades.getSelectedItem();
		String pack = (String) comboPaquetes.getSelectedItem();

		if (act == null || pack == null) {
			JOptionPane.showMessageDialog(null,
					"Se debe seleccionar un departamento, actividad de actividad turística y un paquete", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				controladorPaq.agregarActividadAPaquete(act, pack);
				setVisible(false);
				limpiarSelecciones();
				JOptionPane.showMessageDialog(null, "Se agregó la actividad al paquete",
						"Registro de asociación actividad a paquete", JOptionPane.INFORMATION_MESSAGE);
			} catch (ActividadTuristicaYaRegistradaException exception) {
				JOptionPane.showMessageDialog(null,
						"Se debe seleccionar una actividad que no esté ya dentro del paquete", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private void cancelar() {
		setVisible(false);
		limpiarSelecciones();
	}

}
