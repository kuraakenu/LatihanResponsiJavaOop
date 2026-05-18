/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Kandidat;

/**
 *
 * @author mzida
 */
public class ModelKandidat {
    private Integer id;
    private int writing, coding, interview;
    private float score;
    private String nama, path, status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getWriting() {
        return writing;
    }

    public void setWriting(int writing) {
        this.writing = writing;
    }

    public int getCoding() {
        return coding;
    }

    public void setCoding(int coding) {
        this.coding = coding;
    }

    public int getInterview() {
        return interview;
    }

    public void setInterview(int interview) {
        this.interview = interview;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
