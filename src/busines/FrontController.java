package busines;

import java.io.InputStream;

import domain.Loader;
import domain.Parser;
import jj.ParseException;
import jj.Regador;

public class FrontController
{
   private Dispatcher dispatcher;

   public FrontController()
   {
      dispatcher = new Dispatcher(this);
   }
   
   private boolean isAuthenticUser()
   {
      return true;
   }
   
   public void dispatchRequest(String request)
   {
      if(isAuthenticUser())
      {
         dispatcher.dispatch(request);
      }  
   }
   
   public String dispatchRequestResponse(String request)
   {
      return dispatcher.dispatchResponse(request);
   }
   
   public void validarPrograma(InputStream input) throws ParseException
   {	   
	   dispatcher.validarPrograma(input);
   }
   
   public String generarINO(InputStream input) throws NumberFormatException, ParseException
   {
	   return dispatcher.generarINO(input);
   }
   
   public Loader loaderINO()
   {	   
	   return dispatcher.loaderINO();
   }

}
