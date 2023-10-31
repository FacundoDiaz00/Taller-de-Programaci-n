package logica.datatypes.colleciones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTUsuarioSeparadosPorTipoCollection {
	private List<DTProveedor> proveedores;
	private List<DTTurista> turistas;

	public DTUsuarioSeparadosPorTipoCollection() {
	}

	public DTUsuarioSeparadosPorTipoCollection(Collection<DTUsuario> usuarios) {
		this.proveedores = new ArrayList<>();
		this.turistas = new ArrayList<>();

		for (DTUsuario dtu : usuarios) {
			if (dtu instanceof DTProveedor) {
				proveedores.add((DTProveedor) dtu);
			} else {
				turistas.add((DTTurista) dtu);
			}
		}
	}

	public DTUsuarioSeparadosPorTipoCollection(List<DTProveedor> proveedores, List<DTTurista> turistas) {
		this.turistas = turistas;
		this.proveedores = proveedores;
	}

	public List<DTTurista> getTuristas() {
		return turistas;
	}

	public List<DTProveedor> getProveedores() {
		return proveedores;
	}
}
