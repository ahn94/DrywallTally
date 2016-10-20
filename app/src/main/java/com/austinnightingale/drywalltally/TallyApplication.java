package com.austinnightingale.drywalltally;

import android.app.Application;
import android.content.Context;


public class TallyApplication extends Application {

    private static Context context;

    private ApplicationComponent component = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
