package presentacion;

import java.util.List;
import logica.controladores.IControladorActividadTuristica;
import logica.datatypes.DTActividadTuristicaDetalle;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import logica.datatypes.DTSalidaTuristica;
import logica.datatypes.DTPaquete;

public class ConsultaDeActividadTuristica extends JInternalFrame {
	private String seleccionActividad;

	private final JComboBox comboSalidas;
	private final JComboBox comboPaquetes;
	private JComboBox comboActividades;
	private JComboBox comboDepartamentos;
	private IControladorActividadTuristica icat;
	private JTextArea nombre;
	private JTextArea descripcion;
	private JTextArea duracion;
	private JTextArea ciudad;
	private JTextArea costo;
	private JTextArea fechaAlta;
	private JTextArea proveedor;
	
	/**
	 * Create the frame.
	 */
	public ConsultaDeActividadTuristica(IControladorActividadTuristica icat) {
		this.icat = icat;
		setTitle("Consutla de Actividad Turística");
		setBounds(100, 100, 409, 424);
		getContentPane().setLayout(null);
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
		
		JLabel departamentos = new JLabel("Departamentos:");
		departamentos.setHorizontalAlignment(SwingConstants.RIGHT);
		departamentos.setBounds(7, 12, 120, 14);
		getContentPane().add(departamentos);
		
		JLabel Actividades = new JLabel("Actividades:");
		Actividades.setHorizontalAlignment(SwingConstants.RIGHT);
		Actividades.setBounds(7, 37, 120, 14);
		getContentPane().add(Actividades);
		
		JLabel nombreLabel = new JLabel("Nombre:");
		nombreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nombreLabel.setBounds(6, 63, 121, 14);
		getContentPane().add(nombreLabel);
		
		JLabel descripcionLabel = new JLabel("Descripcion:");
		descripcionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		descripcionLabel.setBounds(7, 89, 120, 14);
		getContentPane().add(descripcionLabel);
		
		JLabel duracionLabel = new JLabel("Duracion:");
		duracionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		duracionLabel.setBounds(7, 164, 120, 14);
		getContentPane().add(duracionLabel);
		
		JLabel costoLabel = new JLabel("Costo:");
		costoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		costoLabel.setBounds(0, 192, 121, 14);
		getContentPane().add(costoLabel);
		
		JLabel ciudadLabel = new JLabel("Ciudad:");
		ciudadLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		ciudadLabel.setBounds(7, 218, 120, 14);
		getContentPane().add(ciudadLabel);
		
		JLabel fechaAltaLabel = new JLabel("Fecha de alta:");
		fechaAltaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		fechaAltaLabel.setBounds(7, 244, 120, 14);
		getContentPane().add(fechaAltaLabel);
		
		JComboBox comboDeps = new JComboBox<String>();
		comboDeps.setBounds(145, 7, 212, 24);
		getContentPane().add(comboDeps);
		
		
        comboDeps.addPopupMenuListener( new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// Esto es lo que actualiza la lista cada vez que se abre.
				actualizarComboDepartamentos();
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				
			}
        	
        });
        
        this.comboDepartamentos = comboDeps;
        
	 
		
		JComboBox comboActividades = new JComboBox();
		comboActividades.setBounds(145, 32, 212, 24);
		getContentPane().add(comboActividades);
		this.comboActividades = comboActividades;
		
		JTextArea nombre = new JTextArea();
		nombre.setEditable(false);
		nombre.setBounds(145, 63, 212, 15);
		getContentPane().add(nombre);
		this.nombre = nombre;
		
		JTextArea duracion = new JTextArea();
		duracion.setEditable(false);
		duracion.setBounds(145, 164, 212, 15);
		getContentPane().add(duracion);
		this.duracion = duracion;
	
		
		JTextArea costo = new JTextArea();
		costo.setEditable(false);
		costo.setBounds(145, 192, 212, 15);
		getContentPane().add(costo);
		this.costo = costo;
		
		JTextArea ciudad = new JTextArea();
		ciudad.setEditable(false);
		ciudad.setBounds(145, 218, 212, 15);
		getContentPane().add(ciudad);
		this.ciudad = ciudad;
		
		JTextArea fechaAlta = new JTextArea();
		fechaAlta.setEditable(false);
		fechaAlta.setBounds(145, 244, 212, 15);
		getContentPane().add(fechaAlta);
		this.fechaAlta = fechaAlta;
		
		JComboBox comboSalidas = new JComboBox();
		comboSalidas.setBounds(145, 289, 212, 24);
		getContentPane().add(comboSalidas);
		this.comboSalidas = comboSalidas;

		
		JLabel salidasTuristicasLabel = new JLabel("Salidas turísticas:");
		salidasTuristicasLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		salidasTuristicasLabel.setBounds(-12, 294, 139, 14);
		getContentPane().add(salidasTuristicasLabel);
		
		JComboBox comboPaquetes = new JComboBox();
		comboPaquetes.setBounds(145, 314, 212, 24);
		getContentPane().add(comboPaquetes);
		this.comboPaquetes = comboPaquetes;
		
		JLabel paquetesLabel = new JLabel("Paquetes:");
		paquetesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		paquetesLabel.setBounds(46, 319, 81, 14);
		getContentPane().add(paquetesLabel);
		
		descripcion = new JTextArea();
		descripcion.setLineWrap(true);
		descripcion.setWrapStyleWord(true);
		descripcion.setEditable(false);
		descripcion.setBounds(145, 84, 212, 69);
		getContentPane().add(descripcion);
		
		JLabel lblProveedor = new JLabel("Proveedor:");
		lblProveedor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProveedor.setBounds(7, 269, 120, 14);
		getContentPane().add(lblProveedor);
		
		proveedor = new JTextArea();
		proveedor.setEditable(false);
		proveedor.setBounds(145, 270, 212, 15);
		getContentPane().add(proveedor);
		
	   comboDeps.addActionListener(new ActionListener() {     
		     public void actionPerformed(ActionEvent e) {
		    	 List<String> actividades = icat.obtenerIdActividadesTuristicas(comboDeps.getSelectedItem().toString());
		    	 comboActividades.setModel(new DefaultComboBoxModel(actividades.toArray()));
		   
		     }
	   });
	   
	   comboActividades.addActionListener(new ActionListener() {     
		     public void actionPerformed(ActionEvent e) {
		    	 consultaDeActividad(e);
		   }
	   });

}
	
	public void seleccionYaHecha(String nombreAct) {
		limpiarFormulario();
		seleccionActividad = nombreAct;
		seSeleccionoUnaActividad();
	}
	
	private void consultaDeActividad(ActionEvent action) {
		seleccionActividad = (String) comboActividades.getSelectedItem();
		seSeleccionoUnaActividad();
	}
		
	
	private void seSeleccionoUnaActividad() {
		String seleccion = seleccionActividad;

		try {
			DTActividadTuristicaDetalle actividad = icat.obtenerDetallesActividadTuristica(seleccion);
			
			nombre.setText(actividad.getNombre());
			ciudad.setText(actividad.getCuidad());
			costo.setText(String.valueOf(actividad.getCostoPorTurista()));
			descripcion.setText(actividad.getDescripcion());
			fechaAlta.setText(actividad.getFechaAlta().toString());
			proveedor.setText(actividad.getNicknameProveedor());
			duracion.setText(String.valueOf(actividad.getDuracion()));
			
			List<DTSalidaTuristica> salidas = new ArrayList<DTSalidaTuristica>(actividad.getSalidas().values());
			for(DTSalidaTuristica salida: salidas) {
				comboSalidas.addItem(salida.getNombre());
			}
			
			List<DTPaquete> paquetes = new ArrayList<DTPaquete>(actividad.getPaquetes().values());
			for(DTPaquete paquete: paquetes) {
				comboPaquetes.addItem(paquete.getNombre());
			}
		
		} catch (Exception ex) {
			// Esta excepcion no debería ocurrir pero por las dudas la pongo
			
		}
	}
	
	public void actualizarComboDepartamentos() {
		List<String> deps = icat.obtenerIdDepartamentos();
		comboDepartamentos.setModel(new DefaultComboBoxModel(deps.toArray()));
	}
	
	
	
    private void limpiarFormulario() {
        nombre.setText("");
        descripcion.setText("");
        duracion.setText("");
        costo.setText("");
		ciudad.setText("");
		fechaAlta.setText("");
		comboSalidas.setModel(new DefaultComboBoxModel<>(new String[0]));
		comboPaquetes.setModel(new DefaultComboBoxModel<>(new String[0]));
    }
}
