package logica.manejadores;

import java.util.*;

import logica.entidades.ActividadTuristica;
import logica.entidades.Departamento;

/**
 * @author Equipo taller prog 16
 */

public class ManejadorDepartamento {
    private static ManejadorDepartamento instancia;

    private Map<String, Departamento> departamentos;

    private ManejadorDepartamento(){
        departamentos = new HashMap<>();
    }

    public static ManejadorDepartamento getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorDepartamento();
        }
        return instancia;
    }

    public List<Departamento> getDepartamentos() {
        return new ArrayList<Departamento>(departamentos.values());
    }

    public Set<String> obtenerIdDepartamentos(){
        return departamentos.keySet();
    }

    public void addDepartamento(Departamento departamento) {
        departamentos.put(departamento.getNombre(), departamento);
    }

    public Departamento getDepartamento(String nombre) {
        return departamentos.get(nombre);
    }

    public boolean exists(String nomDep){
        return departamentos.containsKey(nomDep);
    }

}
