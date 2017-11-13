package ua.shtain.irina.newbacking.domain;

import android.util.Log;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import io.reactivex.Observable;
import ua.shtain.irina.newbacking.BackingApplication;
import ua.shtain.irina.newbacking.data.exeptions.ConnectionLostException;
import ua.shtain.irina.newbacking.data.model.ThemeItem;
import ua.shtain.irina.newbacking.presentation.utils.Constants;

import static java.util.concurrent.TimeUnit.SECONDS;
import static ua.shtain.irina.newbacking.presentation.utils.ParseManager.parseGipsyForum;
import static ua.shtain.irina.newbacking.presentation.utils.ParseManager.parseStrategyForum;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class ServiceThemeRepository extends NetworkRepository {
    @App
    protected BackingApplication app;

    public Observable<ArrayList<ThemeItem>> getServiceThemes(String url, int forumType) {
        return getNetworkFixedObservable(Observable.fromCallable(() -> getData(url, forumType)))
                .repeatWhen(observable -> observable.delay(Constants.TIME_DELAY, SECONDS))
                .doOnError(throwable -> Log.i("mLog", "************ ServiceThemeRepository: getServiceThemes "+ throwable.getMessage()))
                .retryWhen(throwableObservable -> throwableObservable.delay(Constants.ERROR_TIME_DELAY, SECONDS));
    }

    private ArrayList<ThemeItem> getData(String url, int forumType) {
        if (!app.hasInternetConnection()) throw new ConnectionLostException();
        if (forumType == Constants.POKER_STRATEGY)
            return parseStrategyForum(url);
        else
            return parseGipsyForum(url);
    }
}
