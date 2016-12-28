package manager;

public class FrontController
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
   
   public String dispatchRequestResponse(String request)
   {
      return dispatcher.dispatchResponse(request);
   }

}
