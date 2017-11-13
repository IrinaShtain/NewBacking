package ua.shtain.irina.newbacking.presentation.screens.splash;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

public class SplashPresenter  implements SplashContract.Presenter {

    private SplashContract.View view;

    public SplashPresenter(SplashContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        view.runSplashAnimation();
    }

    @Override
    public void unsubscribe() {
    }

    @Override
    public void startNextScreen() {
        view.openHome();
    }
}
