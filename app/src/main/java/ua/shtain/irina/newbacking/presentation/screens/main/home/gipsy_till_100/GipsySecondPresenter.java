package ua.shtain.irina.newbacking.presentation.screens.main.home.gipsy_till_100;

import ua.shtain.irina.newbacking.presentation.screens.main.home.base_forum.PackageContract;
import ua.shtain.irina.newbacking.presentation.screens.main.home.base_forum.PackagePresenter;
import ua.shtain.irina.newbacking.presentation.utils.Constants;

/**
 * Created by Irina Shtain on 14.11.2017.
 */

public class GipsySecondPresenter extends PackagePresenter {

    public GipsySecondPresenter(PackageContract.View view, PackageContract.Model model) {
        super(view, model);
    }

    @Override
    protected String getUrl() {
        return Constants.GIPSY_TEAM_OVER_100;
    }

    @Override
    protected int getForumType() {
        return Constants.GIPSY_TEAM;
    }
}
