package ua.shtain.irina.newbacking.presentation.screens.main.home.PS_over_500;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EFragment;

import ua.shtain.irina.newbacking.presentation.screens.main.home.base_forum.PackageFragment;

/**
 * Created by Irina Shtain on 14.11.2017.
 */

@EFragment
public class PokerStrategyThirdFragment extends PackageFragment {
    @AfterInject
    @Override
    public void initPresenter() {
        new PokerStrategyThirdPresenter(this, repository);
    }
}
