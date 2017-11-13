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
import ua.shtain.irina.newbacking.data.model.PokerStrategyFrom50To500;
import ua.shtain.irina.newbacking.data.model.PokerStrategyLongTerm;
import ua.shtain.irina.newbacking.data.model.PokerStrategyOver500;
import ua.shtain.irina.newbacking.data.model.PokerStrategyTill50;
import ua.shtain.irina.newbacking.data.model.ThemeItem;
import ua.shtain.irina.newbacking.domain.ServiceThemeRepository;
import ua.shtain.irina.newbacking.presentation.utils.Constants;
import ua.shtain.irina.newbacking.presentation.utils.NotificationManager;

/**
 * Created by Irina Shtain on 13.11.2017.
 */
@EService
public class PokerStrategyService extends Service {
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
        startPolingPSTill50();
        startPolingPSFrom50To500();
        startPolingPSOver500();
        startPolingPSLongTerm();
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

    private void startPolingPSTill50() {
        compositeDisposable.add(themeRepository.getServiceThemes(Constants.POKER_STRATEGY_TILL_50, Constants.POKER_STRATEGY)
                .subscribe(items -> {
                    ArrayList<PokerStrategyTill50> dataList = new ArrayList<>();
                    boolean existTable = themeDBHelper.existTablePSTill50();
                    if (!items.isEmpty()) {
                        Log.i("mLog", "Tread" + Thread.currentThread().getName() + "===========POKER_STRATEGY_TILL_50 items has " + items.size());
                        for (ThemeItem t : items) {
                            PokerStrategyTill50 model = new PokerStrategyTill50(t.getTitle(), t.getLink(), t.getUser());
                            dataList.add(model);
                            if (existTable && !themeDBHelper.isAuthorInBlackList(t.getUser()) &&
                                    themeDBHelper.notExistRecord(model) && hasNotManyViews(t.getViews())) {
                                Log.i("mLog", "=========== new element ");
                                NotificationManager.sendNotificationsStrategy(getApplicationContext(), t);
                            }
                        }
                        themeDBHelper.saveDataPSTill50(dataList);
                    } else
                        Log.i("mLog", "===========items no ");

                }, throwableConsumer));
    }

    private void startPolingPSFrom50To500() {
        compositeDisposable.add(themeRepository.getServiceThemes(Constants.POKER_STRATEGY_FROM_50_TO_500, Constants.POKER_STRATEGY)
                .subscribe(items -> {
                    ArrayList<PokerStrategyFrom50To500> dataList = new ArrayList<>();
                    boolean existTable = themeDBHelper.existTablePSFrom50To500();
                    if (!items.isEmpty()) {
                        Log.i("mLog", "Tread" + Thread.currentThread().getName() + "===========POKER_STRATEGY_TILL_50 items has " + items.size());
                        for (ThemeItem t : items) {
                            PokerStrategyFrom50To500 model = new PokerStrategyFrom50To500(t.getTitle(), t.getLink(), t.getUser());
                            dataList.add(model);
                            if (existTable && !themeDBHelper.isAuthorInBlackList(t.getUser())
                                    && themeDBHelper.notExistRecord(model) && hasNotManyViews(t.getViews())) {
                                Log.i("mLog", "=========== new element ");
                                NotificationManager.sendNotificationsStrategy(getApplicationContext(), t);
                            }
                        }
                        themeDBHelper.saveDataPSFrom50Till500(dataList);
                    } else
                        Log.i("mLog", "===========items no ");

                }, throwableConsumer));
    }

    private void startPolingPSOver500() {
        compositeDisposable.add(themeRepository.getServiceThemes(Constants.POKER_STRATEGY_FROM_OVER_500, Constants.POKER_STRATEGY)
                .subscribe(items -> {
                    ArrayList<PokerStrategyOver500> dataList = new ArrayList<>();
                    boolean existTable = themeDBHelper.existTablePSOver500();
                    if (!items.isEmpty()) {
                        Log.i("mLog", "Tread" + Thread.currentThread().getName() + "===========POKER_STRATEGY_TILL_50 items has " + items.size());
                        for (ThemeItem t : items) {
                            PokerStrategyOver500 model = new PokerStrategyOver500(t.getTitle(), t.getLink(), t.getUser());
                            dataList.add(model);
                            if (existTable && !themeDBHelper.isAuthorInBlackList(t.getUser())
                                    && themeDBHelper.notExistRecord(model) && hasNotManyViews(t.getViews())) {
                                Log.i("mLog", "=========== new element ");
                                NotificationManager.sendNotificationsStrategy(getApplicationContext(), t);
                            }
                        }
                        themeDBHelper.saveDataPSOver500(dataList);
                    } else
                        Log.i("mLog", "===========items no ");

                }, throwableConsumer));
    }

    private void startPolingPSLongTerm() {
        compositeDisposable.add(themeRepository.getServiceThemes(Constants.POKER_STRATEGY_LONG_TERM, Constants.POKER_STRATEGY)
                .subscribe(items -> {
                    ArrayList<PokerStrategyLongTerm> dataList = new ArrayList<>();
                    boolean existTable = themeDBHelper.existTablePSLongTerm();
                    if (!items.isEmpty()) {
                        Log.i("mLog", "Tread" + Thread.currentThread().getName() + "===========POKER_STRATEGY_TILL_50 items has " + items.size());
                        for (ThemeItem t : items) {
                            PokerStrategyLongTerm model = new PokerStrategyLongTerm(t.getTitle(), t.getLink(), t.getUser());
                            dataList.add(model);
                            if (existTable && !themeDBHelper.isAuthorInBlackList(t.getUser())
                                    && themeDBHelper.notExistRecord(model) && hasNotManyViews(t.getViews())) {
                                Log.i("mLog", "=========== new element ");
                                NotificationManager.sendNotificationsStrategy(getApplicationContext(), t);
                            }
                        }
                        themeDBHelper.saveDataPSLongTerm(dataList);
                    } else
                        Log.i("mLog", "===========items no ");
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

