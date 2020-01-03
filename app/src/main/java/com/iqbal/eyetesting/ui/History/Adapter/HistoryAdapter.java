package com.iqbal.eyetesting.ui.History.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iqbal.eyetesting.R;
import com.iqbal.eyetesting.ui.History.Activity_History_Detail;
import com.iqbal.eyetesting.ui.History.Model.HistoryModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static com.iqbal.eyetesting.ui.Database.DatabaseContract.UserColumns.JENIS_KELAMIN;
import static com.iqbal.eyetesting.ui.Database.DatabaseContract.UserColumns.NAMA;
import static com.iqbal.eyetesting.ui.Database.DatabaseContract.UserColumns.NILAI;
import static com.iqbal.eyetesting.ui.Database.DatabaseContract.UserColumns.TGL_TEST;
import static com.iqbal.eyetesting.ui.Database.DatabaseContract.UserColumns.UMUR;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ListViewHolder> {
    private ArrayList<HistoryModel> listHistoryModel;
    private Context mContexl;

    public HistoryAdapter(ArrayList<HistoryModel> list, Context mContexl) {

        this.listHistoryModel = list;
        this.mContexl = mContexl;
    }

    public void setData(ArrayList<HistoryModel> items) {
        listHistoryModel.clear();
        listHistoryModel.addAll(items);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, final int position) {
        final HistoryModel history = listHistoryModel.get(position);
        final Date dateFormatter;
        String formattedDate="";
        try {
            dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(history.getTgl_test());
            formattedDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(dateFormatter);
            holder.tv_tgltest.setText(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.tv_nama.setText(history.getNama());
        holder.tv_umur.setText(history.getUmur());
        if (history.getJenis_kelamin().equals("Laki-Laki")) {
            holder.tv_jeniskelamin.setText("L");
        } else {
            holder.tv_jeniskelamin.setText("P");
        }
        holder.tv_nilai.setText(history.getNilai());

        final String finalFormattedDate = formattedDate;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContexl, Activity_History_Detail.class);
                i.putExtra(TGL_TEST, finalFormattedDate);
                i.putExtra(NAMA, history.getNama());
                i.putExtra(UMUR, history.getUmur());
                i.putExtra(JENIS_KELAMIN, history.getJenis_kelamin());
                i.putExtra(NILAI, history.getNilai());
                mContexl.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listHistoryModel.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tgltest, tv_nama, tv_umur, tv_jeniskelamin, tv_nilai;

        ListViewHolder(View itemView) {
            super(itemView);
            tv_tgltest = itemView.findViewById(R.id.tv_tgltest);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_umur = itemView.findViewById(R.id.tv_umur);
            tv_jeniskelamin = itemView.findViewById(R.id.tv_jeniskelamin);
            tv_nilai = itemView.findViewById(R.id.tv_nilai);
        }
    }
}
