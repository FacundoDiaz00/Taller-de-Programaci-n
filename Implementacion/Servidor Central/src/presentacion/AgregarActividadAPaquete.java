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

public class AgregarActividadAPaquete extends JInternalFrame {
	private JComboBox comboPaquetes;
	private JComboBox comboDepartamentos;
	private JComboBox comboActividades;
	

	/**
	 * Create the frame.
	 */
	public AgregarActividadAPaquete() {
		setTitle("Agregar Actividad Turística a Paquete");
		setBounds(100, 100, 409, 328);
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
		panel.add(selecciones, BorderLayout.CENTER);
		selecciones.setLayout(new BoxLayout(selecciones, BoxLayout.Y_AXIS));
		
		JPanel panelSeleccionPaquete = new JPanel();
		selecciones.add(panelSeleccionPaquete);
		
		JLabel lblNewLabel_2 = new JLabel("Paquete:");
		
		
		comboPaquetes = new JComboBox();
		comboPaquetes.addPopupMenuListener( new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// Esto es lo que actualiza la lista cada vez que se abre.
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
		
		panelSeleccionPaquete.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		panelSeleccionPaquete.add(lblNewLabel_2);
		panelSeleccionPaquete.add(comboPaquetes);
		
	   comboPaquetes.addActionListener(new ActionListener() {     
		     public void actionPerformed(ActionEvent e) {
		        // Se seleccionó un paquete
		     }
		   });
	   
	   JPanel panelSeleccionDepartamento = new JPanel();
	   selecciones.add(panelSeleccionDepartamento);
	   panelSeleccionDepartamento.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
	   
	   JLabel lblNewLabel_2_1 = new JLabel("Departamento de la Actividad:");
	   panelSeleccionDepartamento.add(lblNewLabel_2_1);
	   
	   comboDepartamentos = new JComboBox();
	   
	   comboDepartamentos.addPopupMenuListener( new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// Esto es lo que actualiza la lista cada vez que se abre.
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
	   panelSeleccionDepartamento.add(comboDepartamentos);
	   
	   JPanel panelSeleccionActividad = new JPanel();
	   selecciones.add(panelSeleccionActividad);
	   panelSeleccionActividad.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
	   
	   JLabel lblNewLabel_2_1_1 = new JLabel("Actividad:");
	   panelSeleccionActividad.add(lblNewLabel_2_1_1);
	   
	   comboActividades = new JComboBox();
	   
	   comboActividades.addPopupMenuListener( new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// Esto es lo que actualiza la lista cada vez que se abre.
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
	   
	   panelSeleccionActividad.add(comboActividades);
	   
	   JPanel parte_inferior = new JPanel();
	   panel.add(parte_inferior, BorderLayout.SOUTH);
	   parte_inferior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	   
	   JButton btnAgregar_1 = new JButton("Agregar");
	   parte_inferior.add(btnAgregar_1);
	   
	   JButton btnCancelar_1 = new JButton("Cancelar");
	   parte_inferior.add(btnCancelar_1);

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
