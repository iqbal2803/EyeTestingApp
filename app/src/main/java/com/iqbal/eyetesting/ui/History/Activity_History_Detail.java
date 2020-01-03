package com.iqbal.eyetesting.ui.History;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.iqbal.eyetesting.R;
import com.iqbal.eyetesting.ui.Activity_Home;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import static com.iqbal.eyetesting.ui.Database.DatabaseContract.UserColumns.JENIS_KELAMIN;
import static com.iqbal.eyetesting.ui.Database.DatabaseContract.UserColumns.NAMA;
import static com.iqbal.eyetesting.ui.Database.DatabaseContract.UserColumns.NILAI;
import static com.iqbal.eyetesting.ui.Database.DatabaseContract.UserColumns.TGL_TEST;
import static com.iqbal.eyetesting.ui.Database.DatabaseContract.UserColumns.UMUR;

public class Activity_History_Detail extends AppCompatActivity {
    TextView tv_tgltest, tv_nama, tv_umur, tv_jeniskelamin, tv_nilai;
    JustifiedTextView tv_keterangan;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        getSupportActionBar().setTitle("Detail History");

        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);

        BitmapDrawable background = new BitmapDrawable(BitmapFactory.decodeResource(getResources(), R.drawable.bg_toolbar_fix));
        menu.setBackgroundDrawable(background);
        menu.setElevation(0);

        tv_tgltest = findViewById(R.id.tv_tgltest);
        tv_nama = findViewById(R.id.tv_nama);
        tv_umur = findViewById(R.id.tv_umur);
        tv_jeniskelamin = findViewById(R.id.tv_jeniskelamin);
        tv_nilai = findViewById(R.id.tv_nilai);
        tv_keterangan = findViewById(R.id.tv_keterangan);

        tv_tgltest.setText(getIntent().getStringExtra(TGL_TEST));
        tv_nama.setText(getIntent().getStringExtra(NAMA));
        tv_umur.setText(getIntent().getStringExtra(UMUR));
        if(getIntent().getStringExtra(JENIS_KELAMIN).equals("Laki-Laki")){
            tv_jeniskelamin.setText("L");
        }else{
            tv_jeniskelamin.setText("P");
        }
        tv_nilai.setText(getIntent().getStringExtra(NILAI));

        if(Integer.parseInt(getIntent().getStringExtra(NILAI))<5){
            tv_keterangan.setText(R.string.keterangan_test1);
        }else if(Integer.parseInt(getIntent().getStringExtra(NILAI))<10){
            tv_keterangan.setText(R.string.keterangan_test2);
        }else if(Integer.parseInt(getIntent().getStringExtra(NILAI))<=14){
            tv_keterangan.setText(R.string.keterangan_test3);
        }
    }
}
