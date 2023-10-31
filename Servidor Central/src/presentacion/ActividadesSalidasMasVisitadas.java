package presentacion;

import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import logica.controladores.IControladorActividadTuristica;

public class ActividadesSalidasMasVisitadas extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private IControladorActividadTuristica controladorAct;
	private JTable topAS;
	private JScrollPane scrollPane;
	private List<String[]> listaTop;

	@Override
	public void setVisible(boolean visi) {
		if (visi) {
			String[] columnNames = { "Nombre", "Proveedor", "Tipo", "Cant. de Visitas" };

			listaTop = controladorAct.obtenerDatosVisitas();
			String[][] data = listaTop.toArray(new String[][] {});
			topAS = new JTable(data, columnNames);

			scrollPane.setViewportView(topAS);
			topAS.setRowSelectionAllowed(false);
		}
		super.setVisible(visi);
	}

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
		setResizable(true);
		setClosable(true);
		setTitle("Actividades/Salidas más Visitadas");
		setBounds(100, 100, 600, 300);
		getContentPane().setLayout(null);

		String[] columnNames = { "Nombre", "Proveedor", "Tipo", "Cant. de Visitas" };

		listaTop = controladorAct.obtenerDatosVisitas();
		String[][] data = listaTop.toArray(new String[][] {});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 566, 220);
		getContentPane().add(scrollPane);

		topAS = new JTable(data, columnNames);
		scrollPane.setViewportView(topAS);
		topAS.setRowSelectionAllowed(false);

	}
}