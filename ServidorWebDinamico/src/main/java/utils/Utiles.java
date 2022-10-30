package utils;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import publicar.actividadesturisticasservice.WebServiceActividadesService;

public class Utiles {

    public Utiles() {
    }

    public static HttpServletRequest insertarLoDeSiempre(HttpServletRequest req) {
        var departamentos = (new WebServiceActividadesService().getWebServiceActividadesPort()).obtenerIdDepartamentos();
        var categorias = (new WebServiceActividadesService().getWebServiceActividadesPort()).obtenerIdCategorias();

        req.setAttribute("departamentos", departamentos);
        req.setAttribute("categorias", categorias);

        if (!req.getSession().equals(null)) {
            req.setAttribute("usuarioLogeado", null);
        }

        return req;
    }

    public static String devolverExtencionDelNombreDeArchivo(String nom) {
        String ext = "";
        if (nom == null) {
            ext = "";
        } else {
            int posLastPunto = nom.lastIndexOf('.');
            if (posLastPunto != -1) {
                ext = nom.substring(posLastPunto, nom.length());
            } else {
                ext = "";
            }
        }
        return ext;

    }

    public static void crearDirectorioImagenesSiNoEstaCreado(String dirrServidor) {
        File theDir = new File(dirrServidor + File.pathSeparator + "img");
        if (!theDir.exists()) {
            theDir.mkdirs();
            File theDirUsers = new File(dirrServidor + File.pathSeparator + "img" + File.pathSeparator + "usuarios");
            File theDirActs = new File(dirrServidor + File.pathSeparator + "img" + File.pathSeparator + "actividades");
            File theDirSalis = new File(dirrServidor + File.pathSeparator + "img" + File.pathSeparator + "salidas");
            File theDirPacks = new File(dirrServidor + File.pathSeparator + "img" + File.pathSeparator + "paquetes");

            theDirUsers.mkdirs();
            theDirActs.mkdirs();
            theDirSalis.mkdirs();
            theDirPacks.mkdirs();
        }
    }

}
