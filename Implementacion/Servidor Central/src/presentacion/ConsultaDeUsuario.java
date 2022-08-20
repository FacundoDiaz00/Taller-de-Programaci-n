package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;

import javax.swing.JTextPane;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.List;

public class ConsultaDeUsuario extends JInternalFrame {


	/**
	 * Create the frame.
	 */
	public ConsultaDeUsuario() {
        List<String> usuarios = Fabrica.getInstancia().getIControladorUsuario().obtenerIdUsuarios();
		
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consultar un Usuario");
        setBounds(30, 30, 400, 280);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        
        JPanel panel_principal = new JPanel();
        getContentPane().add(panel_principal);
        panel_principal.setLayout(new BoxLayout(panel_principal, BoxLayout.Y_AXIS));
        
        JPanel panel_eleccion = new JPanel();
        panel_principal.add(panel_eleccion);
        panel_eleccion.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JLabel lblNewLabel_1 = new JLabel("Elija un usuario:");
        panel_eleccion.add(lblNewLabel_1);
        JComboBox comboBox_1 = new JComboBox(usuarios.toArray());
        

        panel_eleccion.add(comboBox_1);
        
        
        
        JPanel panel_consulta = new JPanel();
        panel_principal.add(panel_consulta);
        
        JPanel panel_izquierda = new JPanel();
        panel_izquierda.setVisible(false);
        panel_consulta.add(panel_izquierda);
        panel_izquierda.setLayout(new BoxLayout(panel_izquierda, BoxLayout.Y_AXIS));
        
        JPanel panel_mostrar_datos = new JPanel();
        
        panel_izquierda.add(panel_mostrar_datos);
        panel_mostrar_datos.setLayout(new BoxLayout(panel_mostrar_datos, BoxLayout.Y_AXIS));
        
        JPanel panel_3 = new JPanel();
        panel_mostrar_datos.add(panel_3);
        panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
        
        JLabel lblNewLabel = new JLabel("Nickname: ");
        panel_3.add(lblNewLabel);
        
        JTextArea txtNickname = new JTextArea();
        txtNickname.setEditable(false);
        txtNickname.setText("...");
        panel_3.add(txtNickname);
        
        JPanel panel_4 = new JPanel();
        panel_mostrar_datos.add(panel_4);
        panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
        
        JLabel lblNewLabel_2 = new JLabel("Nombre: ");
        panel_4.add(lblNewLabel_2);
        
        JTextArea txtNombre = new JTextArea();
        txtNombre.setText("...");
        txtNombre.setEditable(false);
        panel_4.add(txtNombre);
        
        JPanel panel_5 = new JPanel();
        panel_mostrar_datos.add(panel_5);
        panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
        
        JLabel lblNewLabel_2_1 = new JLabel("Apellido: ");
        panel_5.add(lblNewLabel_2_1);
        
        JTextArea txtApellido = new JTextArea();
        txtApellido.setText("...");
        txtApellido.setEditable(false);
        panel_5.add(txtApellido);
        
        JPanel panel_6 = new JPanel();
        panel_mostrar_datos.add(panel_6);
        panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));
        
        JLabel lblNewLabel_3 = new JLabel("Correo: ");
        panel_6.add(lblNewLabel_3);
        
        JTextArea txtCorreo = new JTextArea();
        txtCorreo.setText("...");
        txtCorreo.setEditable(false);
        panel_6.add(txtCorreo);
        
        JPanel panel_7 = new JPanel();
        panel_mostrar_datos.add(panel_7);
        panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));
        
        JLabel lblNewLabel_4 = new JLabel("Fecha de Nacimiento: ");
        panel_7.add(lblNewLabel_4);
        
        JTextArea txtFechaDeNacimiento = new JTextArea();
        txtFechaDeNacimiento.setText("...");
        txtFechaDeNacimiento.setEditable(false);
        panel_7.add(txtFechaDeNacimiento);
        
        JPanel panel_3_1 = new JPanel();
        panel_mostrar_datos.add(panel_3_1);
        panel_3_1.setLayout(new BoxLayout(panel_3_1, BoxLayout.X_AXIS));
        
        JLabel lblTipo = new JLabel("Tipo: ");
        panel_3_1.add(lblTipo);
        
        JTextArea txtTipo = new JTextArea();
        txtTipo.setText("...");
        txtTipo.setEditable(false);
        panel_3_1.add(txtTipo);
        
        JPanel panel_derecha = new JPanel();
        panel_consulta.add(panel_derecha);
        panel_derecha.setLayout(new BoxLayout(panel_derecha, BoxLayout.Y_AXIS));
        
        JPanel casos = new JPanel();
        
        // HACER VISIBLE CUANDO SE SELECCIONE UN USUARIO
        casos.setVisible(false);
        panel_derecha.add(casos);
        
        casos.setLayout(new BoxLayout(casos, BoxLayout.Y_AXIS));
        
        JPanel proveedor_panel = new JPanel();
        proveedor_panel.setVisible(false);
        casos.add(proveedor_panel);
        proveedor_panel.setLayout(new BoxLayout(proveedor_panel, BoxLayout.Y_AXIS));
        
        JLabel lblNewLabel_5 = new JLabel("Actividades y salidas: ");
        proveedor_panel.add(lblNewLabel_5);
        
        JTextArea txtActividadesYSalidasProveedor = new JTextArea();
        txtActividadesYSalidasProveedor.setText("...");
        proveedor_panel.add(txtActividadesYSalidasProveedor);
        
        JPanel turista_panel = new JPanel();
        turista_panel.setVisible(false);
        casos.add(turista_panel);
        turista_panel.setLayout(new BoxLayout(turista_panel, BoxLayout.Y_AXIS));
        
        JLabel lblNewLabel_6_1 = new JLabel("Nacionalidad: ");
        turista_panel.add(lblNewLabel_6_1);
        
        JTextArea txtNacionalidad = new JTextArea();
        txtNacionalidad.setText("...");
        turista_panel.add(txtNacionalidad);
        
        JLabel lblNewLabel_6 = new JLabel("Salidas a las que se inscribió: ");
        turista_panel.add(lblNewLabel_6);
        
        JTextArea txtSalidasTurista = new JTextArea();
        txtSalidasTurista.setText("...");
        turista_panel.add(txtSalidasTurista);
        
        
        
        comboBox_1.addPopupMenuListener( new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// Esto es lo que actualiza la lista cada vez que se abre.
				var usuarios = Fabrica.getInstancia().getIControladorUsuario().obtenerIdUsuarios();
				comboBox_1.setModel(new DefaultComboBoxModel(usuarios.toArray()));
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
        
        comboBox_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Esto es lo que se ejecuta cada vez que se selecciona un item de la lista
        		String seleccion = (String) comboBox_1.getSelectedItem();
        		
        		try {
        			DTUsuario usr = Fabrica.getInstancia().getIControladorUsuario().obtenerDTUsuario(seleccion);
        			boolean mostrar_datos = true;
        			
        			txtNickname.setText(usr.getNickname());
        			txtNombre.setText(usr.getNombre());
        			txtApellido.setText(usr.getApellido());
        			txtCorreo.setText(usr.getCorreo());
        			txtFechaDeNacimiento.setText(usr.getFechaNac().toString());

            		boolean mostrar_proveedor;
            		boolean mostrar_turista;
            		
            		if (usr instanceof DTTurista) { 
            			DTTurista tur = (DTTurista) usr;
            			mostrar_proveedor = false;
            			mostrar_turista = true;
            			      
            			txtNacionalidad.setText(tur.getNacionalidad());
            			// TODO: Esto hay que formatearlo mejor pero por ahora debería funcionar
            			txtSalidasTurista.setText(tur.getInscripciones().toString());
            		} else if (usr instanceof DTProveedor) {
            			DTProveedor prov = (DTProveedor) usr;
            			mostrar_proveedor = true;
            			mostrar_turista = false;
            			// TODO: Esto hay que formatearlo mejor pero por ahora debería funcionar
            			txtActividadesYSalidasProveedor.setText(prov.getActividadesSalidas().toString());
            		} else {
            			throw new Exception("Se devolvió un tipo de DTUsuario no registado");
            		}
            		
            		panel_consulta.setVisible(mostrar_datos);
            		proveedor_panel.setVisible(mostrar_proveedor);
            		turista_panel.setVisible(mostrar_turista);
        		} catch (Exception exepcion_busqueda_usuario) {
        			// Esta excepcion no debería ocurrir pero por las dudas la pongo
        			
        		}
        		
        		
        	}
        });
    }
}
