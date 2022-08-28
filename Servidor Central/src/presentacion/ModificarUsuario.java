package presentacion;

import logica.controladores.IControladorUsuario;
import logica.datatypes.DTSalidaTuristica;

import javax.swing.*;
import java.awt.Color;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ModificarUsuario extends JInternalFrame {
	private JTextField nicknameTextField;
	private JTextField nombreTextFIeld;
	private JTextField apellidoTextFIeld;
	private JTextField correoTextFIeld;
	private JTextField nacionalidadTextFIeld;
	private JTextField urlTextField;
	private JList usuariosList;
	private JSpinner diaSpinner;
	private JSpinner mesSpinner;
	private JSpinner anioSprinner;
	IControladorUsuario icu;

	/**
	 * Create the frame.
	 */
	public ModificarUsuario(IControladorUsuario icu) {
		this.icu = icu;
		setTitle("Modificar usuario");
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 605, 370);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setBounds(10, 38, 166, 253);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		usuariosList = new JList();
		usuariosList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			}
		});
		scrollPane.setViewportView(usuariosList);
		
		JLabel lblNewLabel = new JLabel("Seleccionar el usuario a modificar");
		lblNewLabel.setBounds(10, 11, 206, 31);
		getContentPane().add(lblNewLabel);
		
		nicknameTextField = new JTextField();
		nicknameTextField.setBounds(293, 32, 286, 20);
		getContentPane().add(nicknameTextField);
		nicknameTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nickname");
		lblNewLabel_1.setBounds(199, 35, 84, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre");
		lblNewLabel_1_1.setBounds(199, 66, 84, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		nombreTextFIeld = new JTextField();
		nombreTextFIeld.setColumns(10);
		nombreTextFIeld.setBounds(293, 63, 286, 20);
		getContentPane().add(nombreTextFIeld);
		
		apellidoTextFIeld = new JTextField();
		apellidoTextFIeld.setColumns(10);
		apellidoTextFIeld.setBounds(293, 94, 286, 20);
		getContentPane().add(apellidoTextFIeld);
		
		correoTextFIeld = new JTextField();
		correoTextFIeld.setColumns(10);
		correoTextFIeld.setBounds(293, 125, 286, 20);
		getContentPane().add(correoTextFIeld);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Apellido");
		lblNewLabel_1_1_1.setBounds(199, 97, 84, 14);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Correo");
		lblNewLabel_1_1_1_1.setBounds(199, 128, 84, 14);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Fecha Nacimiento");
		lblNewLabel_1_1_1_1_1.setBounds(199, 159, 84, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		diaSpinner = new JSpinner();
		diaSpinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
		diaSpinner.setBounds(293, 156, 44, 20);
		getContentPane().add(diaSpinner);
		
		JLabel lblNewLabel_2 = new JLabel("/");
		lblNewLabel_2.setBounds(343, 159, 14, 14);
		getContentPane().add(lblNewLabel_2);
		
		mesSpinner = new JSpinner();
		mesSpinner.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		mesSpinner.setBounds(353, 156, 44, 20);
		getContentPane().add(mesSpinner);
		
		JLabel lblNewLabel_2_1 = new JLabel("/");
		lblNewLabel_2_1.setBounds(405, 159, 14, 14);
		getContentPane().add(lblNewLabel_2_1);
		
		anioSprinner = new JSpinner();
		anioSprinner.setModel(new SpinnerNumberModel(2022, 1900, 2100, 1));
		anioSprinner.setBounds(417, 156, 66, 20);
		getContentPane().add(anioSprinner);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Nacionalidad");
		lblNewLabel_1_1_1_1_2.setBounds(199, 187, 84, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_2);
		
		nacionalidadTextFIeld = new JTextField();
		nacionalidadTextFIeld.setColumns(10);
		nacionalidadTextFIeld.setBounds(293, 184, 286, 20);
		getContentPane().add(nacionalidadTextFIeld);
		
		JLabel lblNewLabel_1_1_1_1_2_1 = new JLabel("URL");
		lblNewLabel_1_1_1_1_2_1.setBounds(199, 218, 84, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_2_1);
		
		JLabel lblNewLabel_1_1_1_1_2_1_1 = new JLabel("Descripcion");
		lblNewLabel_1_1_1_1_2_1_1.setBounds(199, 249, 84, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_2_1_1);
		
		JTextArea descripcionTextArea = new JTextArea();
		descripcionTextArea.setBounds(293, 244, 286, 47);
		getContentPane().add(descripcionTextArea);
		
		urlTextField = new JTextField();
		urlTextField.setColumns(10);
		urlTextField.setBounds(293, 215, 286, 20);
		getContentPane().add(urlTextField);
		
		JButton aceptarButton = new JButton("Aceptar");
		aceptarButton.setBounds(490, 302, 89, 23);
		getContentPane().add(aceptarButton);
		
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(394, 302, 89, 23);
		getContentPane().add(cancelarButton);
		
		JButton recargarButton = new JButton("Recargar");
		recargarButton.setBounds(10, 302, 89, 23);
		getContentPane().add(recargarButton);

	}

	private void actualizarUsuarios(){
		List<String> idUsers = icu.obtenerIdUsuarios();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (String id : idUsers){
			listModel.addElement(id);
		}
		usuariosList.setModel(listModel);
		if(idUsers.size() > 0){
			seleccionarUsuario();
		} else {
			enableCamposInfoUsuario(false);
		}
	}

	private void seleccionarUsuario(){
		enableCamposInfoUsuario(true);
	}

	private void enableCamposInfoUsuario(boolean estado/*, boolean soyProveedor*/){
		nicknameTextField.setEnabled(estado);
		nombreTextFIeld.setEnabled(estado);
		apellidoTextFIeld.setEnabled(estado);
		correoTextFIeld.setEnabled(estado);
		nicknameTextField.setEnabled(estado);
		nicknameTextField.setEnabled(estado);
		nicknameTextField.setEnabled(estado);
		nicknameTextField.setEnabled(estado);
		nicknameTextField.setEnabled(estado);
	}



}
