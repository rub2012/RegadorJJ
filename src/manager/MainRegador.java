package manager;

public class MainRegador
{

   public static void main(String[] args)
   {
      FrontController frontController = new FrontController();
      frontController.dispatchRequest("CARGA",null);

   }

}
