package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import manager.FrontController;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GeneraArchivoView extends JDialog
{

   private final JPanel contentPanel = new JPanel();
   private FrontController controller;
   public GeneraArchivoView(final FrontController controller)
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
            JButton btnCancelar = new JButton("Cancelar");
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
