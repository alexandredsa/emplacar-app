package br.com.fatecpg.emplacar.application;

import android.app.Application;

import com.orm.SugarContext;

/**
 * Created by alexandre on 02/11/16.
 */

public class EmplacarApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }
}
