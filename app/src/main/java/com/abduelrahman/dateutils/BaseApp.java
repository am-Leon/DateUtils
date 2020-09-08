package com.abduelrahman.dateutils;

import android.app.Application;
import android.content.Context;

import java.util.Locale;

public class BaseApp extends Application {

    private static BaseApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public static synchronized BaseApp getInstance() {
        return instance;
    }


    @Override
    protected void attachBaseContext(Context base) {
        if (Locale.getDefault().getLanguage().equals("ar"))
            super.attachBaseContext(LocaleHelper.onAttach(base, "ar"));
        else if (Locale.getDefault().getLanguage().equals("en"))
            super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
        else
            super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }

}
