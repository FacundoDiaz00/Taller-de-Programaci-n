package presentacion;

import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultaDeActividadTuristica extends JInternalFrame {

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
	
	/**
	 * Create the frame.
	 */
	public ConsultaDeActividadTuristica(IControladorActividadTuristica icat) {
		Fabrica f = Fabrica.getInstancia();
		this.icat = f.getIControladorActividadTuristica();
		setTitle("Consutla de Actividad Turística");
		setBounds(100, 100, 409, 369);
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
		duracionLabel.setBounds(7, 111, 120, 14);
		getContentPane().add(duracionLabel);
		
		JLabel costoLabel = new JLabel("Costo:");
		costoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		costoLabel.setBounds(0, 139, 121, 14);
		getContentPane().add(costoLabel);
		
		JLabel ciudadLabel = new JLabel("Ciudad:");
		ciudadLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		ciudadLabel.setBounds(7, 165, 120, 14);
		getContentPane().add(ciudadLabel);
		
		JLabel fechaAltaLabel = new JLabel("Fecha de alta:");
		fechaAltaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		fechaAltaLabel.setBounds(7, 191, 120, 14);
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

	
		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaDeActividad(e);
			}
		});
		aceptar.setBounds(270, 303, 117, 25);
		getContentPane().add(aceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
			}
		});
		btnCancelar.setBounds(7, 303, 117, 25);
		getContentPane().add(btnCancelar);
		
		JTextArea nombre = new JTextArea();
		nombre.setEditable(false);
		nombre.setBounds(145, 63, 212, 15);
		getContentPane().add(nombre);
		this.nombre = nombre;
		
		JTextArea descripcion = new JTextArea();
		descripcion.setEditable(false);
		descripcion.setBounds(145, 89, 212, 15);
		getContentPane().add(descripcion);
		this.descripcion = descripcion;
		
		JTextArea duracion = new JTextArea();
		duracion.setEditable(false);
		duracion.setBounds(145, 111, 212, 15);
		getContentPane().add(duracion);
		this.duracion = duracion;
	
		
		JTextArea costo = new JTextArea();
		costo.setEditable(false);
		costo.setBounds(145, 139, 212, 15);
		getContentPane().add(costo);
		this.costo = costo;
		
		JTextArea ciudad = new JTextArea();
		ciudad.setEditable(false);
		ciudad.setBounds(145, 165, 212, 15);
		getContentPane().add(ciudad);
		this.ciudad = ciudad;
		
		JTextArea fechaAlta = new JTextArea();
		fechaAlta.setEditable(false);
		fechaAlta.setBounds(145, 191, 212, 15);
		getContentPane().add(fechaAlta);
		this.fechaAlta = fechaAlta;
		
		JComboBox comboSalidas = new JComboBox();
		comboSalidas.setBounds(145, 218, 212, 24);
		getContentPane().add(comboSalidas);
		this.comboSalidas = comboSalidas;

		
		JLabel salidasTuristicasLabel = new JLabel("Salidas turísticas:");
		salidasTuristicasLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		salidasTuristicasLabel.setBounds(0, 223, 139, 14);
		getContentPane().add(salidasTuristicasLabel);
		
		JComboBox comboPaquetes = new JComboBox();
		comboPaquetes.setBounds(145, 251, 212, 24);
		getContentPane().add(comboPaquetes);
		this.comboPaquetes = comboPaquetes;
		
		JLabel paquetesLabel = new JLabel("Paquetes:");
		paquetesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		paquetesLabel.setBounds(46, 256, 81, 14);
		getContentPane().add(paquetesLabel);
		
	   comboDeps.addActionListener(new ActionListener() {     
		     public void actionPerformed(ActionEvent e) {
		    	 List<String> actividades = Fabrica.getInstancia().getIControladorActividadTuristica().obtenerIdActividadesTuristicas(comboDeps.getSelectedItem().toString());
		    	 comboActividades.setModel(new DefaultComboBoxModel(actividades.toArray()));
		   
		     }
	   });

}
	
	private void consultaDeActividad(ActionEvent action) {

	}
	
	public void actualizarComboDepartamentos() {
		List<String> deps = Fabrica.getInstancia().getIControladorActividadTuristica().obtenerIdDepartamentos();
		comboDepartamentos.setModel(new DefaultComboBoxModel(deps.toArray()));
	}
	
    private void limpiarFormulario() {
        nombre.setText("");
        descripcion.setText("");
        duracion.setText("");
        costo.setText("");
		ciudad.setText("");
		fechaAlta.setText("");
		comboSalidas.setSelectedIndex(0);
		comboPaquetes.setSelectedIndex(0);
    }
}
