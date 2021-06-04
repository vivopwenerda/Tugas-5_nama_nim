package id.ac.upnyk.tugas5.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface DataNCTDAO {
    @Insert
    Long insertData (DataNCT dataNCT);

    @Query("Select * from nct_db")
    List<DataNCT> getData();

    @Update
    int updateData(DataNCT item);

    @Delete
    void deleteData(DataNCT item);
}
