package presentacion;

import excepciones.ModificacionUsuarioNoPermitida;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTSalidaTuristica;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;

import javax.swing.*;
import java.awt.Color;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.util.List;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModificarUsuario extends JInternalFrame {


	DTUsuario dtUsuario;

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
	private JTextArea descripcionTextArea;
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
		setBounds(100, 100, 596, 391);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setBounds(10, 38, 166, 282);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		usuariosList = new JList();
		usuariosList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				seleccionarUsuario();
			}
		});
		scrollPane.setViewportView(usuariosList);
		
		JLabel lblNewLabel = new JLabel("Seleccionar el usuario a modificar");
		lblNewLabel.setBounds(10, 11, 206, 31);
		getContentPane().add(lblNewLabel);
		
		nicknameTextField = new JTextField();
		nicknameTextField.setEditable(false);
		nicknameTextField.setBounds(293, 32, 286, 20);
		getContentPane().add(nicknameTextField);
		nicknameTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nickname");
		lblNewLabel_1.setBounds(180, 38, 84, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre");
		lblNewLabel_1_1.setBounds(180, 66, 84, 14);
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
		correoTextFIeld.setEditable(false);
		correoTextFIeld.setColumns(10);
		correoTextFIeld.setBounds(293, 125, 286, 20);
		getContentPane().add(correoTextFIeld);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Apellido");
		lblNewLabel_1_1_1.setBounds(180, 97, 84, 14);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Correo");
		lblNewLabel_1_1_1_1.setBounds(180, 128, 84, 14);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Fecha Nacimiento");
		lblNewLabel_1_1_1_1_1.setBounds(180, 159, 103, 14);
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
		lblNewLabel_1_1_1_1_2.setBounds(180, 187, 84, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_2);
		
		nacionalidadTextFIeld = new JTextField();
		nacionalidadTextFIeld.setColumns(10);
		nacionalidadTextFIeld.setBounds(293, 184, 286, 20);
		getContentPane().add(nacionalidadTextFIeld);
		
		JLabel lblNewLabel_1_1_1_1_2_1 = new JLabel("URL");
		lblNewLabel_1_1_1_1_2_1.setBounds(180, 218, 84, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_2_1);
		
		JLabel lblNewLabel_1_1_1_1_2_1_1 = new JLabel("Descripcion");
		lblNewLabel_1_1_1_1_2_1_1.setBounds(180, 243, 103, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_2_1_1);
		
		descripcionTextArea = new JTextArea();
		descripcionTextArea.setLineWrap(true);
		descripcionTextArea.setWrapStyleWord(true);
		descripcionTextArea.setBounds(293, 244, 286, 76);
		getContentPane().add(descripcionTextArea);
		
		urlTextField = new JTextField();
		urlTextField.setColumns(10);
		urlTextField.setBounds(293, 215, 286, 20);
		getContentPane().add(urlTextField);
		
		JButton aceptarButton = new JButton("Aceptar");
		aceptarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aceptar();
			}
		});
		aceptarButton.setBounds(490, 331, 89, 23);
		getContentPane().add(aceptarButton);
		
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		cancelarButton.setBounds(394, 331, 89, 23);
		getContentPane().add(cancelarButton);
		
		JButton recargarButton = new JButton("Recargar");
		recargarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarUsuarios();
			}
		});
		recargarButton.setBounds(10, 331, 89, 23);
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
			usuariosList.setSelectedIndex(0);
			//seleccionarUsuario();
		} else {
			enableCamposInfoUsuario(false, false);//No importa el valor de soyProveedor
		}
	}

	@Override
	public void setVisible(boolean aFlag) {
		if(aFlag){
			actualizarUsuarios();
		}
		super.setVisible(aFlag);
	}

	private void seleccionarUsuario(){
		String nicknameUser = (String)usuariosList.getSelectedValue();
		if(nicknameUser == null){
			return;
		}
		DTUsuario dtsUsuarios = icu.obtenerDTUsuario(nicknameUser);
		this.dtUsuario = dtsUsuarios;
		nicknameTextField.setText(dtsUsuarios.getNickname());
		nombreTextFIeld.setText(dtsUsuarios.getNombre());
		apellidoTextFIeld.setText(dtsUsuarios.getApellido());
		correoTextFIeld.setText(dtsUsuarios.getCorreo());
		diaSpinner.setValue(dtsUsuarios.getFechaNac().getDayOfMonth());
		mesSpinner.setValue(dtsUsuarios.getFechaNac().getMonthValue());
		anioSprinner.setValue(dtsUsuarios.getFechaNac().getYear());
		if(dtsUsuarios instanceof DTProveedor){
			DTProveedor dtProveedor = (DTProveedor) dtsUsuarios;
			descripcionTextArea.setText(dtProveedor.getDescrpicionGeneral());
			urlTextField.setText(dtProveedor.getLink());
			enableCamposInfoUsuario(true, true);
		}else{
			DTTurista dtTurista = (DTTurista) dtsUsuarios;
			nacionalidadTextFIeld.setText(dtTurista.getNacionalidad());
			enableCamposInfoUsuario(true, false);
		}



	}

	private void enableCamposInfoUsuario(boolean estado, boolean soyProveedor){
		if(!estado){
			nicknameTextField.setText("");
			nombreTextFIeld.setText("");
			apellidoTextFIeld.setText("");
			correoTextFIeld.setText("");
			diaSpinner.setValue(1);
			mesSpinner.setValue(1);
			anioSprinner.setValue(2022);
		}
		nombreTextFIeld.setEditable(estado);
		apellidoTextFIeld.setEditable(estado);
		diaSpinner.setEnabled(estado);
		mesSpinner.setEnabled(estado);
		anioSprinner.setEnabled(estado);
		if(!soyProveedor && estado){
			nacionalidadTextFIeld.setEditable(true);
		} else {
			nacionalidadTextFIeld.setText("");
			nacionalidadTextFIeld.setEditable(false);
		}
		if(soyProveedor && estado){
			urlTextField.setEditable(true);
			descripcionTextArea.setEditable(true);
		} else {
			urlTextField.setEditable(false);
			urlTextField.setText("");
			descripcionTextArea.setEditable(false);
			descripcionTextArea.setText("");
		}
	}


	private void aceptar(){
		String nickname = nicknameTextField.getText();
		String nombre = nombreTextFIeld.getText();
		String apellido = apellidoTextFIeld.getText();
		String correo = correoTextFIeld.getText();
		String nacionalidad = nacionalidadTextFIeld.getText();
		String url = urlTextField.getText();
		String descripcion = descripcionTextArea.getText();

		LocalDate fechaNac;
		try{
			int dia = (int)diaSpinner.getValue();
			int mes = (int)mesSpinner.getValue();
			int anio = (int)anioSprinner.getValue();
			fechaNac = LocalDate.of(anio, mes, dia);
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "La Fecha nacimiento es invalida", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if(nombre == null || nombre.trim().equals("")){
			JOptionPane.showMessageDialog(null, "El nombre es un campo obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if(apellido == null || apellido.trim().equals("")){
			JOptionPane.showMessageDialog(null, "El apellido es un campo obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}



		DTUsuario dtu;

		if(this.dtUsuario instanceof DTProveedor){
			if(descripcion == null || descripcion.trim().equals("")){
				JOptionPane.showMessageDialog(null, "La descripcion es un campo obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			dtu = new DTProveedor(nickname, nombre, apellido, correo, fechaNac, descripcion, url );
		} else {
			if(nacionalidad== null || nacionalidad.trim().equals("")){
				JOptionPane.showMessageDialog(null, "La nacionalidad es un campo obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			dtu = new DTTurista(nickname, nombre, apellido, correo, fechaNac, nacionalidad);
		}

		try{
			icu.modificarUsuario(dtu);
			setVisible(false);
			JOptionPane.showMessageDialog(null, "Usuario modificado con éxito.", "Operación con éxito.", JOptionPane.INFORMATION_MESSAGE);
		}catch (ModificacionUsuarioNoPermitida e){
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "La Fecha nacimiento es invalida", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}



	}

	private void cancelar(){
		enableCamposInfoUsuario(false,false);
		setVisible(false);
	}


}
