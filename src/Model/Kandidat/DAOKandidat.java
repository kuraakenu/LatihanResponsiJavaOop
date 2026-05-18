/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Kandidat;

import Model.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mzida
 */
public class DAOKandidat implements InterfaceDAOKandidat{

    @Override
    public void insert(ModelKandidat kandidat) {
        try{
            
            String query = "INSERT INTO recruit (nama, path, writing, coding, interview, score, status) VALUES (?, ?, ?, ?, ?, ?, ?);";
             
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, kandidat.getNama());
            statement.setString(2, kandidat.getPath());
            statement.setInt(3, kandidat.getWriting());
            statement.setInt(4, kandidat.getCoding());
            statement.setInt(5, kandidat.getInterview());
            statement.setFloat(6, kandidat.getScore());
            statement.setString(7, kandidat.getStatus());
            
            statement.executeUpdate();
            statement.close();
             
        }catch(SQLException e){
            System.out.println("Input Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void update(ModelKandidat kandidat) {
        try{
            String query = "UPDATE recruit SET nama=?, path=?, writing=?, coding=?, interview=?, score=?, status=? WHERE id=?;";
        
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, kandidat.getNama());
            statement.setString(2, kandidat.getPath());
            statement.setInt(3, kandidat.getWriting());
            statement.setInt(4, kandidat.getCoding());
            statement.setInt(5, kandidat.getInterview());
            statement.setFloat(6, kandidat.getScore());
            statement.setString(7, kandidat.getStatus());
            
            statement.setInt(8, kandidat.getId());
            
            statement.executeUpdate();
            statement.close();
  
        }catch(SQLException e){
            System.out.println("Update Failed! (" + e.getMessage() + ")");
        }
    }

    @Override
    public void delete(int id) {
       try{
           String query = "DELETE FROM recruit WHERE id=?;";
           
           PreparedStatement statement;
           statement = Connector.Connect().prepareStatement(query);
           statement.setInt(1, id);
           
           statement.executeUpdate();
           statement.close();
           
       }catch(SQLException e){
           System.out.println("Delete Failed: " + e.getLocalizedMessage());
       }
    }

    @Override
    public List<ModelKandidat> getAll() {
        List<ModelKandidat> listKandidat = null;
        
        try{
            listKandidat = new ArrayList<>();
            Statement statement = Connector.Connect().createStatement();
            String query = "SELECT * FROM recruit;";
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                
                ModelKandidat kandidat = new ModelKandidat();
                
                kandidat.setId(resultSet.getInt("id"));
                kandidat.setNama(resultSet.getString("name"));
                kandidat.setPath(resultSet.getString("path"));
                kandidat.setWriting(resultSet.getInt("writing"));
                kandidat.setCoding(resultSet.getInt("coding"));
                kandidat.setInterview(resultSet.getInt("interview"));
                kandidat.setScore(resultSet.getFloat("score"));
                kandidat.setStatus(resultSet.getString("status"));
                
                /* 
                  Menambahkan mahasiswa ke dalam daftar mahasiswa.
                  Daftar mahasiswa disimpan ke dalam variabel "listMahasiswa"
                  yang memiliki tipe data List.
                */
                listKandidat.add(kandidat);
            }
            
            // Menutup koneksi untuk menghemat penggunaan memory.
            statement.close();
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return listKandidat;
    }
    
}
