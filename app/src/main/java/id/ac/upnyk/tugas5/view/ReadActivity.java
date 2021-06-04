package id.ac.upnyk.tugas5.view;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

import id.ac.upnyk.tugas5.entity.AppDatabase;
import id.ac.upnyk.tugas5.view.MainAdapter;
import id.ac.upnyk.tugas5.R;
import id.ac.upnyk.tugas5.entity.AppDatabase;
import id.ac.upnyk.tugas5.entity.DataNCT;
public class ReadActivity extends AppCompatActivity{
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private AppDatabase appDatabase;
    private ArrayList<DataNCT> listNct = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        recyclerView = findViewById(R.id.rv_main);
        mainAdapter = new MainAdapter(getApplicationContext());
        mainAdapter.notifyDataSetChanged();

        if (appDatabase == null){
            appDatabase = AppDatabase.inidb(getApplicationContext());
        }

        listNct.addAll(appDatabase.nctdao().getData());
        mainAdapter.setList(listNct);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(mainAdapter);


    }
}
