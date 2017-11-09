package ua.shtain.irina.newbacking;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.facebook.stetho.Stetho;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.SystemService;

/**
 * Created by Irina Shtain on 09.11.2017.
 */

@EApplication
public class BackingApplication extends Application {
    @SystemService
    protected ConnectivityManager connectivityManager;
    @AfterInject
    public void create() {
        Stetho.initializeWithDefaults(this);
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    public boolean hasInternetConnection() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

}

