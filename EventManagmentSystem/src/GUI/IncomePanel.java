/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BackEnd.EventSystem.Committee;
import BackEnd.EventSystem.Income;
import BackEnd.ManagerSystem.MainManager;
import GUI.Dialog.NewIncomeDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sara
 */
public class IncomePanel extends javax.swing.JPanel {

    /**
     * Creates new form IncomePanel
     */
    private MainManager manager;
    public IncomePanel() {
        initComponents();
        manager = MainManager.getInstance();
        updateInfo();
    }
    
    public void updateInfo()
    {
        DefaultTableModel model = getTableModel();
        model.setRowCount(0);
        if(manager.getCommitteeManager().getSelectedCommittee() != null)
        {
            Committee c = manager.getCommitteeManager().getSelectedCommittee();
            for(Income i : c.getBudget().getIncomeList())
            {
                model.addRow(
                    new Object[]
                    {
                        i.getBUDGET_ITEM_ID(),i.getDescription(),i.getValue()
                    });
            }
        }
        
    }
    
    public DefaultTableModel getTableModel()
    { return (DefaultTableModel)incomeTable.getModel(); }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        incomeLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        incomeTable = new javax.swing.JTable();
        addIncomeButton = new javax.swing.JButton();
        deleteIncomeButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(325, 500));

        incomeLabel.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N
        incomeLabel.setText("Income");

        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 500));

        incomeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "no",  new Double(201.0)}
            },
            new String [] {
                "Item ID", "Description", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        incomeTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(incomeTable);

        addIncomeButton.setText("Add Income");
        addIncomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addIncomeButtonActionPerformed(evt);
            }
        });

        deleteIncomeButton.setText("Delete Income");
        deleteIncomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteIncomeButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(incomeLabel)
                        .add(250, 430, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(addIncomeButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(deleteIncomeButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(incomeLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(deleteIncomeButton)
                    .add(addIncomeButton))
                .add(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addIncomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addIncomeButtonActionPerformed
        // TODO add your handling code here:
        NewIncomeDialog nid = new NewIncomeDialog(null, true);
        nid.setVisible(true);
        if(nid.getConfirm())
        {
            try
            {
                Income i = nid.createIncome();
                manager.getBudgetManager().createIncome(i, manager.getLogInManager().getLoggedInUser(), manager.getEventManager().getSelectedEvent(), manager.getCommitteeManager().getSelectedCommittee());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        updateInfo();    
    }//GEN-LAST:event_addIncomeButtonActionPerformed

    private void deleteIncomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteIncomeButtonActionPerformed
        // TODO add your handling code here:
        int selection = incomeTable.getSelectedRowCount();
        Income income = manager.getCommitteeManager().getSelectedCommittee().getBudget().getIncomeList().get(selection);
        try
        {
            manager.getBudgetManager().deleteIncome(income, manager.getLogInManager().getLoggedInUser(), manager.getEventManager().getSelectedEvent(), manager.getCommitteeManager().getSelectedCommittee());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        updateInfo();
    }//GEN-LAST:event_deleteIncomeButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addIncomeButton;
    private javax.swing.JButton deleteIncomeButton;
    private javax.swing.JLabel incomeLabel;
    private javax.swing.JTable incomeTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}