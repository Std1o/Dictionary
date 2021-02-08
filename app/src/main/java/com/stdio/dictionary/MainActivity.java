package com.stdio.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DataModel> dataList;
    private RecyclerView rv;
    SQLiteDatabase db;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        DataBaseHelper databaseHelper = new DataBaseHelper(getApplicationContext());
        databaseHelper.create_db();
        db = databaseHelper.open();
        readDb();
        setOnQueryTextListener();
    }

    private void setOnQueryTextListener() {
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<DataModel> list = getFilteredList(s.toLowerCase());
                Collections.sort(list, new SortByName());
                setAdapter(list);
                return false;
            }
        });
    }

    private ArrayList<DataModel> getFilteredList(String query) {
        ArrayList<DataModel> filteredList = new ArrayList<>();
        for (DataModel dataModel : dataList) {
            if (query.isEmpty()) {
                filteredList.add(dataModel);
            }
            else if (dataModel.title.toLowerCase().contains(query)) {
                filteredList.add(dataModel);
            }
        }
        return filteredList;
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
                Collections.sort(dataList, new SortByName());
                setAdapter(dataList);
            } while (cursor.moveToNext());
        } else
            cursor.close();
    }

    private void initRecyclerView() {
        rv = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
    }

    private void setAdapter(ArrayList<DataModel> list) {
        RVAdapter adapter = new RVAdapter(list, this);
        rv.setAdapter(adapter);
    }
}
