package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import busines.FrontController;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JLabel;

public class CargaView extends JDialog
{

   private JFileChooser seleccionador;
   private final JPanel contentPanel = new JPanel();
   private JTextField filepath;
   private JButton btnSiguiente;
   private FrontController controller;
   public CargaView(final FrontController controller)
   {
      seleccionador = new JFileChooser();
      seleccionador.setFileFilter(new FileNameExtensionFilter("Archivos de texto","txt"));
      btnSiguiente = new JButton("Siguiente");
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
         filepath = new JTextField();
         filepath.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
               if (filepath.getText().length() == 0){
                  btnSiguiente.setEnabled(false);
               }else{
                  btnSiguiente.setEnabled(true);
               }
            }
         });
         filepath.setEditable(false);
         filepath.setBounds(10, 28, 241, 20);
         contentPanel.add(filepath);
         filepath.setColumns(10);
      }
      {
         JButton btnExaminar = new JButton("Examinar");
         btnExaminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               int returnVal = seleccionador.showOpenDialog(getParent());
               if (returnVal == JFileChooser.APPROVE_OPTION) {
                  String pathAbsoluto = seleccionador.getSelectedFile().getAbsolutePath();
                  filepath.setText(pathAbsoluto);
                  filepath.setToolTipText(pathAbsoluto);
               }
            }
         });
         btnExaminar.setBounds(261, 27, 89, 23);
         contentPanel.add(btnExaminar);
      }
      
      JLabel lblSeleccioneElPrograma = new JLabel("Seleccione el programa a validar:");
      lblSeleccioneElPrograma.setBounds(10, 11, 241, 14);
      contentPanel.add(lblSeleccioneElPrograma);
      {
         JPanel buttonPane = new JPanel();
         buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
         getContentPane().add(buttonPane, BorderLayout.SOUTH);
         {
               btnSiguiente.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  controller.dispatchRequest("SIGUIENTE");
               }
            });
            buttonPane.add(btnSiguiente);
         }
         {
            JButton btnCancelar = new JButton("Cancelar");
            btnCancelar.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  filepath.setText("");
                  filepath.setToolTipText(null);
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
   public String getLink(){
      return filepath.getText();
   }
}
