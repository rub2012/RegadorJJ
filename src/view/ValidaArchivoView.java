package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import manager.FrontController;
import plugin.Loader;
import plugin.LoaderArchivoTxt;
import jj.ParseException;
import jj.Regador;
import jj.TokenMgrError;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ValidaArchivoView extends JDialog
{

   private final JPanel contentPanel = new JPanel();
   private FrontController controller;
   private JButton btnSiguiente;
   public ValidaArchivoView(final FrontController controller)
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
      btnSiguiente = new JButton("Siguiente");
      btnSiguiente.setEnabled(false);
      {
         JButton btnValidar = new JButton("Validar");
         btnValidar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String filepath = controller.dispatchRequestResponse("LINK");
               btnSiguiente.setEnabled(false);
               try
               {
                  Loader cargador = new LoaderArchivoTxt();
                  Regador parser = new Regador(cargador.cargar(filepath));
                  parser.validar();
                  JOptionPane.showMessageDialog(getParent(), "Archivo Válido","",JOptionPane.INFORMATION_MESSAGE);
                  btnSiguiente.setEnabled(true);
               } catch (FileNotFoundException e1)
               {
                  JOptionPane.showMessageDialog(getParent(), "Archivo no encontrado","",JOptionPane.ERROR_MESSAGE);
                  e1.printStackTrace();
               } catch (ParseException e1)
               {
                  JOptionPane.showMessageDialog(getParent(), "Error de formato","",JOptionPane.ERROR_MESSAGE);
                  e1.printStackTrace();
               } catch(TokenMgrError e1)
               {
                  JOptionPane.showMessageDialog(getParent(), "Error de parseo léxico","",JOptionPane.ERROR_MESSAGE);
                  e1.printStackTrace();
               }
            }
         });
         btnValidar.setBounds(261, 10, 89, 23);
         contentPanel.add(btnValidar);
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
                  btnSiguiente.setEnabled(false);
               }
            });
            buttonPane.add(btnAtras);
         }
         {
            
            btnSiguiente.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  controller.dispatchRequest("SIGUIENTE");
                  btnSiguiente.setEnabled(false);
               }
            });
            buttonPane.add(btnSiguiente);
         }
         {
            JButton btnCancelar = new JButton("Cancelar");
            btnCancelar.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  btnSiguiente.setEnabled(false);
               }
            });
            btnCancelar.setActionCommand("OK");
            buttonPane.add(btnCancelar);
            getRootPane().setDefaultButton(btnCancelar);
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
