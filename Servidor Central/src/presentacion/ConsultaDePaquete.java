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
		paquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paqueteSeleccionado = (String) paquete.getSelectedItem();
				seSeleccionoUnPaquete();
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
		
		actividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (! desactivarMostrarConsultas) {
					var act = (String) actividad.getSelectedItem();
					ejecutarCasoConsultaActividadTuristca(act);
				}
			}
		});
		
		actividad.setEnabled(false);
		actividad.setBounds(152, 295, 261, 24);
		getContentPane().add(actividad);
		
		JTextPane descuento = new JTextPane();
		descuento.setBounds(160, 109, 212, 21);
		getContentPane().add(descuento);
		this.descuento = descuento;
		descuento.setEditable(false);
	}
	
	@Override
	public void setVisible(boolean flag) {
		super.setVisible(flag);
		
		if (flag)
			actualizarCamposFormulario();
	}
	
	private void ejecutarCasoConsultaActividadTuristca(String nombreActividad) {
		principal.mostrarConsultaDeActividadTuristica(nombreActividad);
	}
	
	private void seSeleccionoUnPaquete() {
		String idPaquete = this.paqueteSeleccionado;	
		desactivarMostrarConsultas = true;
		
		paquete.setSelectedItem(idPaquete);
		actividad.setModel(new DefaultComboBoxModel());
		
		actividad.setEnabled(true);
		DTPaqueteDetalles paqueteSeleccionado = null;
		for(var paquete: paquetesDetalles) {
			if(Objects.equals(paquete.getNombre(), idPaquete)) {
				paqueteSeleccionado = paquete;
				break;
			}
		}
		if(paqueteSeleccionado != null) {		
			perVal.setText(String.valueOf(paqueteSeleccionado.getValidez()));
			descr.setText(paqueteSeleccionado.getDescrpicion());
			descuento.setText(String.valueOf(paqueteSeleccionado.getDescuento()));
			for (var i : paqueteSeleccionado.getActividades().values()){
				actividad.addItem(i.getNombre().toString());
			}
		}

		desactivarMostrarConsultas = false;
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
	
	public void seleccionYaHecha(String nombrePaq) {
		paqueteSeleccionado = nombrePaq;
		seSeleccionoUnPaquete();
	}
}