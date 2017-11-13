package ua.shtain.irina.newbacking.domain;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import io.reactivex.Observable;
import ua.shtain.irina.newbacking.BackingApplication;
import ua.shtain.irina.newbacking.data.db.AuthorDBHelper;
import ua.shtain.irina.newbacking.data.exeptions.ConnectionLostException;
import ua.shtain.irina.newbacking.data.model.ThemeItem;
import ua.shtain.irina.newbacking.presentation.screens.main.home.base_forum.PackageContract;
import ua.shtain.irina.newbacking.presentation.utils.Constants;

import static ua.shtain.irina.newbacking.presentation.utils.ParseManager.parseGipsyForum;
import static ua.shtain.irina.newbacking.presentation.utils.ParseManager.parseStrategyForum;

/**
 * Created by Irina Shtain on 134.11.2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class ThemeRepository extends NetworkRepository implements PackageContract.Model {

    @Bean
    protected AuthorDBHelper authorHelper;
    @App
    protected BackingApplication app;

    @Override
    public Observable<ArrayList<ThemeItem>> getThemes(String url, int forumType) {
        return getNetworkObservable(Observable.fromCallable(() -> getData(url, forumType)));
    }

    @Override
    public boolean addAuthor(String authorName) {
        return authorHelper.addUser(authorName);
    }

    private ArrayList<ThemeItem> getData(String url, int forumType) {
        if (!app.hasInternetConnection()) throw new ConnectionLostException();
        if (forumType == Constants.POKER_STRATEGY)
            return parseStrategyForum(url);
        else
            return parseGipsyForum(url);
    }

}
