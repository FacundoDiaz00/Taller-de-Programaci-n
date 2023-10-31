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

import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTProveedorDetalle;
import logica.datatypes.DTTuristaDetalle;
import logica.datatypes.DTUsuario;

public class ConsultaDeUsuario extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private Principal principal;
    private IControladorUsuario contrUsuario;

    private String seleccionNickname;

    private JComboBox<String> comboBoxSeleccionUsr;

    private JPanel panelPrincipal;
    private JPanel panelEleccion;
    private JPanel panelConsulta;

    private JPanel panelIzquierda;
    private JPanel panelMostrarDatos;

    private JTextArea txtNickname;
    private JTextArea txtNombre;
    private JTextArea txtApellido;
    private JTextArea txtCorreo;
    private JTextArea txtFechaDeNacimiento;
    private JTextArea txtNacionalidad;
    private JTextArea txtTipo;

    private JPanel panelDerecha;
    private JPanel casos;
    private JPanel proveedorPanel;
    private JPanel panelActividadesYSalidasProveedor;
    private JPanel turistaPanel;
    private JPanel panelSalidasTurista;
    private JLabel lblNewLabel62;
    private JTextArea txtDescripcion;
    private JLabel lblNewLabel63;
    private JTextArea txtURL;
    private JLabel lblSalidasTurista;
    private JLabel lblActividadesProv;

    /**
     * Create the frame.
     */
    public ConsultaDeUsuario(Principal principal, IControladorUsuario icu) {
        this.principal = principal;
        this.contrUsuario = icu;

        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consultar un Usuario");
        setBounds(30, 30, 400, 279);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        panelPrincipal = new JPanel();
        getContentPane().add(panelPrincipal);
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        panelEleccion = new JPanel();
        panelPrincipal.add(panelEleccion);
        panelEleccion.setLayout(null);

        JLabel lblNewLabel1 = new JLabel("Elija un usuario:");
        lblNewLabel1.setBounds(12, 10, 114, 15);
        panelEleccion.add(lblNewLabel1);

        comboBoxSeleccionUsr = new JComboBox<String>();
        comboBoxSeleccionUsr.setBounds(133, 5, 245, 24);
        panelEleccion.add(comboBoxSeleccionUsr);

        panelConsulta = new JPanel();
        panelPrincipal.add(panelConsulta);

        panelIzquierda = new JPanel();
        panelConsulta.add(panelIzquierda);
        panelIzquierda.setLayout(new BoxLayout(panelIzquierda, BoxLayout.Y_AXIS));

        panelMostrarDatos = new JPanel();

        panelIzquierda.add(panelMostrarDatos);
        panelMostrarDatos.setLayout(new BoxLayout(panelMostrarDatos, BoxLayout.Y_AXIS));

        JPanel panel3 = new JPanel();
        panelMostrarDatos.add(panel3);
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

        JLabel lblNewLabel = new JLabel("Nickname: ");
        panel3.add(lblNewLabel);

        txtNickname = new JTextArea();
        txtNickname.setEditable(false);
        txtNickname.setText("...");
        panel3.add(txtNickname);

        JPanel panel_4 = new JPanel();
        panelMostrarDatos.add(panel_4);
        panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));

        JLabel lblNewLabel_2 = new JLabel("Nombre: ");
        panel_4.add(lblNewLabel_2);

        txtNombre = new JTextArea();
        txtNombre.setText("...");
        txtNombre.setEditable(false);
        panel_4.add(txtNombre);

        JPanel panel_5 = new JPanel();
        panelMostrarDatos.add(panel_5);
        panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));

        JLabel lblNewLabel_2_1 = new JLabel("Apellido: ");
        panel_5.add(lblNewLabel_2_1);

        txtApellido = new JTextArea();
        txtApellido.setText("...");
        txtApellido.setEditable(false);
        panel_5.add(txtApellido);

        JPanel panel_6 = new JPanel();
        panelMostrarDatos.add(panel_6);
        panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));

        JLabel lblNewLabel_3 = new JLabel("Correo: ");
        panel_6.add(lblNewLabel_3);

        txtCorreo = new JTextArea();
        txtCorreo.setText("...");
        txtCorreo.setEditable(false);
        panel_6.add(txtCorreo);

        JPanel panel_7 = new JPanel();
        panelMostrarDatos.add(panel_7);
        panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));

        JLabel lblNewLabel_4 = new JLabel("Fecha de Nacimiento: ");
        panel_7.add(lblNewLabel_4);

        txtFechaDeNacimiento = new JTextArea();
        txtFechaDeNacimiento.setText("...");
        txtFechaDeNacimiento.setEditable(false);
        panel_7.add(txtFechaDeNacimiento);

        JPanel panel_3_1 = new JPanel();
        panelMostrarDatos.add(panel_3_1);
        panel_3_1.setLayout(new BoxLayout(panel_3_1, BoxLayout.X_AXIS));

        JLabel lblTipo = new JLabel("Tipo: ");
        panel_3_1.add(lblTipo);

        txtTipo = new JTextArea();
        txtTipo.setText("...");
        txtTipo.setEditable(false);
        panel_3_1.add(txtTipo);

        panelDerecha = new JPanel();
        panelConsulta.add(panelDerecha);
        panelDerecha.setLayout(new BoxLayout(panelDerecha, BoxLayout.Y_AXIS));

        casos = new JPanel();
        panelDerecha.add(casos);

        casos.setLayout(new BoxLayout(casos, BoxLayout.Y_AXIS));

        proveedorPanel = new JPanel();
        casos.add(proveedorPanel);
        proveedorPanel.setLayout(new BoxLayout(proveedorPanel, BoxLayout.Y_AXIS));

        lblNewLabel63 = new JLabel("URL: ");
        proveedorPanel.add(lblNewLabel63);

        txtURL = new JTextArea();
        txtURL.setText("...");
        txtURL.setEditable(false);
        proveedorPanel.add(txtURL);

        lblNewLabel62 = new JLabel("Descripcion general:");
        proveedorPanel.add(lblNewLabel62);

        txtDescripcion = new JTextArea();
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setText("...");
        txtDescripcion.setEditable(false);
        proveedorPanel.add(txtDescripcion);

        lblActividadesProv = new JLabel("Actividades y salidas: ");
        proveedorPanel.add(lblActividadesProv);

        panelActividadesYSalidasProveedor = new JPanel();
        proveedorPanel.add(panelActividadesYSalidasProveedor);
        panelActividadesYSalidasProveedor.setLayout(new BoxLayout(panelActividadesYSalidasProveedor, BoxLayout.Y_AXIS));

        turistaPanel = new JPanel();
        casos.add(turistaPanel);
        turistaPanel.setLayout(new BoxLayout(turistaPanel, BoxLayout.Y_AXIS));

        JLabel lblNewLabel_6_1 = new JLabel("Nacionalidad: ");
        turistaPanel.add(lblNewLabel_6_1);

        txtNacionalidad = new JTextArea();
        txtNacionalidad.setEditable(false);
        txtNacionalidad.setText("...");
        turistaPanel.add(txtNacionalidad);

        lblSalidasTurista = new JLabel("Salidas a las que se inscribió: ");
        turistaPanel.add(lblSalidasTurista);

        panelSalidasTurista = new JPanel();
        turistaPanel.add(panelSalidasTurista);
        panelSalidasTurista.setLayout(new BoxLayout(panelSalidasTurista, BoxLayout.Y_AXIS));

        comboBoxSeleccionUsr.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent event) {
                // Esto es lo que actualiza la lista cada vez que se abre.
                actualizarComboBox();
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent event) {

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent event) {

            }

        });

        comboBoxSeleccionUsr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                seleccionNickname = (String) comboBoxSeleccionUsr.getSelectedItem();
                seSeleccionoUnUsuario();
            }
        });

        casos.removeAll();
    }

    private void actualizarComboBox() {
        List<String> usuarios = contrUsuario.obtenerIdUsuarios();
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
            DTUsuario usr;
            try {
                usr = contrUsuario.obtenerDTUsuarioDetalle(seleccion);

                txtNickname.setText(usr.getNickname());
                txtNombre.setText(usr.getNombre());
                txtApellido.setText(usr.getApellido());
                txtCorreo.setText(usr.getCorreo());
                txtFechaDeNacimiento.setText(usr.getFechaNac().toString());

                if (usr instanceof DTTuristaDetalle) {
                    DTTuristaDetalle tur = (DTTuristaDetalle) usr;

                    casos.removeAll();
                    casos.add(turistaPanel);

                    txtTipo.setText("Turista");
                    txtNacionalidad.setText(tur.getNacionalidad());

                    panelSalidasTurista.removeAll();
                    var inscripciones = tur.getInscripciones();

                    if (inscripciones.isEmpty()) {
                        lblSalidasTurista.setVisible(false);
                        panelSalidasTurista.setVisible(false);
                    } else {
                        lblSalidasTurista.setVisible(true);
                        panelSalidasTurista.setVisible(true);
                    }

                    for (var inscDT : inscripciones) {
                        var insc = inscDT.getNombre();
                        JTextArea txt = new JTextArea();
                        txt.setText(insc);
                        txt.setEditable(false);
                        txt.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent event) {
                                ejecutarCasoConsultaSalidaTuristca(insc);
                            }
                        });

                        panelSalidasTurista.add(txt);
                    }

                    panelSalidasTurista.add(panelSalidasTurista);
                } else if (usr instanceof DTProveedorDetalle) {
                    DTProveedorDetalle prov = (DTProveedorDetalle) usr;

                    casos.removeAll();
                    casos.add(proveedorPanel);

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
                            public void mouseClicked(MouseEvent event) {
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
                                public void mouseClicked(MouseEvent event) {
                                    ejecutarCasoConsultaSalidaTuristca(salida.getNombre());
                                }
                            });
                            panel.add(txt_sal);
                        }

                        panelActividadesYSalidasProveedor.add(panel);
                    }
                } else {
                    throw new IllegalArgumentException("Se devolvió un tipo de DTUsuario no registado");
                }

            } catch (ObjetoNoExisteEnTurismoUy e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (IllegalArgumentException ex) {
            // Esta excepcion no debería ocurrir pero por las dudas la pongo

        }
    }
}
