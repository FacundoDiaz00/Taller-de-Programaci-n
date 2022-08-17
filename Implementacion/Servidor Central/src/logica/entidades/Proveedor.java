package logica.entidades;

import java.time.LocalDate;

/**
 * @author Equipo taller prog 16
 */

public class Proveedor extends Usuario{

    private String descrpicionGeneral;
    private String link;

    public Proveedor(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac, String descrpicionGeneral, String link) {
        super(nickname, nombre, apellido, correo, fechaNac);
        this.descrpicionGeneral = descrpicionGeneral;
        this.link = link;
    }

    public String getDescrpicionGeneral() {
        return descrpicionGeneral;
    }

    public void setDescrpicionGeneral(String descrpicionGeneral) {
        this.descrpicionGeneral = descrpicionGeneral;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
