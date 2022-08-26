package presentacion;

import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorUsuario;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class InscribirseASalidaTurística extends JInternalFrame {
	private JTextField nombreTextField;
	private JTextField lugarTextField;
	private JTextField fechaAltaTextField;
	private JTextField capacidadDeTuristaTextField;
	private JTextField fechaYHoraDeSalidaTextField;
	private JTextField diaTextField;

	private JComboBox comboDepartamento;
	private JComboBox comboActividad;
	private JComboBox comboTurista;
	private JTextField mesTextField;
	private JTextField añoTextField;

	/**
	 * Create the frame.
	 */
	public InscribirseASalidaTurística() {
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
		setBounds(100, 100, 545, 398);
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
		
		JList salidaList = new JList();
		salidaList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				seleccionarSalidaTuristicas();
			}
		});
		panel_2.add(salidaList, BorderLayout.CENTER);
		
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
		
		diaTextField = new JTextField();
		panel_9.add(diaTextField);
		diaTextField.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel(" / ");
		panel_9.add(lblNewLabel_11);
		
		mesTextField = new JTextField();
		panel_9.add(mesTextField);
		mesTextField.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel(" / ");
		panel_9.add(lblNewLabel_12);
		
		añoTextField = new JTextField();
		panel_9.add(añoTextField);
		añoTextField.setColumns(10);
		
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
		
		JSpinner spinner = new JSpinner();
		panel_10.add(spinner);
		
		Component horizontalStrut_31 = Box.createHorizontalStrut(20);
		panel_10.add(horizontalStrut_31);

	}


	private void actualizarDepartamentos(){
		IControladorActividadTuristica cat = Fabrica.getInstancia().getIControladorActividadTuristica();
		List<String> idDeps = cat.obtenerIdDepartamentos();
		comboDepartamento.setModel(new DefaultComboBoxModel<>(idDeps.toArray()));
		if(idDeps.size() > 0){
			comboDepartamento.setSelectedIndex(0);
			seleccionarDepartamento();
			actualizarActividadesTuristicas();
		}

	}

	private void seleccionarDepartamento(){
		comboActividad.setEnabled(true);
	}

	private void actualizarActividadesTuristicas(){
		IControladorActividadTuristica cat = Fabrica.getInstancia().getIControladorActividadTuristica();
		String dep = (String)comboDepartamento.getSelectedItem();
		List<String> idActs = cat.obtenerIdActividadesTuristicas(dep);
		comboActividad.setModel(new DefaultComboBoxModel<>(idActs.toArray()));
		if(idActs.size() > 0){
			comboActividad.setSelectedIndex(0);
			seleccionarActividadesTuristicas();
			actualizarSalidaTuristicas();
		}
	}

	private void seleccionarActividadesTuristicas(){

	}

	private void actualizarSalidaTuristicas(){

	}

	private void seleccionarSalidaTuristicas(){

	}

	private void actualizarTuristas(){
		IControladorUsuario cu = Fabrica.getInstancia().getIControladorUsuario();
		List<String> idTuris = cu.obtenerIdTuristas();
		comboTurista.setModel(new DefaultComboBoxModel<>(idTuris.toArray()));
		if(idTuris.size() > 0){
			comboTurista.setSelectedIndex(0);
		}
	}

	private void cancelar(){

	}

	private void acpetar(){

	}






}
