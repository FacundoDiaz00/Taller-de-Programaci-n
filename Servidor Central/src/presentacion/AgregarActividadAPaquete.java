package presentacion;

import logica.controladores.IControladorUsuario;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import logica.controladores.Fabrica;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;

public class AgregarActividadAPaquete extends JInternalFrame {
	private JComboBox comboPaquetes;
	private JComboBox comboDepartamentos;
	private JComboBox comboActividades;
	

	/**
	 * Create the frame.
	 */
	public AgregarActividadAPaquete() {
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
				actualizarComboActividades();
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
		panelSeleccionPaquete.setLayout(new BoxLayout(panelSeleccionPaquete, BoxLayout.X_AXIS));
		panelSeleccionPaquete.add(lblNewLabel_2);
		
		Component gluePaquete = Box.createHorizontalStrut(120);
		panelSeleccionPaquete.add(gluePaquete);
		panelSeleccionPaquete.add(comboPaquetes);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelSeleccionPaquete.add(horizontalStrut);
		
	   comboPaquetes.addActionListener(new ActionListener() {     
		     public void actionPerformed(ActionEvent e) {
		        // Se seleccionó un paquete
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
				// TODO Auto-generated method stub
				
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}
       	
       });
	   
	   comboDepartamentos.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
		   		// Se seleccionó un departamento
		   		actualizarComboActividades();
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
	   
	   comboActividades.addPopupMenuListener( new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				actualizarComboActividades();
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
	   
	   Component glueActividadTuristica = Box.createHorizontalStrut(120);
	   panelSeleccionActividad.add(glueActividadTuristica);
	   
	   panelSeleccionActividad.add(comboActividades);
	   
	   Component horizontalStrut_2 = Box.createHorizontalStrut(20);
	   panelSeleccionActividad.add(horizontalStrut_2);
	   

	   
	   JPanel parte_inferior = new JPanel();
	   panel.add(parte_inferior, BorderLayout.SOUTH);
	   parte_inferior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	   
	   JButton botonCancelar = new JButton("Cancelar");
	   parte_inferior.add(botonCancelar);
	   
	   JButton botonAgregar = new JButton("Agregar");
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
	
	private void actualizarComboPaquete() {
		
	}
	
	private void actualizarComboDepartamentos() {
		
	}
	
	private void actualizarComboActividades() {
		
	}
	
    private void limpiarSelecciones() {
    	comboPaquetes.setSelectedItem("elija un paquete");
    	comboDepartamentos.setSelectedItem("elija un departamento");
    	comboActividades.setSelectedItem("elija una actividad");
    }
    
    private void agregarActividad() {
    	
    }
}
