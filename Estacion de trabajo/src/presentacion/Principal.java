package presentacion;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import excepciones.TurismoUyException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorPaquete;
import logica.controladores.IControladorUsuario;

public class Principal {
    private IControladorUsuario contrUsuario;
    private IControladorActividadTuristica contrActTur;
    private IControladorPaquete contrPaquete;

    private JFrame frmEstacionDeTrabajo;

    private AltaDeUsuario frmIntAltaUsuario;
    private ConsultaDeUsuario frmIntConsultaDeUsuario; // Lo dejo de esta forma
                                                       // asi queda igual al
                                                       // ejemplo y no tenemos
                                                       // que tocar el metodo
                                                       // initialize
    private AltaDeActividadTuristica frmIntAltaActividadTuristica;
    private ConsultaDeActividadTuristica frmIntConsultaDeActividadTuristica;
    private AltaDePaquete frmIntAltaPaquete;
    private AgregarActividadAPaquete frmIntAgregarActividadAPaquete;
    private AltaDeSalidaTuristica frmIntAltaSalidaTuristica;
    private ConsultaDeSalidaTuristica frmIntConsultaDeSalidaTuristica;
    private InscribirseASalidaTurística frmInscribirseASalidaTuristica;
    private AltaCategoria frmAltaCategoria;

    private ModificarUsuario frmModificarUsuario;

    private ConsultaDePaquete frmIntConsultaDePaquete;

