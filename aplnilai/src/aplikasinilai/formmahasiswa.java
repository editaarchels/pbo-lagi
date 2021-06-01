/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasinilai;
import aplikasinilai.Koneksi;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author asus
 */
public class formmahasiswa extends javax.swing.JFrame {
    private String sql;
    private PreparedStatement ps=null;
    public String tanggal;
    private Object cdltgllahir;
    
    
    public formmahasiswa() {
       initComponents();
       tampilanData ();
    }
    private void tampilanData(){
    try {
      String sql="SELECT * FROM tb_mahasiswa";
      PreparedStatement ps=null;
      Object [] row={"NIM","Nama Mahasiswa","Tempat lahir","Tanggal Lahir"
              ,"jenis kelamin", "Alamat"};
      DefaultTableModel dtm=new DefaultTableModel (null,row);
      tabelmhs.setModel (dtm);
      jScrollPane1.setEnabled(true);
      jScrollPane1.setBorder(null);
      jScrollPane1.setViewportView(tabelmhs);
    ResultSet rs=null;
        ps=Koneksi.KoneksiDatabase().prepareStatement(sql);
        rs=ps.executeQuery();
        while(rs.next()){
            String nim=rs.getString(1);
            String namamahasiswa=rs.getString(2);
            String tempatlahir=rs.getString(3);
            String tgllahir=rs.getString(4);
            String jeniskelamin=rs.getString(5);
            String alamat=rs.getString(6);
            String [] baris={nim,namamahasiswa,tempatlahir,tgllahir,jeniskelamin,alamat};
            dtm.addRow(baris);
        }
        }catch(Exception e){}
    }
    
    private void bersih(){
        txtnim.setText("");
        txtnamamhs.setText("");
        txttempatlahir.setText("");
        cmbjk.setSelectedItem("--Pilih jenis kelamin--");
        txtalamat.setText("");
        txtnim.setEnabled(true);
    }
       
    private void simpan() throws SQLException{
        try{
            if((txtnim.getText().equals(""))
              ||((txtnamamhs.getText().equals(""))
              ||(txttempatlahir.getText().equals(""))
              ||(cmbjk.getSelectedItem().equals("--Pilih jenis Kelamin--"))
              ||(txtalamat.getText().equals(""))))
            {
                JOptionPane.showMessageDialog(rootPane,"Lengkapi Data dengan benar !");
            }
            else
            {
                String sqlA="select*from tb_mahasiswa where nim='"+txtnim.getText()+"'";
                ps=Koneksi.KoneksiDatabase().prepareStatement(sqlA);
                ResultSet rs=ps.executeQuery(sqlA);
                System.out.println(rs.first());
                if(rs.first()==true)
                {
                    JOptionPane.showMessageDialog(rootPane,"Data Sudah Ada!");
                }
                else
                {
                    String sqlB="insert into tb_mahasiswa(nim,nama_mahasiswa,tempat_lahir,tgl_lahir,jk,alamat)values"+
                    "('"+txtnim.getText()+
                    "','"+txtnamamhs.getText()+
                    "','"+txttempatlahir.getText()+
                    "','"+tanggal+
                    "','"+cmbjk.getSelectedItem()+
                    "','"+txtalamat.getText()+"')";
                   ps=Koneksi.KoneksiDatabase().prepareStatement(sqlB);
                   ps.executeUpdate(sqlB);
                   JOptionPane.showMessageDialog(rootPane,"Data berhasil di simpan!");
                   tampilanData();
                   bersih();
                }  
            }
            }catch(Exception e)
                    {
                    JOptionPane.showMessageDialog(rootPane,"Cek lagi sistem!"+e.getMessage());
                    }
        }
    
