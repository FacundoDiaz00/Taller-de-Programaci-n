package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;

import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class AltaDeSalidaTuristica extends JInternalFrame {
	private JTextField dia;
	private JTextField mes;
	private JTextField anio;
	private JTextField nombre;
	private JTextField lugar;
	JComboBox<String> actividadTuristica;
	JComboBox<Integer> hora;
	JSpinner maxTuristas;
	Fabrica f = Fabrica.getInstancia();
	IControladorActividadTuristica ca = f.getIControladorActividadTuristica();
	
	/**
	 * Create the frame.
	 */
	public AltaDeSalidaTuristica() {
		setTitle("Registrar Salida Turistica.");
		setBounds(100, 100, 428, 391);
		getContentPane().setLayout(null);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos de la Salida Turistica a Registrar.");
		lblIngreseLosDatos.setBounds(12, 12, 385, 15);
		getContentPane().add(lblIngreseLosDatos);
		
		JLabel lblDepart = new JLabel("Departamento :");
		lblDepart.setBounds(12, 50, 112, 15);
		getContentPane().add(lblDepart);
		
		//Cuando se setea un departamento, se habilita la seleccion de una actividad.
		JComboBox<String> departamento = new JComboBox<String>();
		departamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(departamento.getSelectedItem() != null) {
					actividadTuristica.setEnabled(true);
					List<String> acts = ca.obtenerIdActividadesTuristicas(departamento.getSelectedItem().toString());
					for(int i = 0;i<acts.size();i++) {
						actividadTuristica.addItem(acts.get(i));
					}
				}
			}
		});
		departamento.setBounds(150, 45, 175, 24);
		List<String> deptos = ca.obtenerIdDepartamentos();
		for(int i = 0;i<deptos.size();i++) {
			departamento.addItem(deptos.get(i));
		}
		
		getContentPane().add(departamento);
		
		
		JLabel lblActividadTurisica = new JLabel("Actividad Turisica:");
		lblActividadTurisica.setBounds(12, 85, 128, 15);
		getContentPane().add(lblActividadTurisica);
		
		actividadTuristica = new JComboBox<String>();
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
		
		dia = new JTextField();
		dia.setEnabled(false);
		dia.setBounds(157, 153, 30, 19);
		getContentPane().add(dia);
		dia.setColumns(10);
		
		JLabel label = new JLabel("/");
		label.setBounds(190, 155, 4, 15);
		getContentPane().add(label);
		
		mes = new JTextField();
		mes.setEnabled(false);
		mes.setBounds(199, 153, 30, 19);
		getContentPane().add(mes);
		mes.setColumns(10);
		
		JLabel label_1 = new JLabel("/");
		label_1.setBounds(231, 155, 4, 15);
		getContentPane().add(label_1);
		
		anio = new JTextField();
		anio.setEnabled(false);
		anio.setBounds(238, 153, 60, 19);
		getContentPane().add(anio);
		anio.setColumns(10);
		
		JLabel lblddmmyyyy = new JLabel("(dd/mm/yyyy)");
		lblddmmyyyy.setBounds(303, 155, 112, 15);
		getContentPane().add(lblddmmyyyy);
		
		nombre = new JTextField();
		nombre.setEnabled(false);
		nombre.setBounds(80, 118, 245, 19);
		getContentPane().add(nombre);
		nombre.setColumns(10);
		
		hora = new JComboBox<Integer>();
		hora.setEnabled(false);
		hora.setBounds(66, 185, 58, 24);
		List<Integer> aux = null;
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
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setEnabled(false);
		btnAceptar.setBounds(12, 320, 117, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(280, 320, 117, 25);
		getContentPane().add(btnCancelar);

	}
}
