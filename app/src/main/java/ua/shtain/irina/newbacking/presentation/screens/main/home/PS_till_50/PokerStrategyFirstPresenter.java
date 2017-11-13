package ua.shtain.irina.newbacking.presentation.screens.main.home.PS_till_50;

import ua.shtain.irina.newbacking.presentation.screens.main.home.base_forum.PackageContract;
import ua.shtain.irina.newbacking.presentation.screens.main.home.base_forum.PackagePresenter;
import ua.shtain.irina.newbacking.presentation.utils.Constants;

/**
 * Created by Irina Shtain on 14.11.2017.
 */

public class PokerStrategyFirstPresenter extends PackagePresenter {

    public PokerStrategyFirstPresenter(PackageContract.View view, PackageContract.Model model) {
        super(view, model);
    }

    @Override
    protected String getUrl() {
        return Constants.POKER_STRATEGY_TILL_50;
    }

    @Override
    protected int getForumType() {
        return Constants.POKER_STRATEGY;
    }
}
