package id.meetsme.meetsme;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Ibam on 9/10/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
