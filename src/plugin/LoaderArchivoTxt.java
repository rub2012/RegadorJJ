package plugin;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class LoaderArchivoTxt implements Loader
{

   @Override
   public FileInputStream cargar(String path) throws FileNotFoundException
   {
      return new FileInputStream(path);      
   }

   @Override
   public void guardar(String contenido,String ruta) throws IOException
   {
      BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));
      bw.write(contenido);
      bw.close();
      
   }

}
