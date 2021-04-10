/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laundry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class jenisCuci extends javax.swing.JFrame {

    private DefaultTableModel model;
    Connection kon;
    
    public jenisCuci() {
        initComponents();
                this.setLocationRelativeTo(null);

        model = new DefaultTableModel ( );
        cuci.setModel(model);
             model.addColumn("Kode Jenis");
             model.addColumn("Jenis Cucian");
             model.addColumn("Harga");
             getData();
             cariData();
    }

    public void getData() {

        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        kon = koneksi.koneksiDb();
        try {

//membuat statemen untuk memanggil data table cuci
            Statement stat = kon.createStatement();
            String sql = "Select * from jenis_cucian";
            ResultSet res = stat.executeQuery(sql);

//pengecekan terhadap data cuci
            while (res.next()) {
                Object[] obj = new Object[3];
                obj[0] = res.getString("kd_jenis");
                obj[1] = res.getString("jenis_cucian");
                obj[2] = res.getString("harga");
                model.addRow(obj);
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }

    public void tambahData() {
        kon = koneksi.koneksiDb();
        try {
            Statement stat = kon.createStatement();
            String sql = "insert into jenis_cucian values('" + kd.getText() + "','" + jenis.getText() + "','" + hrg.getText() + "')";
            PreparedStatement pst = kon.prepareStatement(sql);
            pst.execute();

            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        getData();
        hapus();
    }
    public void noID(){
          kon=koneksi.koneksiDb();
        try {
           Statement stat = kon.createStatement( );
            String sql     = "Select * from jenis_cucian order by kd_jenis desc";
           ResultSet res  = stat.executeQuery(sql);
            
            if(res.next()){
                String noId = res.getString("kd_jenis").substring (1);
                String AN = ""+(Integer.parseInt(noId)+1);
                String Nol = "";
                
                if(AN.length( )==1){
                    Nol="00";
                }else if(AN.length( )==2){
                    Nol="0";
                }else if(AN.length( )==3){
                    Nol=" ";
                } kd.setText ("A"+Nol+AN);
            }else{ 
                kd.setText ("A001");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
      }
    
    public void hapusData() {
        kon = koneksi.koneksiDb();
        try {
            Statement stat = kon.createStatement();
            String sql = "Delete from jenis_cucian where kd_jenis='" + kd.getText() + "'";
            PreparedStatement pst = kon.prepareStatement(sql);
            pst.execute();

            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        getData();
        hapus();
    }

    public void editData() {
        kon = koneksi.koneksiDb();
        try {
            Statement stat = kon.createStatement();
            String sql = "UPDATE jenis_cucian SET jenis_cucian='" + jenis.getText() +"',harga='" + hrg.getText() + "' WHERE kd_jenis='" + kd.getText() + "'";
            PreparedStatement pst = kon.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diedit");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal" + e.getMessage());
        }
        getData();
        hapus();
    }

    private void hapus() {
        kd.setText(null);
        jenis.setText(null);
        hrg.setText(null);
    }

    public void cariData() {

        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        kon = koneksi.koneksiDb();

        try {
//membuat statemen untuk memanggil data table cuci

            Statement stat = kon.createStatement();
            String sql = "Select * from jenis_cucian where kd_jenis like'%" + cr.getText() + "%' or jenis_cucian like'%" + cr.getText()+ "%'";
            
            ResultSet res = stat.executeQuery(sql);

//pengecekan terhadap data cuci
            while (res.next()) {
                Object[] obj = new Object[3];
                obj[0] = res.getString("kd_jenis");
                obj[1] = res.getString("jenis_cucian");
                obj[2] = res.getString("harga");
                model.addRow(obj);
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cuci = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        kd = new javax.swing.JTextField();
        jenis = new javax.swing.JTextField();
        hrg = new javax.swing.JTextField();
        clear = new javax.swing.JButton();
        tambah = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        kembali1 = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        cr = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Jenis Cuci"));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        cuci.setModel(new javax.swing.table.DefaultTableModel(
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
        cuci.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuciMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(cuci);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 985, 200));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Form Jenis Cuci"));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Kode Jenis");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 100, 30));

        jLabel2.setText("Jenis Cucian");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 100, 30));

        jLabel3.setText("Harga");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 100, 30));

        kd.setText(" ");
        jPanel4.add(kd, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 95, 30));

        jenis.setText(" ");
        jPanel4.add(jenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 270, 30));

        hrg.setText(" ");
        jPanel4.add(hrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 140, 30));

        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel4.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 180, 71, 30));

        tambah.setText("Tambah");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });
        jPanel4.add(tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 30, -1, 30));

        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        jPanel4.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, 71, 30));

        hapus.setText("Delete");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        jPanel4.add(hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, 71, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("/ KG");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 30, 30));

        jButton2.setText(" ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, -1, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 409, 985, 220));

        kembali1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ITutup.png"))); // NOI18N
        kembali1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembali1ActionPerformed(evt);
            }
        });
        jPanel1.add(kembali1, new org.netbeans.lib.awtextra.AbsoluteConstraints(954, 11, 30, 30));

        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        jPanel1.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 24, 30));

        jButton1.setText("cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, -1, 30));
        jPanel1.add(cr, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 150, 30));

        jLabel8.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        jLabel8.setText("JENIS CUCIAN");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 180, 60));

        jLabel6.setText("Berdasarkan KD jenis/jenis cucian");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 230, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/form.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cariData();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
        getData();
        cr.setText(null);
    }//GEN-LAST:event_refreshActionPerformed

    private void cuciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuciMouseClicked
        // TODO add your handling code here:
        int baris=cuci.rowAtPoint(evt.getPoint());
        String kd_jenis=cuci.getValueAt(baris,0).toString();
        kd.setText(kd_jenis);
        String jenis_cucian=cuci.getValueAt(baris,1).toString();
        jenis.setText(jenis_cucian);
        String harga=cuci.getValueAt(baris,2).toString();
        hrg.setText(harga);
    }//GEN-LAST:event_cuciMouseClicked

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        hapus();
    }//GEN-LAST:event_clearActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here:
        tambahData();
    }//GEN-LAST:event_tambahActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        editData();
    }//GEN-LAST:event_editActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
        hapusData();
    }//GEN-LAST:event_hapusActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        noID();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void kembali1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembali1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_kembali1ActionPerformed

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
            java.util.logging.Logger.getLogger(jenisCuci.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jenisCuci.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jenisCuci.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jenisCuci.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jenisCuci().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clear;
    private javax.swing.JTextField cr;
    private javax.swing.JTable cuci;
    private javax.swing.JButton edit;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField hrg;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jenis;
    private javax.swing.JTextField kd;
    private javax.swing.JButton kembali1;
    private javax.swing.JButton refresh;
    private javax.swing.JButton tambah;
    // End of variables declaration//GEN-END:variables
}
