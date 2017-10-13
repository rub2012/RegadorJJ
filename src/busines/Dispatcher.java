package busines;

import view.CargaArchivoView;
import view.GeneraArchivoView;

public class Dispatcher
{
   private CargaArchivoView cargaArchivo;
   private GeneraArchivoView generaArchivo;
   
   public Dispatcher(FrontController frontController)
   {
      cargaArchivo = new CargaArchivoView(frontController);
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
      generaArchivo.setVisible(false);
   }
   
   private String siguiente()
   {
      String siguiente ="";
      if (cargaArchivo.isVisible()){
         siguiente = "GENERA";
      }
      return siguiente;
   }
   
   private String anterior()
   {
      String anterior ="";
      if(generaArchivo.isVisible()){
         anterior = "CARGA";
      }
      return anterior;
   }

   public String dispatchResponse(String request)
   {
      String response="";
      switch (request)
      {
         case "LINK":
         {
            response = cargaArchivo.getLink();
            break;
         }
         default: 
         {
            break;
         }
      }
      return response;
   }

}

