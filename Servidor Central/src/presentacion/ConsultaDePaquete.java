package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.event.PopupMenuListener;

import logica.controladores.Fabrica;
import logica.controladores.IControladorPaquete;
import javax.swing.event.PopupMenuEvent;
import logica.datatypes.DTPaqueteDetalles;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class ConsultaDePaquete extends JInternalFrame {
	private JComboBox paquete;
	private JTextPane perVal;
	private JTextPane descr;
	private JTextPane descuento;
	private List<DTPaqueteDetalles> paquetesDetalles;
	private JComboBox actividad;
	/**
	 * Create the frame.
	 */
	public ConsultaDePaquete() {
		
		setTitle("Consulta de Paquete");
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setBounds(100, 100, 450, 382);
		getContentPane().setLayout(null);
		
		JLabel lblPaqueteAConsultar = new JLabel("Paquete a consultar:");
		lblPaqueteAConsultar.setBounds(12, 40, 154, 15);
		getContentPane().add(lblPaqueteAConsultar);
		
		paquete = new JComboBox();

		paquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		paquete.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				actualizarCamposFormulario();
				
			}
		});
		paquete.setBounds(166, 35, 247, 24);
		getContentPane().add(paquete);
		
		JLabel lblPeriodoDeValidez = new JLabel("Periodo de validez:");
		lblPeriodoDeValidez.setBounds(12, 85, 136, 15);
		getContentPane().add(lblPeriodoDeValidez);
		
		JLabel lblNewLabel = new JLabel("Descuento:");
		lblNewLabel.setBounds(12, 115, 81, 15);
		getContentPane().add(lblNewLabel);
		
		perVal = new JTextPane();
		perVal.setBounds(166, 79, 247, 21);
		getContentPane().add(perVal);
		perVal.setEditable(false);

		JLabel label = new JLabel("%");
		label.setBounds(377, 115, 24, 15);
		getContentPane().add(label);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(12, 150, 115, 15);
		getContentPane().add(lblDescripcion);
		
		descr = new JTextPane();
		descr.setBounds(12, 174, 401, 107);
		getContentPane().add(descr);
		descr.setEditable(false);
		
		JLabel lblActividadTurisitica = new JLabel("Actividad Turisitica:");
		lblActividadTurisitica.setBounds(12, 300, 154, 15);
		getContentPane().add(lblActividadTurisitica);
		
		actividad = new JComboBox();
		actividad.setEnabled(false);
		actividad.setBounds(152, 295, 261, 24);
		getContentPane().add(actividad);
		
		JTextPane descuento = new JTextPane();
		descuento.setBounds(160, 109, 212, 21);
		getContentPane().add(descuento);
		this.descuento = descuento;
		descuento.setEditable(false);
	   paquete.addActionListener(new ActionListener() {     
	     public void actionPerformed(ActionEvent e) {
			 actividad.setModel(new DefaultComboBoxModel());
			 actividad.setEnabled(true);
			 String idPaquete = paquete.getSelectedItem().toString();
			 DTPaqueteDetalles paqueteSeleccionado = null;
			 for(var paquete: paquetesDetalles) {
				if(Objects.equals(paquete.getNombre(), idPaquete)) {
					paqueteSeleccionado = paquete;
					break;
				}
			}

			perVal.setText(String.valueOf(paqueteSeleccionado.getValidez()));
			descr.setText(paqueteSeleccionado.getDescrpicion());
			descuento.setText(String.valueOf(paqueteSeleccionado.getDescuento()));
			for (var i : paqueteSeleccionado.getActividades().values()){
				actividad.addItem(i.getNombre().toString());
			}

	     }
	   });


	}
	private void actualizarCamposFormulario() {
		paquete.setModel(new DefaultComboBoxModel());
		Fabrica fab = Fabrica.getInstancia();
		IControladorPaquete cp = fab.getIControladorPaquete();
		paquetesDetalles = cp.obtenerDetallesPaquetes(); 
		String[] ids;
		for(var i: paquetesDetalles) {
			paquete.addItem(i.getNombre());
		}

	}
}
