package ua.shtain.irina.newbacking.presentation.screens.main.home.PS_till_50;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EFragment;

import ua.shtain.irina.newbacking.presentation.screens.main.home.base_forum.PackageFragment;

/**
 * Created by Irina Shtain on 14.11.2017.
 */

@EFragment
public class PokerStrategyFirstFragment extends PackageFragment {
    @AfterInject
    @Override
    public void initPresenter() {
        new PokerStrategyFirstPresenter(this, repository);
    }
}
