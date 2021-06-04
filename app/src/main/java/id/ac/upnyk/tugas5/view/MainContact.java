package id.ac.upnyk.tugas5.view;

import android.view.View;

import java.util.List;

import id.ac.upnyk.tugas5.entity.AppDatabase;
import id.ac.upnyk.tugas5.entity.DataNCT;

public interface MainContact {
    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataNCT> list);
        void editData(DataNCT item);
        void deleteData(DataNCT item);
    }

    interface presenter{
        void insertData(String nama, String tanggal, String zodiak, String goldar, AppDatabase database);
        void readData(AppDatabase database);
        void editData(String nama, String tanggal, String zodiak, String goldar, int id, AppDatabase database);
        void deleteData(DataNCT dataNCT, AppDatabase database);
    }
}
