package utils;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import publicar.actividadesturisticasservice.WebServiceActividadesService;

public class Utiles {

    public static final DateTimeFormatter formatterLocalDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static final DateTimeFormatter formatterLocalDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
    public Utiles() {
    }

    public static HttpServletRequest insertarLoDeSiempre(HttpServletRequest req) {
        List<String> departamentos = (new WebServiceActividadesService().getWebServiceActividadesPort()).obtenerIdDepartamentos().getItem();
        List<String> categorias = (new WebServiceActividadesService().getWebServiceActividadesPort()).obtenerIdCategorias().getItem();

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
    
    public static LocalDate stringToLocalDate(String fecha) {
    	return LocalDate.parse(fecha, Utiles.formatterLocalDate);
    }
    
    public static String localDateToString(LocalDate fecha) {
    	return fecha.format(Utiles.formatterLocalDate);
    }
    
    public static LocalDateTime stringToLocalDateTime(String fechaYHora) {
    	return LocalDateTime.parse(fechaYHora, Utiles.formatterLocalDateTime);
    }
    
    public static String localDateTimeToString(LocalDateTime fechaYHora) {
    	return fechaYHora.format(Utiles.formatterLocalDateTime);
    }
    
    public static String obtenerUrlParaImagen(publicar.actividadesturisticasservice.Imagen img) {
    	if(img == null) {
    		return "img/noFoto.png";
    	} else {
    		return "Imagen?href=" + img.getPath();
    	}
    }
    public static String obtenerUrlParaImagen(publicar.usuarioturisticasservice.Imagen img) {
    	if(img == null) {
    		return "img/noFoto.png";
    	} else {
    		return "Imagen?href=" + img.getPath();
    	}
    }
    public static String obtenerUrlParaImagen(publicar.paqueteturisticasservice.Imagen img) {
    	if(img == null) {
    		return "img/noFoto.png";
    	} else {
    		return "Imagen?href=" + img.getPath();
    	}
    }

}
