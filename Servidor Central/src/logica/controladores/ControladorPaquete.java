package logica.controladores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.CompraYaRegistradaException;
import excepciones.ObjetoNoExisteEnTurismoUy;
import excepciones.PaqueteYaRegistradoException;
import excepciones.PaquetesSinActividadesExcepcion;
import logica.datatypes.DTActividadTuristica;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTPaqueteDetalles;
import logica.datatypes.Imagen;
import logica.entidades.ActividadTuristica;
import logica.entidades.Compra;
import logica.entidades.Paquete;
import logica.entidades.Turista;
import logica.manejadores.ManejadorActividadTuristica;
import logica.manejadores.ManejadorPaquete;
import logica.manejadores.ManejadorUsuario;

/**
 * @author Equipo taller prog 16
 */

public class ControladorPaquete implements IControladorPaquete {

    public ControladorPaquete() {

    }

    public void altaPaquete(String nombre, String descripcion, int periodovalidez, float descuento, LocalDate fechaR,
            Imagen img) throws PaqueteYaRegistradoException {

        ManejadorPaquete manejadosPaq = ManejadorPaquete.getInstancia();

        if (manejadosPaq.existePaquete(nombre)) {
            throw new PaqueteYaRegistradoException("Ya existe en el sistema un paquete con el nombre: " + nombre);
        }
        Paquete paq = new Paquete(nombre, descripcion, periodovalidez, descuento, fechaR, img);
        manejadosPaq.addPaquete(paq);
    }

    public void comprarPaquete(String nickTurista, String nombrePaquete, int cantTuristas, LocalDate fechaCompra)
            throws ObjetoNoExisteEnTurismoUy, CompraYaRegistradaException, PaquetesSinActividadesExcepcion {
        Turista turista = (Turista) ManejadorUsuario.getInstancia().getUsuarioPorNick(nickTurista);
        Paquete paquete = ManejadorPaquete.getInstancia().getPaquete(nombrePaquete);
        if (!paquete.hayActividades()) {
            throw new PaquetesSinActividadesExcepcion("Este paquete no tiene ninguna actividad vinculada");
        }
        if (turista.existeCompra(nombrePaquete)) {
            throw new CompraYaRegistradaException("Se intentó comprar dos veces el mismo paquete");
        }

        if (fechaCompra == null) {
            fechaCompra = LocalDate.now();
        }
        Compra compra = new Compra(paquete, cantTuristas, fechaCompra);
        turista.asociarCompra(compra);
        paquete.asociarCompra(compra);
    }

    @Override
    public List<DTPaqueteDetalles> obtenerDTPaquetesDetalles() {
        ArrayList<DTPaqueteDetalles> dtsPacks = new ArrayList<>();
        ManejadorPaquete manejadorPaq = ManejadorPaquete.getInstancia();
        for (Paquete pack : manejadorPaq.getPaquetes()) {
            dtsPacks.add(pack.obtenerDTPaqueteDetalle());
        }
        return dtsPacks;
    }

    @Override
    public DTPaqueteDetalles obtenerDTPaqueteDetalle(String nombrePaquete) throws ObjetoNoExisteEnTurismoUy {
        return ManejadorPaquete.getInstancia().getPaquete(nombrePaquete).obtenerDTPaqueteDetalle();
    }

    @Override
    public List<DTPaquete> obtenerDTPaquetes() {
        List<DTPaquete> ret = new ArrayList<DTPaquete>();

        ManejadorPaquete.getInstancia().getPaquetes().forEach((Paquete p) -> ret.add(p.obtenerDTPaquete()));

        return ret;
    }

    @Override
    public List<String> obtenerIdPaquetes() {
        ManejadorPaquete manejadorPaq = ManejadorPaquete.getInstancia();
        return new ArrayList<String>(manejadorPaq.obtenerIdPaquetes());
    }

    @Override
    public List<String> obtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete(String nombreDep, String nombrePaq)
            throws ObjetoNoExisteEnTurismoUy {
        var cat = new ControladorActividadTuristica();

        var dtActividadesDep = cat.obtenerDTActividadesTuristicasConfirmadasPorDepartamento(nombreDep);
        var actividadesDep = new ArrayList<String>();
        dtActividadesDep.forEach((DTActividadTuristica act) -> actividadesDep.add(act.getNombre()));

        ManejadorPaquete manejadorPaq = ManejadorPaquete.getInstancia();
        Paquete paq = manejadorPaq.getPaquete(nombrePaq);

        List<String> actividadesPaq = new ArrayList<String>(paq.obtenerIdActividadesIncluidas());

        ArrayList<String> actividadesNoPaq = new ArrayList<String>();

        for (String actividadDep : actividadesDep) {
            if (!actividadesPaq.contains(actividadDep)) {
                actividadesNoPaq.add(actividadDep);
            }
        }

        return actividadesNoPaq;
    }

    @Override
    public void agregarActividadAPaquete(String nombreAct, String nombrePaq)
            throws ActividadTuristicaYaRegistradaException, ObjetoNoExisteEnTurismoUy {
        ManejadorPaquete manejadorPaq = ManejadorPaquete.getInstancia();
        Paquete paq = manejadorPaq.getPaquete(nombrePaq);

        if (paq.obtenerIdActividadesIncluidas().contains(nombreAct))
            throw new ActividadTuristicaYaRegistradaException("El paquete ya incluye esta actividad");

        ManejadorActividadTuristica mat = ManejadorActividadTuristica.getInstancia();
        ActividadTuristica act = mat.getActividad(nombreAct);

        paq.agregarActividad(act);
        act.agregarPaquete(paq);
    }

    @Override
    public List<String> obtenerIdPaquetesSinComprar() {
        List<String> ret = new ArrayList<String>();

        ManejadorPaquete.getInstancia().getPaquetes().forEach((Paquete p) -> {
            if (!p.estaComprado())
                ret.add(p.getNombre());
        });

        return ret;
    }

}
