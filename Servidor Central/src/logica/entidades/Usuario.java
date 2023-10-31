package logica.entidades;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import logica.datatypes.DTUsuario;
import logica.datatypes.Imagen;
import logica.jpa.UsuarioJPA;

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
    private Map<String, Usuario> seguidores;
    private Map<String, Usuario> usuariosSeguidos;

    public Usuario(String nickname, String nombre, String apellido, String correo, String contra, LocalDate fechaNac,
            Imagen img) {
        setNickname(nickname);
        setNombre(nombre);
        setApellido(apellido);
        setCorreo(correo);
        setContrasenia(contra);
        setFechaNac(fechaNac);
        setImagen(img);
        setSeguidores(new HashMap<>());
        setUsuariosSeguidos(new HashMap<>());
    }

    public void setUsuariosSeguidos(Map<String, Usuario> seguidos) {
		this.usuariosSeguidos = seguidos;
	}

	public void setSeguidores(Map<String, Usuario> seguidores) {
		this.seguidores = seguidores;
	}
	
	public Map<String, Usuario> getUsuariosSeguidos() {
		return usuariosSeguidos;
	}

	public Map<String, Usuario> getSeguidores() {
		return seguidores;
	}

	public void setContrasenia(String contra) {
        contrasenia = contra;
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Usuario)) 
            return false;
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

    public boolean usuarioValido(String _contrasenia) {
        return _contrasenia.equals(this.contrasenia);
    }

	public void agregarOBorrarSeguidor(Usuario seguidorUsuario) {
		if (seguidorUsuario.sigueA(nickname)) {
			seguidores.remove(seguidorUsuario.getNickname());
		} else {
			seguidores.put(seguidorUsuario.getNickname(), seguidorUsuario);
		}
	}

	public void agregarOBorrarDeSeguidos(Usuario seguidoUsuario) {
		if (sigueA(seguidoUsuario.getNickname())) {
			usuariosSeguidos.remove(seguidoUsuario.getNickname());
		} else {
			usuariosSeguidos.put(seguidoUsuario.getNickname(), seguidoUsuario);
		}
	}
	
	public boolean sigueA(String nickSeguido) {
		return usuariosSeguidos.containsKey(nickSeguido);
	}
	
	public Collection<Usuario> obtenerSeguidores(){
		return seguidores.values();
	}
	
	public Collection<Usuario> obtenerSeguidos(){
		return usuariosSeguidos.values();
	}

    public abstract UsuarioJPA obtenerUsuarioJPA();
}
