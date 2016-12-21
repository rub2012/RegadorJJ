package plugin;

import java.io.File;

public interface Loader
{
   public File cargar(String path);
   
   public void guardar(String path);

}
