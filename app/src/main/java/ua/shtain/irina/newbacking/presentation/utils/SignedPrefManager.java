package ua.shtain.irina.newbacking.presentation.utils;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by Irina Shtain on 13.11.2017.
 */
@EBean(scope = EBean.Scope.Singleton)
public class SignedPrefManager {
    @Pref
    protected SharedPrefManager_ prefManager;

    public void needPollingStrategy(boolean isEnabled) {
        prefManager.edit()
                .isStrategyNeeded()
                .put(isEnabled)
                .apply();
    }

    public void needPollingGipsy(boolean isEnabled) {
        prefManager.edit()
                .isGipsyNeeded()
                .put(isEnabled)
                .apply();
    }

    public boolean getPollingStrategy() {
        return prefManager.isStrategyNeeded().get();
    }

    public boolean getPollingGipsy() {
        return prefManager.isGipsyNeeded().get();
    }


}
