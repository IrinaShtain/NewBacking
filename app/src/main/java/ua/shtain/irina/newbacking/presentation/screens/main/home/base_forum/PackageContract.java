package ua.shtain.irina.newbacking.presentation.screens.main.home.base_forum;

import java.util.ArrayList;

import io.reactivex.Observable;
import ua.shtain.irina.newbacking.data.model.ThemeItem;
import ua.shtain.irina.newbacking.presentation.base.BaseView;
import ua.shtain.irina.newbacking.presentation.base.content.ContentView;
import ua.shtain.irina.newbacking.presentation.base.content.RefreshablePresenter;
import ua.shtain.irina.newbacking.presentation.screens.main.home.adapters.ThemeDH;

/**
 * Created by Irina Shtain on 14.11.2017.
 */

public interface PackageContract {

    interface View extends BaseView<Presenter>, ContentView {
        void setThemes(ArrayList<ThemeDH> themeDHs);
        void open(String url);
        void showAlert(String author);
    }

    interface Presenter extends RefreshablePresenter {

        void themePressed(ThemeDH item);
        void authorPressed(ThemeDH item);
        void addBlackList(String author);
    }

    interface Model {
        Observable<ArrayList<ThemeItem>> getThemes(String url, int forumType);
        boolean addAuthor(String authorName);
    }
}
