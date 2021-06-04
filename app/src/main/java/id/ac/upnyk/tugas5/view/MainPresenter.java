package id.ac.upnyk.tugas5.view;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import id.ac.upnyk.tugas5.entity.AppDatabase;
import id.ac.upnyk.tugas5.entity.DataNCT;

public class MainPresenter implements MainContact.presenter {
    private MainContact.view view;

    public MainPresenter(MainContact.view view) {
        this.view = view;
    }

    class InsertData extends AsyncTask<Void,Void,Long>{
        private AppDatabase appDatabase;
        private DataNCT dataNCT;

        public InsertData(AppDatabase appDatabase, DataNCT dataNCT) {
            this.appDatabase = appDatabase;
            this.dataNCT = dataNCT;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return appDatabase.nctdao().insertData(dataNCT);
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successAdd();
        }
    }

    @Override
    public void insertData(String nama, String tanggal, String zodiak, String goldar, AppDatabase database) {
        final DataNCT dataNCT= new DataNCT();
        dataNCT.setNama(nama);
        dataNCT.setTanggal(tanggal);
        dataNCT.setZodiak(zodiak);
        dataNCT.setGoldar(goldar);
        new InsertData(database,dataNCT).execute();
    }

    @Override
    public void readData(AppDatabase database) {
        List<DataNCT> list;
        list=database.nctdao().getData();
        view.getData(list);
    }

    class EditData extends AsyncTask<Void, Void, Integer> {
        private AppDatabase appDatabase;
        private DataNCT dataNCT;

        public EditData(AppDatabase appDatabase, DataNCT dataNCT) {
            this.appDatabase = appDatabase;
            this.dataNCT = dataNCT;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return appDatabase.nctdao().updateData(dataNCT);
        }

        protected void onPostExecute(Integer integer){
            super.onPostExecute(integer);
            Log.d("integer db", "On Post Execute : "+integer);
            view.successAdd();
        }
    }

    @Override
    public void editData(String nama, String tanggal, String zodiak, String goldar, int id, AppDatabase database) {
        final DataNCT dataNCT= new DataNCT();
        dataNCT.setNama(nama);
        dataNCT.setTanggal(tanggal);
        dataNCT.setZodiak(zodiak);
        dataNCT.setGoldar(goldar);
        dataNCT.setId(id);
        new EditData(database,dataNCT).execute();
    }

    class DeleteData extends AsyncTask<Void,Void,Long>{
        private AppDatabase appDatabase;
        private DataNCT dataNCT;

        public DeleteData(AppDatabase appDatabase, DataNCT dataNCT) {
            this.appDatabase = appDatabase;
            this.dataNCT = dataNCT;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            appDatabase.nctdao().deleteData(dataNCT);
            return null;
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successDelete();
        }
    }

    @Override
    public void deleteData(DataNCT dataNCT, AppDatabase database) {
        new DeleteData(database,dataNCT).execute();
    }
}
