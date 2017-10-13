package busines;

import view.CargaView;
import view.GeneraView;

public class Dispatcher
{
   private CargaView cargaArchivo;
   private GeneraView generaArchivo;
   
   public Dispatcher(FrontController frontController)
   {
      cargaArchivo = new CargaView(frontController);
      generaArchivo = new GeneraView(frontController);
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

