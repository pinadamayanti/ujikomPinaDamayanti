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
public class master extends javax.swing.JFrame {
    private DefaultTableModel model;
    Connection kon;
    /**
     * Creates new form master1
     */
    public master() {
        initComponents();
        this.setLocationRelativeTo(null);
        model = new DefaultTableModel ( );
        master.setModel(model);
             model.addColumn("ID Pengguna");
             model.addColumn("Username");
             model.addColumn("Password");
             model.addColumn("Hak Akses");
             model.addColumn("Nama Lengkap");
             getData();
             cariData();
    }

    public void getData( ){
     
     model.getDataVector( ).removeAllElements( );
     model.fireTableDataChanged( );
     kon =koneksi.koneksiDb();
     try{
           
//membuat statemen untuk memanggil data table master
              
           Statement stat = kon.createStatement( );
           String sql     = "Select * from user";
           ResultSet res  = stat.executeQuery(sql);
     
//pengecekan terhadap data master
           while(res.next ()){
                Object[] obj = new Object[5];
                obj[0] = res.getString("id_user");
                obj[1] = res.getString("username");
                obj[2] = res.getString("password");
                obj[3] = res.getString("role");
                obj[4] = res.getString("nama_user");
                model.addRow(obj);
            }
      }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage() );
      }      
    }

      public void tambahData( ){
          kon=koneksi.koneksiDb();     
        try {
           Statement stat = kon.createStatement( );
           String sql     = "insert into user values('"+id.getText()+"','"+usernm.getText()+"','"+pass.getText()+"','"+jComboBox1.getSelectedItem()+"','"+namalengkap.getText()+"')";
           PreparedStatement pst  = kon.prepareStatement(sql);
           noID();
           pst.execute();  
           
           JOptionPane.showMessageDialog(null,"Penyimpanan Data Berhasil");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        getData();
        hapus();
    }
      
      public void noID(){
          kon=koneksi.koneksiDb();
        try {
           Statement stat = kon.createStatement( );
           String sql     = "Select * from user order by id_user desc";
           ResultSet res  = stat.executeQuery(sql);
            
            if(res.next()){
                String noId = res.getString("id_user").substring (1);
                String AN = ""+(Integer.parseInt(noId)+1);
                String Nol = "";
                
                if(AN.length( )==1){
                    Nol="00";
                }else if(AN.length( )==2){
                    Nol="0";
                }else if(AN.length( )==3){
                    Nol=" ";
                } id.setText ("M"+Nol+AN);
            }else{ 
                id.setText ("M001");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
      }
      
      public void hapusData( ){
        kon=koneksi.koneksiDb();
        try {
           Statement stat = kon.createStatement( );
           String sql     = "Delete from user where id_user='"+id.getText()+"'";
           PreparedStatement pst  = kon.prepareStatement(sql);
           pst.execute();  
           
           JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }getData();
        hapus();
    }
      
      public void editData( ){
        kon=koneksi.koneksiDb();
        try{
            Statement stat = kon.createStatement( );
            String sql="UPDATE user SET username='"+usernm.getText()+"',password='"+pass.getText()+"',role='"+jComboBox1.getSelectedItem()+"',nama_user='"+ namalengkap.getText()+"' WHERE id_user='"+id.getText()+"'";
            PreparedStatement pst=kon.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Data Berhasil Diedit");  
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Perubahan Data Gagal"+e.getMessage());  
        }getData();
        hapus();
    }
      
      private void hapus() {
        id.setText(null);
        usernm.setText(null);
        pass.setText(null);
        jComboBox1.setSelectedItem(null);
        namalengkap.setText(null);
    }
      
      public void cariData( ){
     
     model.getDataVector( ).removeAllElements( );
     model.fireTableDataChanged( );
     kon =koneksi.koneksiDb();
     
     try{
//membuat statemen untuk memanggil data table master
              
           Statement stat = kon.createStatement( );
           String sql     = "Select * from user where id_user like'%"+cr.getText()+"%' or username like'%"+cr.getText()+"%' or nama_user like'%"+cr.getText()+"%'";
           ResultSet res  = stat.executeQuery(sql);
     
//pengecekan terhadap data master
           while(res.next ()){
                Object[] obj = new Object[5];
                obj[0] = res.getString("id_user");
                obj[1] = res.getString("username");
                obj[2] = res.getString("password");
                obj[3] = res.getString("role");
                obj[4] = res.getString("nama_user");
                model.addRow(obj);
            }
      }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage() );
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

        jPanel4 = new javax.swing.JPanel();
        refresh1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        usernm = new javax.swing.JTextField();
        pass = new javax.swing.JTextField();
        namalengkap = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        clear = new javax.swing.JButton();
        tambah = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        master = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        cr = new javax.swing.JTextField();
        kembali1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        refresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh1ActionPerformed(evt);
            }
        });
        jPanel4.add(refresh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 24, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Form Master"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("User ID");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 70, 30));

        jLabel2.setText("Username");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 70, 20));

        jLabel3.setText("Password");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 70, 30));

        jLabel4.setText("Hak Akses");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 151, 70, 30));

        jLabel5.setText("Nama Lengkap");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 194, -1, 20));

        id.setEditable(false);
        id.setText(" ");
        jPanel1.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 90, 30));

        usernm.setText(" ");
        jPanel1.add(usernm, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 275, 30));

        pass.setText(" ");
        jPanel1.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 275, 30));

        namalengkap.setText(" ");
        jPanel1.add(namalengkap, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 275, 30));

        jComboBox1.setEditable(true);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User", "Owner" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, -1, 30));

        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel1.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, 71, -1));

        tambah.setText("Tambah");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });
        jPanel1.add(tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 35, -1, -1));

        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        jPanel1.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 88, 71, -1));

        hapus.setText("Delete");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        jPanel1.add(hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 140, 71, -1));

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 30, 30));

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 990, 240));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Master"));

        master.setModel(new javax.swing.table.DefaultTableModel(
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
        master.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(master);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 990, 170));

        jButton1.setText("cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, -1, 30));
        jPanel4.add(cr, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 170, 30));

        kembali1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ITutup.png"))); // NOI18N
        kembali1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembali1ActionPerformed(evt);
            }
        });
        jPanel4.add(kembali1, new org.netbeans.lib.awtextra.AbsoluteConstraints(954, 11, 30, 30));

        jLabel8.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        jLabel8.setText("MASTER");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 100, 60));

        jLabel6.setText("Berdasarkan ID user/username");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 200, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/form.png"))); // NOI18N
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1005, 635));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void masterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterMouseClicked
        // TODO add your handling code here:
        int baris=master.rowAtPoint(evt.getPoint());
        String id_user=master.getValueAt(baris,0).toString();
        id.setText(id_user);
        String username=master.getValueAt(baris,1).toString();
        usernm.setText(username);
        String password=master.getValueAt(baris,2).toString();
        pass.setText(password);
        String role=master.getValueAt(baris,3).toString();
        jComboBox1.setSelectedItem(role);
        String nama_lengkap=master.getValueAt(baris,4).toString();
        namalengkap.setText(nama_lengkap);
    }//GEN-LAST:event_masterMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cariData();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void kembali1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembali1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_kembali1ActionPerformed

    private void refresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh1ActionPerformed
        // TODO add your handling code here:
        getData();
        cr.setText(null);
    }//GEN-LAST:event_refresh1ActionPerformed

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
            java.util.logging.Logger.getLogger(master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new master().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clear;
    private javax.swing.JTextField cr;
    private javax.swing.JButton edit;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kembali1;
    private javax.swing.JTable master;
    private javax.swing.JTextField namalengkap;
    private javax.swing.JTextField pass;
    private javax.swing.JButton refresh1;
    private javax.swing.JButton tambah;
    private javax.swing.JTextField usernm;
    // End of variables declaration//GEN-END:variables
}
