/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package uct.cs.dee.gui;

import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import uct.cs.dee.lib.*;
import uct.cs.dee.lib.models.*;
import org.tweetyproject.logics.pl.parser.PlParser;
import org.tweetyproject.logics.pl.syntax.PlBeliefSet;
import org.tweetyproject.logics.pl.syntax.PlFormula;

import com.formdev.flatlaf.FlatLightLaf;

/**
 *
 * @author SteveW
 */
public class MainWindow extends javax.swing.JFrame {

    private PlBeliefSet knowledgeBase;
    private PlFormula query;
    /**
     * Creates new form MainFrame
     */
    public MainWindow() {
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

        FileChooserKnowledgeBase = new javax.swing.JFileChooser();
        MainPanel = new javax.swing.JPanel();
        lblKnowledgeBase = new javax.swing.JLabel();
        txtFieldKnowledgeBase = new javax.swing.JTextField();
        lblQuery = new javax.swing.JLabel();
        txtFieldQuery = new javax.swing.JTextField();
        btnCompute = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtAreaKnowledgeBase = new javax.swing.JTextArea();
        lblOutput = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaOutput = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaException = new javax.swing.JTextArea();
        btnExit = new javax.swing.JButton();
        btnSelectFile = new javax.swing.JButton();
        btnCompute1 = new javax.swing.JButton();
        btnExit1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAreaOutput1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblKnowledgeBase.setText("Knowledge Base File (.txt) :");

        txtFieldKnowledgeBase.setEditable(false);
        txtFieldKnowledgeBase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFieldKnowledgeBaseMouseClicked(evt);
            }
        });
        txtFieldKnowledgeBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldKnowledgeBaseActionPerformed(evt);
            }
        });

        lblQuery.setText("Query:");

        btnCompute.setText("Compute Defeasible Justification");
        btnCompute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComputeActionPerformed(evt);
            }
        });

        TxtAreaKnowledgeBase.setEditable(false);
        TxtAreaKnowledgeBase.setColumns(20);
        TxtAreaKnowledgeBase.setRows(5);
        jScrollPane1.setViewportView(TxtAreaKnowledgeBase);

        lblOutput.setText("Output:");

        txtAreaOutput.setEditable(false);
        txtAreaOutput.setColumns(20);
        txtAreaOutput.setRows(5);
        jScrollPane2.setViewportView(txtAreaOutput);

        txtAreaException.setEditable(false);
        txtAreaException.setColumns(20);
        txtAreaException.setRows(5);
        jScrollPane3.setViewportView(txtAreaException);

        btnExit.setText("Exit Application");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnSelectFile.setText("Select File");
        btnSelectFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectFileActionPerformed(evt);
            }
        });

        btnCompute1.setText("Verify the KnowledgeBase");
        btnCompute1.setName("btnVerifyTheKnowledgeBase"); // NOI18N
        btnCompute1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifyTheKnowledgeBaseClicked(evt);
            }
        });

        btnExit1.setText("Clear");
        btnExit1.setName("btnClear"); // NOI18N
        btnExit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearClicked(evt);
            }
        });

        txtAreaOutput1.setEditable(false);
        txtAreaOutput1.setColumns(20);
        txtAreaOutput1.setRows(5);
        jScrollPane4.setViewportView(txtAreaOutput1);

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblQuery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblKnowledgeBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFieldQuery, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(txtFieldKnowledgeBase, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSelectFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(lblOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                                .addComponent(btnExit1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                                .addComponent(btnCompute1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCompute, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKnowledgeBase)
                    .addComponent(txtFieldKnowledgeBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSelectFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuery)
                    .addComponent(txtFieldQuery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCompute)
                    .addComponent(btnCompute1))
                .addGap(15, 15, 15)
                .addComponent(lblOutput)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit)
                    .addComponent(btnExit1))
                .addContainerGap())
        );

        btnExit.getAccessibleContext().setAccessibleName("Exit Application");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnComputeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComputeActionPerformed
        String queryString = txtFieldQuery.getText();
        if (queryString.isEmpty() || queryString == null)
        {
            JOptionPane.showMessageDialog(new Frame(), "Please enter a valid query.", "Invalid Query", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try
        {
            initQuery(queryString);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(new Frame(), "Could not parse query with following exception:\n"+e, "Query Parsing Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try 
        {
            computeDefeasibleExplanation(knowledgeBase, query);
        } 
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(new Frame(), "Error in computing the defeasible justification:\n"+ex, "Defeaible Justification - Computation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_btnComputeActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void txtFieldKnowledgeBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldKnowledgeBaseActionPerformed

    }//GEN-LAST:event_txtFieldKnowledgeBaseActionPerformed

    private void txtFieldKnowledgeBaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFieldKnowledgeBaseMouseClicked
        try 
        {
            File knowledgeBaseFile = selectFile();
            txtFieldKnowledgeBase.setText(knowledgeBaseFile.getPath());
            initKnowledgeBase(knowledgeBaseFile);
        } 
        catch (Exception ex)
        {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtFieldKnowledgeBaseMouseClicked

    private void btnSelectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectFileActionPerformed
        try 
        {
            File knowledgeBaseFile = selectFile();
            txtFieldKnowledgeBase.setText(knowledgeBaseFile.getPath());
            initKnowledgeBase(knowledgeBaseFile);
        } 
        catch (Exception ex)
        {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSelectFileActionPerformed

    private void btnVerifyTheKnowledgeBaseClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyTheKnowledgeBaseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerifyTheKnowledgeBaseClicked

    private void btnClearClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearClicked

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
        	FlatLightLaf.setup();

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            	java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.WARNING, "LookAndFeels: " + info.getName());
            	
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                   // break;
                }
                
                javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
            }
            
            javax.swing.UIManager.setLookAndFeel( new FlatLightLaf() );
            
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser FileChooserKnowledgeBase;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JTextArea TxtAreaKnowledgeBase;
    private javax.swing.JButton btnCompute;
    private javax.swing.JButton btnCompute1;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnExit1;
    private javax.swing.JButton btnSelectFile;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblKnowledgeBase;
    private javax.swing.JLabel lblOutput;
    private javax.swing.JLabel lblQuery;
    private javax.swing.JTextArea txtAreaException;
    private javax.swing.JTextArea txtAreaOutput;
    private javax.swing.JTextArea txtAreaOutput1;
    private javax.swing.JTextField txtFieldKnowledgeBase;
    private javax.swing.JTextField txtFieldQuery;
    // End of variables declaration//GEN-END:variables

    private static File selectFile()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        fileChooser.setDialogTitle("Select KnowledgeBase file");
        
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            System.out.println("Selected file: " + fileChooser.getSelectedFile());
            return fileChooser.getSelectedFile();
        }else
        {
            System.out.println("Invalid selection.");
            return null;
        }
    }
    
    private void initKnowledgeBase(File knowledgeBaseFile) throws FileNotFoundException, Exception
    {
        knowledgeBase = new PlBeliefSet();
        PlParser classicalParser = new PlParser();
        DefeasibleLogicParser defeasibleParser = new DefeasibleLogicParser(classicalParser);
        Scanner scanner = new Scanner(knowledgeBaseFile);
            
        while (scanner.hasNextLine())
        {
            String inputFormula = scanner.nextLine();
            if (inputFormula.contains("~>"))
                knowledgeBase.add(defeasibleParser.parseFormula(inputFormula));
            else
                knowledgeBase.add(classicalParser.parseFormula(inputFormula));
        }
        TxtAreaKnowledgeBase.append("Knowledge Base:\n" + knowledgeBase + "\n");
    }
    
    private void initQuery(String queryString) throws Exception
    {
        PlParser classicalParser = new PlParser();
        DefeasibleLogicParser defeasibleParser = new DefeasibleLogicParser(classicalParser);
        query = defeasibleParser.parseFormula(queryString);
        TxtAreaKnowledgeBase.append("Query:\n" + query.toString() + "\n");
    }
    
    private void computeDefeasibleExplanation(PlBeliefSet knowledgeBase, PlFormula query) throws Exception
    {
        List<PlFormula> classicalFormulas = Utils.getClassicalFormulas(knowledgeBase);
        
        RationalClosureResults rationalClosure= RationalClosure.computeRationalClosure(knowledgeBase, query);
        System.out.println(rationalClosure);
        
        if (!rationalClosure.entailmentsHolds())
        {
            txtAreaOutput.append("Entailment does not hold." + "\n");
            txtAreaOutput.append("Following remaining formulas does not entail the query: \n" + rationalClosure.getRemainingFormulas() + "\n");
            return;
        }
        
        int ranksRemoved = rationalClosure.getRanksRemoved();
        
        if (ranksRemoved == 0)
        {
            Node rootNode = ClassicJust.computeJustification(Utils.materialise(knowledgeBase), Utils.materialise(query));
            List<List<PlFormula>> justifiactions = rootNode.getAllJustifications();
            List<List<PlFormula>> dematerialisedJustification = new ArrayList<List<PlFormula>>();
            for (List<PlFormula> justification : justifiactions)
            {
                dematerialisedJustification.add(Utils.dematerialise(justification, classicalFormulas));
            }
            txtAreaOutput.append("<<Final Justification>>\n");
            for (List<PlFormula> newJust : dematerialisedJustification)
            {
                txtAreaOutput.append(Utils.printJustificationAsCSV(newJust) + "\n");
            }
            return;
        }
        
        int i = 0;
        
        while (i < ranksRemoved)
        {
            knowledgeBase = Utils.remove(knowledgeBase, rationalClosure.getMinimalRanking().getFinitlyRankedFormula(i));
            txtAreaOutput.append("Removing rank " + i + " =====" + "\n");
            txtAreaOutput.append(knowledgeBase.toString()+ "\n");
            i ++;
        }
        
        Node rootNode = ClassicJust.computeJustification(Utils.materialise(knowledgeBase), Utils.materialise(query));
        List<List<PlFormula>> justifiactions = rootNode.getAllJustifications();
        List<List<PlFormula>> dematerialisedJustification = new ArrayList<List<PlFormula>>();
        for (List<PlFormula> justification : justifiactions)
        {
            dematerialisedJustification.add(Utils.dematerialise(justification, classicalFormulas));
        }
        
        txtAreaOutput.append("<<Final Justification>>"+ "\n");
        for (List<PlFormula> newJust : dematerialisedJustification)
        {
            txtAreaOutput.append(Utils.printJustificationAsCSV(newJust)+ "\n");
        }
        
    }
    
}
