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
public class penerimaan extends javax.swing.JFrame {

    
    
    public String dateChooser;
    private final DefaultTableModel model;
    Connection kon;
    
    public penerimaan() {
        initComponents();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        tglM.setText(dateFormat.format(cal.getTime()));
        this.setLocationRelativeTo(null);
        model = new DefaultTableModel ( );
        penerimaan.setModel(model);
             model.addColumn("No Order");
             model.addColumn("ID");
             model.addColumn("Pelanggan");
             model.addColumn("Tanggal Masuk");
             model.addColumn("Tanggal Keluar");
             model.addColumn("Total Bayar");
             model.addColumn("Bayar");
             model.addColumn("Sisa");
             model.addColumn("Pengerjaan ");
             model.addColumn("Status");
             model.addColumn("ID User");
             getData();    
    }
    
    public void getData( ){
     
     model.getDataVector( ).removeAllElements( );
     model.fireTableDataChanged( );
     kon =koneksi.koneksiDb();
     try{
           
//membuat statemen untuk memanggil data table penerimaan
              
           Statement stat = kon.createStatement( );
           String sql     = "Select * from penerimaan";
           ResultSet res  = stat.executeQuery(sql);
     
//pengecekan terhadap data penerimaan
           while(res.next ()){
                Object[] obj = new Object[11];
                obj[0] = res.getString("no_order");
                obj[1] = res.getString("id_pel");
                obj[2] = res.getString("nama_pel");
                obj[3] = res.getString("tgl_masuk");
                obj[4] = res.getString("tgl_keluar");
                obj[5] = res.getString("t_bayar");
                obj[6] = res.getString("bayar");
                obj[7] = res.getString("sisa");
                obj[8] = res.getString("status");
                obj[9] = res.getString("dibayar");
                obj[10] = res.getString("id_user");
                model.addRow(obj);
            }
      }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage() );
      }      
    }

    public void tambahData( ){
          kon=koneksi.koneksiDb();
          String tampilan = "yyyy-MM-dd";
          SimpleDateFormat fm = new SimpleDateFormat (tampilan);
          String tanggal = String.valueOf(fm.format(tglK.getDate()));
          
          
                  
        try {
           Statement stat = kon.createStatement( );
           String sql     = "insert into penerimaan values ('"+order.getText()+"','"+idpel.getText()+"','"+nmPel.getText()+"','"+tglM.getText()+"','"+tanggal+"','"+total.getText()+"','"+byr.getText()+"','"+ss.getText()+"','"+jComboBox1.getSelectedItem()+"','"+dby.getSelectedItem()+"','"+id.getText()+"')";
           PreparedStatement pst  = kon.prepareStatement(sql);
           noID();
           pst.execute();  
          JOptionPane.showMessageDialog(null,"Data Berhasil Ditambah");
          
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        getData();
    }
    
    public void tambahData2( ){
          kon=koneksi.koneksiDb();
        try {
           Statement stat = kon.createStatement( );
           String sql2    = "insert into detail_penerimaan values('"+order.getText()+"','"+kd.getText()+"','"+jenis.getText()+"','"+hrg.getText()+"','"+brt.getText()+"','"+jml.getText()+"','"+ket.getText()+"','"+jComboBox1.getSelectedItem()+"')";
           PreparedStatement pst  = kon.prepareStatement(sql2);
           noID();
           pst.execute();
          JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        getData();
    }
      public void noID(){
          kon=koneksi.koneksiDb();
        try {
           Statement stat = kon.createStatement( );
           String sql     = "Select * from penerimaan order by no_order desc";
           ResultSet res  = stat.executeQuery(sql);
            
            if(res.next()){
                String noId = res.getString("no_order").substring (1);
                String AN = ""+(Integer.parseInt(noId)+1);
                String Nol = "";
                
                if(AN.length( )==1){
                    Nol="00";
                }else if(AN.length( )==2){
                    Nol="0";
                }else if(AN.length( )==3){
                    Nol=" ";
                } order.setText ("N"+Nol+AN);
            }else{ 
                order.setText ("N001");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
      }
      
      public void hapusData( ){
        kon=koneksi.koneksiDb();
        try {
           Statement stat = kon.createStatement( );
           String sql     = "Delete from penerimaan where no_order='" + order.getText() + "'";
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
            String sql="UPDATE penerimaan SET status='"+jComboBox1.getSelectedItem()+"' WHERE no_order='"+order.getText()+"'";
            PreparedStatement pst=kon.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Data Berhasil Diedit");  
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Perubahan Data Gagal"+e.getMessage());  
        }getData();
        hapus();
    }
      
      private void hapus() {
        order.setText(null);
        idpel.setText(null);
        nmPel.setText(null);
        ket.setText(null);
        kd.setText(null);
        jenis.setText(null);
        hrg.setText(null);
        jComboBox1.setSelectedItem(null);
        brt.setText(null);
        jml.setText(null);       
        tglK.setDate(null);
        total.setText(null);
        byr.setText(null);
        ss.setText(null);
        dby.setSelectedItem(null);
        id.setText(null);
    }
      
      public void cariData( ){
     
     model.getDataVector( ).removeAllElements( );
     model.fireTableDataChanged( );
     kon =koneksi.koneksiDb();
     
     try{
//membuat statemen untuk memanggil data table penerimaan 
              
           Statement stat = kon.createStatement( );
           String sql     = "Select * from penerimaan where no_order like'%"+cr.getText()+"%' or nama_pel like'%"+cr.getText()+"%' or id_pel like'%"+cr.getText()+"%'";
           ResultSet res  = stat.executeQuery(sql);
     
//pengecekan terhadap data penerimaan
           while(res.next ()){
                Object[] obj = new Object[11];
                obj[0] = res.getString("no_order");
                obj[1] = res.getString("id_pel");
                obj[2] = res.getString("nama_pel");
                obj[3] = res.getString("tgl_masuk");
                obj[4] = res.getString("tgl_keluar");
                obj[5] = res.getString("t_bayar");
                obj[6] = res.getString("bayar");
                obj[7] = res.getString("sisa");
                obj[8] = res.getString("status");
                obj[9] = res.getString("dibayar");
                obj[10] = res.getString("id_user");
                model.addRow(obj);
            }
      }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage() );
      }   
    }
    
    public String idPel, namaPel;
        public String getIdPel(){
            return idPel;
        }
        public String getNamaPel(){
            return namaPel;
        }
    public void idTerpilih(){
        LDataPel ldp = new LDataPel();
        ldp.pnm=this;
        idpel.setText(idPel);
        nmPel.setText(namaPel);
    }
    
    public String kdCuci, jCuci, hrgCuci;
        public String getKdCuci(){
            return kdCuci;
        }
        public String getJCuci(){
            return jCuci;
        }
        public String getHrgCuci(){
            return hrgCuci;
        }
    public void KdTerpilih(){
        LJenisCuci ljc = new LJenisCuci();
        ljc.pnm=this;
        kd.setText(kdCuci);
        jenis.setText(jCuci);
        hrg.setText(hrgCuci);
    }
    
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        penerimaan = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        idpel = new javax.swing.JTextField();
        jml = new javax.swing.JTextField();
        hrg = new javax.swing.JTextField();
        jenis = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        brt = new javax.swing.JTextField();
        kd = new javax.swing.JTextField();
        order = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        byr = new javax.swing.JTextField();
        ss = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        ket = new javax.swing.JTextArea();
        tglK = new com.toedter.calendar.JDateChooser();
        clear = new javax.swing.JButton();
        nmPel = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tambah = new javax.swing.JButton();
        editPengerjaan = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        tglM = new javax.swing.JTextField();
        simpan = new javax.swing.JButton();
        dby = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        cr = new javax.swing.JTextField();
        kembali = new javax.swing.JButton();
        detail = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Penerimaan"));

        penerimaan.setModel(new javax.swing.table.DefaultTableModel(
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
        penerimaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                penerimaanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(penerimaan);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 990, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Form Penerimaan"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("No Order");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 80, 30));

        jLabel2.setText("Pengerjaan");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 100, 30));

        jLabel3.setText("KD Jenis");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 90, 30));

        jLabel4.setText("Jenis Cucian");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 80, 30));

        jLabel5.setText("Harga");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 80, 30));

        idpel.setEditable(false);
        idpel.setText(" ");
        jPanel4.add(idpel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 95, 30));

        jml.setText(" ");
        jPanel4.add(jml, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 100, 30));

        hrg.setEditable(false);
        hrg.setText(" ");
        jPanel4.add(hrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 130, 30));

        jenis.setEditable(false);
        jenis.setText(" ");
        jPanel4.add(jenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 130, 30));

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 30, 30));

        jLabel6.setText("Berat");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 60, 30));

        jLabel15.setText("Nama");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 90, 30));

        jLabel7.setText("Keterangan ");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 80, 30));

        jLabel9.setText("Jumlah");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 60, 30));

        jLabel10.setText("Status");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 230, 70, 30));

        jLabel12.setText("Bayar");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, 70, 30));

        total.setEditable(false);
        total.setText(" ");
        jPanel4.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, 140, 30));

        brt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                brtKeyReleased(evt);
            }
        });
        jPanel4.add(brt, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 100, 30));

        kd.setEditable(false);
        kd.setText(" ");
        jPanel4.add(kd, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 95, 30));

        order.setText(" ");
        jPanel4.add(order, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 95, 30));

        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 30, 30));

        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 30, 30));

        jLabel14.setText(" KG");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 40, 30));

        jComboBox1.setEditable(true);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "baru", "proses", "selesai", "diambil" }));
        jPanel4.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 230, 130, 30));

        jLabel16.setText("PCS");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 30, 30));

        byr.setText(" ");
        byr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                byrKeyReleased(evt);
            }
        });
        jPanel4.add(byr, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 150, 140, 30));

        ss.setEditable(false);
        ss.setText(" ");
        jPanel4.add(ss, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 190, 140, 30));

        ket.setColumns(20);
        ket.setRows(5);
        jScrollPane2.setViewportView(ket);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 130, 70));
        jPanel4.add(tglK, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 140, 30));

        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel4.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 220, 70, 30));

        nmPel.setEditable(false);
        jPanel4.add(nmPel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 130, 30));

        jLabel17.setText("ID Pelanggan");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 90, 30));

        jLabel11.setText("Total");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, 100, 30));

        jLabel13.setText("Sisa");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 70, 30));

        tambah.setText("Tambah");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });
        jPanel4.add(tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 100, -1, 30));

        editPengerjaan.setText("Edit");
        editPengerjaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPengerjaanActionPerformed(evt);
            }
        });
        jPanel4.add(editPengerjaan, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 160, 71, 30));

        jLabel18.setText("ID User");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 100, 30));

        tglM.setEditable(false);
        tglM.setText(" ");
        jPanel4.add(tglM, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 140, 30));

        simpan.setText("Simpan");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });
        jPanel4.add(simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 40, -1, 30));

        dby.setEditable(true);
        dby.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Belum Bayar", "Bayar" }));
        jPanel4.add(dby, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 230, 140, 30));

        jLabel20.setText("Estimasi Keluar");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, 110, 30));

        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });
        jPanel4.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 20, 130, 30));

        jLabel21.setText("Tanggal  Masuk");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 110, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 990, 280));
        jPanel1.add(cr, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 135, 30));

        kembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ITutup.png"))); // NOI18N
        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
            }
        });
        jPanel1.add(kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(954, 11, 30, 30));

        detail.setText("Detail");
        detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailActionPerformed(evt);
            }
        });
        jPanel1.add(detail, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 130, 70, 40));

        jButton1.setText("cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, -1, 30));

        jButton2.setText("CETAK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 130, -1, 40));

        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 25, 30));

        jLabel19.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        jLabel19.setText("ORDER MASUK");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 180, 60));

        jLabel22.setText("Berdasarkan Nama Pelanggan/No Order");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 230, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/form.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 635));

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

    private void penerimaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_penerimaanMouseClicked
        // TODO add your handling code here:
        int baris=penerimaan.rowAtPoint(evt.getPoint());
        String no_order=penerimaan.getValueAt(baris,0).toString();
        order.setText(no_order);
        String nama_pel=penerimaan.getValueAt(baris,2).toString();
        nmPel.setText(nama_pel);
        String id_pel=penerimaan.getValueAt(baris,1).toString();
        idpel.setText(id_pel);
        String tgl_masuk=penerimaan.getValueAt(baris,3).toString();
        tglM.setText(tgl_masuk);
        String status=penerimaan.getValueAt(baris,8).toString();
        jComboBox1.setSelectedItem(status);
        String dibayar=penerimaan.getValueAt(baris,9).toString();
        dby.setSelectedItem(dibayar);
        String id_user=penerimaan.getValueAt(baris,10).toString();
        id.setText(id_user);
    }//GEN-LAST:event_penerimaanMouseClicked

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_kembaliActionPerformed

    private void editPengerjaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPengerjaanActionPerformed
        // TODO add your handling code here:
        editData();
    }//GEN-LAST:event_editPengerjaanActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here:
        tambahData();
        
    }//GEN-LAST:event_tambahActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        hapus();
    }//GEN-LAST:event_clearActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
        LDataPel ldp = new LDataPel ();
        ldp.pnm=this;

        ldp.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        LJenisCuci ljc = new LJenisCuci ();
        ljc.pnm=this;
        ljc.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        noID();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void detailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailActionPerformed
        // TODO add your handling code here:
        new detailPenerimaan ().setVisible(true); 
    }//GEN-LAST:event_detailActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
        tambahData2();
    }//GEN-LAST:event_simpanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cariData();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void brtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_brtKeyReleased
        // TODO add your handling code here:
        String h=hrg.getText();
        String br=brt.getText();

        float nominalH = Float.valueOf(h);
        float nominalBr= Float.valueOf(br);
        float nominalT= nominalH*nominalBr;
        total.setText(Float.toString(nominalT));
    }//GEN-LAST:event_brtKeyReleased

    private void byrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_byrKeyReleased
        // TODO add your handling code here:
        String b=byr.getText();
        String t=total.getText();

        float nominalB = Float.valueOf(b);
        float nominalT = Float.valueOf(t);
        float sisa= nominalB-nominalT;
        ss.setText(Float.toString(sisa));
    }//GEN-LAST:event_byrKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        kon=koneksi.koneksiDb();
        int confirm=JOptionPane.showConfirmDialog(null,"Cetak Nota Order?","Cetak", JOptionPane.YES_NO_CANCEL_OPTION);
            if (confirm== JOptionPane.YES_OPTION ){
        try {

  String path="src/laporan/strukOrder2.jasper";      // letak penyimpanan report

  HashMap parameter = new HashMap();

  parameter.put("order",order.getText());   
  parameter.put("id",id.getText());
  
  JasperPrint print = JasperFillManager.fillReport(path,parameter,kon);

  JasperViewer.viewReport(print, false);

    } catch (Exception ex) {

  JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada "+ex);

    }
    
            }else{JOptionPane.showMessageDialog(null,"Data Disimpan") ;}
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        getData();
        cr.setText(null);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed

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
            java.util.logging.Logger.getLogger(penerimaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(penerimaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(penerimaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(penerimaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new penerimaan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField brt;
    private javax.swing.JTextField byr;
    private javax.swing.JButton clear;
    private javax.swing.JTextField cr;
    private javax.swing.JComboBox<String> dby;
    private javax.swing.JButton detail;
    private javax.swing.JButton editPengerjaan;
    public javax.swing.JTextField hrg;
    private javax.swing.JTextField id;
    public javax.swing.JTextField idpel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
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
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextField jenis;
    private javax.swing.JTextField jml;
    public javax.swing.JTextField kd;
    private javax.swing.JButton kembali;
    private javax.swing.JTextArea ket;
    private javax.swing.JTextField nmPel;
    private javax.swing.JTextField order;
    private javax.swing.JTable penerimaan;
    private javax.swing.JButton simpan;
    private javax.swing.JTextField ss;
    private javax.swing.JButton tambah;
    private com.toedter.calendar.JDateChooser tglK;
    private javax.swing.JTextField tglM;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
}
