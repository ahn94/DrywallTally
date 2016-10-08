package com.austinnightingale.android.drywalltally;

import android.app.Application;

/**
 * Created by Austin on 8/12/2016.
 */
public class TallyApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                        .applicationModule(new ApplicationModule(this))
                        .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
