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

public class ConsultaDeSalidaTuristica extends JInternalFrame {
	private String seleccionActividad;

	private final JComboBox comboSalidas;
	private final JComboBox comboPaquetes;
	private JComboBox comboActividades;
	private JComboBox comboDepartamentos;
	private IControladorActividadTuristica icat;
	private JTextArea nombre;
	private JTextArea fechaSalida;
	private JTextArea horaSalida;
	private JTextArea lugarSalida;
	private JTextArea maxCantTuristas;
	private JTextArea fechaAlta;
	
	/**
	 * Create the frame.
	 */
	public ConsultaDeSalidaTuristica(IControladorActividadTuristica icat) {
		this.icat = icat;
		setTitle("Consulta de Salida Turística");
		setBounds(100, 100, 409, 512);
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
		nombreLabel.setBounds(7, 97, 121, 14);
		getContentPane().add(nombreLabel);
		
		JLabel fechaLabel = new JLabel("Fecha:");
		fechaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		fechaLabel.setBounds(7, 123, 120, 14);
		getContentPane().add(fechaLabel);
		
		JLabel hora = new JLabel("Hora:");
		hora.setHorizontalAlignment(SwingConstants.RIGHT);
		hora.setBounds(7, 149, 120, 14);
		getContentPane().add(hora);
		
		JLabel lugarLabel = new JLabel("Lugar de salida:");
		lugarLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lugarLabel.setBounds(7, 175, 121, 14);
		getContentPane().add(lugarLabel);
		
		JLabel maxTuristasLabel = new JLabel("Maxima cantidad de turistas:");
		maxTuristasLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		maxTuristasLabel.setBounds(7, 201, 212, 14);
		getContentPane().add(maxTuristasLabel);
		
		JLabel fechaAltaLabel = new JLabel("Fecha de alta:");
		fechaAltaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		fechaAltaLabel.setBounds(7, 227, 120, 14);
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
				// TODO Auto-generated method stub
				
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        this.comboDepartamentos = comboDeps;
        
	 
		
		JComboBox comboActividades = new JComboBox();
		comboActividades.setBounds(145, 32, 212, 24);
		getContentPane().add(comboActividades);
		this.comboActividades = comboActividades;
		
		JTextArea nombre = new JTextArea();
		nombre.setEditable(false);
		nombre.setBounds(145, 98, 212, 15);
		getContentPane().add(nombre);
		this.nombre = nombre;
		
		JTextArea horaSalida = new JTextArea();
		horaSalida.setEditable(false);
		horaSalida.setBounds(145, 150, 212, 15);
		getContentPane().add(horaSalida);
		this.horaSalida = horaSalida;
	
		
		JTextArea lugarSalida = new JTextArea();
		lugarSalida.setEditable(false);
		lugarSalida.setBounds(145, 177, 212, 15);
		getContentPane().add(lugarSalida);
		this.lugarSalida = lugarSalida;
		
		JTextArea maxCantTuristas = new JTextArea();
		maxCantTuristas.setEditable(false);
		maxCantTuristas.setBounds(227, 201, 130, 15);
		getContentPane().add(maxCantTuristas);
		this.maxCantTuristas = maxCantTuristas;
		
		JTextArea fechaAlta = new JTextArea();
		fechaAlta.setEditable(false);
		fechaAlta.setBounds(145, 227, 212, 15);
		getContentPane().add(fechaAlta);
		this.fechaAlta = fechaAlta;
		
		JComboBox comboSalidas = new JComboBox();
		comboSalidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//salida fue seleccionada:
				DTSalidaTuristica sal = icat.obtenerDTSalidaTuristica(comboSalidas.getSelectedItem().toString());
				mostrarDatosSalida(sal);
			}
		});
		comboSalidas.setBounds(145, 58, 212, 24);
		getContentPane().add(comboSalidas);
		this.comboSalidas = comboSalidas;

		
		JLabel salidasTuristicasLabel = new JLabel("Salidas turísticas:");
		salidasTuristicasLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		salidasTuristicasLabel.setBounds(0, 63, 139, 14);
		getContentPane().add(salidasTuristicasLabel);
		
		JComboBox comboInscriptos = new JComboBox();
		comboInscriptos.setBounds(145, 248, 212, 24);
		getContentPane().add(comboInscriptos);
		this.comboPaquetes = comboInscriptos;
		
		JLabel inscriptosLabel = new JLabel("Inscriptos:");
		inscriptosLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		inscriptosLabel.setBounds(46, 253, 81, 14);
		getContentPane().add(inscriptosLabel);
		
		fechaSalida = new JTextArea();
		fechaSalida.setLineWrap(true);
		fechaSalida.setWrapStyleWord(true);
		fechaSalida.setEditable(false);
		fechaSalida.setBounds(145, 123, 212, 15);
		getContentPane().add(fechaSalida);
		this.fechaSalida = fechaSalida;
		
		//proveedor = new JTextArea();
		//proveedor.setEditable(false);
	//	proveedor.setBounds(145, 319, 212, 15);
		//getContentPane().add(proveedor);
		
	   comboDeps.addActionListener(new ActionListener() {     
		     public void actionPerformed(ActionEvent e) {
		    	 List<String> actividades = icat.obtenerIdActividadesTuristicas(comboDeps.getSelectedItem().toString());
		    	 comboActividades.setModel(new DefaultComboBoxModel(actividades.toArray()));
		   
		     }
	   });
	   
	   comboActividades.addActionListener(new ActionListener() {     
		     public void actionPerformed(ActionEvent e) {
		    	//se cargan identificadores de salidas
		    	 String act = comboActividades.getSelectedItem().toString();
		    	List<String> salidas = icat.obtenerIdSalidasTuristicas(act);
		    	comboSalidas.setModel(new DefaultComboBoxModel(salidas.toArray()));
		   }
	   });

}
	
	private void mostrarDatosSalida(DTSalidaTuristica salida) {
		try {
			
			nombre.setText(salida.getNombre());
			fechaSalida.setText(salida.getFechaHoraSalida().toString());
			horaSalida.setText(salida.getFechaHoraSalida().toString());
			lugarSalida.setText(salida.getLugarSalida());
			maxCantTuristas.setText(String.valueOf(salida.getCantMaxTuristas()));
			fechaAlta.setText(salida.getFechaAlta().toString());	
			
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
        fechaSalida.setText("");
        horaSalida.setText("");
        lugarSalida.setText("");
		maxCantTuristas.setText("");
		fechaAlta.setText("");
    }
}
