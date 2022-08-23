package logica.entidades;

import java.time.LocalDate;

import logica.datatypes.DTUsuario;

/**
 * @author Equipo taller prog 16
 */

public class Usuario {

    private String nickname;
    private String nombre;
    private String apellido;
    private String correo;
    private LocalDate fechaNac;

    public Usuario(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaNac = fechaNac;
    }

    @Override
    public boolean equals(Object obj) {
        return this.nickname.equals(((Usuario) obj).nickname)
                || this.correo.equals(((Usuario) obj).correo);
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
    
    public DTUsuario getDTUsuario() {
        return new DTUsuario(nickname, nombre, apellido, correo, fechaNac);
    }

    public DTUsuario getDTUsuarioDetalle() {
        return new DTUsuario(nickname, nombre, apellido, correo, fechaNac);
    }

}
