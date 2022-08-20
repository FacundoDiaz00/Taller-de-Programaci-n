package presentacion;

import java.awt.EventQueue;
import logica.controladores.IControladorActividadTuristica;
import logica.entidades.Proveedor;
import logica.entidades.Usuario;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import logica.controladores.Fabrica;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

public class ConsultaDeActividadTuristica extends JInternalFrame {
	
	private JComboBox<String> comboProveedores;
	private JComboBox comboDepartamentos;
	private IControladorActividadTuristica icat;
	
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
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(6, 63, 121, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Descripcion:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(7, 89, 120, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Duracion:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setBounds(7, 111, 120, 14);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Costo:");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1.setBounds(0, 139, 121, 14);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Ciudad:");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1.setBounds(7, 165, 120, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Fecha de alta:");
		lblNewLabel_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1_1_1.setBounds(7, 191, 120, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1);
		
		JComboBox comboProveedores = new JComboBox<String>();
		comboProveedores.setBounds(145, 7, 212, 24);
		getContentPane().add(comboProveedores);
		
		
        comboProveedores.addPopupMenuListener( new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// Esto es lo que actualiza la lista cada vez que se abre.
				actualizarComboProveedores();
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
        
	   
       comboProveedores.addPopupMenuListener( new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// Esto es lo que actualiza la lista cada vez que se abre.
				actualizarComboProveedores();
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
		
		JComboBox comboDepartamentos = new JComboBox();
		comboDepartamentos.setBounds(145, 32, 212, 24);
		getContentPane().add(comboDepartamentos);
		//this.comboProveedores = comboProveedores;
	
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarAT(e);
			}
		});
		btnNewButton.setBounds(270, 303, 117, 25);
		getContentPane().add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
			}
		});
		btnCancelar.setBounds(7, 303, 117, 25);
		getContentPane().add(btnCancelar);
		
		JTextArea txtNickname = new JTextArea();
		txtNickname.setEditable(false);
		txtNickname.setBounds(145, 63, 212, 15);
		getContentPane().add(txtNickname);
		
		JTextArea txtNickname_1 = new JTextArea();
		txtNickname_1.setEditable(false);
		txtNickname_1.setBounds(145, 89, 212, 15);
		getContentPane().add(txtNickname_1);
		
		JTextArea txtNickname_2 = new JTextArea();
		txtNickname_2.setEditable(false);
		txtNickname_2.setBounds(145, 111, 212, 15);
		getContentPane().add(txtNickname_2);
		
		JTextArea txtNickname_3 = new JTextArea();
		txtNickname_3.setEditable(false);
		txtNickname_3.setBounds(145, 139, 212, 15);
		getContentPane().add(txtNickname_3);
		
		JTextArea txtNickname_4 = new JTextArea();
		txtNickname_4.setEditable(false);
		txtNickname_4.setBounds(145, 165, 212, 15);
		getContentPane().add(txtNickname_4);
		
		JTextArea txtNickname_5 = new JTextArea();
		txtNickname_5.setEditable(false);
		txtNickname_5.setBounds(145, 191, 212, 15);
		getContentPane().add(txtNickname_5);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(145, 218, 212, 24);
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1_1 = new JLabel("Salidas turísticas:");
		lblNewLabel_1_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1_1_1_1.setBounds(0, 223, 139, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(145, 251, 212, 24);
		getContentPane().add(comboBox_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1 = new JLabel("Paquetes:");
		lblNewLabel_1_1_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1_1_1_1_1.setBounds(46, 256, 81, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1_1_1);
}
	
	private void agregarAT(ActionEvent action) {
		String prov = comboProveedores.getSelectedItem().toString();
		String dpto = comboDepartamentos.getSelectedItem().toString();
		String nom = nombre.getText();
		String desc = descripcion.getText();
		int dur = Integer.parseInt(duracion.getText());
		float cost = Float.parseFloat(costo.getText());
		String ciu = ciudad.getText();
		String FAlta = FdeAlta.getText();
	      DateTimeFormatter JEFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	      LocalDate fecha = LocalDate.parse(FAlta, JEFormatter);
	      boolean existeActividad;
	    existeActividad = icat.altaActividadTuristica(prov, dpto, nom, desc, dur, cost, ciu, fecha);

		if(existeActividad) {
			limpiarFormulario();
            setVisible(false);
			JOptionPane.showMessageDialog (null, "Se ha dado de alta la Actividad Turistica con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al dar de alta la Actividad Turistica", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void actualizarComboProveedores() {
		ArrayList<String> provs = Fabrica.getInstancia().getIControladorActividadTuristica().obtenerIdProveedores();
		comboProveedores.setModel(new DefaultComboBoxModel(provs.toArray()));
	}
	
    private void limpiarFormulario() {
        nombre.setText("");
        descripcion.setText("");
        //nickname.setText("");
        duracion.setText("");
        costo.setText("");
    }
}
