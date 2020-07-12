package com.abduelrahman.dateutils;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import am.dateutils.DateUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, new DateUtils(this, "2020-07-12T17:00:49Z").setSpecificFormat("MMMM dd, yyyy"), Toast.LENGTH_LONG).show();

    }

}