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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Lenovo
 */
public class transaksi extends javax.swing.JFrame {
    private DefaultTableModel model;
    Connection kon;
    /**
     * Creates new form transaksi
     */
    public transaksi() {
        initComponents();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        tglTr.setText(dateFormat.format(cal.getTime()));
        this.setLocationRelativeTo(null);
        model = new DefaultTableModel ( );
        transaksi.setModel(model);
             model.addColumn("No Transaksi");
             model.addColumn("Tanggal Transaksi");
             model.addColumn("No Order");
             model.addColumn("Dibayar");
             model.addColumn("Kembalian");
             model.addColumn("ID User");
             getData();  
    }
    
    public void getData( ){
     
     model.getDataVector( ).removeAllElements( );
     model.fireTableDataChanged( );
     kon =koneksi.koneksiDb();
     try{
           
//membuat statemen untuk memanggil data table transaksi2
              
           Statement stat = kon.createStatement( );
           String sql     = "Select * from transaksi";
           ResultSet res  = stat.executeQuery(sql);
     
//pengecekan terhadap data transaksi2
           while(res.next ()){
                Object[] obj = new Object[6];
                obj[0] = res.getString("no_transaksi");
                obj[1] = res.getString("tgl_transaksi");
                obj[2] = res.getString("no_order");
                obj[3] = res.getString("dibayar");
                obj[4] = res.getString("kembalian");
                obj[5] = res.getString("id_user");
                model.addRow(obj);
            }
      }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage() );
      }      
    }
    
    public void noID(){
          kon=koneksi.koneksiDb();
        try {
           Statement stat = kon.createStatement( );
           String sql     = "Select * from transaksi order by no_transaksi desc";
           ResultSet res  = stat.executeQuery(sql);
            
            if(res.next()){
                String noId = res.getString("no_transaksi").substring (1);
                String AN = ""+(Integer.parseInt(noId)+1);
                String Nol = "";
                
                if(AN.length( )==1){
                    Nol="00";
                }else if(AN.length( )==2){
                    Nol="0";
                }else if(AN.length( )==3){
                    Nol=" ";
                } idTr.setText ("T"+Nol+AN);
            }else{ 
                idTr.setText ("T001");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
      }
    
    public void hapusData( ){
        kon=koneksi.koneksiDb();
        try {
           Statement stat = kon.createStatement( );
           String sql     = "Delete from transaksi where no_transaksi='"+idTr.getText()+"'";
           PreparedStatement pst  = kon.prepareStatement(sql);
           pst.execute();  
           
           JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }getData();
        hapus();
    }
    
    public void tambahData( ){
          kon=koneksi.koneksiDb();
          
        try {
            Statement stat = kon.createStatement();
            String sql = "insert into transaksi values('" + idTr.getText() + "','" + tglTr.getText() + "','" + order1.getText() + "','" + byrSS.getText() + "','" + backSS.getText() + "','" + id.getText() + "')";
            PreparedStatement pst = kon.prepareStatement(sql);
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Penambahan Data Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        getData();
    }
    
    
      public void editData( ){
        kon=koneksi.koneksiDb();
        try{
            Statement stat = kon.createStatement( );
            String sql="UPDATE penerimaan SET dibayar='"+dby.getSelectedItem()+"'WHERE no_order='"+order1.getText()+"'";
            PreparedStatement pst=kon.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");  
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Perubahan Data Gagal"+e.getMessage());  
        }getData();
    }
      
      private void hapus() {
        idTr.setText(null);
        order1.setText(null);
        nmPel.setText(null);
        total.setText(null);
        byr.setText(null);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        tglTr.setText(dateFormat.format(cal.getTime()));
        dby.setSelectedItem(null);
        ss.setText(null);
        byrSS.setText(null);
        backSS.setText(null);
    }
      
      public void cariData( ){
     
     model.getDataVector( ).removeAllElements( );
     model.fireTableDataChanged( );
     kon =koneksi.koneksiDb();
     
     try{
//membuat statemen untuk memanggil data table transaksi
              
           Statement stat = kon.createStatement( );
           String sql     = "Select * from transaksi where no_order like'%"+cr.getText()+"%' or no_transaksi like'%"+cr.getText()+"%'";
           ResultSet res  = stat.executeQuery(sql);
     
//pengecekan terhadap data transaksi2
           while(res.next ()){
                Object[] obj = new Object[6];
                obj[0] = res.getString("no_transaksi");
                obj[1] = res.getString("tgl_transaksi");
                obj[2] = res.getString("no_order");
                obj[3] = res.getString("dibayar");
                obj[4] = res.getString("kembalian");
                obj[5] = res.getString("id_user");
                model.addRow(obj);
            }
      }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage() );
      }   
    }
    
    public String noOr, namaPel,subTotal,byrDp,sisa,status;
        public String getNoOr(){
            return noOr;
        }
        public String getNamaPel(){
            return namaPel;
        }
        public String getSubTotal(){
            return subTotal;
        }
        public String getByrDp(){
            return byrDp;
        }
        public String getSisa(){
            return sisa;
        }
        public String getStatus(){
            return status;
        }
    public void noTerpilih(){
        LDataPnm ldpn = new LDataPnm();
        ldpn.trs=this;
        order1.setText(noOr);
        nmPel.setText(namaPel);
        total.setText(subTotal);
        byr.setText(byrDp);
        ss.setText(sisa);        
        dby.setSelectedItem(status);
    }
    
    public void print(){
        kon =koneksi.koneksiDb();
        try {

  String path="src/laporan/strukTransaksi2.jasper";      // letak penyimpanan report

  HashMap parameter = new HashMap(2);

  parameter.put("transaksi",idTr.getText());    
  parameter.put("user",id.getText());  

  JasperPrint print = JasperFillManager.fillReport(path,parameter,kon);

  JasperViewer.viewReport(print, false);

    } catch (Exception ex) {

  JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada "+ex);

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
        transaksi = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nmPel = new javax.swing.JTextField();
        backSS = new javax.swing.JTextField();
        byrSS = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        idTr = new javax.swing.JTextField();
        byr = new javax.swing.JTextField();
        ss = new javax.swing.JTextField();
        order1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        tglTr = new javax.swing.JTextField();
        dby = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        tambah = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        cr = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        kembali = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(1005, 635));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Transaksi"));

        transaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transaksiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                transaksiMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(transaksi);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 990, 180));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Form Penerimaan"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("No Order");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 90, 30));

        jLabel4.setText("No Transaksi");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 110, 30));

        jLabel5.setText("Dibayar");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, 110, 30));

        nmPel.setEditable(false);
        nmPel.setText(" ");
        jPanel4.add(nmPel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 130, 30));

        backSS.setEditable(false);
        backSS.setText(" ");
        jPanel4.add(backSS, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, 140, 30));

        byrSS.setText(" ");
        byrSS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                byrSSKeyReleased(evt);
            }
        });
        jPanel4.add(byrSS, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 140, 30));

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 30, 30));

        jLabel15.setText("Pelanggan");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 100, 30));

        jLabel8.setText("Tanggal Transaksi");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 110, 30));

        jLabel9.setText("Kembali");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 110, 30));

        jLabel10.setText("Status");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, 110, 30));

        jLabel11.setText("Sisa");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 110, 30));

        jLabel12.setText(" Bayar");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 60, 30));

        jLabel13.setText("Total Bayar");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 90, 30));

        total.setEditable(false);
        total.setText(" ");
        jPanel4.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 130, 30));

        idTr.setEditable(false);
        idTr.setText(" ");
        jPanel4.add(idTr, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 95, 30));

        byr.setEditable(false);
        byr.setText(" ");
        jPanel4.add(byr, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 130, 30));

        ss.setEditable(false);
        ss.setText(" ");
        ss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ssActionPerformed(evt);
            }
        });
        jPanel4.add(ss, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, 140, 30));

        order1.setEditable(false);
        order1.setText(" ");
        jPanel4.add(order1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 95, 30));

        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 30, 30));

        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel4.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 190, 70, 30));

        tglTr.setEditable(false);
        tglTr.setText(" ");
        jPanel4.add(tglTr, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 140, 30));

        dby.setEditable(true);
        dby.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Belum Bayar", "Bayar" }));
        jPanel4.add(dby, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 190, 140, 30));

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 30, 90, 30));

        tambah.setText("Tambah");
        tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahMouseClicked(evt);
            }
        });
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });
        jPanel4.add(tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 90, -1, 30));

        hapus.setText("Delete");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        jPanel4.add(hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 140, 71, 30));

        jLabel7.setText("ID User ");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 120, 20));
        jPanel4.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 100, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 990, 247));
        jPanel1.add(cr, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 150, 30));

        jButton2.setText("cari");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, -1, 30));

        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 25, 30));

        jButton6.setText("CETAK");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 170, -1, 34));

        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        jLabel3.setText("TRANSAKSI");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 150, 60));

        kembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ITutup.png"))); // NOI18N
        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
            }
        });
        jPanel1.add(kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(954, 11, 30, 30));

        jLabel6.setText("Berdasarkan No transaksi/No Order");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 230, -1));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/form.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1005, 635));

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

    private void transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaksiMouseClicked
        // TODO add your handling code here:
        int baris=transaksi.rowAtPoint(evt.getPoint());
        String no_transaksi=transaksi.getValueAt(baris,0).toString();
        idTr.setText(no_transaksi);
        String tgl_transaksi=transaksi.getValueAt(baris,1).toString();
        tglTr.setText(tgl_transaksi);
        String no_order=transaksi.getValueAt(baris,2).toString();
        order1.setText(no_order);
        String id_user=transaksi.getValueAt(baris,5).toString();
        id.setText(id_user);
    }//GEN-LAST:event_transaksiMouseClicked

    private void transaksiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaksiMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_transaksiMouseEntered

    private void byrSSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_byrSSKeyReleased
        // TODO add your handling code here:
        String b=ss.getText();
        String t=byrSS.getText();

        float nominalB = Float.valueOf(b);
        float nominalT = Float.valueOf(t);
        float nominalSisa= nominalB+nominalT;
        backSS.setText(Float.toString(nominalSisa));
    }//GEN-LAST:event_byrSSKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        noID();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void ssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ssActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ssActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        LDataPnm ldpn = new LDataPnm ();
        ldpn.trs=this;
        ldpn.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        hapus();
    }//GEN-LAST:event_clearActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
        hapusData();
    }//GEN-LAST:event_hapusActionPerformed

    private void tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tambahMouseClicked

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here:
        tambahData();
        
    }//GEN-LAST:event_tambahActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        editData();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cariData();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        getData();
        cr.setText(null);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        kon=koneksi.koneksiDb();
        int confirm=JOptionPane.showConfirmDialog(null,"Cetak Nota Transaksi?","Cetak", JOptionPane.YES_NO_CANCEL_OPTION);
            if (confirm== JOptionPane.YES_OPTION ){
                print();
    
            }else{JOptionPane.showMessageDialog(null,"Data Disimpan") ;}
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_kembaliActionPerformed

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
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField backSS;
    private javax.swing.JTextField byr;
    private javax.swing.JTextField byrSS;
    private javax.swing.JButton clear;
    private javax.swing.JTextField cr;
    private javax.swing.JComboBox<String> dby;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField id;
    private javax.swing.JTextField idTr;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kembali;
    private javax.swing.JTextField nmPel;
    private javax.swing.JTextField order1;
    private javax.swing.JTextField ss;
    private javax.swing.JButton tambah;
    private javax.swing.JTextField tglTr;
    private javax.swing.JTextField total;
    private javax.swing.JTable transaksi;
    // End of variables declaration//GEN-END:variables
}
