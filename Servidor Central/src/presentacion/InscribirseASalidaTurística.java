package presentacion;

import excepciones.FechaAltaSalidaTuristicaPosteriorAFechaInscripcion;
import excepciones.InscripcionYaRegistradaException;
import excepciones.SuperaElMaximoDeTuristasException;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTSalidaTuristica;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class InscribirseASalidaTurística extends JInternalFrame {
	private IControladorActividadTuristica cat;
	private IControladorUsuario cu;

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
	public InscribirseASalidaTurística(IControladorActividadTuristica cat, IControladorUsuario cu) {
		this.cat = cat;
		this.cu = cu;
		
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
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
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		botones.add(cancelarButton);
		
		JButton aceptarButton = new JButton("Aceptar");
		aceptarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		Component horizontalStrut_23 = Box.createHorizontalStrut(20);
		departamentoPanel.add(horizontalStrut_23);
		
		JLabel lblNewLabel = new JLabel("Departamento:");
		departamentoPanel.add(lblNewLabel);
		
		Component horizontalStrut = Box.createHorizontalStrut(43);
		departamentoPanel.add(horizontalStrut);
		
		comboDepartamento = new JComboBox();
		comboDepartamento.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				actualizarDepartamentos();
			}
		});
		comboDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarDepartamento();
			}
		});
		departamentoPanel.add(comboDepartamento);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		departamentoPanel.add(horizontalStrut_1);
		
		Component verticalStrut1 = Box.createVerticalStrut(10);
		elementos.add(verticalStrut1);
		
		JPanel actividadPanel = new JPanel();
		elementos.add(actividadPanel);
		actividadPanel.setLayout(new BoxLayout(actividadPanel, BoxLayout.X_AXIS));
		
		Component horizontalStrut_24 = Box.createHorizontalStrut(20);
		actividadPanel.add(horizontalStrut_24);
		
		JLabel lblNewLabel_1 = new JLabel("Actividad Turistica:");
		actividadPanel.add(lblNewLabel_1);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		actividadPanel.add(horizontalStrut_2);
		
		comboActividad = new JComboBox();
		comboActividad.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				actualizarActividadesTuristicas();
			}
		});
		comboActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarActividadesTuristicas();
			}
		});
		actividadPanel.add(comboActividad);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		actividadPanel.add(horizontalStrut_3);
		
		Component verticalStrut2 = Box.createVerticalStrut(10);
		elementos.add(verticalStrut2);
		
		JPanel infoSalida = new JPanel();
		infoSalida.setBorder(new TitledBorder(null, "Salidas turisticas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		elementos.add(infoSalida);
		infoSalida.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		infoSalida.add(panel, BorderLayout.WEST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8, BorderLayout.NORTH);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(40);
		panel_8.add(horizontalStrut_6);
		
		JLabel lblNewLabel_4 = new JLabel("Seleccione una salida");
		panel_8.add(lblNewLabel_4);
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(40);
		panel_8.add(horizontalStrut_7);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		salidaList = new JList();
		salidaList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				seleccionarSalidaTuristicas();
			}
		});
		salidaList.setVisibleRowCount(5);
		scrollPane.setViewportView(salidaList);
		
		JPanel panel_11 = new JPanel();
		panel_2.add(panel_11, BorderLayout.SOUTH);
		panel_11.setLayout(new BoxLayout(panel_11, BoxLayout.X_AXIS));
		
		JButton btnNewButton = new JButton("Recargar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recargarSalidas();
			}
		});
		panel_11.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		infoSalida.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		Component horizontalStrut_18 = Box.createHorizontalStrut(20);
		panel_3.add(horizontalStrut_18);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre");
		panel_3.add(lblNewLabel_3);
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(93);
		panel_3.add(horizontalStrut_8);
		
		nombreTextField = new JTextField();
		nombreTextField.setEditable(false);
		panel_3.add(nombreTextField);
		nombreTextField.setColumns(10);
		
		Component horizontalStrut_13 = Box.createHorizontalStrut(20);
		panel_3.add(horizontalStrut_13);
		
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		Component horizontalStrut_19 = Box.createHorizontalStrut(20);
		panel_4.add(horizontalStrut_19);
		
		JLabel lblNewLabel_5 = new JLabel("Lugar salida");
		panel_4.add(lblNewLabel_5);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(68);
		panel_4.add(horizontalStrut_9);
		
		lugarTextField = new JTextField();
		lugarTextField.setEditable(false);
		panel_4.add(lugarTextField);
		lugarTextField.setColumns(10);
		
		Component horizontalStrut_14 = Box.createHorizontalStrut(20);
		panel_4.add(horizontalStrut_14);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
		
		Component horizontalStrut_20 = Box.createHorizontalStrut(20);
		panel_5.add(horizontalStrut_20);
		
		JLabel lblNewLabel_6 = new JLabel("Fecha alta");
		panel_5.add(lblNewLabel_6);
		
		Component horizontalStrut_10 = Box.createHorizontalStrut(80);
		panel_5.add(horizontalStrut_10);
		
		fechaAltaTextField = new JTextField();
		fechaAltaTextField.setEditable(false);
		panel_5.add(fechaAltaTextField);
		fechaAltaTextField.setColumns(10);
		
		Component horizontalStrut_15 = Box.createHorizontalStrut(20);
		panel_5.add(horizontalStrut_15);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));
		
		Component horizontalStrut_21 = Box.createHorizontalStrut(20);
		panel_6.add(horizontalStrut_21);
		
		JLabel lblNewLabel_7 = new JLabel("Capacidad de turistas");
		panel_6.add(lblNewLabel_7);
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(14);
		panel_6.add(horizontalStrut_11);
		
		capacidadDeTuristaTextField = new JTextField();
		capacidadDeTuristaTextField.setEditable(false);
		panel_6.add(capacidadDeTuristaTextField);
		capacidadDeTuristaTextField.setColumns(10);
		
		Component horizontalStrut_16 = Box.createHorizontalStrut(20);
		panel_6.add(horizontalStrut_16);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));
		
		Component horizontalStrut_22 = Box.createHorizontalStrut(20);
		panel_7.add(horizontalStrut_22);
		
		JLabel lblNewLabel_8 = new JLabel("Fecha y hora de salida");
		panel_7.add(lblNewLabel_8);
		
		Component horizontalStrut_12 = Box.createHorizontalStrut(12);
		panel_7.add(horizontalStrut_12);
		
		fechaYHoraDeSalidaTextField = new JTextField();
		fechaYHoraDeSalidaTextField.setEditable(false);
		panel_7.add(fechaYHoraDeSalidaTextField);
		fechaYHoraDeSalidaTextField.setColumns(10);
		
		Component horizontalStrut_17 = Box.createHorizontalStrut(20);
		panel_7.add(horizontalStrut_17);
		

		
		Component verticalStrut4 = Box.createVerticalStrut(10);
		elementos.add(verticalStrut4);

		
		JPanel turista = new JPanel();
		elementos.add(turista);
		turista.setLayout(new BoxLayout(turista, BoxLayout.X_AXIS));
		
		Component horizontalStrut_25 = Box.createHorizontalStrut(20);
		turista.add(horizontalStrut_25);
		
		JLabel lblNewLabel_2 = new JLabel("Turista: ");
		turista.add(lblNewLabel_2);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(80);
		turista.add(horizontalStrut_4);
		
		comboTurista = new JComboBox();
		comboTurista.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				actualizarTuristas();
			}
		});
		turista.add(comboTurista);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		turista.add(horizontalStrut_5);
		
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		elementos.add(verticalStrut_1);
		
		JPanel panel_9 = new JPanel();
		elementos.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.X_AXIS));
		
		Component horizontalStrut_26 = Box.createHorizontalStrut(20);
		panel_9.add(horizontalStrut_26);
		
		JLabel lblNewLabel_9 = new JLabel("Fecha inscripcion");
		panel_9.add(lblNewLabel_9);
		
		Component horizontalStrut_28 = Box.createHorizontalStrut(27);
		panel_9.add(horizontalStrut_28);
		
		diaSpinner = new JSpinner();
		diaSpinner.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		panel_9.add(diaSpinner);
		
		JLabel lblNewLabel_11 = new JLabel(" / ");
		panel_9.add(lblNewLabel_11);
		
		mesSpinner = new JSpinner();
		mesSpinner.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		panel_9.add(mesSpinner);
		
		JLabel lblNewLabel_12 = new JLabel(" / ");
		panel_9.add(lblNewLabel_12);
		
		anioSpinner = new JSpinner();
		anioSpinner.setModel(new SpinnerNumberModel(2022, 1900, 2100, 1));
		panel_9.add(anioSpinner);
		
		JLabel lblNewLabel_13 = new JLabel(" (dd/mm/yyyy)");
		panel_9.add(lblNewLabel_13);
		
		Component horizontalStrut_27 = Box.createHorizontalStrut(20);
		panel_9.add(horizontalStrut_27);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel_9.add(horizontalGlue);
		
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		elementos.add(verticalStrut_2);
		
		JPanel panel_10 = new JPanel();
		elementos.add(panel_10);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.X_AXIS));
		
		Component horizontalStrut_29 = Box.createHorizontalStrut(20);
		panel_10.add(horizontalStrut_29);
		
		JLabel lblNewLabel_10 = new JLabel("Cantidad turistas");
		panel_10.add(lblNewLabel_10);
		
		Component horizontalStrut_30 = Box.createHorizontalStrut(30);
		panel_10.add(horizontalStrut_30);
		
		cantTuristasSprinner = new JSpinner();
		cantTuristasSprinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		panel_10.add(cantTuristasSprinner);
		
		Component horizontalStrut_31 = Box.createHorizontalStrut(20);
		panel_10.add(horizontalStrut_31);

	}


	private void actualizarDepartamentos(){
		List<String> idDeps = cat.obtenerIdDepartamentos();
		comboDepartamento.setModel(new DefaultComboBoxModel<>(idDeps.toArray()));
		if(idDeps.size() > 0){
			comboActividad.setEnabled(true);
			comboDepartamento.setSelectedIndex(0);
			seleccionarDepartamento();
			actualizarActividadesTuristicas();
		}

	}

	private void seleccionarDepartamento(){
		comboActividad.setEnabled(true);
		actualizarActividadesTuristicas();
	}

	private void actualizarActividadesTuristicas(){
		String dep = (String)comboDepartamento.getSelectedItem();
		List<String> idActs = cat.obtenerIdActividadesTuristicas(dep);
		comboActividad.setModel(new DefaultComboBoxModel<>(idActs.toArray()));
		if(idActs.size() > 0){
			comboActividad.setSelectedIndex(0);
			seleccionarActividadesTuristicas();
		}
		actualizarSalidaTuristicas();
	}

	private void seleccionarActividadesTuristicas(){
		actualizarSalidaTuristicas();
	}

	private void actualizarSalidaTuristicas(){
		String act = (String)comboActividad.getSelectedItem();
		if(act == null){
			dtSalidas = new HashMap<>();
			DefaultListModel<String> listModel = new DefaultListModel<>();
			salidaList.setModel(listModel);
		} else {
			List<DTSalidaTuristica> salidasLists =  cat.obtenerDTSalidasTuristicas(act);
			dtSalidas = new HashMap<>();
			for(DTSalidaTuristica salidaDtIter : salidasLists){
				dtSalidas.put(salidaDtIter.getNombre(), salidaDtIter);
			}
			DefaultListModel<String> listModel = new DefaultListModel<>();
			for (DTSalidaTuristica ss : dtSalidas.values()){
				listModel.addElement(ss.getNombre());
			}
			salidaList.setModel(listModel);

			if(dtSalidas.size() > 0){
				salidaList.setSelectedIndex(0);
			}
		}

		seleccionarSalidaTuristicas();

	}

	private void seleccionarSalidaTuristicas(){
		String idSeleccionado = (String) salidaList.getSelectedValue();
		if(idSeleccionado == null){
			nombreTextField.setText("");
			lugarTextField.setText("");
			fechaAltaTextField.setText("");
			fechaYHoraDeSalidaTextField.setText("");
			capacidadDeTuristaTextField.setText("");
		} else {
			DTSalidaTuristica seleccionado = dtSalidas.get(idSeleccionado);
			nombreTextField.setText(seleccionado.getNombre());
			lugarTextField.setText(seleccionado.getLugarSalida());

			if(seleccionado.getFechaAlta() != null){
				fechaAltaTextField.setText( seleccionado.getFechaAlta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			} else {
				fechaAltaTextField.setText("");
			}

			if(seleccionado.getFechaHoraSalida() != null){
				fechaYHoraDeSalidaTextField.setText( seleccionado.getFechaHoraSalida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy ' a las ' HH:mm")));
			} else {
				fechaYHoraDeSalidaTextField.setText("");
			}

			capacidadDeTuristaTextField.setText(String.valueOf(seleccionado.getCantMaxTuristas()));
		}


	}

	private void actualizarTuristas(){
		List<String> idTuris = cu.obtenerIdTuristas();
		comboTurista.setModel(new DefaultComboBoxModel<>(idTuris.toArray()));
		if(idTuris.size() > 0){
			comboTurista.setSelectedIndex(0);
		}
	}
	
	private void recargarSalidas() {
		actualizarSalidaTuristicas();
	}

	private void cancelar(){
		limpiarFormulario();
	}

	private void acpetar(){
		String idSalida = (String)salidaList.getSelectedValue();
		String idTuristas = (String)comboTurista.getSelectedItem();
		if(idSalida == null){
			JOptionPane.showMessageDialog(null, "Se debe seleccionar una salida turística, seleccione una y vuelve a intentar ", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(idTuristas == null){
			JOptionPane.showMessageDialog(null, "Se debe seleccionar un turista, seleccione uno y vuelve a intentar", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		LocalDate fechaInscripcion;
		try{
			int day = (int)diaSpinner.getValue();
			int mouth = (int)mesSpinner.getValue();
			int year = (int)anioSpinner.getValue();
			fechaInscripcion = LocalDate.of(year,mouth,day);
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "La fecha de inscripción elegida inválida.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int cantidadTurista = (int)cantTuristasSprinner.getValue();
		try {
			cat.altaInscripcionSalidaTuristica(idSalida, idTuristas, cantidadTurista, fechaInscripcion);
			setVisible(false);
			limpiarFormulario();
			JOptionPane.showMessageDialog(null, "Inscripción creada con éxito..", "Error", JOptionPane.INFORMATION_MESSAGE);
		} catch (InscripcionYaRegistradaException e) {
			JOptionPane.showMessageDialog(null, "Este turista ya esta inscripto a esta salida turística.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (SuperaElMaximoDeTuristasException e) {
			JOptionPane.showMessageDialog(null, "Con los turistas de esta inscripción se supera la cantidad máxima de turistas permitidos en esta salida turística.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (FechaAltaSalidaTuristicaPosteriorAFechaInscripcion e) {
			JOptionPane.showMessageDialog(null, "La fecha de inscripción no puede ser anterior a la fecha de alta de la salida turística.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error general al crear la inscripción.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		if(aFlag){
			//Cuando se encienda el formulario tambien nos aseguramos que to_do este limpio
			limpiarFormulario();
		}

	}

	private void limpiarFormulario(){
		actualizarDepartamentos(); //Eso actualiza las actividades y salidas
		actualizarTuristas();
		diaSpinner.setValue(1);
		mesSpinner.setValue(1);
		anioSpinner.setValue(2022);
		cantTuristasSprinner.setValue(1);

	}






}
