package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import logica.controladores.Fabrica;
import logica.datatypes.DTProveedorDetalle;
import logica.datatypes.DTTuristaDetalle;
import logica.datatypes.DTUsuario;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConsultaDeUsuario extends JInternalFrame {
	private Principal principal;
	
	String seleccionNickname;
	
	private JComboBox<String> comboBoxSeleccionUsr;
	
	JPanel panel_principal;
	JPanel panel_eleccion;
	JPanel panel_consulta;
	
	JPanel panel_izquierda;
	JPanel panel_mostrar_datos;
	
	JTextArea txtNickname;
	JTextArea txtNombre;
	JTextArea txtApellido;
	JTextArea txtCorreo;
	JTextArea txtFechaDeNacimiento;
	JTextArea txtTipo;
	JTextArea txtNacionalidad;
	
	
	JPanel panel_derecha;
	JPanel casos;
	JPanel proveedor_panel;
	JPanel panelActividadesYSalidasProveedor;
	JPanel turista_panel;
	JPanel panelSalidasTurista;
	
	


	/**
	 * Create the frame.
	 */
	public ConsultaDeUsuario(Principal principal) {
		this.principal = principal;
		
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consultar un Usuario");
        setBounds(30, 30, 400, 279);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        
        panel_principal = new JPanel();
        getContentPane().add(panel_principal);
        panel_principal.setLayout(new BoxLayout(panel_principal, BoxLayout.Y_AXIS));
        
        panel_eleccion = new JPanel();
        panel_principal.add(panel_eleccion);
        panel_eleccion.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JLabel lblNewLabel_1 = new JLabel("Elija un usuario:");
        panel_eleccion.add(lblNewLabel_1);
        
        

        comboBoxSeleccionUsr = new JComboBox<String>();
        panel_eleccion.add(comboBoxSeleccionUsr);
        actualizarComboBox();
        
        
        
        panel_consulta = new JPanel();
        panel_principal.add(panel_consulta);
        
        panel_izquierda = new JPanel();
        panel_consulta.add(panel_izquierda);
        panel_izquierda.setLayout(new BoxLayout(panel_izquierda, BoxLayout.Y_AXIS));
        
        panel_mostrar_datos = new JPanel();
        
        panel_izquierda.add(panel_mostrar_datos);
        panel_mostrar_datos.setLayout(new BoxLayout(panel_mostrar_datos, BoxLayout.Y_AXIS));
        
        JPanel panel_3 = new JPanel();
        panel_mostrar_datos.add(panel_3);
        panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
        
        JLabel lblNewLabel = new JLabel("Nickname: ");
        panel_3.add(lblNewLabel);
        
        txtNickname = new JTextArea();
        txtNickname.setEditable(false);
        txtNickname.setText("...");
        panel_3.add(txtNickname);
        
        JPanel panel_4 = new JPanel();
        panel_mostrar_datos.add(panel_4);
        panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
        
        JLabel lblNewLabel_2 = new JLabel("Nombre: ");
        panel_4.add(lblNewLabel_2);
        
        txtNombre = new JTextArea();
        txtNombre.setText("...");
        txtNombre.setEditable(false);
        panel_4.add(txtNombre);
        
        JPanel panel_5 = new JPanel();
        panel_mostrar_datos.add(panel_5);
        panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
        
        JLabel lblNewLabel_2_1 = new JLabel("Apellido: ");
        panel_5.add(lblNewLabel_2_1);
        
        txtApellido = new JTextArea();
        txtApellido.setText("...");
        txtApellido.setEditable(false);
        panel_5.add(txtApellido);
        
        JPanel panel_6 = new JPanel();
        panel_mostrar_datos.add(panel_6);
        panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));
        
        JLabel lblNewLabel_3 = new JLabel("Correo: ");
        panel_6.add(lblNewLabel_3);
        
        txtCorreo = new JTextArea();
        txtCorreo.setText("...");
        txtCorreo.setEditable(false);
        panel_6.add(txtCorreo);
        
        JPanel panel_7 = new JPanel();
        panel_mostrar_datos.add(panel_7);
        panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));
        
        JLabel lblNewLabel_4 = new JLabel("Fecha de Nacimiento: ");
        panel_7.add(lblNewLabel_4);
        
        txtFechaDeNacimiento = new JTextArea();
        txtFechaDeNacimiento.setText("...");
        txtFechaDeNacimiento.setEditable(false);
        panel_7.add(txtFechaDeNacimiento);
        
        JPanel panel_3_1 = new JPanel();
        panel_mostrar_datos.add(panel_3_1);
        panel_3_1.setLayout(new BoxLayout(panel_3_1, BoxLayout.X_AXIS));
        
        JLabel lblTipo = new JLabel("Tipo: ");
        panel_3_1.add(lblTipo);
        
        txtTipo = new JTextArea();
        txtTipo.setText("...");
        txtTipo.setEditable(false);
        panel_3_1.add(txtTipo);
        
        panel_derecha = new JPanel();
        panel_consulta.add(panel_derecha);
        panel_derecha.setLayout(new BoxLayout(panel_derecha, BoxLayout.Y_AXIS));
        
        casos = new JPanel();
        panel_derecha.add(casos);
        
        casos.setLayout(new BoxLayout(casos, BoxLayout.Y_AXIS));
        
        proveedor_panel = new JPanel();
        casos.add(proveedor_panel);
        proveedor_panel.setLayout(new BoxLayout(proveedor_panel, BoxLayout.Y_AXIS));
        
        JLabel lblNewLabel_5 = new JLabel("Actividades y salidas: ");
        proveedor_panel.add(lblNewLabel_5);
        
        panelActividadesYSalidasProveedor = new JPanel();
        proveedor_panel.add(panelActividadesYSalidasProveedor);
        panelActividadesYSalidasProveedor.setLayout(new BoxLayout(panelActividadesYSalidasProveedor, BoxLayout.Y_AXIS));
        
        turista_panel = new JPanel();
        casos.add(turista_panel);
        turista_panel.setLayout(new BoxLayout(turista_panel, BoxLayout.Y_AXIS));
        
        JLabel lblNewLabel_6_1 = new JLabel("Nacionalidad: ");
        turista_panel.add(lblNewLabel_6_1);
        
        txtNacionalidad = new JTextArea();
        txtNacionalidad.setEditable(false);
        txtNacionalidad.setText("...");
        turista_panel.add(txtNacionalidad);
        
        JLabel lblNewLabel_6 = new JLabel("Salidas a las que se inscribió: ");
        turista_panel.add(lblNewLabel_6);
        
        panelSalidasTurista = new JPanel();
        turista_panel.add(panelSalidasTurista);
        panelSalidasTurista.setLayout(new BoxLayout(panelSalidasTurista, BoxLayout.Y_AXIS));
        
        
        
        comboBoxSeleccionUsr.addPopupMenuListener( new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// Esto es lo que actualiza la lista cada vez que se abre.
				actualizarComboBox();
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
        
        comboBoxSeleccionUsr.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		seleccionNickname = (String) comboBoxSeleccionUsr.getSelectedItem();
        		seSeleccionoUnUsuario();
        	}
        });
        
        casos.removeAll();
    }
	
	public void actualizarComboBox() {
		List<String> usuarios = Fabrica.getInstancia().getIControladorUsuario().obtenerIdUsuarios();
		comboBoxSeleccionUsr.setModel(new DefaultComboBoxModel(usuarios.toArray()));
	}
	
	public void seleccionYaHecha(String nickname) {
		seleccionNickname = nickname;
		seSeleccionoUnUsuario();
	}
	
	public void ejecutarCasoConsultaSalidaTuristca(String nombreSalida) {
		// TODO: implementar que se abra la ventana con esta salida ya elegida
		System.out.println("Se quiso ejecutar el caso de uso Consulta Salida Turistica con el nombre "+ nombreSalida);
	}
	
	public void ejecutarCasoConsultaActividadTuristca(String nombreActividad) {
		// TODO: implementar que se abra la ventana con esta actividad ya elegida
		System.out.println("Se quiso ejecutar el caso de uso Consulta Actividad Turistica con el nombre "+ nombreActividad);
		principal.mostrarConsultaDeActividadTuristica(nombreActividad);
	}


	private void seSeleccionoUnUsuario() {
		String seleccion = seleccionNickname;
		System.out.println(seleccion);
		try {
			DTUsuario usr = Fabrica.getInstancia().getIControladorUsuario().obtenerDTUsuarioDetalle(seleccion);
			
			boolean mostrar_datos = true;
			boolean mostrar_proveedor = true;
			
			txtNickname.setText(usr.getNickname());
			txtNombre.setText(usr.getNombre());
			txtApellido.setText(usr.getApellido());
			txtCorreo.setText(usr.getCorreo());
			txtFechaDeNacimiento.setText(usr.getFechaNac().toString());

			
			if (usr instanceof DTTuristaDetalle) { 
				DTTuristaDetalle tur = (DTTuristaDetalle) usr;

				casos.removeAll();
				casos.add(turista_panel);
				      
				txtTipo.setText("Turista");
				txtNacionalidad.setText(tur.getNacionalidad());
				
				panelSalidasTurista.removeAll();
				var inscripciones = tur.getInscripciones();
				
				for (var insc : inscripciones) {
					JLabel txt = new JLabel(insc);
					txt.addMouseListener(new MouseAdapter() {
			        	@Override
			        	public void mouseClicked(MouseEvent e) {
			        		ejecutarCasoConsultaSalidaTuristca(insc);
			        	}
			        });
					
					panelSalidasTurista.add(txt);
				}
				
				panelSalidasTurista.add(panelSalidasTurista);
			} else if (usr instanceof DTProveedorDetalle) {
				DTProveedorDetalle prov = (DTProveedorDetalle) usr;
				
				casos.removeAll();
				casos.add(proveedor_panel);
				
				txtTipo.setText("Proveedor");
				
				
				var act_sal = prov.getActividadesSalidas();
				
				System.out.print(act_sal.toString());
				
				panelActividadesYSalidasProveedor.removeAll();
				
				for (var actividad : act_sal.keySet()) {
					System.out.print(actividad);
					JPanel panel = new JPanel();
					JLabel titulo = new JLabel("Actividad: ");
					panel.add(titulo);
					
					JLabel nomb_actividad = new JLabel(actividad);
					nomb_actividad.addMouseListener(new MouseAdapter() {
			        	@Override
			        	public void mouseClicked(MouseEvent e) {
			        		ejecutarCasoConsultaActividadTuristca(actividad);
			        	}
			        });
					panel.add(nomb_actividad);
					
					for (var salida : act_sal.get(actividad)) {
						System.out.print(salida);
						JLabel nomb_salida = new JLabel(salida);
						nomb_salida.addMouseListener(new MouseAdapter() {
				        	@Override
				        	public void mouseClicked(MouseEvent e) {
				        		ejecutarCasoConsultaSalidaTuristca(salida);
				        	}
				        });
					}
					panelActividadesYSalidasProveedor.add(panel);
				}
			} else {
				throw new Exception("Se devolvió un tipo de DTUsuario no registado");
			}
			
			
		} catch (Exception ex) {
			// Esta excepcion no debería ocurrir pero por las dudas la pongo
			
		}
	}
}
