package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import busines.FrontController;
import busines.LoaderINO;
import domain.Loader;
import jj.ParseException;
import jj.Regador;
import jj.TokenMgrError;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;

public class GeneraView extends JDialog
{

   private final JPanel contentPanel = new JPanel();
   private FrontController controller;
   public GeneraView(final FrontController controller)
   {
      this.controller = controller;
      setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
      setTitle("Asistente");
      setResizable(false);
      setBounds(100, 100, 376, 160);
      setLocationRelativeTo(null);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      contentPanel.setLayout(null);
      {
         JButton btnGenerar = new JButton("Generar");
         btnGenerar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               JFileChooser seleccionador = new JFileChooser();
               seleccionador.setFileFilter(new FileNameExtensionFilter("Arduino INO","ino"));
               int returnVal = seleccionador.showSaveDialog(getParent());
               if (returnVal == JFileChooser.APPROVE_OPTION) {
                  String filepath = controller.dispatchRequestResponse("LINK");
                  String pathAbsoluto = seleccionador.getSelectedFile().getAbsolutePath();
                  pathAbsoluto += ".ino";
                  try
                  {
                     Loader cargador = new LoaderINO();
                     Regador parser = new Regador(cargador.cargar(filepath));
                     parser.validar();
                     cargador.guardar(parser.generarContenido(),pathAbsoluto);
                  } catch (FileNotFoundException e1)
                  {
                	 JOptionPane.showMessageDialog(getParent(), "Archivo no encontrado","",JOptionPane.ERROR_MESSAGE);
                     e1.printStackTrace();
                  } catch (NumberFormatException e1)
                  {
                     e1.printStackTrace();
                  } catch (ParseException e1)
                  {
                	 JOptionPane.showMessageDialog(getParent(), "Error de formato","",JOptionPane.ERROR_MESSAGE);
                     e1.printStackTrace();
                  } catch(TokenMgrError e1)
                  {
                      JOptionPane.showMessageDialog(getParent(), "Error de parseo léxico","",JOptionPane.ERROR_MESSAGE);
                      e1.printStackTrace();
                  } catch (IOException e1)
                  {
                     JOptionPane.showMessageDialog(getParent(), "Error al guardar el archivo","",JOptionPane.ERROR_MESSAGE);
                     e1.printStackTrace();
                  } catch (Exception e1)
                  {
                      JOptionPane.showMessageDialog(getParent(), "Hubo un error","",JOptionPane.ERROR_MESSAGE);
                      e1.printStackTrace();
                  }
               }
            }
         });
         btnGenerar.setBounds(261, 10, 89, 23);
         contentPanel.add(btnGenerar);
      }
      {
         JPanel buttonPane = new JPanel();
         buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
         getContentPane().add(buttonPane, BorderLayout.SOUTH);
         {
            JButton btnAtras = new JButton("Atras");
            btnAtras.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  controller.dispatchRequest("ANTERIOR");
               }
            });
            buttonPane.add(btnAtras);
         }
         {
            JButton btnFinalizar = new JButton("Finalizar");
            btnFinalizar.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  dispose();
                  System.exit(0);
               }
            });
            btnFinalizar.setActionCommand("Cancel");
            buttonPane.add(btnFinalizar);
         }
      }
   }

}
