package com.iqbal.eyetesting.ui.EnterTest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.graphics.CanvasView;
import com.iqbal.eyetesting.R;
import com.iqbal.eyetesting.ui.Database.Helper.TestHelper;
import com.iqbal.eyetesting.ui.Util.TextExtractUtil;

public class Activity_Test extends AppCompatActivity {

    private CanvasView canvas = null;
    private ImageView iv_test;
    private Button btn_undo,btn_redo,btn_clear,btn_next;
    private TextView tv_notest;
    private TestHelper db;

    int skor=0;
    int x=0;

    public String gambartest[] = new String[14];
    public String angkatest[] = new String[14];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        db = new TestHelper(this);
        getDataTest();

        //INISIALISASI OBJEK
        tv_notest = findViewById(R.id.tv_nomortest);
        iv_test = findViewById(R.id.iv_test);
        canvas = findViewById(R.id.canvas_test);
        btn_undo = findViewById(R.id.btn_undo);
        btn_redo = findViewById(R.id.btn_redo);
        btn_clear = findViewById(R.id.btn_clear);
        btn_next = findViewById(R.id.btn_next);

        //SET TEST PERTAMA
        int imageResource = getResources().getIdentifier(gambartest[x], null, getPackageName());
        iv_test.setImageDrawable(getDrawable(imageResource));

        //PANGGIL CONFIG CANVAS
        config_canvas();

        //KONFIGURASI BUTTON
        btn_undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.undo();
            }
        });

        btn_redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.redo();
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while (canvas.canUndo()){ // RESET CANVAS
                    canvas.undo();
                }

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.setBaseColor(Color.WHITE);
                // Getter
                //String text = canvas.getText();
                //Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
                Bitmap bitmaptest = canvas.getScaleBitmap(1024, 768);  // 300 x 200;
                String hasildraw = TextExtractUtil.getText(getApplicationContext(),bitmaptest);

                //Toast.makeText(getApplicationContext(), angkatest[x]+"="+hasildraw,Toast.LENGTH_SHORT).show();
                if(angkatest[x].equals(hasildraw) || (angkatest[x].equals("0") && hasildraw.equals("")) ){ //jika benar atau jika tidak angka dan tidak mengisi maka benar
                    skor++;
                    //Toast.makeText(getApplicationContext(), "Benar ="+skor,Toast.LENGTH_SHORT).show();
                }

                x++;
                if(x>=14){
                    //config_canvas();
                    //Toast.makeText(getApplicationContext(),"Total benar"+skor,Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Activity_Test.this,Activity_Hasil.class);
                    i.putExtra("nama",getIntent().getStringExtra("nama"));
                    i.putExtra("umur",getIntent().getStringExtra("umur"));
                    i.putExtra("jenis_kelamin",getIntent().getStringExtra("jenis_kelamin"));
                    i.putExtra("score",String.valueOf(skor));
                    startActivity(i);
                }else {
                    while (canvas.canUndo()){ //RESET CANVAS
                        canvas.undo();
                    }
                    config_canvas();

                    //SET TEST BERIKUTNYA
                    int imageResource = getResources().getIdentifier(gambartest[x], null, getPackageName());
                    iv_test.setImageDrawable(getDrawable(imageResource));
                    //tv_notest.setText("No " + String.valueOf(x+1) + gambartest[x]);
                    tv_notest.setText("No " + String.valueOf(x+1));


                }

            }
        });

    }

    public void config_canvas(){
        //KONFIGURASI CANVAS
        canvas.setMode(CanvasView.Mode.DRAW);    // for drawing
        canvas.setDrawer(CanvasView.Drawer.PEN);
        canvas.setFontFamily(Typeface.DEFAULT_BOLD);
        canvas.setBaseColor(Color.TRANSPARENT);
        canvas.setPaintStyle(Paint.Style.STROKE);
        canvas.setPaintStrokeWidth(20);
        canvas.setLineCap(Paint.Cap.ROUND);
    }

    public void getDataTest(){
        int no=0;
        db.open();
        Cursor cursor = db.queryDataRandom14();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                  angkatest[no] = cursor.getString(1); //ambil angkatest
                  String gbr = cursor.getString(3);
                  String gambar = gbr.replace(".png",""); //hilangkan ekstensi untuk get drawable
                  gambartest[no] = "drawable/"+gambar; //ambil gambartest
                  no++;
            }
        }
        db.close();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
