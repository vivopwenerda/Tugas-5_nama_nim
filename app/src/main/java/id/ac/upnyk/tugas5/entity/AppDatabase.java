package id.ac.upnyk.tugas5.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataNCT.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract DataNCTDAO nctdao();
    private static AppDatabase appDatabase;

    public static AppDatabase inidb(Context context){
        if (appDatabase==null)
            appDatabase= Room.databaseBuilder(context,AppDatabase.class,"dbNCT").allowMainThreadQueries().build();
        return appDatabase;
    }
}
