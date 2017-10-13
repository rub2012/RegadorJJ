package domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Loader
{
   public FileInputStream cargar(String path) throws FileNotFoundException;
   
   public void guardar(String contenido,String ruta) throws IOException;

}
