package domain;

import jj.ParseException;

public interface Parser
{
   public void validarPrograma() throws ParseException;
   
   public String generarINO() throws ParseException, NumberFormatException;

}
