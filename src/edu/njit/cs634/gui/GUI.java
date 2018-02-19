/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.njit.cs634.gui;

import edu.njit.cs634.apriori.external.DatabaseUtils;
import edu.njit.cs634.apriori.external.Reader;
import edu.njit.cs634.helper.Apriori;
import java.awt.Component;
import java.io.File;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ashley Le
 */
public class GUI extends javax.swing.JFrame {
    
    public final Chooser fileChooser = new Chooser();
    public File[] files;
    //public final int totalTransaction;  // total number of transactions accross all databases
    //public final int minTransaction;    // the minimum number of transactions needed to satisfy
                                        //  support percentage
    
    private List<String> TRANSACTIONS_LIST = new ArrayList ();
    private HashMap <String, Integer> ITEM_SET = new HashMap();
    
    private final Component frame = null;
    public static double SUPPORT, CONFIDENCE; // store user's input
    
    /**
     * Creates new form Apriori
     */
    public GUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        runBttn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        inputSupport = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        inputConfidence = new javax.swing.JTextField();
        resetBttn = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        transactionTextArea = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTextArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        associationTextArea = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        fileRadio = new javax.swing.JRadioButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        fileDir = new javax.swing.JTextArea();
        dbRadio = new javax.swing.JRadioButton();
        dbBttn = new javax.swing.JButton();
        browseBttn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Apriori Algorithm");

        runBttn.setText("Run Apriori Algorithm");
        runBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runBttnActionPerformed(evt);
            }
        });

        jLabel3.setText("Support (in decimal):");

        inputSupport.setText("0.3");

        jLabel4.setText("Confidence (in decimal):");

        inputConfidence.setText("0.4");

        resetBttn.setText("RESET");
        resetBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBttnActionPerformed(evt);
            }
        });

        transactionTextArea.setColumns(20);
        transactionTextArea.setRows(5);
        jScrollPane3.setViewportView(transactionTextArea);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Transactions Listing", jPanel4);

        resultTextArea.setColumns(20);
        resultTextArea.setRows(5);
        jScrollPane1.setViewportView(resultTextArea);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Apriori Itemsets", jPanel6);

        associationTextArea.setColumns(20);
        associationTextArea.setRows(5);
        jScrollPane4.setViewportView(associationTextArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Associations", jPanel1);

        buttonGroup1.add(fileRadio);
        fileRadio.setText("Files:");
        fileRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileRadioActionPerformed(evt);
            }
        });

        fileDir.setEditable(false);
        fileDir.setColumns(20);
        fileDir.setRows(5);
        fileDir.setWrapStyleWord(true);
        fileDir.setAutoscrolls(false);
        jScrollPane5.setViewportView(fileDir);

        buttonGroup1.add(dbRadio);
        dbRadio.setSelected(true);
        dbRadio.setText("Databases");
        dbRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbRadioActionPerformed(evt);
            }
        });

        dbBttn.setLabel("Load from Databases");
        dbBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbBttnActionPerformed(evt);
            }
        });

        browseBttn.setText("Browse...");
        browseBttn.setEnabled(false);
        browseBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseBttnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inputSupport, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(512, 512, 512))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fileRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dbRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dbBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(browseBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5))
                            .addComponent(resetBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputConfidence, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77)
                                .addComponent(runBttn))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dbRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dbBttn)
                        .addGap(3, 3, 3)
                        .addComponent(fileRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseBttn))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(inputSupport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(inputConfidence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(runBttn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(resetBttn)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void runBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runBttnActionPerformed
        try {
            browseBttn.setEnabled(false);
            runBttn.setEnabled(false);
            inputSupport.setEnabled(false);
            inputConfidence.setEnabled(false);
            
            // Check for valid input
            // only allow whole number from 0 to 100
            SUPPORT = checkUserValues(inputSupport.getText());
            CONFIDENCE = checkUserValues(inputConfidence.getText());
                 
            
            if(fileRadio.isSelected())
                Reader.convertAndMergeFile(files);
            
            Apriori apr = new Apriori(Reader.tmpFile, SUPPORT, CONFIDENCE);
        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }//GEN-LAST:event_runBttnActionPerformed

    /**
     * Reset all parameters
     * @param evt 
     */
    private void resetBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBttnActionPerformed
        browseBttn.setEnabled(true);
        runBttn.setEnabled(true);
        inputSupport.setEnabled(true);
        inputConfidence.setEnabled(true);
        
        inputSupport.setText("0.3");
        inputConfidence.setText("0.3");
        fileDir.setText("");
        transactionTextArea.setText("");
        resultTextArea.setText("");
        associationTextArea.setText("");
        
        ITEM_SET = new HashMap();
        Reader.tmpFile.delete();
    }//GEN-LAST:event_resetBttnActionPerformed

    private void browseBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseBttnActionPerformed
        fileChooser.showOpenDialog(frame);
        files = fileChooser.getSelectedFiles();  
        
        for(File f : files)
        {
            fileDir.append(f.getAbsolutePath() + "\n");
        }       
        runBttn.setEnabled(true);
    }//GEN-LAST:event_browseBttnActionPerformed

    private void dbRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbRadioActionPerformed
        if(dbRadio.isSelected())
        {
            dbBttn.setEnabled(true);
            browseBttn.setEnabled(false); 
        }
        
    }//GEN-LAST:event_dbRadioActionPerformed

    private void fileRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileRadioActionPerformed
        if(fileRadio.isSelected())
        {
            dbBttn.setEnabled(false);
            browseBttn.setEnabled(true);
        }
    }//GEN-LAST:event_fileRadioActionPerformed

    private void dbBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbBttnActionPerformed
        
            DatabaseUtils db = new DatabaseUtils();
    }//GEN-LAST:event_dbBttnActionPerformed
      
    /**
     * Check the user's input value
     * Values must be between 0 and 1
     * @param input the user's input
     */
    private double checkUserValues(String input) {
        try
        {
            Double.parseDouble(input);

            if (Double.parseDouble(input) > 1.0 || Double.parseDouble(input) < 0) {
                JOptionPane.showMessageDialog(this, "Invalid input. You entered: " + input + ".\nYou must enter the percentage (in decimal) between 0 and 1.",
                                            "WARNING", JOptionPane.WARNING_MESSAGE);
            }
        }
        catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(this, "Invalid input. You entered: " + input + ".\nYou must enter the percentage (in decimal) between 0 and 1.",
                                            "WARNING", JOptionPane.WARNING_MESSAGE);
        }   
        return Double.parseDouble(input);
    }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextArea associationTextArea;
    private javax.swing.JButton browseBttn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton dbBttn;
    private javax.swing.JRadioButton dbRadio;
    public static javax.swing.JTextArea fileDir;
    private javax.swing.JRadioButton fileRadio;
    private javax.swing.JTextField inputConfidence;
    private javax.swing.JTextField inputSupport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JButton resetBttn;
    public static javax.swing.JTextArea resultTextArea;
    private javax.swing.JButton runBttn;
    public static javax.swing.JTextArea transactionTextArea;
    // End of variables declaration//GEN-END:variables
}
