/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Kandidat.DAOKandidat;
import Model.Kandidat.InterfaceDAOKandidat;
import Model.Kandidat.ModelKandidat;
import Model.Kandidat.ModelTable;
import View.View;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mzida
 */
public class ControllerKandidat {
    View halamanUtama;
    InterfaceDAOKandidat daoKandidat;
    List<ModelKandidat> daftarKandidat;
    
    public ControllerKandidat(View halamanUtama){
        this.halamanUtama = halamanUtama;
        this.daoKandidat = new DAOKandidat();
        
        showAllKandidat();
    }
    
    public void showAllKandidat(){
        daftarKandidat = daoKandidat.getAll();
        
        ModelTable table = new ModelTable(daftarKandidat);
        
        halamanUtama.getTableKandidat().setModel(table);
    }
    
    public void insertKandidat(){
        try{
            ModelKandidat kandidatBaru = new ModelKandidat();
            
            float totalScore;
            String status;
            
            String nama = halamanUtama.getInputNama();
            String path = halamanUtama.getInputPath();
            String writingText = halamanUtama.getInputWritingScore();
            String codingText = halamanUtama.getInputCodingScore();
            String interviewText = halamanUtama.getInputInterviewScore();
            
            if ("".equals(nama) || "".equals(path) || "".equals(writingText) || "".equals(codingText) || "".equals(interviewText)) {
                throw new Exception("Field tidak boleh kosong!");
            }
            
            int writingScore = Integer.parseInt(writingText);
            int codingScore = Integer.parseInt(codingText);
            int interviewScore = Integer.parseInt(interviewText);
            
            if(writingScore > 100 || codingScore > 100 || interviewScore > 100){
                throw new Exception("Score tidak boleh melebihi 100!");
            }
            
            if(writingScore < 0 || codingScore < 0 || interviewScore < 0){
                throw new Exception("Score tidak boleh negatif!");
            }
            
            totalScore = (writingScore + codingScore + interviewScore)/3f;
            
            status = (totalScore < 85) ? "Tidak Diterima" : "Diterima";
            
            kandidatBaru.setNama(nama);
            kandidatBaru.setPath(path);
            kandidatBaru.setWriting(writingScore);
            kandidatBaru.setCoding(codingScore);
            kandidatBaru.setInterview(interviewScore);
            kandidatBaru.setScore(totalScore);
            kandidatBaru.setStatus(status);
            
            daoKandidat.insert(kandidatBaru);
            JOptionPane.showMessageDialog(null, "Kandidat baru berhasil ditambahkan.");
            showAllKandidat();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void editKandidat(){
        try{
            
            if(halamanUtama.getSelectedId() == -1){
                throw new Exception("Pilih baris yang ingin diedit terlebih dahulu!");
            }
            
            ModelKandidat kandidatEdit = new ModelKandidat();
            
            float totalScore;
            String status;
            
            String nama = halamanUtama.getInputNama();
            String path = halamanUtama.getInputPath();
            String writingText = halamanUtama.getInputWritingScore();
            String codingText = halamanUtama.getInputCodingScore();
            String interviewText = halamanUtama.getInputInterviewScore();
            
            if ("".equals(nama) || "".equals(path) || "".equals(writingText) || "".equals(codingText) || "".equals(interviewText)) {
                throw new Exception("Field tidak boleh kosong!");
            }
            
            int writingScore = Integer.parseInt(writingText);
            int codingScore = Integer.parseInt(codingText);
            int interviewScore = Integer.parseInt(interviewText);
            
            if(writingScore > 100 || codingScore > 100 || interviewScore > 100){
                throw new Exception("Score tidak boleh melebihi 100!");
            }
            if(writingScore < 0 || codingScore < 0 || interviewScore < 0){
                throw new Exception("Score tidak boleh negatif!");
            }
            
            kandidatEdit.setId(halamanUtama.getSelectedId());
            kandidatEdit.setNama(nama);
            kandidatEdit.setPath(path);
            kandidatEdit.setWriting(writingScore);
            kandidatEdit.setCoding(codingScore);
            kandidatEdit.setInterview(interviewScore);
            
            totalScore = (writingScore + codingScore + interviewScore) / 3f;
            status = (totalScore < 85) ? "Tidak Diterima" : "Diterima";
            kandidatEdit.setScore(totalScore);
            kandidatEdit.setStatus(status);
            
            daoKandidat.update(kandidatEdit);
            
            JOptionPane.showMessageDialog(null, "Data kandidat berhasil diubah.");
            showAllKandidat();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void deleteKandidat(Integer baris){
        
        if(baris == null){
            JOptionPane.showMessageDialog(null, "Pilih baris yang ingin dihapus terlebih dahulu!");
            return;
        }

        Integer id = (int) halamanUtama.getTableKandidat().getValueAt(baris, 0);
        String nama = halamanUtama.getTableKandidat().getValueAt(baris, 1).toString();
        
        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Kandidat",
                JOptionPane.YES_NO_OPTION
        );
        
        if (input == 0) {
            daoKandidat.delete(id);
            
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");

            showAllKandidat();
        }
    }
    
}
