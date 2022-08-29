package presentacion;

import java.util.List;
import java.util.Set;

import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTInscripcion;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import logica.datatypes.DTSalidaTuristica;
import logica.datatypes.DTSalidaTuristicaDetalle;
import logica.datatypes.DTTurista;
import logica.datatypes.DTTuristaDetalle;
import logica.datatypes.DTPaquete;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;

public class ConsultaDeSalidaTuristica extends JInternalFrame {
	private String seleccionSalida;

	private final JComboBox comboSalidas;
	private JComboBox comboActividades;
	private JComboBox comboDepartamentos;
	private IControladorActividadTuristica icat;
	private JTextArea nombre;
	private JTextArea fechaSalida;
	private JTextArea horaSalida;
	private JTextArea lugarSalida;
	private JTextArea maxCantTuristas;
	private JTextArea fechaAlta;
	
	private JList inscripcionList;
	private JTextArea nombreInscripto;
	private JTextArea fechaInscripto;
	private JTextArea cantTuristasInscripto;
	
	/**
	 * Create the frame.
	 */
	public ConsultaDeSalidaTuristica(IControladorActividadTuristica icat) {
		this.icat = icat;

		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				limpiarFormulario();
			}
		});
			
		setTitle("Consulta de Salida Turística");
		setBounds(100, 100, 430, 512);
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
		
		JLabel nombreLabel = new JLabel("Nombre:");
		nombreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nombreLabel.setBounds(7, 97, 121, 14);
		getContentPane().add(nombreLabel);
		
		JLabel fechaLabel = new JLabel("Fecha:");
		fechaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		fechaLabel.setBounds(7, 123, 120, 14);
		getContentPane().add(fechaLabel);
		
		JLabel hora = new JLabel("Hora:");
		hora.setHorizontalAlignment(SwingConstants.RIGHT);
		hora.setBounds(7, 149, 120, 14);
		getContentPane().add(hora);
		
		JLabel lugarLabel = new JLabel("Lugar de salida:");
		lugarLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lugarLabel.setBounds(7, 175, 121, 14);
		getContentPane().add(lugarLabel);
		
		JLabel maxTuristasLabel = new JLabel("Maxima cantidad de turistas:");
		maxTuristasLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		maxTuristasLabel.setBounds(7, 201, 212, 14);
		getContentPane().add(maxTuristasLabel);
		
		JLabel fechaAltaLabel = new JLabel("Fecha de alta:");
		fechaAltaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		fechaAltaLabel.setBounds(7, 227, 120, 14);
		getContentPane().add(fechaAltaLabel);
		
		JComboBox comboDeps = new JComboBox<String>();
		comboDeps.setBounds(145, 7, 212, 24);
		getContentPane().add(comboDeps);
		
		
        comboDeps.addPopupMenuListener( new PopupMenuListener() {

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
        
        this.comboDepartamentos = comboDeps;
        
	 
		
		JComboBox comboActividades = new JComboBox();
		comboActividades.setBounds(145, 32, 212, 24);
		getContentPane().add(comboActividades);
		this.comboActividades = comboActividades;
		
		JTextArea nombre = new JTextArea();
		nombre.setEditable(false);
		nombre.setBounds(145, 98, 212, 15);
		getContentPane().add(nombre);
		this.nombre = nombre;
		
		JTextArea horaSalida = new JTextArea();
		horaSalida.setEditable(false);
		horaSalida.setBounds(145, 150, 212, 15);
		getContentPane().add(horaSalida);
		this.horaSalida = horaSalida;
	
		
		JTextArea lugarSalida = new JTextArea();
		lugarSalida.setEditable(false);
		lugarSalida.setBounds(145, 177, 212, 15);
		getContentPane().add(lugarSalida);
		this.lugarSalida = lugarSalida;
		
		JTextArea maxCantTuristas = new JTextArea();
		maxCantTuristas.setEditable(false);
		maxCantTuristas.setBounds(227, 201, 130, 15);
		getContentPane().add(maxCantTuristas);
		this.maxCantTuristas = maxCantTuristas;
		
		JTextArea fechaAlta = new JTextArea();
		fechaAlta.setEditable(false);
		fechaAlta.setBounds(145, 227, 212, 15);
		getContentPane().add(fechaAlta);
		this.fechaAlta = fechaAlta;
		
		JComboBox comboSalidas = new JComboBox();
		comboSalidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//salida fue seleccionada:
				seleccionSalida = comboSalidas.getSelectedItem().toString();
				mostrarDatosSalida();
			}
		});
		comboSalidas.setBounds(145, 58, 212, 24);
		getContentPane().add(comboSalidas);
		this.comboSalidas = comboSalidas;

		
		JLabel salidasTuristicasLabel = new JLabel("Salidas turísticas:");
		salidasTuristicasLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		salidasTuristicasLabel.setBounds(0, 63, 139, 14);
		getContentPane().add(salidasTuristicasLabel);
		
		JLabel inscriptosLabel = new JLabel("Inscriptos:");
		inscriptosLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		inscriptosLabel.setBounds(17, 253, 81, 14);
		getContentPane().add(inscriptosLabel);
		
		fechaSalida = new JTextArea();
		fechaSalida.setLineWrap(true);
		fechaSalida.setWrapStyleWord(true);
		fechaSalida.setEditable(false);
		fechaSalida.setBounds(145, 123, 212, 15);
		getContentPane().add(fechaSalida);
		this.fechaSalida = fechaSalida;
		
		inscripcionList = new JList();
		inscripcionList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(inscripcionList.getSelectedValue() != null) {
					String tur = inscripcionList.getSelectedValue().toString();
					turistaSeleccionado(tur);
				}
			}

			private void turistaSeleccionado(String tur) {
				IControladorUsuario icu = Fabrica.getInstancia().getIControladorUsuario();
				DTTuristaDetalle datosTur = (DTTuristaDetalle) icu.obtenerDTUsuarioDetalle(tur);
				String nomSal = seleccionSalida;
				DTInscripcion datosInsc = icat.obtenerDTInscripcion(datosTur.getNickname(), nomSal);
				
				nombreInscripto.setText(datosTur.getNombre());
				fechaInscripto.setText(datosInsc.getFechaInscripcion().toString());
				cantTuristasInscripto.setText(String.valueOf(datosInsc.getCantidadTuristas()));
				
			}
		});
		inscripcionList.setBounds(17, 279, 137, 133);
		getContentPane().add(inscripcionList);
		
		JLabel nombreInscriptoLabel = new JLabel("Nombre:");
		nombreInscriptoLabel.setBounds(172, 280, 60, 15);
		getContentPane().add(nombreInscriptoLabel);
		
		JLabel fechaInscripcionLabel = new JLabel("Fecha:");
		fechaInscripcionLabel.setBounds(172, 307, 60, 15);
		getContentPane().add(fechaInscripcionLabel);
		
		JLabel cantTuristasLabel = new JLabel("Cant. Turistas:");
		cantTuristasLabel.setBounds(172, 334, 113, 15);
		getContentPane().add(cantTuristasLabel);
		
		nombreInscripto = new JTextArea();
		nombreInscripto.setBounds(250, 280, 157, 15);
		getContentPane().add(nombreInscripto);
		
		fechaInscripto = new JTextArea();
		fechaInscripto.setBounds(250, 307, 157, 15);
		getContentPane().add(fechaInscripto);
		
		cantTuristasInscripto = new JTextArea();
		cantTuristasInscripto.setBounds(287, 334, 120, 15);
		getContentPane().add(cantTuristasInscripto);
		
		//proveedor = new JTextArea();
		//proveedor.setEditable(false);
	//	proveedor.setBounds(145, 319, 212, 15);
		//getContentPane().add(proveedor);
		
	   comboDeps.addActionListener(new ActionListener() {     
		     public void actionPerformed(ActionEvent e) {
		    	 List<String> actividades = icat.obtenerIdActividadesTuristicas(comboDeps.getSelectedItem().toString());
		    	 limpiarFormularioSinDepartamento();
		    	 comboActividades.setModel(new DefaultComboBoxModel(actividades.toArray()));
		   
		     }
	   });
	   
	   comboActividades.addActionListener(new ActionListener() {     
		     public void actionPerformed(ActionEvent e) {
		    	//se cargan identificadores de salidas
		    	 String act = comboActividades.getSelectedItem().toString();
		    	List<String> salidas = icat.obtenerIdSalidasTuristicas(act);
		    	limpiarFormularioSinDepartamentoNiActividad();
		    	comboSalidas.setModel(new DefaultComboBoxModel(salidas.toArray()));
		   }
	   });

	}
	
	
	public void seleccionYaHecha(String salida) {
		seleccionSalida = salida;
		mostrarDatosSalida();
	}
	
	private void mostrarDatosSalida() {
		DTSalidaTuristicaDetalle salida = icat.obtenerDTSalidaTuristicaDetalle(seleccionSalida);
		try {
			
			nombre.setText(salida.getNombre());
			fechaSalida.setText(salida.getFechaHoraSalida().toString());
			horaSalida.setText(salida.getFechaHoraSalida().toString());
			lugarSalida.setText(salida.getLugarSalida());
			maxCantTuristas.setText(String.valueOf(salida.getCantMaxTuristas()));
			fechaAlta.setText(salida.getFechaAlta().toString());
			DefaultListModel<String> listModel = new DefaultListModel<>();
            for (DTInscripcion insc : salida.getInscriptos()){
                listModel.addElement(insc.getTurista().getNickname());
            }
            inscripcionList.setModel(listModel);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
	}
	
	public void actualizarComboDepartamentos() {
		List<String> deps = icat.obtenerIdDepartamentos();
		comboDepartamentos.setModel(new DefaultComboBoxModel(deps.toArray()));
	}
	
    private void limpiarFormularioSinDepartamento() {
        nombre.setText("");
        fechaSalida.setText("");
        horaSalida.setText("");
        lugarSalida.setText("");
		maxCantTuristas.setText("");
		fechaAlta.setText("");
		nombreInscripto.setText("");
		fechaInscripto.setText("");
		cantTuristasInscripto.setText("");
		
		inscripcionList.setModel(new DefaultListModel<>());
		
		comboActividades.setModel(new DefaultComboBoxModel<>(new String[0]));
		comboSalidas.setModel(new DefaultComboBoxModel<>(new String[0]));
    }

    private void limpiarFormularioSinDepartamentoNiActividad() {
        nombre.setText("");
        fechaSalida.setText("");
        horaSalida.setText("");
        lugarSalida.setText("");
		maxCantTuristas.setText("");
		fechaAlta.setText("");
		nombreInscripto.setText("");
		fechaInscripto.setText("");
		cantTuristasInscripto.setText("");
		
		inscripcionList.setModel(new DefaultListModel<>());
		comboSalidas.setModel(new DefaultComboBoxModel<>(new String[0]));
    }
    
    private void limpiarFormulario() {
        nombre.setText("");
        fechaSalida.setText("");
        horaSalida.setText("");
        lugarSalida.setText("");
		maxCantTuristas.setText("");
		fechaAlta.setText("");
		nombreInscripto.setText("");
		fechaInscripto.setText("");
		cantTuristasInscripto.setText("");
		
		inscripcionList.setModel(new DefaultListModel<>());
		
		comboDepartamentos.setModel(new DefaultComboBoxModel<>(new String[0]));
		comboActividades.setModel(new DefaultComboBoxModel<>(new String[0]));
		comboSalidas.setModel(new DefaultComboBoxModel<>(new String[0]));
    }
}
