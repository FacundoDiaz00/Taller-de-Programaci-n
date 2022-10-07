package logica.entidades;

import java.time.LocalDate;

import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.datatypes.DTUsuario;
import logica.datatypes.Imagen;

/**
 * @author Equipo taller prog 16
 */

public abstract class Usuario {

	private String nickname;
	private String nombre;
	private String apellido;
	private String correo;
	private String contrasenia;
	private LocalDate fechaNac;
	private Imagen img;

	public Usuario(String nickname, String nombre, String apellido, String correo, String contra, LocalDate fechaNac, Imagen img) {
		setNickname(nickname);
		setNombre(nombre);
		setApellido(apellido);
		setCorreo(correo);
		setContrasenia(contra);
		setFechaNac(fechaNac);
		setImagen(img);
	}

	public void setContrasenia(String contra) {
		contrasenia = contra;
	}

	@Override
	public boolean equals(Object obj) {
		return this.getNickname().equals(((Usuario) obj).getNickname())
				|| this.getCorreo().equals(((Usuario) obj).getCorreo());
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public void setImagen(Imagen img) {
		this.img = img;
	}

	public Imagen getImagen() {
		return img;
	}

	public abstract DTUsuario obtenerDTUsuario();

	public abstract DTUsuario obtenerDTUsuarioDetalle();

	public abstract DTUsuario obtenerDTUsuarioDetallePrivado();

	public void setearDatos(DTUsuario datosNuevos) {
		this.setNombre(datosNuevos.getNombre());
		this.setApellido(datosNuevos.getApellido());
		this.setFechaNac(datosNuevos.getFechaNac());
	}
	
	public boolean usuarioValido(String _correo, String _contrasenia){
		return _correo == this.correo && _contrasenia == this.contrasenia;
	}

}
