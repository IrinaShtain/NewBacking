package ua.shtain.irina.newbacking.presentation.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EService;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import ua.shtain.irina.newbacking.data.db.ThemeDBHelper;
import ua.shtain.irina.newbacking.data.model.GipsyOver100;
import ua.shtain.irina.newbacking.data.model.GipsyTill100;
import ua.shtain.irina.newbacking.data.model.ThemeItem;
import ua.shtain.irina.newbacking.domain.ServiceThemeRepository;
import ua.shtain.irina.newbacking.presentation.utils.Constants;
import ua.shtain.irina.newbacking.presentation.utils.NotificationManager;

/**
 * Created by Irina Shtain on 13.11.2017.
 */
@EService
public class GipsyTeamService extends Service {
    @Bean
    protected ServiceThemeRepository themeRepository;
    @Bean
    protected ThemeDBHelper themeDBHelper;

    private CompositeDisposable compositeDisposable;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("mLog", "===========onCreate has ");
        compositeDisposable = new CompositeDisposable();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("mLog", "===========onStartCommand ");
        startPolingGipsyTill100();
        startPolingGipsyOver100();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
        Log.i("mLog", "===========onDestroy has ");
    }

    private Consumer<Throwable> throwableConsumer = throwable -> {
        Log.d("myLogs", "Error! " + throwable.getMessage());
        throwable.printStackTrace();
    };

    private void startPolingGipsyTill100() {
        compositeDisposable.add(themeRepository.getServiceThemes(Constants.GIPSY_TEAM_TIL_100, Constants.GIPSY_TEAM)
                .subscribe(items -> {
                    ArrayList<GipsyTill100> dataList = new ArrayList<>();
                    boolean existTable = themeDBHelper.existTableGipsyTill100();
                    if (!items.isEmpty()) {
                        Log.i("mLog", "Tread" + Thread.currentThread().getName() + "===========Gipsy items has " + items.size());
                        for (ThemeItem t : items) {
                            GipsyTill100 model = new GipsyTill100(t.getTitle(), t.getLink(), t.getUser());
                            dataList.add(model);
                            if (existTable && !themeDBHelper.isAuthorInBlackList(t.getUser())
                                    && themeDBHelper.notExistRecord(model) && hasNotManyViews(t.getViews())) {
                                Log.i("mLog", "=========== new element ");
                                NotificationManager.sendNotificationsGipsy(getApplicationContext(), t);
                            }
                        }
                        themeDBHelper.saveDataGipsyTill100(dataList);
                    }
                }, throwableConsumer));

    }

    private void startPolingGipsyOver100() {
        compositeDisposable.add(themeRepository.getServiceThemes(Constants.GIPSY_TEAM_OVER_100, Constants.GIPSY_TEAM)
                .subscribe(items -> {
                    ArrayList<GipsyOver100> dataList = new ArrayList<>();
                    boolean existTable = themeDBHelper.existTableGipsyOver100();
                    if (!items.isEmpty()) {
                        Log.i("mLog", "Tread" + Thread.currentThread().getName() + "===========Gipsy items has " + items.size());
                        for (ThemeItem t : items) {
                            GipsyOver100 model = new GipsyOver100(t.getTitle(), t.getLink(), t.getUser());
                            dataList.add(model);
                            if (existTable && !themeDBHelper.isAuthorInBlackList(t.getUser())
                                    && themeDBHelper.notExistRecord(model) && hasNotManyViews(t.getViews())) {
                                Log.i("mLog", "=========== new element ");
                                NotificationManager.sendNotificationsGipsy(getApplicationContext(), t);
                            }
                        }
                        themeDBHelper.saveDataGipsyOver100(dataList);
                    }
                }, throwableConsumer));
    }

    private boolean hasNotManyViews(String views) {
        int viewInt = 0;
        try {
            viewInt = Integer.parseInt(views.replace(".", ""));
        } catch (NumberFormatException e) {
            //ignore
        }
        return viewInt < 20;
    }

}
