package id.ac.upnyk.tugas5.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.ac.upnyk.tugas5.R;
import id.ac.upnyk.tugas5.entity.AppDatabase;
import id.ac.upnyk.tugas5.entity.DataNCT;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    Context context;
    List<DataNCT> list;
    MainContact.view aView;
    AppDatabase appDatabase;

    public MainAdapter(Context context, List<DataNCT> list, MainContact.view view) {
        this.context = context;
        this.list = list;
        aView = view;
    }

    public MainAdapter(Context applicationContext) {
    }




    @NonNull
    @Override
    public MainAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nct,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final DataNCT item= list.get(position);
        holder.tvNama.setText(item.getNama());
        holder.tvTanggal.setText(item.getTanggal());
        holder.tvZodiak.setText(item.getZodiak());
        holder.tvGoldar.setText(item.getGoldar());
        holder.id.setText(item.getId());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                aView.editData(item);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                aView.deleteData(item);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(ArrayList<DataNCT> list){
        list.clear();
        list.addAll(list);
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvTanggal, tvZodiak, tvGoldar,id;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_item_nama);
            tvTanggal = itemView.findViewById(R.id.tv_item_tanggal);
            tvZodiak = itemView.findViewById(R.id.tv_item_zodiak);
            tvGoldar = itemView.findViewById(R.id.tv_item_goldar);
            id = itemView.findViewById(R.id.tv_item_id);
            cardView = itemView.findViewById(R.id.cv_item);

        }
    }
}
