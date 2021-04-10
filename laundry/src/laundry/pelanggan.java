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
public class pelanggan extends javax.swing.JFrame {

    private DefaultTableModel model;
    Connection kon;
    
    public pelanggan() {
        initComponents();
        this.setLocationRelativeTo(null);
        model = new DefaultTableModel ( );
        pelanggan.setModel(model);
             model.addColumn("ID Pelanggan");
             model.addColumn("Nama Pelanggan");
             model.addColumn("Alamat");
             model.addColumn("Telepon");
             model.addColumn("Jenis Kelamin");
             getData();
             cariData();
    }
    
    public void getData( ){
     
     model.getDataVector( ).removeAllElements( );
     model.fireTableDataChanged( );
     kon =koneksi.koneksiDb();
     try{
           
//membuat statemen untuk memanggil data table pelanggan
              
           Statement stat = kon.createStatement( );
           String sql     = "Select * from pelanggan";
           ResultSet res  = stat.executeQuery(sql);
     
//pengecekan terhadap data pelanggan
           while(res.next ()){
                Object[] obj = new Object[5];
                obj[0] = res.getString("id_pel");
                obj[1] = res.getString("nama_pel");
                obj[2] = res.getString("alamat");
                obj[3] = res.getString("tlp");
                obj[4] = res.getString("jenis_kelamin");
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
           String sql     = "insert into pelanggan values('"+idpel.getText()+"','"+nm.getText()+"','"+almt.getText()+"','"+telp.getText()+"','"+jk.getSelectedItem()+"')";
           PreparedStatement pst  = kon.prepareStatement(sql);
           pst.execute();  
           
           JOptionPane.showMessageDialog(null,"Penyimpanan Data Berhasil");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }getData();
        hapus();
    }
    
    
        public void noID(){
          kon=koneksi.koneksiDb();
        try {
           Statement stat = kon.createStatement( );
           String sql     = "Select * from pelanggan order by id_pel desc";
           ResultSet res  = stat.executeQuery(sql);
            
            if(res.next()){
                String noId = res.getString("id_pel").substring (1);
                String AN = ""+(Integer.parseInt(noId)+1);
                String Nol = "";
                
                if(AN.length( )==1){
                    Nol="00";
                }else if(AN.length( )==2){
                    Nol="0";
                }else if(AN.length( )==3){
                    Nol=" ";
                } idpel.setText ("P"+Nol+AN);
            }else{ 
                idpel.setText ("P001");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
      }
 
        
      public void hapusData( ){
        kon=koneksi.koneksiDb();
        try {
           Statement stat = kon.createStatement( );
           String sql     = "delete from pelanggan where id_pel='"+idpel.getText()+"'";
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
            String sql="UPDATE pelanggan SET nama_pel='"+nm.getText()+"',alamat='"+almt.getText()+"',tlp='"+ telp.getText()+"',jenis_kelamin='"+jk.getSelectedItem()+"' WHERE id_pel='"+idpel.getText()+"'";
            PreparedStatement pst=kon.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Data Berhasil Diedit");  
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Perubahan Data Gagal"+e.getMessage());  
        }getData();
        hapus();
    }
      
      private void hapus() {
        idpel.setText(null);
        nm.setText(null);
        almt.setText(null);
        jk.setSelectedItem(null);
        telp.setText(null);
    }
      
      public void cariData( ){
     
     model.getDataVector( ).removeAllElements( );
     model.fireTableDataChanged( );
     kon =koneksi.koneksiDb();
     
     try{
//membuat statemen untuk memanggil data table pelanggan
              
           Statement stat = kon.createStatement( );
           String sql     = "Select * from pelanggan where id_pel like'%"+cr.getText()+"%' or nama_pel like'%"+cr.getText()+"%'";
           ResultSet res  = stat.executeQuery(sql);
     
//pengecekan terhadap data pelanggan
           while(res.next ()){
                Object[] obj = new Object[5];
                obj[0] = res.getString("id_pel");
                obj[1] = res.getString("nama_pel");
                obj[2] = res.getString("alamat");
                obj[3] = res.getString("tlp");
                obj[4] = res.getString("jenis_kelamin");
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pelanggan = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        idpel = new javax.swing.JTextField();
        nm = new javax.swing.JTextField();
        almt = new javax.swing.JTextField();
        telp = new javax.swing.JTextField();
        jk = new javax.swing.JComboBox<>();
        clear = new javax.swing.JButton();
        tambah = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        cari = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        cr = new javax.swing.JTextField();
        kembali1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Pelanggan"));

        pelanggan.setModel(new javax.swing.table.DefaultTableModel(
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
        pelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pelangganMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(pelanggan);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 985, 160));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Form Pelanggan"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText(" ID Pelanggan");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 28, 80, 30));

        jLabel2.setText("Nama");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 76, 70, -1));

        jLabel3.setText("Alamat");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 70, 20));

        jLabel4.setText("Telepon");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 161, 73, 20));

