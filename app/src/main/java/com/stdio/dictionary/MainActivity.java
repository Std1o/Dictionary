package com.stdio.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DataModel> dataList;
    private RecyclerView rv;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        DataBaseHelper databaseHelper = new DataBaseHelper(getApplicationContext());
        databaseHelper.create_db();
        db = databaseHelper.open();
        readDb();
    }

    private void readDb() {
        dataList = new ArrayList<>();
        Cursor cursor = db.query("fraze", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex("name");
            int descriptionIndex = cursor.getColumnIndex("description");
            int idIndex = cursor.getColumnIndex("id");
            do {
                dataList.add(new DataModel(cursor.getString(nameIndex), cursor.getString(descriptionIndex)));
                setAdapter();
            } while (cursor.moveToNext());
        } else
            cursor.close();
    }

    private void initRecyclerView() {
        rv = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
    }

    private void setAdapter() {
        RVAdapter adapter = new RVAdapter(dataList, this);
        rv.setAdapter(adapter);
    }
}
