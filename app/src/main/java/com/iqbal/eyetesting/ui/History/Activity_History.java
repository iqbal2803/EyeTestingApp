package com.iqbal.eyetesting.ui.History;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.TableLayout;

import com.iqbal.eyetesting.R;
import com.iqbal.eyetesting.ui.Database.Helper.UserHelper;
import com.iqbal.eyetesting.ui.History.Adapter.HistoryAdapter;
import com.iqbal.eyetesting.ui.History.Model.HistoryModel;
import com.iqbal.eyetesting.ui.Util.LineDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class Activity_History extends AppCompatActivity {
    private UserHelper helperUser;
    private ArrayList<HistoryModel> mListHistoryItems;
    private RecyclerView rv_history;
    private HistoryModel mHistoryModel;
    private HistoryAdapter mHistoryAdapter;


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        helperUser = new UserHelper(this);

        getSupportActionBar().setTitle("History");

        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);

        BitmapDrawable background = new BitmapDrawable (BitmapFactory.decodeResource(getResources(), R.drawable.bg_toolbar_fix));
        menu.setBackgroundDrawable(background);
        menu.setElevation(0);

        rv_history = findViewById(R.id.rv_history);
        rv_history.setHasFixedSize(true);

        mListHistoryItems = new ArrayList<>();
        //passing to adapter
        mHistoryAdapter = new HistoryAdapter(mListHistoryItems, this);
        mHistoryAdapter.notifyDataSetChanged();
        rv_history.setLayoutManager(new LinearLayoutManager(this));
        rv_history.setItemAnimator(new DefaultItemAnimator());
        rv_history.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv_history.addItemDecoration(new LineDividerItemDecoration(this, R.drawable.line_divider));

        rv_history.setAdapter(mHistoryAdapter);

        showData();
    }

    public void showData(){
        final List<HistoryModel> history = new ArrayList<HistoryModel>();
        helperUser.open();
        Cursor cursor = helperUser.queryAll();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                HistoryModel itemHistory = new HistoryModel();
                itemHistory.setId_history(cursor.getString(0));
                itemHistory.setNama(cursor.getString(1));
                itemHistory.setUmur(cursor.getString(2));
                itemHistory.setJenis_kelamin(cursor.getString(3));
                itemHistory.setNilai(cursor.getString(4));
                itemHistory.setTgl_test(cursor.getString(5));
                history.add(itemHistory);
            }
        }
        mListHistoryItems.clear();
        mListHistoryItems.addAll(history);
        helperUser.close();
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
