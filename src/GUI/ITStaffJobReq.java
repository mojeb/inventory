/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ITStaffJobReq.java
 *
 * Created on 06 18, 13, 1:15:47 PM
 */
package GUI;

import Models.JRF;
import Models.JRF_status;
import Models.JobReqAssignment;
import Service_impl.JRF_serv_impl;
import Service_interface.JRF_serv_interface;
import Tool.ErrorException;
import Tool.Methods;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hatiefm
 */
public class ITStaffJobReq extends javax.swing.JDialog {

    /** Creates new form ITStaffJobReq */
    private Methods m = new Methods();
    private JRF_serv_interface jrfServ;
    
    private ArrayList<JobReqAssignment> list = new ArrayList<JobReqAssignment>();
    
    public ITStaffJobReq(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        
        jrfServ = new JRF_serv_impl();
        
        m.loadPerson(jComboBox1, "ITMS");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 18));
        jLabel1.setForeground(new java.awt.Color(0, 153, 51));
        jLabel1.setText("Job Request Assigned to IT Staff");

        jComboBox1.setFocusable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Control No.", "Assign Date", "Assigned to", "Status", "Assigned by"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(250);
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(250);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/magnifier16.png"))); // NOI18N
        jButton4.setToolTipText("View");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/accept.png"))); // NOI18N
        jButton1.setToolTipText("Mark as completed");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cancel16.png"))); // NOI18N
        jButton3.setToolTipText("Mark as closed");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/application_delete.png"))); // NOI18N
        jButton2.setToolTipText("Close");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Status", "Completed", "Closed", "On Going" }));
        jComboBox2.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int index = jTable1.getSelectedRow();
        if(index >= 0){
            String emp = jComboBox1.getSelectedItem().toString();
            if(emp.equalsIgnoreCase("Select Personnel")){
                JOptionPane.showMessageDialog(this, "Error! Please select personnel.", 
                        "Error Message", JOptionPane.ERROR_MESSAGE);
                m.clearTable((DefaultTableModel)jTable1.getModel());
            }else{
                StringTokenizer token = new StringTokenizer(emp);
                try{
                    JobReqAssignment j = list.get(index);
                    j.setStatus(1);
                    jrfServ.editJob_req_assignment(j);
                    
                    //update status
                    JRF jrf = new JRF();
                    jrf.setControl_no(j.getControl_no());
                    
                    JRF_status stat = new JRF_status();
                    stat.setControl_no(jrf);
                    stat.setStatus("Job Request marked as completed by "+token.nextToken());
                    jrfServ.updateJRF_status(stat);
                    
                    JOptionPane.showMessageDialog(this, "Job Request "+j.getControl_no()+
                            " confirmed as completed.", "Information Message", 
                            JOptionPane.INFORMATION_MESSAGE);

                    list = jrfServ.getAssignment_employee(token.nextToken(), -1);
                    m.displayJobReqAssignmentList((DefaultTableModel)jTable1.getModel(), list);
                }catch(ErrorException ex){}
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int index = jTable1.getSelectedRow();
        if(index >= 0){
            String emp = jComboBox1.getSelectedItem().toString();
            if(emp.equalsIgnoreCase("Select Personnel")){
                JOptionPane.showMessageDialog(this, "Error! Please select personnel.", 
                        "Error Message", JOptionPane.ERROR_MESSAGE);
                m.clearTable((DefaultTableModel)jTable1.getModel());
            }else{
                StringTokenizer token = new StringTokenizer(emp);
                try{
                    JobReqAssignment j = list.get(index);
                    j.setStatus(2);
                    jrfServ.editJob_req_assignment(j);
                    
                    //update status
                    JRF jrf = new JRF();
                    jrf.setControl_no(j.getControl_no());
                    
                    JRF_status stat = new JRF_status();
                    stat.setControl_no(jrf);
                    stat.setStatus("Job Request marked as closed by "+token.nextToken());
                    jrfServ.updateJRF_status(stat);
                    
                    JOptionPane.showMessageDialog(this, "Job Request "+j.getControl_no()+
                            " confirmed as closed.", "Information Message", 
                            JOptionPane.INFORMATION_MESSAGE);

                    list = jrfServ.getAssignment_employee(token.nextToken(), -1);
                    m.displayJobReqAssignmentList((DefaultTableModel)jTable1.getModel(), list);
                }catch(ErrorException ex){}
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String emp = jComboBox1.getSelectedItem().toString();
        String status = jComboBox2.getSelectedItem().toString();
        
        if(emp.equalsIgnoreCase("Select Personnel") || status.equalsIgnoreCase("Select Status")){
            JOptionPane.showMessageDialog(this, "Error@ Please select IT staff and status.", 
                    "Error Message", JOptionPane.ERROR_MESSAGE);
            m.clearTable((DefaultTableModel)jTable1.getModel());
        }else{
            try{
                StringTokenizer token = new StringTokenizer(emp);
                int stat = -1;
                if(status.equalsIgnoreCase("Completed"))
                    stat = 1;
                else if(status.equalsIgnoreCase("Closed"))
                    stat = 2;
                else if(status.equalsIgnoreCase("On Going"))
                    stat = 0;
                
                list = jrfServ.getAssignment_employee(token.nextToken(), stat);
                m.displayJobReqAssignmentList((DefaultTableModel)jTable1.getModel(), list);
            }catch(ErrorException ex){}
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                ITStaffJobReq dialog = new ITStaffJobReq(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
