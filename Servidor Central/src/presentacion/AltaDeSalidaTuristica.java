package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;

import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

public class AltaDeSalidaTuristica extends JInternalFrame {
	private JSpinner dia;
	private JSpinner mes;
	private JSpinner anio;
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
		setTitle("Registrar Salida Turistica.");
		setBounds(100, 100, 490, 391);
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
		dia.setEnabled(false);
		dia.setBounds(157, 153, 47, 19);
		getContentPane().add(dia);
		
		JLabel label = new JLabel("/");
		label.setBounds(208, 155, 13, 15);
		getContentPane().add(label);
		
		mes = new JSpinner();
		mes.setEnabled(false);
		mes.setBounds(215, 153, 47, 19);
		getContentPane().add(mes);
		
		JLabel label_1 = new JLabel("/");
		label_1.setBounds(265, 155, 4, 15);
		getContentPane().add(label_1);
		
		anio = new JSpinner();
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
		lugar.setBounds(66, 223, 163, 19);
		getContentPane().add(lugar);
		lugar.setColumns(10);
		
		maxTuristas = new JSpinner();
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
		btnAceptar.setBounds(280, 322, 117, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
                setVisible(false);
			}
		});
		btnCancelar.setBounds(12, 322, 117, 25);
		getContentPane().add(btnCancelar);

	}
	private void limpiarFormulario() {
		
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
		LocalDate fecha = LocalDate.of((int)anio.getValue(),(int)mes.getValue(),(int)dia.getValue());
		ca.altaSalidaTuristica(departamento.getSelectedItem().toString(),actividadTuristica.getSelectedItem().toString(),nombre.getText().toString(),fecha,(int)hora.getSelectedItem(),lugar.getText().toString(), (int)maxTuristas.getValue());
	}
}
