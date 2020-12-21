package com.abduelrahman.dateutils;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import am.dateutils.DateTimeStyle;
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

        Log.e("aaaaaaaaaa 1 ", String.valueOf(new DateUtils(this, "2020/12/17T15:03:09'Z'", LocaleHelper.getLanguage(this)).toMillis()));
        Log.e("aaaaaaaaaa 1 ", new DateUtils(this, "2020/12/17 20:03:09", LocaleHelper.getLanguage(this)).getTimeAgo());
        Log.e("aaaaaaaaaa 2 ", new DateUtils(this, "2010/09/08 16:23:19Z", LocaleHelper.getLanguage(this)).setSpecificFormat(DateTimeStyle.DATE_FULL_STANDARD_AR));
        Log.e("aaaaaaaaaa 2 ", new DateUtils(this, "2010-09-08 16:23:19Z", LocaleHelper.getLanguage(this)).setSpecificFormat(DateTimeStyle.DATE_FULL_SEPARATOR_AR));
        Log.e("aaaaaaaaaa 2 ", new DateUtils(this, "2010-09-08", LocaleHelper.getLanguage(this)).setSpecificFormat(DateTimeStyle.DATE_FULL_SEPARATOR_AR));
        Log.e("aaaaaaaaaa 2 ", new DateUtils(this, "2010/09/08", LocaleHelper.getLanguage(this)).setSpecificFormat(DateTimeStyle.DATE_FULL_SEPARATOR_AR));
//        Log.e("aaaaaaaaaa 4 ", Utils.millisToTime(1608217389000L));

        Log.e("aaaaaaaaaa 3 ", String.valueOf(DateUtils.isCurrentDateTimeBetweenDates("2020/12/17 17:00:00", "2020/12/17 17:45:00")));


        DateUtils utils = new DateUtils(this, "2020/12/17T20:53:49Z", "ar");
        Log.e("bbbbbbbbbb ", utils.getTimeAgo());
//        Log.e("bbbbbbbbbb ", utils.setSpecificFormat(DateTimeStyle.DATE_LONG_STANDARD_AR).concat(" ").concat(utils.getHourFormat()));
        Log.e("bbbbbbbbbb ", utils.setSpecificFormat(DateTimeStyle.DATE_TIME_FULL_STANDARD_AR));

//        Snackbar.make(findViewById(android.R.id.content), utils.getTimeAgo(),
//                BaseTransientBottomBar.LENGTH_INDEFINITE)
//                .setAction("Again", view -> recreate())
//                .show();

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