package ua.shtain.irina.newbacking.presentation.screens.splash;

import ua.shtain.irina.newbacking.presentation.base.BasePresenter;
import ua.shtain.irina.newbacking.presentation.base.BaseView;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

public interface SplashContract {
    interface View extends BaseView<Presenter> {
        void openHome();
        void runSplashAnimation();
    }

    interface Presenter extends BasePresenter {
        void startNextScreen();
    }
}
