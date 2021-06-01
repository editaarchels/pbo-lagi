/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasinilai;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author asus
 */
public class Koneksi {
   private static Connection koneksi_db=null;
   public static Connection KoneksiDatabase(){
       MysqlDataSource db_nilai=new MysqlDataSource();
       db_nilai.setDatabaseName("dbnilai");
       db_nilai.setPassword("");
       db_nilai.setUser("root");
            try{
                koneksi_db=db_nilai.getConnection();
            }catch(SQLException e){
              JOptionPane.showMessageDialog(null,"Koneksi error"+e.getMessage());
            }if(koneksi_db==null){
               JOptionPane.showMessageDialog(null,"Koneksi");
           }else{
               //JOptionPane.showMessageDialog(null,"Koneksi berhasil!");
           }
       return koneksi_db;
   }
    
}
