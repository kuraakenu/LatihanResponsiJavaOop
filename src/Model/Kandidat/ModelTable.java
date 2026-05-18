/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Kandidat;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mzida
 */
public class ModelTable extends AbstractTableModel{
    
    List<ModelKandidat> daftarKandidat;

    String kolom[] = {
        "id", "nama", "path", "writing", "coding", "interview", "score", "status"
    };
    
    public ModelTable(List<ModelKandidat> daftarKandidat){
        this.daftarKandidat = daftarKandidat;
    }
    
    @Override
    public int getRowCount() {
        return this.daftarKandidat.size();
    }

    @Override
    public int getColumnCount() {
        return kolom.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return daftarKandidat.get(rowIndex).getId();
            case 1:
                return daftarKandidat.get(rowIndex).getNama();
            case 2:
                return daftarKandidat.get(rowIndex).getPath();
            case 3:
                return daftarKandidat.get(rowIndex).getWriting();
            case 4:
                return daftarKandidat.get(rowIndex).getCoding();
            case 5:
                return daftarKandidat.get(rowIndex).getInterview();
            case 6:
                return daftarKandidat.get(rowIndex).getScore();
            case 7:
                return daftarKandidat.get(rowIndex).getStatus();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return kolom[columnIndex];
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
}
