package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.controladores.IControladorActividadTuristica;
import logica.datatypes.DTActividadTuristica;
import logica.datatypes.DTSalidaTuristica;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ActividadesSalidasMasVisitadas extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private IControladorActividadTuristica controladorAct;
	private JTable topAS;

	/**
	 * Create the frame.
	 */
	public ActividadesSalidasMasVisitadas(IControladorActividadTuristica controladorAct) {
		this.controladorAct = controladorAct;
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent event) {
//				limpiarForm();
			}
		});
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Actividades/Salidas más Visitadas");
		setBounds(100, 100, 461, 459);
		getContentPane().setLayout(null);
		
		String[] columnNames = {
                "Nombre",
                "Proveedor",
                "Tipo",
                "Cant. de Visitas"};
		
		List<String[]> listaTop = controladorAct.obtenerDatosVisitas();
		String[][] data = listaTop.toArray(new String[][] {});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 427, 403);
		getContentPane().add(scrollPane);
		
		topAS = new JTable(data, columnNames);
		scrollPane.setViewportView(topAS);
		topAS.setRowSelectionAllowed(false);

	}
}