package manager;

import view.CargaArchivoView;
import view.GeneraArchivoView;
import view.ValidaArchivoView;

public class Dispatcher
{
   private CargaArchivoView cargaArchivo;
   private ValidaArchivoView validaArchivo;
   private GeneraArchivoView generaArchivo;
   
   public Dispatcher(FrontController frontController)
   {
      cargaArchivo = new CargaArchivoView(frontController);
      validaArchivo = new ValidaArchivoView(frontController);
      generaArchivo = new GeneraArchivoView(frontController);
   }
   
   public void dispatch(String request)
   {
      switch (request)
      {
         case "CARGA":
         {
            ocultarVistas();
            cargaArchivo.setVisible(true);
            break;
         }
         case "VALIDA":
         {
            ocultarVistas();
            validaArchivo.setVisible(true);
            break;
         }
         case "GENERA":
         {
            ocultarVistas();
            generaArchivo.setVisible(true);
            break;
         }
         case "SIGUIENTE":
         {
            dispatch(siguiente());
            break;
         }
         case "ANTERIOR":
         {
            dispatch(anterior());
            break;
         }
         default: 
         {
            break;
         }
      } 
   }
   
   private void ocultarVistas()
   {
      cargaArchivo.setVisible(false);
      validaArchivo.setVisible(false);
      generaArchivo.setVisible(false);
   }
   
   private String siguiente()
   {
      String siguiente ="";
      if (cargaArchivo.isVisible()){
         siguiente = "VALIDA";
      }
      else if(validaArchivo.isVisible()){
         siguiente = "GENERA";
      }
      return siguiente;
   }
   
   private String anterior()
   {
      String anterior ="";
      if (validaArchivo.isVisible()){
         anterior = "CARGA";
      }
      else if(generaArchivo.isVisible()){
         anterior = "VALIDA";
      }
      return anterior;
   }

}

