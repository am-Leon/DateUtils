package com.abduelrahman.dateutils;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import am.dateutils.DateUtils;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.txt).setOnClickListener(view -> {
            change();
            recreate();
        });


        DateUtils utils = new DateUtils(this, "2020-09-08T19:53:49Z", LocaleHelper.getLanguage(this));

        Snackbar.make(findViewById(android.R.id.content), utils.getTimeAgo(),
                BaseTransientBottomBar.LENGTH_INDEFINITE)
                .setAction("Again", view -> recreate())
                .show();
    }


    private void change() {
        if (LocaleHelper.getLanguage(MainActivity.this).equals("en")) {
            LocaleHelper.setLocale(MainActivity.this, "ar");
        } else {
            LocaleHelper.setLocale(MainActivity.this, "en");
        }
        BaseApp.getInstance().onCreate();
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

}