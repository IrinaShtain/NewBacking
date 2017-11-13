package ua.shtain.irina.newbacking.presentation.screens.main.settings;

import ua.shtain.irina.newbacking.presentation.base.BasePresenter;
import ua.shtain.irina.newbacking.presentation.base.BaseView;
import ua.shtain.irina.newbacking.presentation.base.content.ContentView;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

public interface SettingsContract {
    interface View extends BaseView<Presenter>, ContentView {
        void setGipsyMonitoringEnabled(boolean isEnabled);
        void setPokerStrategyEnabled(boolean isEnabled);
        void saveGipsyMonitoringPolling(boolean isEnabled);
        void savePokerStrategyPolling(boolean isEnabled);
    }

    interface Presenter extends BasePresenter {
        void enableGipsyMonitoring(boolean isEnabled);
        void enablePokerStrategy(boolean isEnabled);
    }

    interface Model {

    }
}
