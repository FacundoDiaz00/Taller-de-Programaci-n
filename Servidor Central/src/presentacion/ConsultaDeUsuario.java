package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import logica.controladores.IControladorUsuario;
import logica.datatypes.DTProveedorDetalle;
import logica.datatypes.DTTuristaDetalle;
import logica.datatypes.DTUsuario;

public class ConsultaDeUsuario extends JInternalFrame {
	private Principal principal;
	private IControladorUsuario icu;

	private String seleccionNickname;

	private JComboBox<String> comboBoxSeleccionUsr;

	private JPanel panel_principal;
	private JPanel panel_eleccion;
	private JPanel panel_consulta;

	private JPanel panel_izquierda;
	private JPanel panel_mostrar_datos;

	private JTextArea txtNickname;
	private JTextArea txtNombre;
	private JTextArea txtApellido;
	private JTextArea txtCorreo;
	private JTextArea txtFechaDeNacimiento;
	private JTextArea txtNacionalidad;
	private JTextArea txtTipo;

	private JPanel panel_derecha;
	private JPanel casos;
	private JPanel proveedor_panel;
	private JPanel panelActividadesYSalidasProveedor;
	private JPanel turista_panel;
	private JPanel panelSalidasTurista;
	private JLabel lblNewLabel_6_2;
	private JTextArea txtDescripcion;
	private JLabel lblNewLabel_6_3;
	private JTextArea txtURL;
	private JLabel lblSalidasTurista;
	private JLabel lblActividadesProv;

	/**
	 * Create the frame.
	 */
	public ConsultaDeUsuario(Principal principal, IControladorUsuario icu) {
		this.principal = principal;
		this.icu = icu;

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
		panel_eleccion.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Elija un usuario:");
		lblNewLabel_1.setBounds(12, 10, 114, 15);
		panel_eleccion.add(lblNewLabel_1);

		comboBoxSeleccionUsr = new JComboBox<String>();
		comboBoxSeleccionUsr.setBounds(133, 5, 245, 24);
		panel_eleccion.add(comboBoxSeleccionUsr);

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

		lblNewLabel_6_3 = new JLabel("URL: ");
		proveedor_panel.add(lblNewLabel_6_3);

		txtURL = new JTextArea();
		txtURL.setText("...");
		txtURL.setEditable(false);
		proveedor_panel.add(txtURL);

		lblNewLabel_6_2 = new JLabel("Descripcion general:");
		proveedor_panel.add(lblNewLabel_6_2);

		txtDescripcion = new JTextArea();
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		txtDescripcion.setText("...");
		txtDescripcion.setEditable(false);
		proveedor_panel.add(txtDescripcion);

		lblActividadesProv = new JLabel("Actividades y salidas: ");
		proveedor_panel.add(lblActividadesProv);

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

		lblSalidasTurista = new JLabel("Salidas a las que se inscribió: ");
		turista_panel.add(lblSalidasTurista);

		panelSalidasTurista = new JPanel();
		turista_panel.add(panelSalidasTurista);
		panelSalidasTurista.setLayout(new BoxLayout(panelSalidasTurista, BoxLayout.Y_AXIS));

		comboBoxSeleccionUsr.addPopupMenuListener(new PopupMenuListener() {

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

	private void actualizarComboBox() {
		List<String> usuarios = icu.obtenerIdUsuarios();
		comboBoxSeleccionUsr.setModel(new DefaultComboBoxModel(usuarios.toArray()));
	}

	@Override
	public void setVisible(boolean flag) {
		super.setVisible(flag);
		if (flag) {
			actualizarComboBox();
		}
	}

	public void seleccionYaHecha(String nickname) {
		comboBoxSeleccionUsr.removeAllItems();

		seleccionNickname = nickname;
		seSeleccionoUnUsuario();
	}

	private void ejecutarCasoConsultaSalidaTuristca(String nombreSalida) {
		principal.mostrarConsultaDeSalidaTuristica(nombreSalida);
	}

	private void ejecutarCasoConsultaActividadTuristca(String nombreActividad) {
		principal.mostrarConsultaDeActividadTuristica(nombreActividad);
	}

	private void seSeleccionoUnUsuario() {
		String seleccion = seleccionNickname;

		try {
			DTUsuario usr = icu.obtenerDTUsuarioDetalle(seleccion);

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

				if (inscripciones.size() == 0) {
					lblSalidasTurista.setVisible(false);
					panelSalidasTurista.setVisible(false);
				} else {
					lblSalidasTurista.setVisible(true);
					panelSalidasTurista.setVisible(true);
				}

				for (var insc : inscripciones) {
					JTextArea txt = new JTextArea();
					txt.setText(insc);
					txt.setEditable(false);
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
				txtDescripcion.setText(prov.getDescrpicionGeneral());
				txtURL.setText(prov.getLink());

				panelActividadesYSalidasProveedor.removeAll();

				var actividades = prov.getActividades();

				if (actividades.size() == 0) {
					lblActividadesProv.setVisible(false);
					panelActividadesYSalidasProveedor.setVisible(false);
				} else {
					lblActividadesProv.setVisible(true);
					panelActividadesYSalidasProveedor.setVisible(true);
				}

				for (var actividad : actividades) {

					JPanel panel = new JPanel();
					panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

					JLabel titulo = new JLabel("Actividad: ");
					panel.add(titulo);

					JTextArea txt = new JTextArea();
					txt.setText(actividad.getNombre());
					txt.setEditable(false);
					txt.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							ejecutarCasoConsultaActividadTuristca(actividad.getNombre());
						}
					});

					panel.add(txt);

					var salidas_de_la_actividad = actividad.getSalidas();

					for (var salida : salidas_de_la_actividad.values()) {
						JTextArea txt_sal = new JTextArea();
						txt_sal.setText("   Salida: " + salida.getNombre());
						txt_sal.setEditable(false);
						txt_sal.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								ejecutarCasoConsultaSalidaTuristca(salida.getNombre());
							}
						});
						panel.add(txt_sal);
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
