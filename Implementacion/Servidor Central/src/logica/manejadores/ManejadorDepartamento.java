package logica.manejadores;

import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Departamento> getDepartamentos() {
        return departamentos;
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
