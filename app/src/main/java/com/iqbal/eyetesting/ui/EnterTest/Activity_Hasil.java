package com.iqbal.eyetesting.ui.EnterTest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.TextView;

import com.iqbal.eyetesting.R;
import com.iqbal.eyetesting.ui.Activity_Home;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

public class Activity_Hasil extends AppCompatActivity {
    TextView tv_hasil;
    JustifiedTextView tv_keterangan;

    @Override
    public boolean onSupportNavigateUp() {
        Intent i = new Intent(getApplicationContext(), Activity_Home.class);
        startActivity(i);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        getSupportActionBar().setTitle("Hasil Test");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home_white);

        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);

        BitmapDrawable background = new BitmapDrawable (BitmapFactory.decodeResource(getResources(), R.drawable.bg_toolbar_fix));
        menu.setBackgroundDrawable(background);
        menu.setElevation(0);

        tv_hasil = findViewById(R.id.tv_hasil);
        tv_keterangan = findViewById(R.id.tv_keterangan);

        tv_hasil.setText("Hasil Nilai : "+getIntent().getStringExtra("score"));
        if(Integer.parseInt(getIntent().getStringExtra("score"))<5){
            tv_keterangan.setText(R.string.keterangan_test1);
        }else if(Integer.parseInt(getIntent().getStringExtra("score"))<10){
            tv_keterangan.setText(R.string.keterangan_test2);
        }else if(Integer.parseInt(getIntent().getStringExtra("score"))<=14){
            tv_keterangan.setText(R.string.keterangan_test3);
        }
    }
}
