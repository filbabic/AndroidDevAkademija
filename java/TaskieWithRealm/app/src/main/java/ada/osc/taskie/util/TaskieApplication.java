package ada.osc.taskie.util;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TaskieApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("taskie.realm")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
