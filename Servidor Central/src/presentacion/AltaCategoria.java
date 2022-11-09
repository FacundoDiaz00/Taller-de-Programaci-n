package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import excepciones.CategoriaYaRegistradaException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;

public class AltaCategoria extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private IControladorActividadTuristica contrActividad;
	private JTextField nombreCategoria;

	public AltaCategoria(IControladorActividadTuristica contrActividad) {

		Fabrica fabrica = Fabrica.getInstancia();
		this.contrActividad = fabrica.getIControladorActividadTuristica();
		setTitle("Registrar Categoría");
		setBounds(100, 100, 409, 123);
		getContentPane().setLayout(null);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);

		JLabel lblNewLabel2 = new JLabel("Nombre de Categoría:");
		lblNewLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel2.setBounds(10, 22, 130, 14);
		getContentPane().add(lblNewLabel2);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				agregarCategoria(event);
			}
		});
		btnAceptar.setBounds(270, 49, 117, 25);
		getContentPane().add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		btnCancelar.setBounds(10, 49, 117, 25);
		getContentPane().add(btnCancelar);

		JTextField nombreCategoria = new JTextField();
		nombreCategoria.setBounds(152, 16, 235, 23);
		getContentPane().add(nombreCategoria);
		this.nombreCategoria = nombreCategoria;

	}

	private void agregarCategoria(ActionEvent action) {
		try {
			if (nombreCategoria.getText().toString().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El campo Nombre de Categoría es obligatorio", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				this.contrActividad.altaCategoria(nombreCategoria.getText().toString());
				this.limpiarFormulario();
				setVisible(false);
				JOptionPane.showMessageDialog(null, "La categoría se ha creado con éxito", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (CategoriaYaRegistradaException exception) {
			JOptionPane.showMessageDialog(null, "Ya existe una categoría con este nombre", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarFormulario() {
		nombreCategoria.setText("");
	}
}
