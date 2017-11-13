package ua.shtain.irina.newbacking.presentation.screens.main.settings;

import android.content.Intent;
import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import ua.shtain.irina.newbacking.R;
import ua.shtain.irina.newbacking.presentation.base.BasePresenter;
import ua.shtain.irina.newbacking.presentation.base.content.ContentFragment;
import ua.shtain.irina.newbacking.presentation.services.GipsyTeamService_;
import ua.shtain.irina.newbacking.presentation.services.PokerStrategyService_;
import ua.shtain.irina.newbacking.presentation.utils.SignedPrefManager;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

@EFragment
public class SettingsFragment extends ContentFragment implements SettingsContract.View {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_settings;
    }

    private SettingsContract.Presenter presenter;

    @ViewById
    protected SwitchCompat switchGipsy_FS;
    @ViewById
    protected SwitchCompat switchPoker_FS;

    @Bean
    protected SignedPrefManager prefManager;

    @AfterInject
    @Override
    public void initPresenter() {
        new SettingsPresenter(this,
                prefManager.getPollingStrategy(),
                prefManager.getPollingGipsy());
    }

    @Override
    public void setPresenter(SettingsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initUI() {
        mActivity.getToolbarManager().setTitle(R.string.title_settings);
        presenter.subscribe();
    }

    @Override
    public void setGipsyMonitoringEnabled(boolean isEnabled) {
        switchGipsy_FS.setOnCheckedChangeListener(null);
        switchGipsy_FS.setChecked(isEnabled);
        switchGipsy_FS.setOnCheckedChangeListener(gipsyMonitoringSwitchListener);
    }

    @Override
    public void setPokerStrategyEnabled(boolean isEnabled) {
        switchPoker_FS.setOnCheckedChangeListener(null);
        switchPoker_FS.setChecked(isEnabled);
        switchPoker_FS.setOnCheckedChangeListener(pokerStrategySwitchListener);
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    private CompoundButton.OnCheckedChangeListener gipsyMonitoringSwitchListener = (buttonView, isChecked) -> {
        presenter.enableGipsyMonitoring(isChecked);
    };

    private CompoundButton.OnCheckedChangeListener pokerStrategySwitchListener = (buttonView, isChecked) -> {
        presenter.enablePokerStrategy(isChecked);
    };

    @Override
    public void saveGipsyMonitoringPolling(boolean isEnabled) {
        prefManager.needPollingGipsy(isEnabled);
        Intent intent = new Intent(mActivity, GipsyTeamService_.class);
        if (isEnabled)
            mActivity.startService(intent);
        else
            mActivity.stopService(intent);
    }

    @Override
    public void savePokerStrategyPolling(boolean isEnabled) {
        prefManager.needPollingStrategy(isEnabled);
        Intent intent = new Intent(mActivity, PokerStrategyService_.class);
        if (isEnabled)
            mActivity.startService(intent);
        else
            mActivity.stopService(intent);
    }
}
