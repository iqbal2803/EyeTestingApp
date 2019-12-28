package com.iqbal.eyetesting.ui.EnterTest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
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
import android.widget.Toast;

import com.android.graphics.CanvasView;
import com.iqbal.eyetesting.R;
import com.iqbal.eyetesting.ui.Util.TextExtractUtil;

public class Activity_Test extends AppCompatActivity {

    private CanvasView canvas = null;
    private ImageView iv_test;
    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        iv_test = findViewById(R.id.iv_test);
        canvas = findViewById(R.id.canvas_test);
        btn_next = findViewById(R.id.btn_next);
        // Setter
        canvas.setMode(CanvasView.Mode.DRAW);    // for drawing
        canvas.setDrawer(CanvasView.Drawer.PEN);
        canvas.setFontFamily(Typeface.DEFAULT_BOLD);
        //this.canvas.setMode(CanvasView.Mode.TEXT);    // for drawing Text
        //this.canvas.setMode(CanvasView.Mode.ERASER);  // for using Eraser

        canvas.setBaseColor(Color.TRANSPARENT);
        canvas.setPaintStyle(Paint.Style.STROKE);
        canvas.setPaintStrokeWidth(20);
        //canvas.setOpacity(128);
        canvas.setLineCap(Paint.Cap.ROUND);

        // Setter
        //canvas.setText("Canvas View");


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getter
                //String text = canvas.getText();
                //Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
                Bitmap bitmaptest = canvas.getScaleBitmap(1024, 768);  // 300 x 200;
                Toast.makeText(getApplicationContext(), TextExtractUtil.getText(getApplicationContext(),bitmaptest),Toast.LENGTH_SHORT).show();
                iv_test.setImageBitmap(bitmaptest);


            }
        });

    }

    public CanvasView getCanvas() {
        return this.canvas;
    }
}
