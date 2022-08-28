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
public class ConsultaDePaquete extends JInternalFrame {
	private JComboBox paquete;
	private JTextPane perVal;
	private JTextPane descr;
	private JTextPane descuento;
	private List<DTPaqueteDetalles> paquetesDetalles;

	/**
	 * Create the frame.
	 */
	public ConsultaDePaquete() {
		
		setTitle("Consulta de Paquete");
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setBounds(100, 100, 450, 431);
		getContentPane().setLayout(null);
		
		JLabel lblPaqueteAConsultar = new JLabel("Paquete a consultar:");
		lblPaqueteAConsultar.setBounds(12, 40, 154, 15);
		getContentPane().add(lblPaqueteAConsultar);
		
		paquete = new JComboBox();

		paquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//perVal.setText();
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
		
		JLabel label = new JLabel("%");
		label.setBounds(377, 115, 24, 15);
		getContentPane().add(label);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(12, 150, 115, 15);
		getContentPane().add(lblDescripcion);
		
		descr = new JTextPane();
		descr.setBounds(12, 174, 401, 107);
		getContentPane().add(descr);
		
		JLabel lblActividadTurisitica = new JLabel("Actividad Turisitica:");
		lblActividadTurisitica.setBounds(12, 300, 154, 15);
		getContentPane().add(lblActividadTurisitica);
		
		JComboBox actividad = new JComboBox();
		actividad.setEnabled(false);
		actividad.setBounds(152, 295, 261, 24);
		getContentPane().add(actividad);
		
		JButton btnConfirmar = new JButton("Confirmar.");
		btnConfirmar.setBounds(152, 362, 117, 25);
		getContentPane().add(btnConfirmar);
		
		JTextPane descuento = new JTextPane();
		descuento.setBounds(160, 109, 212, 21);
		getContentPane().add(descuento);
		this.descuento = descuento;
	
	   paquete.addActionListener(new ActionListener() {     
	     public void actionPerformed(ActionEvent e) {
			String idPaquete = paquete.getSelectedItem().toString();
			DTPaqueteDetalles paqueteSeleccionado = null;
			for(var paquete: paquetesDetalles) {
				if(paquete.getNombre() == idPaquete) {
					paqueteSeleccionado = paquete;
					break;
				}
			}
			System.out.print(paqueteSeleccionado.getDescripcion());
			perVal.setText(paqueteSeleccionado.getNombre());
			descr.setText(paqueteSeleccionado.getDescripcion());
			descuento.setText(String.valueOf(paqueteSeleccionado.getDescuento()));
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
