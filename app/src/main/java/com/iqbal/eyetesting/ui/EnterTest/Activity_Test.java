package com.iqbal.eyetesting.ui.EnterTest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import android.content.ContentValues;
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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.graphics.CanvasView;
import com.iqbal.eyetesting.R;
import com.iqbal.eyetesting.ui.Database.Helper.TestHelper;
import com.iqbal.eyetesting.ui.Database.Helper.UserHelper;
import com.iqbal.eyetesting.ui.Util.TextExtractUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.iqbal.eyetesting.ui.Database.DatabaseContract.UserColumns.JENIS_KELAMIN;
import static com.iqbal.eyetesting.ui.Database.DatabaseContract.UserColumns.NAMA;
import static com.iqbal.eyetesting.ui.Database.DatabaseContract.UserColumns.NILAI;
import static com.iqbal.eyetesting.ui.Database.DatabaseContract.UserColumns.TGL_TEST;
import static com.iqbal.eyetesting.ui.Database.DatabaseContract.UserColumns.UMUR;

public class Activity_Test extends AppCompatActivity {

    private static final String DEBUG_TAG = "testtouch";
    private CanvasView canvas = null;
    private ImageView iv_test;
    private Button btn_undo, btn_redo, btn_clear, btn_next;
    private TextView tv_notest, tv_hasil_draw;
    private TestHelper helperTest;
    private UserHelper helperUser;

    int skor = 0;
    int x = 0;

    public String gambartest[] = new String[14];
    public String angkatest[] = new String[14];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        helperTest = new TestHelper(this);
        helperUser = new UserHelper(this);
        getDataTest();

        //INISIALISASI OBJEK
        tv_notest = findViewById(R.id.tv_nomortest);
        tv_hasil_draw = findViewById(R.id.tv_ket2);
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

        canvas.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                // ... Respond to touch events
                canvas.onTouchEvent(event);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        // The touch just ended
                        //Log.d(DEBUG_TAG, "tidak sentuh");
                        canvas.setBaseColor(Color.WHITE);
                        Bitmap bitmaptest = canvas.getScaleBitmap(1024, 768);  // 300 x 200;
                        String hasildraw = TextExtractUtil.getText(getApplicationContext(), bitmaptest);
                        if (hasildraw.equals("")) {
                            tv_hasil_draw.setText("Tidak Terdeteksi");
                        } else {
                            tv_hasil_draw.setText(hasildraw);
                        }
                        config_canvas();
                        break;
                    default:
                        // The touch start
                        //Log.d(DEBUG_TAG, "sentuh");
                }
                return true;
            }
        });


        //KONFIGURASI BUTTON
        btn_undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.undo();
                canvas.setBaseColor(Color.WHITE);
                Bitmap bitmaptest = canvas.getScaleBitmap(1024, 768);  // 300 x 200;
                String hasildraw = TextExtractUtil.getText(getApplicationContext(), bitmaptest);
                if (hasildraw.equals("")) {
                    tv_hasil_draw.setText("Tidak Terdeteksi");
                } else {
                    tv_hasil_draw.setText(hasildraw);
                }
                config_canvas();
            }
        });

        btn_redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.redo();
                canvas.setBaseColor(Color.WHITE);
                Bitmap bitmaptest = canvas.getScaleBitmap(1024, 768);  // 300 x 200;
                String hasildraw = TextExtractUtil.getText(getApplicationContext(), bitmaptest);
                if (hasildraw.equals("")) {
                    tv_hasil_draw.setText("Tidak Terdeteksi");
                } else {
                    tv_hasil_draw.setText(hasildraw);
                }
                config_canvas();
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while (canvas.canUndo()) { // RESET CANVAS
                    canvas.undo();
                }
                tv_hasil_draw.setText("");

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.setBaseColor(Color.WHITE);
                Bitmap bitmaptest = canvas.getScaleBitmap(1024, 768);  // 300 x 200;
                String hasildraw = TextExtractUtil.getText(getApplicationContext(), bitmaptest);

                //Toast.makeText(getApplicationContext(), angkatest[x]+"="+hasildraw,Toast.LENGTH_SHORT).show();
                if (angkatest[x].equals(hasildraw)) { //jika benar atau jika tidak angka dan tidak mengisi maka benar
                    skor++;
                    //Toast.makeText(getApplicationContext(), "Benar ="+skor,Toast.LENGTH_SHORT).show();
                }

                x++;
                if (x >= 14) {
                    //AMBIL DATETIME
                    Calendar newCalendar = Calendar.getInstance();
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                    String tglskrg = dateFormatter.format(newCalendar.getTime());

                    helperUser.open();
                    // SET PARAMETER CONTENT YANG AKAN DI INSERT
                    final ContentValues values = new ContentValues();
                    values.put(NAMA, getIntent().getStringExtra("nama"));
                    values.put(UMUR, getIntent().getStringExtra("umur"));
                    values.put(JENIS_KELAMIN, getIntent().getStringExtra("jenis_kelamin"));
                    values.put(NILAI, String.valueOf(skor));
                    values.put(TGL_TEST, tglskrg);
                    //INSERT KE DB
                    helperUser.insert(values);
                    helperUser.close();

                    Intent i = new Intent(Activity_Test.this, Activity_Hasil.class);
                    i.putExtra("score",String.valueOf(skor));
                    startActivity(i);
                } else {
                    while (canvas.canUndo()) { //RESET CANVAS
                        canvas.undo();
                    }
                    config_canvas();

                    //SET TEST BERIKUTNYA
                    int imageResource = getResources().getIdentifier(gambartest[x], null, getPackageName());
                    iv_test.setImageDrawable(getDrawable(imageResource));
                    //tv_notest.setText("No " + String.valueOf(x+1) + gambartest[x]);
                    tv_notest.setText("No " + String.valueOf(x + 1));
                    tv_hasil_draw.setText("");


                }

            }
        });

    }

    public void config_canvas() {
        //KONFIGURASI CANVAS
        canvas.setMode(CanvasView.Mode.DRAW);    // for drawing
        canvas.setDrawer(CanvasView.Drawer.PEN);
        canvas.setFontFamily(Typeface.DEFAULT_BOLD);
        canvas.setBaseColor(Color.TRANSPARENT);
        canvas.setPaintStyle(Paint.Style.STROKE);
        canvas.setPaintStrokeWidth(20);
        canvas.setLineCap(Paint.Cap.ROUND);
    }

    public void getDataTest() {
        int no = 0;
        helperTest.open();
        Cursor cursor = helperTest.queryDataRandom14();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                angkatest[no] = cursor.getString(1); //ambil angkatest
                String gbr = cursor.getString(3);
                String gambar = gbr.replace(".png", ""); //hilangkan ekstensi untuk get drawable
                gambartest[no] = "drawable/" + gambar; //ambil gambartest
                no++;
            }
        }
        helperTest.close();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
