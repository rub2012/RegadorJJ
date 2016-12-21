package plugin;

import java.io.File;

public class LoaderArchivoTxt implements Loader
{
   private String ruta;
   private String extension = ".txt"; 
   
   @Override
   public File cargar(String path)
   {
      ruta = path + extension;
      return new File(ruta);
      
   }

   @Override
   public void guardar(String path)
   {
      // TODO Auto-generated method stub
      
   }

}
