package id.ac.upnyk.tugas5.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "nct_db")

public class DataNCT {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private int id;
    @ColumnInfo(name = "Nama")
    private String nama;
    @ColumnInfo(name = "Tanggal Lahir")
    private String tanggal;
    @ColumnInfo(name = "Zodiak")
    private String zodiak;
    @ColumnInfo(name = "Golongan Darah")
    private String goldar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getZodiak() {
        return zodiak;
    }

    public void setZodiak(String zodiak) {
        this.zodiak = zodiak;
    }

    public String getGoldar() {
        return goldar;
    }

    public void setGoldar(String goldar) {
        this.goldar = goldar;
    }
}