    private void hapus() throws SQLException{
        try{
            if((txtnim.getText().equals(""))
            ||((txtnamamhs.getText().equals(""))
            ||(txttempatlahir.getText().equals(""))
            ||(cmbjk.getSelectedItem().equals("--Pilih jenis kelamin--"))
            ||(txtalamat.getText().equals(""))))
            {
                JOptionPane.showMessageDialog(rootPane,"pilih data yang akan dihapus");
            }
            else
            {
               PreparedStatement ps=null;
               String sql="Delete From tb_mahasiswa where nim='"+txtnim.getText()+"'";
               ps=Koneksi.KoneksiDatabase().prepareStatement(sql);
               ps.executeUpdate();
               JOptionPane.showMessageDialog(rootPane,"Data Berhasil dihapus");
               tampilanData();
               bersih();
               
            }
        }catch(Exception e)
        {}
}
        
    private void ambildatadaritabel(){
        int row = tabelmhs.getSelectedRow();
        txtnim.setText(tabelmhs.getModel ().getValueAt(row,0). toString());
        txtnamamhs.setText(tabelmhs.getModel ().getValueAt(row,1). toString());
        txttempatlahir.setText(tabelmhs.getModel ().getValueAt(row,2). toString());
        
        //khusus tanggal
        String tgl = (String) tabelmhs.getModel().getValueAt(row,3);
        try{
            SimpleDateFormat format= new SimpleDateFormat("yyy-MM-dd");
            java.util.Date d = format.parse(tgl);
        }catch(ParseException ex){}
        
        cmbjk.setSelectedItem(tabelmhs.getModel().getValueAt(row,4).toString());
        txtalamat.setText(tabelmhs.getModel().getValueAt(row,5).toString());
        txtnim.setEnabled(false);
        }
    
     private void caridata(){
        try {
      String search = txtcaridata.getText();
      String sql="SELECT * FROM tb_mahasiswa WHERE nim like'%"+search+"%'OR nama_mahasiswa like'%"+search+"%'";
      PreparedStatement ps=null;
      Object [] row={"NIM","Nama Mahasiswa","Tempat lahir","Tanggal Lahir"
       ,"jenis kelamin", "Alamat"};
      DefaultTableModel dtm=new DefaultTableModel (null,row);
      tabelmhs.setModel (dtm);
      jScrollPane1.setEnabled(true);
      jScrollPane1.setBorder(null);
      jScrollPane1.setViewportView(tabelmhs);
    ResultSet rs=null;
        ps=Koneksi.KoneksiDatabase().prepareStatement(sql);
        rs=ps.executeQuery();
        while(rs.next()){
            String nim=rs.getString(1);
            String namamahasiswa=rs.getString(2);
            String tempatlahir=rs.getString(3);
            String tgllahir=rs.getString(4);
            String jeniskelamin=rs.getString(5);
            String alamat=rs.getString(6);
            String [] baris={nim,namamahasiswa,tempatlahir,tgllahir,jeniskelamin,alamat};
            dtm.addRow(baris);
        }
        }catch(Exception e){
        JOptionPane.showMessageDialog(rootPane,"Data yang anda cari tidak dapat ditemukan,"+ e.getMessage());
        //system.err.printIn("error(search):"+e);
        }
    }
    
