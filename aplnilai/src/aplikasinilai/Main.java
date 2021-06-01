/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasinilai;
import aplikasinilai.formmahasiswa;
import aplikasinilai.Koneksi;
/**
 *
 * @author asus
 */
public class Main {
    
    public static void main(String[] args)  {
        new aplikasinilai.formnilaimahasiswa().setVisible(true);
        Koneksi.KoneksiDatabase();
    }
    
    
}
