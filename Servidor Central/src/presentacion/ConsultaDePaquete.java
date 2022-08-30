package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.event.PopupMenuListener;

import logica.controladores.Fabrica;
import logica.controladores.IControladorPaquete;
import javax.swing.event.PopupMenuEvent;
import logica.datatypes.DTPaqueteDetalles;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class ConsultaDePaquete extends JInternalFrame {
	private List<DTPaqueteDetalles> paquetesDetalles;
	private String paqueteSeleccionado;
	private boolean desactivarMostrarConsultas = false;
	
	private Principal principal;
	private IControladorPaquete cp;
	
	private JComboBox paquete;
	private JTextPane perVal;
	private JTextPane descr;
	private JTextPane descuento;
	private JComboBox actividad;
	/**
	 * Create the frame.
	 */
	public ConsultaDePaquete(Principal principal, IControladorPaquete cp) {
		setResizable(true);
		this.cp = cp;
		this.principal = principal;
		
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
		
		
		paquete.setBounds(163, 35, 239, 24);
		getContentPane().add(paquete);
		
		JLabel lblPeriodoDeValidez = new JLabel("Período de validez:");
		lblPeriodoDeValidez.setBounds(11, 102, 136, 15);
		getContentPane().add(lblPeriodoDeValidez);
		
		JLabel lblNewLabel = new JLabel("Descuento:");
		lblNewLabel.setBounds(11, 132, 81, 15);
		getContentPane().add(lblNewLabel);
		
		perVal = new JTextPane();
		perVal.setBounds(150, 96, 221, 21);
		getContentPane().add(perVal);
		perVal.setEditable(false);

		JLabel label = new JLabel("%");
		label.setBounds(376, 132, 24, 15);
		getContentPane().add(label);
		
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setBounds(12, 150, 115, 15);
		getContentPane().add(lblDescripcion);
		
		descr = new JTextPane();
		descr.setBounds(12, 174, 401, 107);
		getContentPane().add(descr);
		descr.setEditable(false);
		
		JLabel lblActividadTurisitica = new JLabel("Actividad Turística:");
		lblActividadTurisitica.setBounds(12, 300, 154, 15);
		getContentPane().add(lblActividadTurisitica);
				

		actividad = new JComboBox();
		actividad.setEnabled(false);
		actividad.setBounds(152, 295, 261, 24);
		getContentPane().add(actividad);
		
		actividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ejecutarCasoConsultaActividadTuristca((String) actividad.getSelectedItem());
			}
		});
		
		JTextPane descuento = new JTextPane();
		descuento.setBounds(149, 126, 222, 21);
		getContentPane().add(descuento);
		this.descuento = descuento;
		descuento.setEditable(false);
		
		JButton btnNewButton = new JButton("Consultar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paqueteSeleccionado = (String) paquete.getSelectedItem();
				seSeleccionoUnPaquete();
			}
		});
		btnNewButton.setBounds(313, 69, 115, 21);
		getContentPane().add(btnNewButton);
	}
	
	@Override
	public void setVisible(boolean flag) {
		super.setVisible(flag);
		
		if (flag) {
			actualizarDTPaqueteDetalles();
			actualizarCamposFormulario();			
		}
	}
	
	private void ejecutarCasoConsultaActividadTuristca(String nombreActividad) {
		if (!desactivarMostrarConsultas && nombreActividad != null) {
			principal.mostrarConsultaDeActividadTuristica(nombreActividad);
		}
	}
	
	private void seSeleccionoUnPaquete() {
		String idPaquete = paqueteSeleccionado;	
		
		desactivarMostrarConsultas = true;
		
		actividad.removeAllItems();
		
		actividad.setEnabled(true);
		DTPaqueteDetalles p = null;
		for (var dtpaquete: paquetesDetalles) {
			if(dtpaquete.getNombre().equals(idPaquete)) {
				p = dtpaquete;
				break;
			}
		}
		if (p != null) {		
			perVal.setText(String.valueOf(p.getValidez()));
			descr.setText(p.getDescrpicion());
			descuento.setText(String.valueOf(p.getDescuento()));
			
			var nombresAct = p.getActividades().keySet();
			actividad.setModel(new DefaultComboBoxModel(nombresAct.toArray()));
		}
		SwingUtilities.invokeLater(() -> desactivarMostrarConsultas = false);
	}
	
	private void actualizarDTPaqueteDetalles() {
		paquetesDetalles = cp.obtenerDetallesPaquetes(); 
	}
	
	private void actualizarCamposFormulario() {
		paquete.removeAllItems();
		String[] ids;
		for(var i: paquetesDetalles) {
			paquete.addItem(i.getNombre());
		}
	}
	
	public void seleccionYaHecha(String nombrePaq) {
		paqueteSeleccionado = nombrePaq;
		paquete.removeAllItems();

		actualizarDTPaqueteDetalles();
		seSeleccionoUnPaquete();
	}
}