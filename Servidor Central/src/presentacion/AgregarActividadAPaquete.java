package presentacion;


import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import excepciones.ActividadTuristicaYaRegistradaException;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorPaquete;

import java.awt.Dimension;
import java.awt.Component;
import java.util.List;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class AgregarActividadAPaquete extends JInternalFrame {
	private IControladorPaquete cp;
	private IControladorActividadTuristica cat;
	
	private JComboBox comboPaquetes;
	private JComboBox comboDepartamentos;
	private JComboBox comboActividades;
	

	/**
	 * Create the frame.
	 */
	public AgregarActividadAPaquete(IControladorPaquete cp, IControladorActividadTuristica cat) {
		this.cp = cp;
		this.cat = cat;
		
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				cancelar();
			}
		});
		setTitle("Agregar Actividad Turística a Paquete");
		setBounds(100, 100, 410, 206);
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel selecciones = new JPanel();
		panel.add(selecciones, BorderLayout.NORTH);
		selecciones.setLayout(new BoxLayout(selecciones, BoxLayout.Y_AXIS));
		
		JPanel panelSeleccionPaquete = new JPanel();
		selecciones.add(panelSeleccionPaquete);
		
		JLabel lblNewLabel_2 = new JLabel(" Paquete:");
		
		
		comboPaquetes = new JComboBox();
		comboPaquetes.addPopupMenuListener( new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				actualizarComboPaquete();
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				
			}
        	
        });
		panelSeleccionPaquete.setLayout(new BoxLayout(panelSeleccionPaquete, BoxLayout.X_AXIS));
		panelSeleccionPaquete.add(lblNewLabel_2);
		
		Component gluePaquete = Box.createHorizontalStrut(120);
		panelSeleccionPaquete.add(gluePaquete);
		panelSeleccionPaquete.add(comboPaquetes);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelSeleccionPaquete.add(horizontalStrut);
		
	   comboPaquetes.addActionListener(new ActionListener() {     
		     public void actionPerformed(ActionEvent e) {
		    	 selecionarPaquete();
		     }
		   });
	   
	   Component verticalStrut = Box.createVerticalStrut(10);
	   selecciones.add(verticalStrut);
	   
	   JPanel panelSeleccionDepartamento = new JPanel();
	   selecciones.add(panelSeleccionDepartamento);
	   panelSeleccionDepartamento.setLayout(new BoxLayout(panelSeleccionDepartamento, BoxLayout.X_AXIS));
	   
	   JLabel lblNewLabel_2_1 = new JLabel("Departamento de la Actividad:");
	   panelSeleccionDepartamento.add(lblNewLabel_2_1);
	   
	   comboDepartamentos = new JComboBox();
	   
	   comboDepartamentos.addPopupMenuListener( new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				actualizarComboDepartamentos();
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				
			}
       	
       });
	   
	   comboDepartamentos.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
				seleccionDepartamento();
		   	}
	   });
	   
	   Component glue_Departamento = Box.createHorizontalStrut(5);
	   panelSeleccionDepartamento.add(glue_Departamento);
	   panelSeleccionDepartamento.add(comboDepartamentos);
	   
	   Component horizontalStrut_1 = Box.createHorizontalStrut(20);
	   panelSeleccionDepartamento.add(horizontalStrut_1);
	   
	   Component verticalStrut_1 = Box.createVerticalStrut(10);
	   selecciones.add(verticalStrut_1);
	   
	   JPanel panelSeleccionActividad = new JPanel();
	   selecciones.add(panelSeleccionActividad);
	   panelSeleccionActividad.setLayout(new BoxLayout(panelSeleccionActividad, BoxLayout.X_AXIS));
	   
	   JLabel lblNewLabel_2_1_1 = new JLabel("Actividad:");
	   lblNewLabel_2_1_1.setMinimumSize(new Dimension(100, 14));
	   lblNewLabel_2_1_1.setMaximumSize(new Dimension(100, 14));
	   panelSeleccionActividad.add(lblNewLabel_2_1_1);
	   
	   comboActividades = new JComboBox();
	   comboActividades.setEnabled(false);
	   comboActividades.addPopupMenuListener( new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				actualizarComboActividades();
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				
			}
      	
      });
	   
	   Component glueActividadTuristica = Box.createHorizontalStrut(120);
	   panelSeleccionActividad.add(glueActividadTuristica);
	   
	   panelSeleccionActividad.add(comboActividades);
	   
	   Component horizontalStrut_2 = Box.createHorizontalStrut(20);
	   panelSeleccionActividad.add(horizontalStrut_2);
	   

	   
	   JPanel parte_inferior = new JPanel();
	   panel.add(parte_inferior, BorderLayout.SOUTH);
	   parte_inferior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	   
	   JButton botonCancelar = new JButton("Cancelar");
	   botonCancelar.addActionListener(new ActionListener() {
	   	public void actionPerformed(ActionEvent e) {
	   		cancelar();
	   	}
	   });
	   parte_inferior.add(botonCancelar);
	   
	   JButton botonAgregar = new JButton("Agregar");
	   botonAgregar.addActionListener(new ActionListener() {
	   	public void actionPerformed(ActionEvent e) {
	   		agregarActividad();
	   	}
	   });
	   parte_inferior.add(botonAgregar);

	}
	
	public void setVisible(boolean visi) {
		if (visi) {
			limpiarSelecciones();
			actualizarComboDepartamentos();
			actualizarComboPaquete();
		}
		super.setVisible(visi);
	}

	private void seleccionDepartamento(){
		if(comboPaquetes.getSelectedItem() != null){
			comboActividades.setEnabled(true);
			actualizarComboActividades();
		}
	}
	
	private void selecionarPaquete() {
		if(comboDepartamentos.getSelectedItem() != null){
			comboActividades.setEnabled(true);
			actualizarComboActividades();
		}
	}

	private void actualizarComboPaquete() {
		List<String> idsPaquetes = cp.obtenerNombrePaquetes();
		comboPaquetes.setModel(new DefaultComboBoxModel(idsPaquetes.toArray()));
		if(idsPaquetes.size() > 0){
			comboPaquetes.setSelectedIndex(0);
			if(comboDepartamentos.getSelectedItem() != null){
				actualizarComboActividades();
			}
		}
	}
	
	private void actualizarComboDepartamentos() {
		List<String> idsDepartamentos = cat.obtenerIdDepartamentos();
		comboDepartamentos.setModel(new DefaultComboBoxModel<>(idsDepartamentos.toArray()));
		if(idsDepartamentos.size() > 0) {
			comboDepartamentos.setSelectedIndex(0);
			if(comboPaquetes.getSelectedItem() != null){
				actualizarComboActividades();
			}
		}

	}
	
	private void actualizarComboActividades() {
		String depId = (String) comboDepartamentos.getSelectedItem();
		String packId = (String) comboPaquetes.getSelectedItem();
		//No va a estar habilitado el combo si no seleciono la actividad y el pack
		List<String> idsActividades = cp.obtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete(depId, packId);
		comboActividades.setModel(new DefaultComboBoxModel<>(idsActividades.toArray()));
		if(idsActividades.size() > 0){
			comboActividades.setSelectedIndex(0);
		}
	}
	
    private void limpiarSelecciones() {
		actualizarComboPaquete();
		actualizarComboDepartamentos();
    }
    
    private void agregarActividad() {
		String act = (String) comboActividades.getSelectedItem();
		String pack = (String) comboPaquetes.getSelectedItem();

		if(act == null || pack == null){
			JOptionPane.showMessageDialog(null, "Se debe seleccionar un departamento, actividad de actividad turística y un paquete", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				cp.agregarActividadAPaquete(act, pack);
				setVisible(false);
				limpiarSelecciones();
				JOptionPane.showMessageDialog(null, "Se agregó la actividad al paquete", "Registro de asociación actividad a paquete", JOptionPane.INFORMATION_MESSAGE);
			} catch (ActividadTuristicaYaRegistradaException e) {
				JOptionPane.showMessageDialog(null, "Se debe seleccionar una actividad que no esté ya dentro del paquete", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

    }
    
    private void cancelar() {
    	setVisible(false);
    	limpiarSelecciones();
    }
    
    
    
    
}
