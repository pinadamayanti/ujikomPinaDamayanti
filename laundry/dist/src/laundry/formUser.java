/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laundry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Lenovo
 */
public class formUser extends javax.swing.JFrame {
    
    Connection kon;
    
    public formUser() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        pel = new javax.swing.JMenu();
        cuci = new javax.swing.JMenu();
        penerimaan = new javax.swing.JMenu();
        transaksi = new javax.swing.JMenu();
        laporan = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        about = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        logout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        jLabel8.setText("Selamat Datang User");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 230, 60));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/form3.png"))); // NOI18N
        jLabel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 710, -1));

        jMenuBar1.setMaximumSize(new java.awt.Dimension(630, 32769));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(630, 52));

        pel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/IPel.png"))); // NOI18N
        pel.setText("Pelanggan");
        pel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pel.setPreferredSize(new java.awt.Dimension(150, 50));
        pel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pelMouseClicked(evt);
            }
        });
        jMenuBar1.add(pel);
        pel.getAccessibleContext().setAccessibleDescription("");

        cuci.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/IJns.png"))); // NOI18N
        cuci.setText("Jenis Cucian");
        cuci.setPreferredSize(new java.awt.Dimension(150, 50));
        cuci.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuciMouseClicked(evt);
            }
        });
        jMenuBar1.add(cuci);
        cuci.getAccessibleContext().setAccessibleDescription("");

        penerimaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/IPnm2.png"))); // NOI18N
        penerimaan.setText("Penerimaan");
        penerimaan.setPreferredSize(new java.awt.Dimension(150, 50));
        penerimaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                penerimaanMouseClicked(evt);
            }
        });
        jMenuBar1.add(penerimaan);
        penerimaan.getAccessibleContext().setAccessibleDescription("");

        transaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ITransaksi.png"))); // NOI18N
        transaksi.setText("Transaksi");
        transaksi.setPreferredSize(new java.awt.Dimension(150, 50));
        transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transaksiMouseClicked(evt);
            }
        });
        jMenuBar1.add(transaksi);

        laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ILaporan.png"))); // NOI18N
        laporan.setText("Laporan");
        laporan.setName(""); // NOI18N
        laporan.setPreferredSize(new java.awt.Dimension(157, 50));

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem3.setText("Data Pelanggan");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        laporan.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem4.setText("Data Cucian ");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        laporan.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem5.setText("Data Transaksi");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        laporan.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem6.setText("Data Penerimaan");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        laporan.add(jMenuItem6);

        jMenuBar1.add(laporan);
        laporan.getAccessibleContext().setAccessibleDescription("");

        about.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/IDetail.png"))); // NOI18N
        about.setText("About");
        about.setName(""); // NOI18N
        about.setPreferredSize(new java.awt.Dimension(157, 50));
        about.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aboutMouseClicked(evt);
            }
        });

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("Ganti Password");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        about.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setText("Profil Aplikasi");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        about.add(jMenuItem2);

        jMenuBar1.add(about);
        about.getAccessibleContext().setAccessibleDescription("");

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ILog.png"))); // NOI18N
        logout.setText("Log Out");
        logout.setPreferredSize(new java.awt.Dimension(90, 50));
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });
        jMenuBar1.add(logout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah Yakin Ingin Keluar?", "Logout", JOptionPane.YES_NO_CANCEL_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new loading1().setVisible(true);
            dispose();
        }else{ JOptionPane.showMessageDialog(null,"");}
    }//GEN-LAST:event_logoutMouseClicked

    private void pelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pelMouseClicked
        // TODO add your handling code here:
         new pelanggan2().setVisible(true);
    }//GEN-LAST:event_pelMouseClicked

    private void cuciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuciMouseClicked
        // TODO add your handling code here:
        new LJenisCuci().setVisible(true);
    }//GEN-LAST:event_cuciMouseClicked

    private void penerimaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_penerimaanMouseClicked
        // TODO add your handling code here:
        new penerimaan().setVisible(true);
    }//GEN-LAST:event_penerimaanMouseClicked

    private void aboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_aboutMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new aboutPass( ).setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        new aboutView( ).setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaksiMouseClicked
        // TODO add your handling code here:
        new  transaksi().setVisible(true);
    }//GEN-LAST:event_transaksiMouseClicked

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        kon =koneksi.koneksiDb();
        int confirm = JOptionPane.showConfirmDialog(null, "Cetak Data Pelanggan?", "Cetak", JOptionPane.YES_NO_CANCEL_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            
            try {

  String path="src/laporan/dataPelanggan.jasper";      // letak penyimpanan report

  HashMap parameter = new HashMap();
  
  JasperPrint print = JasperFillManager.fillReport(path,null,kon);

  JasperViewer.viewReport(print, false);

    } catch (Exception ex) {

  JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada "+ex);

    }
            dispose();
        }else{ JOptionPane.showMessageDialog(null,"");}
        
        
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        new laporanCuci().setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:        
        new laporanTr().setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        int confirm=JOptionPane.showConfirmDialog(null,"Cetak data Penerimaan?","Cetak", JOptionPane.YES_NO_CANCEL_OPTION);
        if (confirm== JOptionPane.YES_OPTION ){
        kon =koneksi.koneksiDb();
        try {

  String path="src/laporan/dataRekap.jasper";      // letak penyimpanan report

  HashMap parameter = new HashMap();
 
  JasperPrint print = JasperFillManager.fillReport(path,null,kon);

  JasperViewer.viewReport(print, false);

    } catch (Exception ex) {

  JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada "+ex);

    }
            }else{ JOptionPane.showMessageDialog(null,"");
    }                               
    }//GEN-LAST:event_jMenuItem6ActionPerformed

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
            java.util.logging.Logger.getLogger(formUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu about;
    private javax.swing.JMenu cuci;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu laporan;
    private javax.swing.JMenu logout;
    private javax.swing.JMenu pel;
    private javax.swing.JMenu penerimaan;
    private javax.swing.JMenu transaksi;
    // End of variables declaration//GEN-END:variables
}
