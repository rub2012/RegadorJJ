package main;

import busines.FrontController;

public class Main
{

   public static void main(String[] args)
   {
      FrontController frontController = new FrontController();
      frontController.dispatchRequest("CARGA");
   }

}