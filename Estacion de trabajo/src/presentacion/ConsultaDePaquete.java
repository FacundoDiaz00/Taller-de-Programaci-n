package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import logica.controladores.IControladorPaquete;
import logica.datatypes.DTPaqueteDetalles;
import javax.swing.SwingConstants;
import javax.swing.JList;

public class ConsultaDePaquete extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	
	private List<DTPaqueteDetalles> paquetesDetalles;
	private String paqueteSeleccionado;
	private boolean desactivarMostrarConsultas = false;

	private Principal principal;
	private IControladorPaquete contrPaquete;

	private JComboBox paquete;
	private JTextPane perVal;
	private JTextPane descr;
	private JTextPane descuento;
	private JComboBox actividad;
	private JList listCategorias;

	/**
	 * Create the frame.
	 */
	public ConsultaDePaquete(Principal principal, IControladorPaquete contrPaquete) {
		setResizable(true);
		this.contrPaquete = contrPaquete;
		this.principal = principal;

		setTitle("Consulta de Paquete");
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(null);

		JLabel lblPaqueteAConsultar = new JLabel("Paquete:");
		lblPaqueteAConsultar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaqueteAConsultar.setBounds(64, 35, 81, 15);
		getContentPane().add(lblPaqueteAConsultar);

		paquete = new JComboBox();

		paquete.setBounds(163, 30, 239, 24);
		getContentPane().add(paquete);

		JLabel lblPeriodoDeValidez = new JLabel("Período de validez:");
		lblPeriodoDeValidez.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPeriodoDeValidez.setBounds(12, 96, 136, 15);
		getContentPane().add(lblPeriodoDeValidez);

		JLabel lblNewLabel = new JLabel("Descuento:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(67, 129, 81, 15);
		getContentPane().add(lblNewLabel);

		perVal = new JTextPane();
		perVal.setBounds(163, 96, 239, 21);
		getContentPane().add(perVal);
		perVal.setEditable(false);

		JLabel label = new JLabel("%");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(390, 129, 12, 15);
		getContentPane().add(label);

		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setBounds(26, 162, 122, 15);
		getContentPane().add(lblDescripcion);

		descr = new JTextPane();
		descr.setBounds(163, 162, 239, 89);
		getContentPane().add(descr);
		descr.setEditable(false);

		JLabel lblActividadTurisitica = new JLabel("Actividad Turística:");
		lblActividadTurisitica.setHorizontalAlignment(SwingConstants.RIGHT);
		lblActividadTurisitica.setBounds(12, 268, 136, 15);
		getContentPane().add(lblActividadTurisitica);

		actividad = new JComboBox();
		actividad.setEnabled(false);
		actividad.setBounds(163, 263, 239, 24);
		getContentPane().add(actividad);

		actividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ejecutarCasoConsultaActividadTuristca((String) actividad.getSelectedItem());
			}
		});

		JTextPane descuentoPane = new JTextPane();
		descuentoPane.setBounds(163, 129, 220, 21);
		getContentPane().add(descuentoPane);
		this.descuento = descuentoPane;
		descuentoPane.setEditable(false);

		JButton btnNewButton = new JButton("Consultar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				paqueteSeleccionado = (String) paquete.getSelectedItem();
				seSeleccionoUnPaquete();
			}
		});
		btnNewButton.setBounds(287, 63, 115, 21);
		getContentPane().add(btnNewButton);
		
		JLabel lblCategorias = new JLabel("Categorías");
		lblCategorias.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategorias.setBounds(41, 295, 107, 15);
		getContentPane().add(lblCategorias);
		
		listCategorias = new JList();
		listCategorias.setBounds(163, 295, 239, 89);
		getContentPane().add(listCategorias);
	}

	@Override
	public void setVisible(boolean flag) {
		super.setVisible(flag);

		if (flag) {
			actualizarDTPaqueteDetalles();
			actualizarCamposFormulario();
		}
	}

	private void ejecutarCasoConsultaActividadTuristca(String nombreActividad) {
		if (!desactivarMostrarConsultas && nombreActividad != null) {
			principal.mostrarConsultaDeActividadTuristica(nombreActividad);
		}
	}

	private void seSeleccionoUnPaquete() {
		String idPaquete = paqueteSeleccionado;

		desactivarMostrarConsultas = true;

		actividad.removeAllItems();

		actividad.setEnabled(true);
		DTPaqueteDetalles paqueteDetalles = null;
		for (var dtpaquete : paquetesDetalles) {
			if (dtpaquete.getNombre().equals(idPaquete)) {
				paqueteDetalles = dtpaquete;
				break;
			}
		}
		if (paqueteDetalles != null) {
			perVal.setText(String.valueOf(paqueteDetalles.getValidez()));
			descr.setText(paqueteDetalles.getDescrpicion());
			descuento.setText(String.valueOf(paqueteDetalles.getDescuento()));

			var nombresAct = paqueteDetalles.getActividades().keySet();
			actividad.setModel(new DefaultComboBoxModel(nombresAct.toArray()));
			
			//mostrar categorias del paquete:
			DefaultListModel<String> listModelCategorias = new DefaultListModel<>();
			for (String idCat : paqueteDetalles.getCategorias()) {
				listModelCategorias.addElement(idCat);
			}
			listCategorias.setModel(listModelCategorias);
		
		}
		SwingUtilities.invokeLater(() -> desactivarMostrarConsultas = false);
		
		
		
	}

	private void actualizarDTPaqueteDetalles() {
		paquetesDetalles = contrPaquete.obtenerDTPaquetesDetalles();
	}

	private void actualizarCamposFormulario() {
		paquete.removeAllItems();
		String[] identificadores;
		for (var iterador : paquetesDetalles) {
			paquete.addItem(iterador.getNombre());
		}
	}

	public void seleccionYaHecha(String nombrePaq) {
		paqueteSeleccionado = nombrePaq;
		paquete.removeAllItems();

		actualizarDTPaqueteDetalles();
		seSeleccionoUnPaquete();
	}
}