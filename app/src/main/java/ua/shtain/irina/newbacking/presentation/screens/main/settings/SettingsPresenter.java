package ua.shtain.irina.newbacking.presentation.screens.main.settings;

import android.util.Log;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

public class SettingsPresenter implements SettingsContract.Presenter {

    private SettingsContract.View view;
    private boolean needStrategy;
    private boolean needGipsy;

    public SettingsPresenter(SettingsContract.View view, boolean needStrategy, boolean needGipsy) {
        this.view = view;
        this.needStrategy = needStrategy;
        this.needGipsy = needGipsy;

        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        view.setGipsyMonitoringEnabled(needGipsy);
        view.setPokerStrategyEnabled(needStrategy);
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void enableGipsyMonitoring(boolean isEnabled) {
        Log.i("mLog", "***** enableGipsyMonitoring " + isEnabled);
        view.saveGipsyMonitoringPolling(isEnabled);
    }

    @Override
    public void enablePokerStrategy(boolean isEnabled) {
        Log.i("mLog", "***** enablePokerStrategy " + isEnabled);
        view.savePokerStrategyPolling(isEnabled);
    }
}