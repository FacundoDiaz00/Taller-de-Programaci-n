package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import excepciones.FechaAltaActividadPosteriorAFechaAltaSalidaException;
import excepciones.FechaAltaSalidaPosteriorAFechaSalidaException;
import excepciones.SalidaYaRegistradaException;
import logica.controladores.IControladorActividadTuristica;

public class aceptarRechazarActividadTuristica extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	
	private IControladorActividadTuristica controladorAct;
	private JComboBox actividadTuristica;

	/**
	 * Create the frame.
	 */
	public aceptarRechazarActividadTuristica(IControladorActividadTuristica controladorAct) {
		this.controladorAct = controladorAct;
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent event) {
				limpiarForm();
			}
		});
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Aceptar/Rechazar Salida Turística");
		setBounds(100, 100, 461, 160);
		getContentPane().setLayout(null);

		JLabel lblActividadTurisica = new JLabel("Actividad Turística:");
		lblActividadTurisica.setBounds(12, 25, 148, 15);
		getContentPane().add(lblActividadTurisica);

		actividadTuristica = new JComboBox();
		actividadTuristica.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent event) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent event) {
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent event) {
				actualizarActTur();
			}
		});
		actividadTuristica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (actividadTuristica.getSelectedItem() != null) {
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
		actividadTuristica.setBounds(197, 25, 242, 32);

		getContentPane().add(actividadTuristica);
		List<Integer> aux = new ArrayList<Integer>();
		for (int i = 0; i <= 23; i++) {
			aux.add(i);
		}
		for (int i = 0; i < aux.size(); i++) {
			hora.addItem(aux.get(i));
		}

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarForm();
				setVisible(false);
			}
		});
		btnCancelar.setBounds(25, 83, 117, 25);
		getContentPane().add(btnCancelar);
		
		JLabel lblenEstadoagregada = new JLabel("(en estado \"Agregada\")");
		lblenEstadoagregada.setBounds(12, 42, 167, 15);
		getContentPane().add(lblenEstadoagregada);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAceptar.setBounds(309, 83, 117, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnRechazar = new JButton("Rechazar");
		btnRechazar.setBounds(167, 83, 117, 25);
		getContentPane().add(btnRechazar);

	}

	private void actualizarActTur() {
		List<String> acts = controladorAct.obtenerIdActividadesTuristicas(departamento.getSelectedItem().toString());
		actividadTuristica.setModel(new DefaultComboBoxModel<>(acts.toArray()));
		if (!acts.isEmpty()) {
			actividadTuristica.setSelectedIndex(0);
		}
	}

	private void limpiarForm() {
		nombre.setText("");
		departamento.setModel(new DefaultComboBoxModel<>(new String[0]));
		actividadTuristica.setModel(new DefaultComboBoxModel<>(new String[0]));
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
