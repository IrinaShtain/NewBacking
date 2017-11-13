package ua.shtain.irina.newbacking.presentation.screens.main.home.base_forum;

import android.util.Log;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import ua.shtain.irina.newbacking.data.exeptions.ConnectionLostException;
import ua.shtain.irina.newbacking.data.model.ThemeItem;
import ua.shtain.irina.newbacking.presentation.screens.main.home.adapters.ThemeDH;
import ua.shtain.irina.newbacking.presentation.utils.Constants;

/**
 * Created by Irina Shtain on 14.11.2017.
 */

public abstract class PackagePresenter implements PackageContract.Presenter {

    private PackageContract.View view;
    private PackageContract.Model model;
    private CompositeDisposable compositeDisposable;
    private ArrayList<ThemeItem> data;

    public PackagePresenter(PackageContract.View view, PackageContract.Model model) {
        this.view = view;
        this.model = model;
        compositeDisposable = new CompositeDisposable();

        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        view.showProgressMain();
        loadData();
    }

    @Override
    public void themePressed(ThemeDH item) {
        view.open(item.getUrl());
    }

    @Override
    public void authorPressed(ThemeDH item) {
        view.showAlert(item.getAuthor());
    }

    @Override
    public void addBlackList(String author) {
        if (model.addAuthor(author)) {
            view.showMessage(Constants.MessageType.USER_ADDED);
        } else
            view.showMessage(Constants.MessageType.UNKNOWN);
    }

    private void loadData() {
        compositeDisposable.add(
                model.getThemes(getUrl(), getForumType())
                        .subscribe(items -> {
                            view.hideProgress();
                            if (!items.isEmpty()) {
                                data = items;
                                view.setThemes(prepareThemes(items));
                            } else
                                view.showPlaceholder(Constants.PlaceholderType.EMPTY);
                        }, throwableConsumer)
        );
    }

    private ArrayList<ThemeDH> prepareThemes(ArrayList<ThemeItem> items) {
        ArrayList<ThemeDH> themes = new ArrayList<>();
        for (ThemeItem t : items) {
            themes.add(new ThemeDH(t));
        }
        return themes;
    }

    protected abstract String getUrl();

    protected abstract int getForumType();

    @Override
    public void onRefresh() {
        loadData();
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    private Consumer<Throwable> throwableConsumer = throwable -> {
        Log.d("myLogs", "Error! " + throwable.getMessage());
        throwable.printStackTrace();
        view.hideProgress();
        if (data != null)
            if (throwable instanceof ConnectionLostException) {
                view.showMessage(Constants.MessageType.CONNECTION_PROBLEMS);
            } else {
                view.showMessage(Constants.MessageType.UNKNOWN);
            }
        else if (throwable instanceof ConnectionLostException) {
            view.showPlaceholder(Constants.PlaceholderType.NETWORK);
        } else {
            view.showPlaceholder(Constants.PlaceholderType.UNKNOWN);
        }
    };

}
