package logica.utils;

import configuraciones.Cargador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class UtilsDT {

    public static final DateTimeFormatter formatterLocalDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static final DateTimeFormatter formatterLocalDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void guardarImagen(String relativePath, byte[] content) throws IOException {
        try{
            File fileImg = new File(Cargador.getDirrectorioImagenes() + relativePath);
            fileImg.createNewFile();
            FileOutputStream imgFileStream = new FileOutputStream(fileImg);
            imgFileStream.write(content);
            imgFileStream.close();
        } catch (IOException e){
            e.printStackTrace();
            throw new IOException();
        }

    }

    public static void borrarImagen(String relativePath){
        File fileImg = new File(Cargador.getDirrectorioImagenes() + relativePath);
        fileImg.delete();
    }

}
