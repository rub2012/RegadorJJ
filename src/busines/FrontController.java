package busines;

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

}
