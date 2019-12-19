package com.iqbal.eyetesting.ui.Info;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;

import com.iqbal.eyetesting.R;

public class Activity_Info extends AppCompatActivity {
    CardView cv_parsial,cv_nonparsial;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getSupportActionBar().setTitle("Info");

        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);

        BitmapDrawable background = new BitmapDrawable (BitmapFactory.decodeResource(getResources(), R.drawable.bg_toolbar_fix));
        menu.setBackgroundDrawable(background);

        cv_parsial = findViewById(R.id.card_view_buta_warna_parsial);
        cv_nonparsial = findViewById(R.id.card_view_buta_warna_non_parsial);

        cv_parsial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Activity_Info_Butawarna_Parsial.class);
                startActivity(i);
            }
        });

        cv_nonparsial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Activity_Info_Butawarna_Nonparsial.class);
                startActivity(i);
            }
        });
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
