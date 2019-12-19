package com.iqbal.eyetesting.ui.EnterTest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import com.iqbal.eyetesting.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class Activity_Biodata extends AppCompatActivity {
    TextInputEditText et_nama,et_umur;
    MaterialSpinner spinner_jeniskelamin;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);

        getSupportActionBar().setTitle("Biodata");

        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);

        BitmapDrawable background = new BitmapDrawable (BitmapFactory.decodeResource(getResources(), R.drawable.bg_toolbar_fix));
        menu.setBackgroundDrawable(background);

        et_nama = findViewById(R.id.et_nama);
        et_umur = findViewById(R.id.et_umur);
        spinner_jeniskelamin = findViewById(R.id.spinner_jeniskelamin);
        spinner_jeniskelamin.setItems("Pilih Jenis Kelamin","Laki-Laki","Perempuan");

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
