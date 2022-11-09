package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import excepciones.ActividadTuristicaNoAceptada;
import excepciones.FechaAltaActividadPosteriorAFechaAltaSalidaException;
import excepciones.FechaAltaSalidaPosteriorAFechaSalidaException;
import excepciones.ObjetoNoExisteEnTurismoUy;
import excepciones.SalidaYaRegistradaException;
import logica.controladores.IControladorActividadTuristica;

public class AltaDeSalidaTuristica extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private IControladorActividadTuristica controladorAct;

    private JSpinner dia;
    private JSpinner mes;
    private JSpinner anio;
    private JSpinner anior;
    private JSpinner mesr;
    private JSpinner diar;
    private JTextField nombre;
    private JTextField lugar;
    private JComboBox actividadTuristica;
    private JComboBox<Integer> hora;
    private JSpinner maxTuristas;
    private JButton btnAceptar;
    private JComboBox departamento;

    /**
     * Create the frame.
     */
    public AltaDeSalidaTuristica(IControladorActividadTuristica controladorAct) {
        this.controladorAct = controladorAct;
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent event) {
                limpiarForm();
            }
        });
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setTitle("Registrar Salida Turistica.");
        setBounds(100, 100, 461, 415);
        getContentPane().setLayout(null);

        JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos de la Salida Turística a Registrar.");
        lblIngreseLosDatos.setBounds(12, 12, 385, 15);
        getContentPane().add(lblIngreseLosDatos);

        JLabel lblDepart = new JLabel("Departamento: ");
        lblDepart.setBounds(12, 50, 112, 15);
        getContentPane().add(lblDepart);

        // Cuando se setea un departamento, se habilita la seleccion de una
        // actividad.

        departamento = new JComboBox();
        departamento.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuCanceled(PopupMenuEvent event) {
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent event) {
            }

            public void popupMenuWillBecomeVisible(PopupMenuEvent event) {
                actualizarDepartamentos();

            }
        });
        departamento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (departamento.getSelectedItem() != null) {
                    actividadTuristica.setEnabled(true);

                }
            }
        });
        getContentPane().add(departamento);

        departamento.setBounds(150, 45, 175, 24);

        JLabel lblActividadTurisica = new JLabel("Actividad Turística:");
        lblActividadTurisica.setBounds(12, 85, 128, 15);
        getContentPane().add(lblActividadTurisica);

        actividadTuristica = new JComboBox();
        actividadTuristica.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuCanceled(PopupMenuEvent event) {
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent event) {
            }

            public void popupMenuWillBecomeVisible(PopupMenuEvent event) {
                actualizarActTur();
            }
        });
        actividadTuristica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (actividadTuristica.getSelectedItem() != null) {
                    nombre.setEnabled(true);
                    dia.setEnabled(true);
                    mes.setEnabled(true);
                    anio.setEnabled(true);
                    hora.setEnabled(true);
                    lugar.setEnabled(true);
                    maxTuristas.setEnabled(true);
                    btnAceptar.setEnabled(true);
                    diar.setEnabled(true);
                    mesr.setEnabled(true);
                    anior.setEnabled(true);

                }
            }
        });
        actividadTuristica.setEnabled(false);
        actividadTuristica.setBounds(150, 80, 175, 24);

        getContentPane().add(actividadTuristica);

        JLabel lblNombre = new JLabel("Nombre: ");
        lblNombre.setBounds(12, 120, 70, 15);
        getContentPane().add(lblNombre);

        JLabel lblHora = new JLabel("Hora:");
        lblHora.setBounds(12, 190, 47, 15);
        getContentPane().add(lblHora);

        JLabel lblFecha = new JLabel("Fecha de la salida: ");
        lblFecha.setBounds(12, 155, 138, 15);
        getContentPane().add(lblFecha);

        JLabel lblLugar = new JLabel("Lugar: ");
        lblLugar.setBounds(12, 225, 70, 15);
        getContentPane().add(lblLugar);

        JLabel lblCantidadMaximaDe = new JLabel("Cantidad Máxima de turistas: ");
        lblCantidadMaximaDe.setBounds(12, 259, 208, 15);
        getContentPane().add(lblCantidadMaximaDe);

        dia = new JSpinner();
        dia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
        dia.setEnabled(false);
        dia.setBounds(157, 153, 47, 19);
        getContentPane().add(dia);

        JLabel label = new JLabel("/");
        label.setBounds(208, 155, 13, 15);
        getContentPane().add(label);

        mes = new JSpinner();
        mes.setModel(new SpinnerNumberModel(1, 1, 12, 1));
        mes.setEnabled(false);
        mes.setBounds(215, 153, 47, 19);
        getContentPane().add(mes);

        JLabel label1 = new JLabel("/");
        label1.setBounds(265, 155, 4, 15);
        getContentPane().add(label1);

        anio = new JSpinner();
        anio.setModel(new SpinnerNumberModel(2022, 1900, 2100, 1));
        anio.setEnabled(false);
        anio.setBounds(273, 153, 60, 19);
        getContentPane().add(anio);

        JLabel lblddmmyyyy = new JLabel("(dd/mm/yyyy)");
        lblddmmyyyy.setBounds(339, 155, 92, 15);
        getContentPane().add(lblddmmyyyy);

        nombre = new JTextField();
        nombre.setEnabled(false);
        nombre.setBounds(80, 118, 245, 19);
        getContentPane().add(nombre);
        nombre.setColumns(10);

        hora = new JComboBox<Integer>();
        hora.setEnabled(false);
        hora.setBounds(66, 185, 58, 24);
        List<Integer> aux = new ArrayList<Integer>();
        for (int i = 0; i <= 23; i++) {
            aux.add(i);
        }
        for (int i = 0; i < aux.size(); i++) {
            hora.addItem(aux.get(i));
        }
        getContentPane().add(hora);

        lugar = new JTextField();
        lugar.setEnabled(false);
        lugar.setBounds(66, 223, 221, 19);
        getContentPane().add(lugar);
        lugar.setColumns(10);

        maxTuristas = new JSpinner();
        maxTuristas.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        maxTuristas.setEnabled(false);
        maxTuristas.setBounds(227, 257, 60, 20);
        getContentPane().add(maxTuristas);

        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                aceptarAltaSalidaTuristica();
            }
        });
        btnAceptar.setEnabled(false);
        btnAceptar.setBounds(322, 346, 117, 25);
        getContentPane().add(btnAceptar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                limpiarForm();
                setVisible(false);
            }
        });
        btnCancelar.setBounds(12, 346, 117, 25);
        getContentPane().add(btnCancelar);

        JLabel lblFechaDeRegistro = new JLabel("Fecha de Registro:");
        lblFechaDeRegistro.setBounds(12, 295, 138, 15);
        getContentPane().add(lblFechaDeRegistro);

        diar = new JSpinner();
        diar.setModel(new SpinnerNumberModel(1, 1, 31, 1));
        diar.setEnabled(false);
        diar.setBounds(157, 293, 47, 19);
        getContentPane().add(diar);

        JLabel label2 = new JLabel("/");
        label2.setBounds(208, 293, 13, 15);
        getContentPane().add(label2);

        mesr = new JSpinner();
        mesr.setModel(new SpinnerNumberModel(1, 1, 12, 1));
        mesr.setEnabled(false);
        mesr.setBounds(215, 293, 47, 19);
        getContentPane().add(mesr);

        JLabel label11 = new JLabel("/");
        label11.setBounds(265, 293, 4, 15);
        getContentPane().add(label11);

        anior = new JSpinner();
        anior.setModel(new SpinnerNumberModel(2022, 1900, 2100, 1));
        anior.setEnabled(false);
        anior.setBounds(273, 293, 60, 19);
        getContentPane().add(anior);

        JLabel lblddmmyyyy1 = new JLabel("(dd/mm/yyyy)");
        lblddmmyyyy1.setBounds(339, 293, 92, 15);
        getContentPane().add(lblddmmyyyy1);

    }

    private void actualizarDepartamentos() {
        List<String> deptos = controladorAct.obtenerIdDepartamentos();
        departamento.setModel(new DefaultComboBoxModel<>(deptos.toArray()));
        if (!deptos.isEmpty()) {
            departamento.setSelectedIndex(0);
        }
    }

    private void actualizarActTur() {
        List<String> acts;
        try {
            acts = controladorAct.obtenerIdActividadesTuristicas(departamento.getSelectedItem().toString());
            actividadTuristica.setModel(new DefaultComboBoxModel<>(acts.toArray()));
            if (!acts.isEmpty()) {
                actividadTuristica.setSelectedIndex(0);
            }
        } catch (ObjetoNoExisteEnTurismoUy e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void aceptarAltaSalidaTuristica() {
        try {
            LocalDate fechaR = LocalDate.of((int) anior.getValue(), (int) mesr.getValue(), (int) diar.getValue());
            LocalDateTime fecha = LocalDateTime.of((int) anio.getValue(), (int) mes.getValue(), (int) dia.getValue(),
                    (int) hora.getSelectedItem(), 0);
            controladorAct.altaSalidaTuristica(actividadTuristica.getSelectedItem().toString(), nombre.getText(), fecha,
                    fechaR, lugar.getText(), (int) maxTuristas.getValue(), null);
            JOptionPane.showMessageDialog(null, "Operacion realizada con exito.", "Registro de Salida",
                    JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            limpiarForm();
        } catch (SalidaYaRegistradaException exception) {
            JOptionPane.showMessageDialog(null, "Error. Ya existe una Salida con el nombre elegido.",
                    "Registro de Salida", JOptionPane.WARNING_MESSAGE);
        } catch (FechaAltaSalidaPosteriorAFechaSalidaException ee) {
            JOptionPane.showMessageDialog(null, "Error. La Fecha de Salida debe ser posterior a la Fecha de Registro.",
                    "Registro de Salida", JOptionPane.WARNING_MESSAGE);
        } catch (FechaAltaActividadPosteriorAFechaAltaSalidaException eee) {
            JOptionPane.showMessageDialog(null,
                    "Error. La fecha de Registro de la salida debe ser posterior a la del alta de la actividad correspondiente.",
                    "Registro de Salida", JOptionPane.WARNING_MESSAGE);
        } catch (ObjetoNoExisteEnTurismoUy e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ActividadTuristicaNoAceptada e) {
            JOptionPane.showMessageDialog(null,
                    "Error. Se intentó agregar una salida a una actividad no aceptada.",
                    "Registro de Salida", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void limpiarForm() {
        nombre.setText("");
        departamento.setModel(new DefaultComboBoxModel<>(new String[0]));
        actividadTuristica.setModel(new DefaultComboBoxModel<>(new String[0]));
        dia.setValue(1);
        mes.setValue(1);
        anio.setValue(2022);
        hora.setSelectedIndex(0);
        lugar.setText("");
        maxTuristas.setValue(1);
        diar.setValue(1);
        mesr.setValue(1);
        anior.setValue(2022);

    }
}
