package logica.entidades;

import java.time.LocalDate;

import logica.datatypes.DTUsuario;

/**
 * @author Equipo taller prog 16
 */

public abstract class Usuario {

    private String nickname;
    private String nombre;
    private String apellido;
    private String correo;
    private LocalDate fechaNac;

    public Usuario(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac) {
        setNickname(nickname);
        setNombre(nombre);
        setApellido(apellido);
        setCorreo(correo);
        setFechaNac(fechaNac);
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
    
    public abstract DTUsuario obtenerDTUsuario();

    public abstract DTUsuario obtenerDTUsuarioDetalle();
    
	public void setearDatos(DTUsuario datosNuevos) {
		this.setNombre(datosNuevos.getNombre());
		this.setApellido(datosNuevos.getApellido());
		this.setFechaNac(datosNuevos.getFechaNac());
	}

}
