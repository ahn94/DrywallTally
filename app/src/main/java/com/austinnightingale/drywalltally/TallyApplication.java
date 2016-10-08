package com.austinnightingale.drywalltally;

import android.app.Application;


public class TallyApplication extends Application {

    private ApplicationComponent component = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
