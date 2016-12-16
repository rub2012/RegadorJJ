package manager;

import java.util.Observable;
import java.util.Observer;

public class FrontController implements Observer
{
   private Dispatcher dispatcher;

   public FrontController()
   {
      dispatcher = new Dispatcher(this);
   }
   
   private boolean isAuthenticUser()
   {
      System.out.println("User is authenticated successfully.");
      return true;
   }
   
   public void dispatchRequest(String request)
   {
      if(isAuthenticUser())
      {
         dispatcher.dispatch(request);
      }  
   }

   @Override
   public void update(Observable o, Object arg)
   {
      dispatchRequest((String)arg);      
   }

}
