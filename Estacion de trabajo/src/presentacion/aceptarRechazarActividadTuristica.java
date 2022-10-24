package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.controladores.IControladorActividadTuristica;

public class aceptarRechazarActividadTuristica extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private IControladorActividadTuristica controladorAct;
	private JComboBox actividadTuristica;
	private JButton aceptar;
	private JButton rechazar;

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
		actividadTuristica.setBounds(212, 25, 214, 32);
		getContentPane().add(actividadTuristica);
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
					aceptar.setEnabled(true);
					rechazar.setEnabled(true);
				}
			}
		});

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
		aceptar = btnAceptar;
		btnAceptar.setEnabled(false);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String idActividad = actividadTuristica.getSelectedItem().toString();
				try {
					controladorAct.aceptarORechazarActividadTuristica(idActividad, true);
					JOptionPane.showMessageDialog(null, "Operación realizada con éxito.", "Aceptar Actividad Turística",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (ObjetoNoExisteEnTurismoUy e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				limpiarForm();
			}
		});

		btnAceptar.setBounds(309, 83, 117, 25);
		getContentPane().add(btnAceptar);

		JButton btnRechazar = new JButton("Rechazar");
		rechazar = btnRechazar;
		btnRechazar.setEnabled(false);
		btnRechazar.setBounds(167, 83, 117, 25);
		getContentPane().add(btnRechazar);
		btnRechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String idActividad = actividadTuristica.getSelectedItem().toString();
				try {
					controladorAct.aceptarORechazarActividadTuristica(idActividad, false);
					JOptionPane.showMessageDialog(null, "Operación realizada con éxito.",
							"Rechazar Actividad Turística", JOptionPane.INFORMATION_MESSAGE);
				} catch (ObjetoNoExisteEnTurismoUy e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				limpiarForm();
			}
		});

	}

	private void actualizarActTur() {
		List<String> acts = controladorAct.obtenerIdActividadesTuristicasAgregadas();
		actividadTuristica.setModel(new DefaultComboBoxModel<>(acts.toArray()));
		if (!acts.isEmpty()) {
			actividadTuristica.setSelectedIndex(0);
		}
	}

	private void limpiarForm() {
		actividadTuristica.setModel(new DefaultComboBoxModel<>(new String[0]));
		aceptar.setEnabled(false);
		rechazar.setEnabled(false);

	}
}