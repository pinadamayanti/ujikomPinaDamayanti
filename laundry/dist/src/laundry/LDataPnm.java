/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laundry;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class LDataPnm extends javax.swing.JFrame {

    private DefaultTableModel model;
    Connection kon;
    public transaksi trs=null;
    
    public LDataPnm() {
        initComponents();
        this.setLocationRelativeTo(null);
        model = new DefaultTableModel ( );
        Dpenerimaan.setModel(model);
             model.addColumn("No Order");
             model.addColumn("Nama Pelanggan");
             model.addColumn("Subtotal");
             model.addColumn("Bayar");
             model.addColumn("Sisa");             
             model.addColumn("Status");
             getData();
             cariData();
    }
public void getData( ){
     
     model.getDataVector( ).removeAllElements( );
     model.fireTableDataChanged( );
     kon =koneksi.koneksiDb();
     try{
           
//membuat statemen untuk memanggil data table transaksi2
              
           Statement stat = kon.createStatement( );
           String sql     = "Select no_order,nama_pel,t_bayar,bayar,sisa,dibayar from penerimaan ";
           ResultSet res  = stat.executeQuery(sql);
     
//pengecekan terhadap data transaksi2
           while(res.next ()){
                Object[] obj = new Object[6];
                obj[0] = res.getString("no_order");
                obj[1] = res.getString("nama_pel");
                obj[2] = res.getString("t_bayar");
                obj[3] = res.getString("bayar");
                obj[4] = res.getString("sisa");
                obj[5] = res.getString("dibayar");
                
                model.addRow(obj);
            }
      }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage() );
      }      
    }
    
public void cariData( ){
     
     model.getDataVector( ).removeAllElements( );
     model.fireTableDataChanged( );
     kon =koneksi.koneksiDb();
     
     try{
//membuat statemen untuk memanggil data table penerimaan
              
           Statement stat = kon.createStatement( );
           String sql     = "Select no_order,nama_pel,t_bayar,bayar,sisa,dibayar from penerimaan where no_order like'%"+cr.getText()+"%' or nama_pel like'%"+cr.getText()+"%'";
           ResultSet res  = stat.executeQuery(sql);
     
//pengecekan terhadap data penerimaan
           while(res.next ()){
                Object[] obj = new Object[6];
                obj[0] = res.getString("no_order");
                obj[1] = res.getString("nama_pel");
                obj[2] = res.getString("t_bayar");
                obj[3] = res.getString("bayar");
                obj[4] = res.getString("sisa");
                obj[5] = res.getString("dibayar");
                model.addRow(obj);
            }
      }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage() );
      }   
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Dpenerimaan = new javax.swing.JTable();
        cr = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Dpenerimaan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Dpenerimaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DpenerimaanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Dpenerimaan);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 510, 200));

        cr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crActionPerformed(evt);
            }
        });
        jPanel1.add(cr, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 180, 30));

        jButton1.setText("cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, -1, 30));

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 24, 30));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ITutup.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 350, 30, 30));

        jLabel8.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel8.setText("Laporan Order Masuk");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 160, 60));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/load.png"))); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setName(""); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(565, 404));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 565, 404));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DpenerimaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DpenerimaanMouseClicked
        // TODO add your handling code here:
        int baris=Dpenerimaan.getSelectedRow();
        trs.noOr=Dpenerimaan.getValueAt(baris,0).toString();
        trs.namaPel=Dpenerimaan.getValueAt(baris,1).toString();
        trs.subTotal=Dpenerimaan.getValueAt(baris,2).toString();
        trs.byrDp=Dpenerimaan.getValueAt(baris,3).toString();
        trs.sisa=Dpenerimaan.getValueAt(baris,4).toString();        
        trs.status=Dpenerimaan.getValueAt(baris,5).toString();
        trs.noTerpilih();
        this.dispose();
    }//GEN-LAST:event_DpenerimaanMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cariData();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        getData();
        cr.setText(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void crActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_crActionPerformed

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
            java.util.logging.Logger.getLogger(LDataPnm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LDataPnm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LDataPnm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LDataPnm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LDataPnm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Dpenerimaan;
    private javax.swing.JTextField cr;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
