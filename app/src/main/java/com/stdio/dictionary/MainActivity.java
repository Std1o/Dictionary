package com.stdio.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseHelper databaseHelper = new DataBaseHelper(getApplicationContext());
        databaseHelper.create_db();
        db = databaseHelper.open();
        readDb();
    }

    private void readDb() {
        Cursor cursor = db.query("fraze", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex("name");
            int descriptionIndex = cursor.getColumnIndex("description");
            int idIndex = cursor.getColumnIndex("id");
            do {
                System.out.println(cursor.getString(nameIndex));
            } while (cursor.moveToNext());
        } else
            cursor.close();
    }
}