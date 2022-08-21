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

public class AltaDeActividadTuristica extends JInternalFrame {
	
	private JComboBox<String> comboProveedores;
	private JComboBox comboDepartamentos;
	private JTextField nombre;
	private JTextField costo;
	private JTextField descripcion;
	private JTextField duracion;
	private IControladorActividadTuristica icat;
	private JTextField ciudad;
	private JTextField FdeAlta;
	
	/**
	 * Create the frame.
	 */
	public AltaDeActividadTuristica(IControladorActividadTuristica icat) {
		Fabrica f = Fabrica.getInstancia();
		this.icat = f.getIControladorActividadTuristica();
		setTitle("Alta de Actividad Turistica");
		setBounds(100, 100, 409, 328);
		getContentPane().setLayout(null);
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
		
		JLabel lblNewLabel_2 = new JLabel("Proveedores:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(7, 12, 120, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Departamentos:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(7, 37, 120, 14);
		getContentPane().add(lblNewLabel);
		
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
		
		comboProveedores = new JComboBox<String>();
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
		
		comboDepartamentos = new JComboBox();
		comboDepartamentos.setBounds(145, 32, 212, 24);
		getContentPane().add(comboDepartamentos);
		
		nombre = new JTextField();
		nombre.setBounds(131, 61, 258, 20);
		getContentPane().add(nombre);
		nombre.setColumns(10);
		
		descripcion = new JTextField();
		descripcion.setColumns(10);
		descripcion.setBounds(131, 87, 258, 20);
		getContentPane().add(descripcion);
		
		duracion = new JTextField();
		duracion.setColumns(10);
		duracion.setBounds(131, 109, 258, 20);
		getContentPane().add(duracion);
		
		costo = new JTextField();
		costo.setColumns(10);
		costo.setBounds(131, 137, 258, 20);
		getContentPane().add(costo);
		//this.comboProveedores = comboProveedores;
	
       comboDepartamentos.addPopupMenuListener( new PopupMenuListener() {

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
		
		
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarAT(e);
			}
		});
		
		ciudad = new JTextField();
		ciudad.setColumns(10);
		ciudad.setBounds(131, 163, 258, 20);
		getContentPane().add(ciudad);
		ciudad.setEnabled(false);
		
		FdeAlta = new JTextField();
		FdeAlta.setColumns(10);
		FdeAlta.setBounds(131, 189, 258, 20);
		getContentPane().add(FdeAlta);
		btnNewButton.setBounds(272, 259, 117, 25);
		getContentPane().add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
			}
		});
		btnCancelar.setBounds(10, 259, 117, 25);
		getContentPane().add(btnCancelar);
}
	
	private void agregarAT(ActionEvent action) {
		String prov = comboProveedores.getSelectedItem().toString();
		String dpto = comboDepartamentos.getSelectedItem().toString();
		String nom = nombre.getText().toString();
		String desc = descripcion.getText().toString();
		int dur = Integer.parseInt(duracion.getText().toString());
		float cost = Float.parseFloat(costo.getText().toString());
		String ciu = ciudad.getText().toString();
		String FAlta = FdeAlta.getText().toString();
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
		List<String> provs = Fabrica.getInstancia().getIControladorActividadTuristica().obtenerIdProveedores();
		comboProveedores.setModel(new DefaultComboBoxModel(provs.toArray()));
	}
	
	public void actualizarComboDepartamentos() {
		List<String> deptos = Fabrica.getInstancia().getIControladorActividadTuristica().obtenerIdDepartamentos();
		comboDepartamentos.setModel(new DefaultComboBoxModel(deptos.toArray()));
	}
	
    private void limpiarFormulario() {
        nombre.setText("");
        descripcion.setText("");
        //nickname.setText("");
        duracion.setText("");
        costo.setText("");
    }
}
