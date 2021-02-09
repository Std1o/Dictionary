package com.stdio.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public static String name;
    public static String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle(name);
        TextView tvDescription = findViewById(R.id.tvDescription);
        tvDescription.setText(Html.fromHtml(description));
    }
}