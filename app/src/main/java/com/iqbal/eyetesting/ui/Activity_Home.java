package com.iqbal.eyetesting.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.iqbal.eyetesting.R;
import com.iqbal.eyetesting.ui.EnterTest.Activity_Biodata;
import com.iqbal.eyetesting.ui.History.Activity_History;
import com.iqbal.eyetesting.ui.Info.Activity_Info;
import com.iqbal.eyetesting.ui.Tutorial.Activity_Tutorial;

public class Activity_Home extends AppCompatActivity {
    CardView cv_entertest,cv_info,cv_history,cv_tutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cv_entertest = findViewById(R.id.card_view_entertest);
        cv_info = findViewById(R.id.card_view_info);
        cv_history = findViewById(R.id.card_view_history);
        cv_tutorial = findViewById(R.id.card_view_tutorial);

        cv_entertest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Activity_Biodata.class);
                startActivity(i);
            }
        });

        cv_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Activity_Info.class);
                startActivity(i);
            }
        });

        cv_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Activity_History.class);
                startActivity(i);
            }
        });

        cv_tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Activity_Tutorial.class);
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
