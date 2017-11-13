package ua.shtain.irina.newbacking.presentation.screens.main.home.PS_long_term;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EFragment;

import ua.shtain.irina.newbacking.presentation.screens.main.home.base_forum.PackageFragment;

/**
 * Created by Irina Shtain on 14.11.2017.
 */

@EFragment
public class PokerStrategyForthFragment extends PackageFragment {
    @AfterInject
    @Override
    public void initPresenter() {
        new PokerStrategyForthPresenter(this, repository);
    }
}
