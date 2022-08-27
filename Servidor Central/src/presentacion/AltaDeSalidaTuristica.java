package presentacion;

import javax.swing.*;

import excepciones.FechaAltaSalidaPosteriorAFechaSalidaException;
import excepciones.FechaAltaActividadPosteriorAFechaAltaSalidaException;
import excepciones.SalidaYaRegistradaException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class AltaDeSalidaTuristica extends JInternalFrame {
	private JSpinner dia;
	private JSpinner mes;
	private JSpinner anio;
	private JSpinner anior; 
	private JSpinner mesr; 
	private JSpinner diar; 
	private JTextField nombre;
	private JTextField lugar;
	private JComboBox actividadTuristica;
	private JComboBox<Integer> hora;
	private JSpinner maxTuristas;
	private JButton btnAceptar;
	private JComboBox departamento;
	Fabrica f = Fabrica.getInstancia();
	IControladorActividadTuristica ca = f.getIControladorActividadTuristica();
	
	/**
	 * Create the frame.
	 */
	public AltaDeSalidaTuristica() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				limpiarForm();
			}
		});
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Registrar Salida Turistica.");
		setBounds(100, 100, 461, 415);
		getContentPane().setLayout(null);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos de la Salida Turistica a Registrar.");
		lblIngreseLosDatos.setBounds(12, 12, 385, 15);
		getContentPane().add(lblIngreseLosDatos);
		
		JLabel lblDepart = new JLabel("Departamento :");
		lblDepart.setBounds(12, 50, 112, 15);
		getContentPane().add(lblDepart);
		
		//Cuando se setea un departamento, se habilita la seleccion de una actividad.


		departamento = new JComboBox();
		departamento.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				actualizarDepartamentos();
				
			}
		});
		departamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(departamento.getSelectedItem() != null) {
					actividadTuristica.setEnabled(true);
					
				}
			}
		});
		getContentPane().add(departamento);

		departamento.setBounds(150, 45, 175, 24);

		
		JLabel lblActividadTurisica = new JLabel("Actividad Turisica:");
		lblActividadTurisica.setBounds(12, 85, 128, 15);
		getContentPane().add(lblActividadTurisica);
		
		actividadTuristica = new JComboBox();
		actividadTuristica.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				actualizarActTur();
			}
		});
		actividadTuristica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(actividadTuristica.getSelectedItem() != null) {
					nombre.setEnabled(true);
					dia.setEnabled(true);
					mes.setEnabled(true);
					anio.setEnabled(true);
					hora.setEnabled(true);
					lugar.setEnabled(true);
					maxTuristas.setEnabled(true);
					btnAceptar.setEnabled(true);
					diar.setEnabled(true);
					mesr.setEnabled(true);
					anior.setEnabled(true);

				}
			}
		});
		actividadTuristica.setEnabled(false);
		actividadTuristica.setBounds(150, 80, 175, 24);
		
		getContentPane().add(actividadTuristica);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 120, 70, 15);
		getContentPane().add(lblNombre);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(12, 190, 47, 15);
		getContentPane().add(lblHora);
		
		JLabel lblFecha = new JLabel("Fecha de la Salida :");
		lblFecha.setBounds(12, 155, 138, 15);
		getContentPane().add(lblFecha);
		
		JLabel lblLugar = new JLabel("Lugar:");
		lblLugar.setBounds(12, 225, 70, 15);
		getContentPane().add(lblLugar);
		
		JLabel lblCantidadMaximaDe = new JLabel("Cantidad Maxima de turistas:");
		lblCantidadMaximaDe.setBounds(12, 259, 208, 15);
		getContentPane().add(lblCantidadMaximaDe);
		
		dia = new JSpinner();
		dia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		dia.setEnabled(false);
		dia.setBounds(157, 153, 47, 19);
		getContentPane().add(dia);
		
		JLabel label = new JLabel("/");
		label.setBounds(208, 155, 13, 15);
		getContentPane().add(label);
		
		mes = new JSpinner();
		mes.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		mes.setEnabled(false);
		mes.setBounds(215, 153, 47, 19);
		getContentPane().add(mes);
		
		JLabel label_1 = new JLabel("/");
		label_1.setBounds(265, 155, 4, 15);
		getContentPane().add(label_1);
		
		anio = new JSpinner();
		anio.setModel(new SpinnerNumberModel(2022, 1900, 2100, 1));
		anio.setEnabled(false);
		anio.setBounds(273, 153, 60, 19);
		getContentPane().add(anio);
		
		JLabel lblddmmyyyy = new JLabel("(dd/mm/yyyy)");
		lblddmmyyyy.setBounds(339, 155, 92, 15);
		getContentPane().add(lblddmmyyyy);
		
		nombre = new JTextField();
		nombre.setEnabled(false);
		nombre.setBounds(80, 118, 245, 19);
		getContentPane().add(nombre);
		nombre.setColumns(10);
		
		hora = new JComboBox<Integer>();
		hora.setEnabled(false);
		hora.setBounds(66, 185, 58, 24);
		List<Integer> aux = new ArrayList<Integer>();
		for(int i = 0;i<=23;i++) {
			aux.add(i);
		}
		for(int i = 0;i<aux.size();i++) {
			hora.addItem(aux.get(i));
		}
		getContentPane().add(hora);
		
		lugar = new JTextField();
		lugar.setEnabled(false);
		lugar.setBounds(66, 223, 221, 19);
		getContentPane().add(lugar);
		lugar.setColumns(10);
		
		maxTuristas = new JSpinner();
		maxTuristas.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		maxTuristas.setEnabled(false);
		maxTuristas.setBounds(227, 257, 60, 20);
		getContentPane().add(maxTuristas);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aceptarAltaSalidaTuristica();
			}
		});
		btnAceptar.setEnabled(false);
		btnAceptar.setBounds(322, 346, 117, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarForm();
                setVisible(false);
			}
		});
		btnCancelar.setBounds(12, 346, 117, 25);
		getContentPane().add(btnCancelar);
		
		JLabel lblFechaDeRegistro = new JLabel("Fecha de Registro:");
		lblFechaDeRegistro.setBounds(12, 295, 138, 15);
		getContentPane().add(lblFechaDeRegistro);
		
		diar = new JSpinner();
		diar.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		diar.setEnabled(false);
		diar.setBounds(157, 293, 47, 19);
		getContentPane().add(diar);
		
		JLabel label_2 = new JLabel("/");
		label_2.setBounds(208, 293, 13, 15);
		getContentPane().add(label_2);
		
		mesr = new JSpinner();
		mesr.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		mesr.setEnabled(false);
		mesr.setBounds(215, 293, 47, 19);
		getContentPane().add(mesr);
		
		JLabel label_1_1 = new JLabel("/");
		label_1_1.setBounds(265, 293, 4, 15);
		getContentPane().add(label_1_1);
		
		anior = new JSpinner();
		anior.setModel(new SpinnerNumberModel(2022, 1900, 2100, 1));
		anior.setEnabled(false);
		anior.setBounds(273, 293, 60, 19);
		getContentPane().add(anior);
		
		JLabel lblddmmyyyy_1 = new JLabel("(dd/mm/yyyy)");
		lblddmmyyyy_1.setBounds(339, 293, 92, 15);
		getContentPane().add(lblddmmyyyy_1);

	}

	private void actualizarDepartamentos() {
		List<String> deptos = ca.obtenerIdDepartamentos();
		departamento.setModel(new DefaultComboBoxModel<>(deptos.toArray()));
		if(deptos.size() > 0){
			departamento.setSelectedIndex(0);
		}
	}
	private void actualizarActTur() {
		List<String> acts = ca.obtenerIdActividadesTuristicas(departamento.getSelectedItem().toString());
		actividadTuristica.setModel(new DefaultComboBoxModel<>(acts.toArray()));
		if(acts.size() > 0){
			actividadTuristica.setSelectedIndex(0);
		}
	}

	private void aceptarAltaSalidaTuristica() {
		try{
			System.out.print(actividadTuristica.getSelectedItem().toString());
			LocalDate fechaR = LocalDate.of((int)anior.getValue(), (int)mesr.getValue(), (int)diar.getValue());
			LocalDateTime fecha = LocalDateTime.of((int)anio.getValue(),(int)mes.getValue(),(int)dia.getValue(),(int)hora.getSelectedItem(),0);
			ca.altaSalidaTuristica(actividadTuristica.getSelectedItem().toString(),nombre.getText().toString(),fecha,fechaR,lugar.getText().toString(), (int)maxTuristas.getValue());
			JOptionPane.showMessageDialog(null, "Operacion realizada con exito.","Registro de Salida",JOptionPane.INFORMATION_MESSAGE );
			setVisible(false);
			limpiarForm();
		} catch(SalidaYaRegistradaException e) {
			JOptionPane.showMessageDialog(null, "Error. Ya existe una Salida con el nombre elejido.", "Registro de Salida", JOptionPane.WARNING_MESSAGE);
		} catch (FechaAltaSalidaPosteriorAFechaSalidaException ee){
			JOptionPane.showMessageDialog(null, "Error. La Fecha de Salida debe ser posterior a la Fecha de Registro.", "Registro de Salida", JOptionPane.WARNING_MESSAGE);
		} catch (FechaAltaActividadPosteriorAFechaAltaSalidaException eee){
			JOptionPane.showMessageDialog(null, "Error. La fecha de Registro de la salida debe ser posterior a la del alta de la actividad correspondiente.", "Registro de Salida", JOptionPane.WARNING_MESSAGE);
		}

	}

	private void limpiarForm() {
		nombre.setText("");
		dia.setValue(1);
		mes.setValue(1);
		anio.setValue(2022);
		hora.setSelectedIndex(0);
		lugar.setText("");
		maxTuristas.setValue(1);
		diar.setValue(1);
		mesr.setValue(1);
		anior.setValue(2022);

	}
}
