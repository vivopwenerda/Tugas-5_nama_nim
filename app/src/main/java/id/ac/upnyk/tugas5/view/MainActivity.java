package id.ac.upnyk.tugas5.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import id.ac.upnyk.tugas5.R;
import id.ac.upnyk.tugas5.entity.AppDatabase;
import id.ac.upnyk.tugas5.entity.DataNCT;

public class MainActivity extends AppCompatActivity implements MainContact.view{
    private AppDatabase appDatabase;
    private MainPresenter mainPresenter;
    private MainAdapter mainAdapter;

    private Button btnOK;
    private RecyclerView recyclerView;
    private EditText etNama, etTanggal, etZodiak, etGoldar;
    private int id=0;
    boolean edit=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOK=findViewById(R.id.btn_submit);
        etNama=findViewById(R.id.et_nama);
        etTanggal=findViewById(R.id.et_tanggal);
        etZodiak=findViewById(R.id.et_zodiak);
        etGoldar=findViewById(R.id.et_goldar);
        recyclerView=findViewById(R.id.rv_main);

        appDatabase=AppDatabase.inidb(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mainPresenter=new MainPresenter(this);
        mainPresenter.readData(appDatabase);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    DataNCT model = new DataNCT();

                    model.setNama(etNama.getText().toString());
                    model.setTanggal(etTanggal.getText().toString());
                    model.setZodiak(etZodiak.getText().toString());
                    model.setGoldar(etGoldar.getText().toString());

                    appDatabase.nctdao().insertData(model);

                    Log.d("MainAcitity" , "sukses ");
                    Toast.makeText(getApplicationContext(),"Tersimpan", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Log.e("MainAcitity" , "gagal menyimpan , msg : "+ex.getMessage());
                    Toast.makeText(getApplicationContext(),"Gagal Menyimpan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        }


    @Override
    public void successAdd() {
        Toast.makeText(this,"Berhasil",Toast.LENGTH_SHORT).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(this,"Berhasil Menghapus Data",Toast.LENGTH_SHORT).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        etNama.setText("");
        etTanggal.setText("");
        etZodiak.setText("");
        etZodiak.setText("");
        btnOK.setText("SUBMIT");
    }

    @Override
    public void getData(List<DataNCT> list) {
        mainAdapter= new MainAdapter(this, list,this);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void editData(DataNCT item) {
        etNama.setText(item.getNama());
        etTanggal.setText(item.getTanggal());
        etZodiak.setText(item.getZodiak());
        etGoldar.setText(item.getGoldar());
        id=item.getId();
        edit=true;
        btnOK.setText("EDIT DATA");
    }

    @Override
    public void deleteData(DataNCT item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            builder=new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        }else {
            builder=new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda Yakin Menghapus Data Ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        resetForm();
                        mainPresenter.deleteData(item,appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_dialer)
                .show();
    }

    @Override
    public void onClick(View v) {
        if (v==btnOK){
            if (etNama.getText().toString().equals("")||etTanggal.getText().toString().equals("")
                ||etZodiak.getText().toString().equals("")||etGoldar.getText().toString().equals("")){
                Toast.makeText(this, "Harap Isi Semua Data",Toast.LENGTH_SHORT).show();
            } else{
                if (!edit){
                    mainPresenter.insertData(etNama.getText().toString(),etTanggal.getText().toString(),etZodiak.getText().toString(),
                            etGoldar.getText().toString(),appDatabase);
                } else{
                    mainPresenter.editData(etNama.getText().toString(),etTanggal.getText().toString(),etZodiak.getText().toString(),
                            etGoldar.getText().toString(),id,appDatabase);
                    edit=false;
                }
                resetForm();
            }
        }
    }
}