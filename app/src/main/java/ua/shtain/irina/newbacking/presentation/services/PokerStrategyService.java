package ua.shtain.irina.newbacking.presentation.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.androidannotations.annotations.EService;

/**
 * Created by Irina Shtain on 13.11.2017.
 */
@EService
public class PokerStrategyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("mLog", "===========onCreate has ");
    }
}