    private void edit () throws SQLException{
        try{
            if((txtnim.getText().equals(""))
            ||((txtnamamhs.getText().equals(""))
            ||(txttempatlahir.getText().equals(""))
            ||(cmbjk.getSelectedItem().equals("--Pilih jenis kelamin--"))
            ||(txtalamat.getText().equals(""))))
            {
                JOptionPane.showMessageDialog(rootPane,"Silahkan pilih data yang akan di edit");
            }
            else
                
                sql="UPDATE tb_mahasiswa SET nama_mahasiswa='"+txtnamamhs.getText()+
                    "',tempat_lahir='"+txttempatlahir.getText()+
                    "',tgl_lahir='"+tanggal+
                    "',jk='"+cmbjk.getSelectedItem()+
                    "',alamat='"+txtalamat.getText()+
                    "'WHERE nim='"+txtnim.getText()+"'";
            
            ps=Koneksi.KoneksiDatabase().prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Data Berhasil Di edit");
            tampilanData();
            bersih();
            }catch (Exception e){
                JOptionPane.showMessageDialog(rootPane,"Data Gagal di edit"+e.getMessage());
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtalamat = new javax.swing.JTextField();
        txtnim = new javax.swing.JTextField();
        txtcaridata = new javax.swing.JTextField();
        txttempatlahir = new javax.swing.JTextField();
        cmbjk = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelmhs = new javax.swing.JTable();
        btnkeluar = new javax.swing.JButton();
        btnrefresh = new javax.swing.JButton();
        btnsimpan = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtnamamhs = new javax.swing.JTextField();
        cldtgllahir = new com.toedter.calendar.JCalendar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Form Input Data Mahasiswa");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 0));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Pencarian Data Mahasiswa : ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Alamat");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Form Input Data Mahasiswa");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 360, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Nama Mahasiswa");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Tempat Lahir");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Tempat Lahir");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Tanggal lahir ");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Jenis kelamin");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, -1, -1));

        txtalamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtalamatActionPerformed(evt);
            }
        });
        getContentPane().add(txtalamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 550, 270, -1));

        txtnim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnimActionPerformed(evt);
            }
        });
        getContentPane().add(txtnim, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 150, -1));

        txtcaridata.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcaridataKeyPressed(evt);
            }
        });
        getContentPane().add(txtcaridata, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 270, -1));
        getContentPane().add(txttempatlahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 150, -1));

        cmbjk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih jenis kelamin--", "Laki-laki", "Perempuan" }));
        getContentPane().add(cmbjk, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 500, -1, -1));

        tabelmhs.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelmhs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelmhsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelmhs);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, 740, -1));

        btnkeluar.setBackground(new java.awt.Color(255, 51, 51));
        btnkeluar.setText("keluar");
        btnkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkeluarActionPerformed(evt);
            }
        });
        getContentPane().add(btnkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 650, 130, 40));

        btnrefresh.setText("Refresh");
        btnrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefreshActionPerformed(evt);
            }
        });
        getContentPane().add(btnrefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 650, 130, 40));

        btnsimpan.setBackground(new java.awt.Color(0, 204, 51));
        btnsimpan.setText("simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 650, 130, 40));

        btnedit.setText("edit");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });
        getContentPane().add(btnedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 650, 120, 40));

        btnhapus.setText("hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 650, 130, 40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("NIM");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 80, -1));
        getContentPane().add(txtnamamhs, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 270, -1));

        cldtgllahir.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cldtgllahirPropertyChange(evt);
            }
        });
        getContentPane().add(cldtgllahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnimActionPerformed

    private void txtalamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtalamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtalamatActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        try {
            hapus();
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(formmahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
    bersih();        
// TODO add your handling code here:
    }//GEN-LAST:event_btnrefreshActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        try {
            // TODO add your handling code here:
            simpan();
        } catch (SQLException ex) {
            Logger.getLogger(formmahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkeluarActionPerformed
    dispose();        
// TODO add your handling code here:
    }//GEN-LAST:event_btnkeluarActionPerformed

    private void tabelmhsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelmhsMouseClicked
        ambildatadaritabel();
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelmhsMouseClicked

    private void txtcaridataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcaridataKeyPressed
    caridata();
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcaridataKeyPressed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        try {
            edit();
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(formmahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btneditActionPerformed

    private void cldtgllahirPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cldtgllahirPropertyChange
        // TODO add your handling code here:
       try{
       if(cldtgllahir.getDate()!=null){
          String ubahtanggal ="yyyy/MM/dd";
          SimpleDateFormat format=new SimpleDateFormat(ubahtanggal);
          tanggal=String.valueOf(format.format(cldtgllahir.getDate()));
         }
       }catch(Exception e){

       }
    }//GEN-LAST:event_cldtgllahirPropertyChange

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
            java.util.logging.Logger.getLogger(formmahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formmahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formmahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formmahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formmahasiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnkeluar;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnsimpan;
    private com.toedter.calendar.JCalendar cldtgllahir;
    private javax.swing.JComboBox<String> cmbjk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelmhs;
    private javax.swing.JTextField txtalamat;
    private javax.swing.JTextField txtcaridata;
    private javax.swing.JTextField txtnamamhs;
    private javax.swing.JTextField txtnim;
    private javax.swing.JTextField txttempatlahir;
    // End of variables declaration//GEN-END:variables
}