        jLabel5.setText("Jenis Kelamin");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 200, -1, 20));

        idpel.setEditable(false);
        idpel.setText(" ");
        jPanel4.add(idpel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 95, 30));

        nm.setText(" ");
        jPanel4.add(nm, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 275, 30));

        almt.setText(" ");
        jPanel4.add(almt, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 116, 275, 30));

        telp.setText(" ");
        jPanel4.add(telp, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 275, 30));

        jk.setEditable(true);
        jk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "P", "L" }));
        jPanel4.add(jk, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, -1, 30));

        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel4.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 180, 71, 30));

        tambah.setText("Tambah");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });
        jPanel4.add(tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, -1, 30));

        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        jPanel4.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, 71, 30));

        hapus.setText("Delete");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        jPanel4.add(hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 130, 71, 30));

        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        jPanel4.add(cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 30, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 383, 985, 250));

        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        jPanel1.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 24, 30));

        jButton1.setText("cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, -1, 30));
        jPanel1.add(cr, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 170, 30));

        kembali1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ITutup.png"))); // NOI18N
        kembali1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembali1ActionPerformed(evt);
            }
        });
        jPanel1.add(kembali1, new org.netbeans.lib.awtextra.AbsoluteConstraints(954, 11, 30, 30));

        jLabel8.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        jLabel8.setText("PELANGGAN");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 160, 60));

        jLabel6.setText("Berdasarkan ID pel/ Nama pel");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 170, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/form.png"))); // NOI18N
        jLabel7.setToolTipText("");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1005, 635));

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

    private void pelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pelangganMouseClicked
        // TODO add your handling code here:
        int baris=pelanggan.rowAtPoint(evt.getPoint());
        String id_pel=pelanggan.getValueAt(baris,0).toString();
        idpel.setText(id_pel);
        String nama_pel=pelanggan.getValueAt(baris,1).toString();
        nm.setText(nama_pel);
        String alamat=pelanggan.getValueAt(baris,2).toString();
        almt.setText(alamat);
        String tlp=pelanggan.getValueAt(baris,3).toString();
        telp.setText(tlp);
        String jenis_kelamin=pelanggan.getValueAt(baris,4).toString();
        jk.setSelectedItem(jenis_kelamin);
        
    }//GEN-LAST:event_pelangganMouseClicked

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

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
        noID();
    }//GEN-LAST:event_cariActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
        getData();
        cr.setText(null);
    }//GEN-LAST:event_refreshActionPerformed

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
            java.util.logging.Logger.getLogger(pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField almt;
    private javax.swing.JButton cari;
    private javax.swing.JButton clear;
    private javax.swing.JTextField cr;
    private javax.swing.JButton edit;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField idpel;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JComboBox<String> jk;
    private javax.swing.JButton kembali1;
    private javax.swing.JTextField nm;
    private javax.swing.JTable pelanggan;
    private javax.swing.JButton refresh;
    private javax.swing.JButton tambah;
    private javax.swing.JTextField telp;
    // End of variables declaration//GEN-END:variables
}
