package presentacion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import excepciones.FechaAltaSalidaTuristicaPosteriorAFechaInscripcion;
import excepciones.InscripcionYaRegistradaException;
import excepciones.SuperaElMaximoDeTuristasException;
import excepciones.TurismoUyException;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTSalidaTuristica;

public class InscribirseASalidaTurística extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	
	private IControladorActividadTuristica contrActTur;
	private IControladorUsuario contrUsuario;

	private JSpinner diaSpinner;
	private JSpinner mesSpinner;
	private JSpinner anioSpinner;
	private JSpinner cantTuristasSprinner;
	private Map<String, DTSalidaTuristica> dtSalidas;

	private JTextField nombreTextField;
	private JTextField lugarTextField;
	private JTextField fechaAltaTextField;
	private JTextField capacidadDeTuristaTextField;
	private JTextField fechaYHoraDeSalidaTextField;
	private JList salidaList;

	private JComboBox comboDepartamento;
	private JComboBox comboActividad;
	private JComboBox comboTurista;

	/**
	 * Create the frame.
	 */
	public InscribirseASalidaTurística(IControladorActividadTuristica contrActTur, IControladorUsuario contrUsuario) {
		this.contrActTur = contrActTur;
		this.contrUsuario = contrUsuario;

		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent event) {
				cancelar();
			}
		});
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Inscribirse a salida turistica");
		setBounds(100, 100, 600, 427);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel botones = new JPanel();
		getContentPane().add(botones, BorderLayout.SOUTH);
		botones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				cancelar();
			}
		});
		botones.add(cancelarButton);

		JButton aceptarButton = new JButton("Aceptar");
		aceptarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				acpetar();
			}
		});
		botones.add(aceptarButton);

		JPanel elementos = new JPanel();
		getContentPane().add(elementos, BorderLayout.NORTH);
		elementos.setLayout(new BoxLayout(elementos, BoxLayout.Y_AXIS));

		JPanel departamentoPanel = new JPanel();
		elementos.add(departamentoPanel);
		departamentoPanel.setLayout(new BoxLayout(departamentoPanel, BoxLayout.X_AXIS));

		Component horizontalStrut23 = Box.createHorizontalStrut(20);
		departamentoPanel.add(horizontalStrut23);

		JLabel lblNewLabel = new JLabel("Departamento:");
		departamentoPanel.add(lblNewLabel);

		Component horizontalStrut = Box.createHorizontalStrut(43);
		departamentoPanel.add(horizontalStrut);

		comboDepartamento = new JComboBox();
		comboDepartamento.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent event) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent event) {
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent event) {
				actualizarDepartamentos();
			}
		});
		comboDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				seleccionarDepartamento();
			}
		});
		departamentoPanel.add(comboDepartamento);

		Component horizontalStrut1 = Box.createHorizontalStrut(20);
		departamentoPanel.add(horizontalStrut1);

		Component verticalStrut1 = Box.createVerticalStrut(10);
		elementos.add(verticalStrut1);

		JPanel actividadPanel = new JPanel();
		elementos.add(actividadPanel);
		actividadPanel.setLayout(new BoxLayout(actividadPanel, BoxLayout.X_AXIS));

		Component horizontalStrut24 = Box.createHorizontalStrut(20);
		actividadPanel.add(horizontalStrut24);

		JLabel lblNewLabel1 = new JLabel("Actividad Turistica:");
		actividadPanel.add(lblNewLabel1);

		Component horizontalStrut2 = Box.createHorizontalStrut(20);
		actividadPanel.add(horizontalStrut2);

		comboActividad = new JComboBox();
		comboActividad.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent event) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent event) {
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent event) {
				actualizarActividadesTuristicas();
			}
		});
		comboActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				seleccionarActividadesTuristicas();
			}
		});
		actividadPanel.add(comboActividad);

		Component horizontalStrut3 = Box.createHorizontalStrut(20);
		actividadPanel.add(horizontalStrut3);

		Component verticalStrut2 = Box.createVerticalStrut(10);
		elementos.add(verticalStrut2);

		JPanel infoSalida = new JPanel();
		infoSalida.setBorder(
				new TitledBorder(null, "Salidas turisticas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		elementos.add(infoSalida);
		infoSalida.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		infoSalida.add(panel, BorderLayout.WEST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel2 = new JPanel();
		panel.add(panel2);
		panel2.setLayout(new BorderLayout(0, 0));

		JPanel panel8 = new JPanel();
		panel2.add(panel8, BorderLayout.NORTH);

		Component horizontalStrut6 = Box.createHorizontalStrut(40);
		panel8.add(horizontalStrut6);

		JLabel lblNewLabel4 = new JLabel("Seleccione una salida");
		panel8.add(lblNewLabel4);

		Component horizontalStrut7 = Box.createHorizontalStrut(40);
		panel8.add(horizontalStrut7);

		JScrollPane scrollPane = new JScrollPane();
		panel2.add(scrollPane, BorderLayout.CENTER);

		salidaList = new JList();
		salidaList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				seleccionarSalidaTuristicas();
			}
		});
		salidaList.setVisibleRowCount(5);
		scrollPane.setViewportView(salidaList);

		JPanel panel11 = new JPanel();
		panel2.add(panel11, BorderLayout.SOUTH);
		panel11.setLayout(new BoxLayout(panel11, BoxLayout.X_AXIS));

		JButton btnNewButton = new JButton("Recargar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				recargarSalidas();
			}
		});
		panel11.add(btnNewButton);

		JPanel panel1 = new JPanel();
		infoSalida.add(panel1);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

		Component verticalStrut = Box.createVerticalStrut(20);
		panel1.add(verticalStrut);

		JPanel panel3 = new JPanel();
		panel1.add(panel3);
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

		Component horizontalStrut18 = Box.createHorizontalStrut(20);
		panel3.add(horizontalStrut18);

		JLabel lblNewLabel3 = new JLabel("Nombre");
		panel3.add(lblNewLabel3);

		Component horizontalStrut8 = Box.createHorizontalStrut(93);
		panel3.add(horizontalStrut8);

		nombreTextField = new JTextField();
		nombreTextField.setEditable(false);
		panel3.add(nombreTextField);
		nombreTextField.setColumns(10);

		Component horizontalStrut13 = Box.createHorizontalStrut(20);
		panel3.add(horizontalStrut13);

		JPanel panel4 = new JPanel();
		panel1.add(panel4);
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));

		Component horizontalStrut19 = Box.createHorizontalStrut(20);
		panel4.add(horizontalStrut19);

		JLabel lblNewLabel5 = new JLabel("Lugar salida");
		panel4.add(lblNewLabel5);

		Component horizontalStrut9 = Box.createHorizontalStrut(68);
		panel4.add(horizontalStrut9);

		lugarTextField = new JTextField();
		lugarTextField.setEditable(false);
		panel4.add(lugarTextField);
		lugarTextField.setColumns(10);

		Component horizontalStrut14 = Box.createHorizontalStrut(20);
		panel4.add(horizontalStrut14);

		JPanel panel5 = new JPanel();
		panel1.add(panel5);
		panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));

		Component horizontalStrut20 = Box.createHorizontalStrut(20);
		panel5.add(horizontalStrut20);

		JLabel lblNewLabel6 = new JLabel("Fecha alta");
		panel5.add(lblNewLabel6);

		Component horizontalStrut10 = Box.createHorizontalStrut(80);
		panel5.add(horizontalStrut10);

		fechaAltaTextField = new JTextField();
		fechaAltaTextField.setEditable(false);
		panel5.add(fechaAltaTextField);
		fechaAltaTextField.setColumns(10);

		Component horizontalStrut15 = Box.createHorizontalStrut(20);
		panel5.add(horizontalStrut15);

		JPanel panel6 = new JPanel();
		panel1.add(panel6);
		panel6.setLayout(new BoxLayout(panel6, BoxLayout.X_AXIS));

		Component horizontalStrut21 = Box.createHorizontalStrut(20);
		panel6.add(horizontalStrut21);

		JLabel lblNewLabel7 = new JLabel("Capacidad de turistas");
		panel6.add(lblNewLabel7);

		Component horizontalStrut11 = Box.createHorizontalStrut(14);
		panel6.add(horizontalStrut11);

		capacidadDeTuristaTextField = new JTextField();
		capacidadDeTuristaTextField.setEditable(false);
		panel6.add(capacidadDeTuristaTextField);
		capacidadDeTuristaTextField.setColumns(10);

		Component horizontalStrut16 = Box.createHorizontalStrut(20);
		panel6.add(horizontalStrut16);

		JPanel panel7 = new JPanel();
		panel1.add(panel7);
		panel7.setLayout(new BoxLayout(panel7, BoxLayout.X_AXIS));

		Component horizontalStrut_22 = Box.createHorizontalStrut(20);
		panel7.add(horizontalStrut_22);

		JLabel lblNewLabel8 = new JLabel("Fecha y hora de salida");
		panel7.add(lblNewLabel8);

		Component horizontalStrut12 = Box.createHorizontalStrut(12);
		panel7.add(horizontalStrut12);

		fechaYHoraDeSalidaTextField = new JTextField();
		fechaYHoraDeSalidaTextField.setEditable(false);
		panel7.add(fechaYHoraDeSalidaTextField);
		fechaYHoraDeSalidaTextField.setColumns(10);

		Component horizontalStrut17 = Box.createHorizontalStrut(20);
		panel7.add(horizontalStrut17);

		Component verticalStrut4 = Box.createVerticalStrut(10);
		elementos.add(verticalStrut4);

		JPanel turista = new JPanel();
		elementos.add(turista);
		turista.setLayout(new BoxLayout(turista, BoxLayout.X_AXIS));

		Component horizontalStrut25 = Box.createHorizontalStrut(20);
		turista.add(horizontalStrut25);

		JLabel lblNewLabel2 = new JLabel("Turista: ");
		turista.add(lblNewLabel2);

		Component horizontalStrut4 = Box.createHorizontalStrut(80);
		turista.add(horizontalStrut4);

		comboTurista = new JComboBox();
		comboTurista.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent event) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent event) {
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent event) {
				actualizarTuristas();
			}
		});
		turista.add(comboTurista);

		Component horizontalStrut5 = Box.createHorizontalStrut(20);
		turista.add(horizontalStrut5);

		Component verticalStrut11 = Box.createVerticalStrut(10);
		elementos.add(verticalStrut11);

		JPanel panel9 = new JPanel();
		elementos.add(panel9);
		panel9.setLayout(new BoxLayout(panel9, BoxLayout.X_AXIS));

		Component horizontalStrut26 = Box.createHorizontalStrut(20);
		panel9.add(horizontalStrut26);

		JLabel lblNewLabel9 = new JLabel("Fecha inscripción");
		panel9.add(lblNewLabel9);

		Component horizontalStrut28 = Box.createHorizontalStrut(27);
		panel9.add(horizontalStrut28);

		diaSpinner = new JSpinner();
		diaSpinner.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		panel9.add(diaSpinner);

		JLabel lblNewLabel11 = new JLabel(" / ");
		panel9.add(lblNewLabel11);

		mesSpinner = new JSpinner();
		mesSpinner.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		panel9.add(mesSpinner);

		JLabel lblNewLabel12 = new JLabel(" / ");
		panel9.add(lblNewLabel12);

		anioSpinner = new JSpinner();
		anioSpinner.setModel(new SpinnerNumberModel(2022, 1900, 2100, 1));
		panel9.add(anioSpinner);

		JLabel lblNewLabel13 = new JLabel(" (dd/mm/yyyy)");
		panel9.add(lblNewLabel13);

		Component horizontalStrut27 = Box.createHorizontalStrut(20);
		panel9.add(horizontalStrut27);

		Component horizontalGlue = Box.createHorizontalGlue();
		panel9.add(horizontalGlue);

		Component verticalStrut22 = Box.createVerticalStrut(10);
		elementos.add(verticalStrut22);

		JPanel panel10 = new JPanel();
		elementos.add(panel10);
		panel10.setLayout(new BoxLayout(panel10, BoxLayout.X_AXIS));

		Component horizontalStrut29 = Box.createHorizontalStrut(20);
		panel10.add(horizontalStrut29);

		JLabel lblNewLabel10 = new JLabel("Cantidad turistas");
		panel10.add(lblNewLabel10);

		Component horizontalStrut30 = Box.createHorizontalStrut(30);
		panel10.add(horizontalStrut30);

		cantTuristasSprinner = new JSpinner();
		cantTuristasSprinner
				.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		panel10.add(cantTuristasSprinner);

		Component horizontalStrut31 = Box.createHorizontalStrut(20);
		panel10.add(horizontalStrut31);

	}

	private void actualizarDepartamentos() {
		List<String> idDeps = contrActTur.obtenerIdDepartamentos();
		comboDepartamento.setModel(new DefaultComboBoxModel<>(idDeps.toArray()));
		if (!idDeps.isEmpty()) {
			comboActividad.setEnabled(true);
			comboDepartamento.setSelectedIndex(0);
			seleccionarDepartamento();
			actualizarActividadesTuristicas();
		}

	}

	private void seleccionarDepartamento() {
		comboActividad.setEnabled(true);
		actualizarActividadesTuristicas();
	}

	private void actualizarActividadesTuristicas() {
		String dep = (String) comboDepartamento.getSelectedItem();
		List<String> idActs = contrActTur.obtenerIdActividadesTuristicas(dep);
		comboActividad.setModel(new DefaultComboBoxModel<>(idActs.toArray()));
		if (!idActs.isEmpty()) {
			comboActividad.setSelectedIndex(0);
			seleccionarActividadesTuristicas();
		}
		actualizarSalidaTuristicas();
	}

	private void seleccionarActividadesTuristicas() {
		actualizarSalidaTuristicas();
	}

	private void actualizarSalidaTuristicas() {
		String act = (String) comboActividad.getSelectedItem();
		if (act == null) {
			dtSalidas = new HashMap<>();
			DefaultListModel<String> listModel = new DefaultListModel<>();
			salidaList.setModel(listModel);
		} else {
			List<DTSalidaTuristica> salidasLists = contrActTur.obtenerDTSalidasTuristicas(act);
			dtSalidas = new HashMap<>();
			for (DTSalidaTuristica salidaDtIter : salidasLists) {
				dtSalidas.put(salidaDtIter.getNombre(), salidaDtIter);
			}
			DefaultListModel<String> listModel = new DefaultListModel<>();
			for (DTSalidaTuristica ss : dtSalidas.values()) {
				listModel.addElement(ss.getNombre());
			}
			salidaList.setModel(listModel);

			if (dtSalidas.size() > 0) {
				salidaList.setSelectedIndex(0);
			}
		}

		seleccionarSalidaTuristicas();

	}

	private void seleccionarSalidaTuristicas() {
		String idSeleccionado = (String) salidaList.getSelectedValue();
		if (idSeleccionado == null) {
			nombreTextField.setText("");
			lugarTextField.setText("");
			fechaAltaTextField.setText("");
			fechaYHoraDeSalidaTextField.setText("");
			capacidadDeTuristaTextField.setText("");
		} else {
			DTSalidaTuristica seleccionado = dtSalidas.get(idSeleccionado);
			nombreTextField.setText(seleccionado.getNombre());
			lugarTextField.setText(seleccionado.getLugarSalida());

			if (seleccionado.getFechaAlta() != null) {
				fechaAltaTextField
						.setText(seleccionado.getFechaAlta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			} else {
				fechaAltaTextField.setText("");
			}

			if (seleccionado.getFechaHoraSalida() != null) {
				fechaYHoraDeSalidaTextField.setText(seleccionado.getFechaHoraSalida()
						.format(DateTimeFormatter.ofPattern("dd/MM/yyyy ' a las ' HH:mm")));
			} else {
				fechaYHoraDeSalidaTextField.setText("");
			}

			capacidadDeTuristaTextField.setText(String.valueOf(seleccionado.getCantMaxTuristas()));
		}

	}

	private void actualizarTuristas() {
		List<String> idTuris = contrUsuario.obtenerIdTuristas();
		comboTurista.setModel(new DefaultComboBoxModel<>(idTuris.toArray()));
		if (!idTuris.isEmpty()) {
			comboTurista.setSelectedIndex(0);
		}
	}

	private void recargarSalidas() {
		actualizarSalidaTuristicas();
	}

	private void cancelar() {
		limpiarFormulario();
	}

	private void acpetar() {
		String idSalida = (String) salidaList.getSelectedValue();
		String idTuristas = (String) comboTurista.getSelectedItem();
		if (idSalida == null) {
			JOptionPane.showMessageDialog(null,
					"Se debe seleccionar una salida turística, seleccione una y vuelve a intentar ", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (idTuristas == null) {
			JOptionPane.showMessageDialog(null, "Se debe seleccionar un turista, seleccione uno y vuelve a intentar",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		LocalDate fechaInscripcion;
		try {
			int day = (int) diaSpinner.getValue();
			int mouth = (int) mesSpinner.getValue();
			int year = (int) anioSpinner.getValue();
			fechaInscripcion = LocalDate.of(year, mouth, day);
		} catch (Exception exception) { // TODO: averiguar qué excepcion tira
										// LocalDate
			JOptionPane.showMessageDialog(null, "La fecha de inscripción elegida inválida.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		int cantidadTurista = (int) cantTuristasSprinner.getValue();
		try {
			contrActTur.altaInscripcionSalidaTuristica(idSalida, idTuristas, cantidadTurista, fechaInscripcion);
			setVisible(false);
			limpiarFormulario();
			JOptionPane.showMessageDialog(null, "Inscripción creada con éxito.", "Error",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (InscripcionYaRegistradaException exception) {
			JOptionPane.showMessageDialog(null, "Este turista ya esta inscripto a esta salida turística.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (SuperaElMaximoDeTuristasException exception) {
			JOptionPane.showMessageDialog(null,
					"Con los turistas de esta inscripción se supera la cantidad máxima de turistas permitidos en esta salida turística.",
					"Error", JOptionPane.ERROR_MESSAGE);
		} catch (FechaAltaSalidaTuristicaPosteriorAFechaInscripcion exception) {
			JOptionPane.showMessageDialog(null,
					"La fecha de inscripción no puede ser anterior a la fecha de alta de la salida turística.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (TurismoUyException exception) {
			JOptionPane.showMessageDialog(null, "Error general al crear la inscripción.", "Error",
					JOptionPane.ERROR_MESSAGE);
			exception.printStackTrace();
		}
	}

	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		if (aFlag) {
			// Cuando se encienda el formulario tambien nos aseguramos que to_do
			// este limpio
			limpiarFormulario();
		}

	}

	private void limpiarFormulario() {
		actualizarDepartamentos(); // Eso actualiza las actividades y salidas
		actualizarTuristas();
		diaSpinner.setValue(1);
		mesSpinner.setValue(1);
		anioSpinner.setValue(2022);
		cantTuristasSprinner.setValue(1);

	}

}