    private aceptarRechazarActividadTuristica frmAceptarRechazarActividadTuristica;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal window = new Principal();
                    window.frmEstacionDeTrabajo.setVisible(true);
                } catch (RuntimeException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Principal() {
        initialize();
        Fabrica fabrica = Fabrica.getInstancia();
        contrUsuario = fabrica.getIControladorUsuario();
        contrActTur = fabrica.getIControladorActividadTuristica();
        contrPaquete = fabrica.getIControladorPaquete();

        frmIntConsultaDeUsuario = new ConsultaDeUsuario(this, contrUsuario);
        frmIntAltaSalidaTuristica = new AltaDeSalidaTuristica(contrActTur);
        frmIntAltaActividadTuristica = new AltaDeActividadTuristica(contrActTur);
        frmIntConsultaDeActividadTuristica = new ConsultaDeActividadTuristica(this, contrActTur);
        frmInscribirseASalidaTuristica = new InscribirseASalidaTurística(contrActTur, contrUsuario);
        frmIntConsultaDeSalidaTuristica = new ConsultaDeSalidaTuristica(contrActTur);
        frmIntAltaUsuario = new AltaDeUsuario(contrUsuario);
        frmIntAltaPaquete = new AltaDePaquete(contrPaquete);
        frmIntAgregarActividadAPaquete = new AgregarActividadAPaquete(contrPaquete, contrActTur);
        frmModificarUsuario = new ModificarUsuario(contrUsuario);
        frmIntAltaPaquete.setNormalBounds(new Rectangle(100, 100, 425, 350));
        frmIntConsultaDePaquete = new ConsultaDePaquete(this, contrPaquete);
        frmAceptarRechazarActividadTuristica = new aceptarRechazarActividadTuristica(contrActTur);
        frmAltaCategoria = new AltaCategoria(contrActTur);

        frmEstacionDeTrabajo.getContentPane().setLayout(null);
        frmIntAltaUsuario.setVisible(false);
        frmIntAltaPaquete.setVisible(false);
        frmIntConsultaDeUsuario.setVisible(false);
        frmIntAltaActividadTuristica.setVisible(false);
        frmIntConsultaDeActividadTuristica.setVisible(false);
        frmIntAgregarActividadAPaquete.setVisible(false);
        frmInscribirseASalidaTuristica.setVisible(false);
        frmIntAltaSalidaTuristica.setVisible(false);
        frmIntConsultaDePaquete.setVisible(false);
        frmIntConsultaDeSalidaTuristica.setVisible(false);
        frmModificarUsuario.setVisible(false);
        frmAltaCategoria.setVisible(false);
        frmAceptarRechazarActividadTuristica.setVisible(false);

        frmEstacionDeTrabajo.getContentPane().setLayout(null);
        frmEstacionDeTrabajo.getContentPane().add(frmIntAltaPaquete);
        frmEstacionDeTrabajo.getContentPane().add(frmIntConsultaDeUsuario);
        frmEstacionDeTrabajo.getContentPane().add(frmIntAltaUsuario);
        frmEstacionDeTrabajo.getContentPane().add(frmIntConsultaDeUsuario);
        frmEstacionDeTrabajo.getContentPane().add(frmIntAltaActividadTuristica);
        frmEstacionDeTrabajo.getContentPane().add(frmIntConsultaDeActividadTuristica);
        frmEstacionDeTrabajo.getContentPane().add(frmIntAgregarActividadAPaquete);
        frmEstacionDeTrabajo.getContentPane().add(frmInscribirseASalidaTuristica);
        frmEstacionDeTrabajo.getContentPane().add(frmIntAltaSalidaTuristica);
        frmEstacionDeTrabajo.getContentPane().add(frmIntConsultaDePaquete);
        frmEstacionDeTrabajo.getContentPane().add(frmIntConsultaDeSalidaTuristica);
        frmEstacionDeTrabajo.getContentPane().add(frmModificarUsuario);
        frmEstacionDeTrabajo.getContentPane().add(frmAltaCategoria);
        frmEstacionDeTrabajo.getContentPane().add(frmAceptarRechazarActividadTuristica);

        frmIntAltaUsuario.setVisible(false);
        frmEstacionDeTrabajo.getContentPane().add(frmIntAltaUsuario);

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmEstacionDeTrabajo = new JFrame();
        frmEstacionDeTrabajo.setTitle("Estacion de trabajo");
        frmEstacionDeTrabajo.setBounds(100, 100, 940, 600);
        frmEstacionDeTrabajo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        frmEstacionDeTrabajo.setJMenuBar(menuBar);

        JMenu sistemaMenu = new JMenu("Sistema");
        menuBar.add(sistemaMenu);
        frmEstacionDeTrabajo.getContentPane()
                .setLayout(new BoxLayout(frmEstacionDeTrabajo.getContentPane(), BoxLayout.X_AXIS));

        JDesktopPane desktopPane = new JDesktopPane();
        frmEstacionDeTrabajo.getContentPane().add(desktopPane);

        JMenuItem salirJMenuItem = new JMenuItem("Salir");
        salirJMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frmEstacionDeTrabajo.setVisible(false);
                frmEstacionDeTrabajo.dispose();
            }
        });
        salirJMenuItem.addMouseListener(new MouseAdapter() {
        });
        salirJMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
        sistemaMenu.add(salirJMenuItem);

        JMenu mnUsuario = new JMenu("Usuarios");
        menuBar.add(mnUsuario);

        JMenuItem registrarUsuarioJMenuItem = new JMenuItem("Registrar Usuario");
        registrarUsuarioJMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frmIntAltaUsuario.setVisible(true);
            }
        });
        mnUsuario.add(registrarUsuarioJMenuItem);

        JMenuItem consultarUsuarioJMenuItem = new JMenuItem("Consultar Usuario");
        consultarUsuarioJMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frmIntConsultaDeUsuario.setVisible(true);
            }

        });
        mnUsuario.add(consultarUsuarioJMenuItem);

        JMenuItem modificarUsuarioJMenuItem = new JMenuItem("Modificar Usuario");
        modificarUsuarioJMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frmModificarUsuario.setVisible(true);
            }
        });
        mnUsuario.add(modificarUsuarioJMenuItem);

        JMenu mnNewMenu3 = new JMenu("Actividades");
        menuBar.add(mnNewMenu3);

        JMenuItem registrarActividadJMenuItem = new JMenuItem("Registrar Actividad Turística");
        registrarActividadJMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frmIntAltaActividadTuristica.setVisible(true);
            }
        });

        mnNewMenu3.add(registrarActividadJMenuItem);

        JMenuItem mnNewMenuAltaCategoria = new JMenuItem("Registrar Categoría");

        mnNewMenuAltaCategoria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frmAltaCategoria.setVisible(true);
            }
        });

        mnNewMenu3.add(mnNewMenuAltaCategoria);

        JMenuItem consultarActividadTuristicaJMenuItem = new JMenuItem("Consultar Actividad Turística");
        consultarActividadTuristicaJMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frmIntConsultaDeActividadTuristica.setVisible(true);
            }
        });
        mnNewMenu3.add(consultarActividadTuristicaJMenuItem);

        JMenuItem aceptarRechazarActividadTuristicaJMenuItem = new JMenuItem("Aceptar/Rechazar Actividad Turística");
        aceptarRechazarActividadTuristicaJMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frmAceptarRechazarActividadTuristica.setVisible(true);
            }
        });
        mnNewMenu3.add(aceptarRechazarActividadTuristicaJMenuItem);

        JMenu mnNewMenu_1 = new JMenu("Salidas");
        menuBar.add(mnNewMenu_1);

        JMenuItem consultarSalidaTuristicaJMenuItem = new JMenuItem("Consultar Salida Turística");
        mnNewMenu_1.add(consultarSalidaTuristicaJMenuItem);
        consultarSalidaTuristicaJMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frmIntConsultaDeSalidaTuristica.setVisible(true);
            }
        });

        JMenuItem registrarSalidaJMenuItem = new JMenuItem("Registrar Salida Turística");
        mnNewMenu_1.add(registrarSalidaJMenuItem);

        JMenuItem inscribirseASalidaTuristicaJMenuItem = new JMenuItem("Inscribirse a Salida Turistica");
        mnNewMenu_1.add(inscribirseASalidaTuristicaJMenuItem);
        inscribirseASalidaTuristicaJMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frmInscribirseASalidaTuristica.setVisible(true);
            }
        });
        registrarSalidaJMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frmIntAltaSalidaTuristica.setVisible(true);
            }
        });

        JMenu mnNewMenu = new JMenu("Paquetes");
        menuBar.add(mnNewMenu);

        JMenuItem altaPaqueteJMenuItem = new JMenuItem("Registrar Paquete");
        altaPaqueteJMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frmIntAltaPaquete.setVisible(true);
            }
        });
        mnNewMenu.add(altaPaqueteJMenuItem);

        JMenuItem mntmNewMenuItem2 = new JMenuItem("Consulta de Paquete");
        mntmNewMenuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frmIntConsultaDePaquete.setVisible(true);
            }
        });
        mnNewMenu.add(mntmNewMenuItem2);

        JMenuItem mntmNewMenuItem1 = new JMenuItem("Agregar Actividad Turística a Paquete");
        mntmNewMenuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frmIntAgregarActividadAPaquete.setVisible(true);
            }
        });
        mnNewMenu.add(mntmNewMenuItem1);

        JMenu datosInicoMenu = new JMenu("Datos de inicio");
        menuBar.add(datosInicoMenu);

        JMenuItem mntmNewMenuItem = new JMenuItem("Cargar datos de prueba");
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cargarDatosDePrueba();
            }
        });
        datosInicoMenu.add(mntmNewMenuItem);
    }

    /*
     * Exponemos estos métodos para poder llamarlo desde otros módulos Se puede
     * pasar null como parámetro y que no se seleccione nada desde antes
     */
    public void mostrarConsultaDeUsuario(String nickname) {
        if (nickname != null) {
            frmIntConsultaDeUsuario.seleccionYaHecha(nickname);
        }
        frmIntConsultaDeUsuario.setVisible(true);
    }

    public void mostrarConsultaDeActividadTuristica(String nombreActividad) {
        if (nombreActividad != null) {
            frmIntConsultaDeActividadTuristica.seleccionYaHecha(nombreActividad);
        }
        frmIntConsultaDeActividadTuristica.setVisible(true);
    }

    public void mostrarConsultaDeSalidaTuristica(String nombreSalida) {
        if (nombreSalida != null) {
            frmIntConsultaDeSalidaTuristica.seleccionYaHecha(nombreSalida);
        }
        frmIntConsultaDeSalidaTuristica.setVisible(true);
    }

    public void mostrarConsultaDePaquete(String nombrePaquete) {
        if (nombrePaquete != null) {
            frmIntConsultaDePaquete.seleccionYaHecha(nombrePaquete);
        }
        frmIntConsultaDePaquete.setVisible(true);
    }

    private void cargarDatosDePrueba() {
        try {
            Fabrica.getInstancia().getIControladorMaestro().generarDatosDePrueba();
            JOptionPane.showMessageDialog(null, "Los datos de prueba han sido cargado con exito", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (TurismoUyException exception) {
            JOptionPane.showMessageDialog(null,
                    "Ha ocurrido un error a la hora de cargar los datos de prueba."
                            + " Sugerimos que esta operación sea la primera que se haga cuando se ejecuta el sistema.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            exception.printStackTrace();
        }

    }
}
