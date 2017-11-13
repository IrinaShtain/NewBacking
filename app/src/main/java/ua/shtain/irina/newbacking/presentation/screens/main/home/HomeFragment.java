package ua.shtain.irina.newbacking.presentation.screens.main.home;

import android.support.design.widget.TabLayout;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import ua.shtain.irina.newbacking.R;
import ua.shtain.irina.newbacking.presentation.base.BasePresenter;
import ua.shtain.irina.newbacking.presentation.base.content.ContentFragment;
import ua.shtain.irina.newbacking.presentation.custom.ScrollableViewPager;
import ua.shtain.irina.newbacking.presentation.screens.main.home.PS_from_50_to_100.PokerStrategySecondFragment_;
import ua.shtain.irina.newbacking.presentation.screens.main.home.PS_long_term.PokerStrategyForthFragment_;
import ua.shtain.irina.newbacking.presentation.screens.main.home.PS_over_500.PokerStrategyThirdFragment_;
import ua.shtain.irina.newbacking.presentation.screens.main.home.PS_till_50.PokerStrategyFirstFragment_;
import ua.shtain.irina.newbacking.presentation.screens.main.home.adapters.TabPagerAdapter;
import ua.shtain.irina.newbacking.presentation.screens.main.home.gipsy_over_100.GipsyFirstFragment_;
import ua.shtain.irina.newbacking.presentation.screens.main.home.gipsy_till_100.GipsySecondFragment_;

/**
 * Created by Irina Shtain on 14.11.2017.
 */

@EFragment
public class HomeFragment extends ContentFragment {

    public TabPagerAdapter tabPagerAdapter;

    @ViewById
    protected TabLayout tlTabs_FCT;
    @ViewById
    public ScrollableViewPager vpContent_FCT;

    @StringRes(R.string.title_packets_till_50)
    protected String titlePackets_50;
    @StringRes(R.string.title_packets_from_50_to_500)
    protected String titlePackets_500;
    @StringRes(R.string.title_packets_above_500)
    protected String titlePacketsMore_500;
    @StringRes(R.string.title_packets_long_term)
    protected String titlePacketsLongTerm;
    @StringRes(R.string.title_gipsy_till_100)
    protected String titleGipsyTill_100;
    @StringRes(R.string.title_gipsy_over_100)
    protected String titleGipsyOver_100;


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_content_tabs;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @AfterInject
    protected void initData() {
        tabPagerAdapter = new TabPagerAdapter(getChildFragmentManager());
        addFragmentsToAdapter(tabPagerAdapter);
    }

    public void addFragmentsToAdapter(TabPagerAdapter adapter) {
        adapter.addFragment(PokerStrategyFirstFragment_.builder().build(), titlePackets_50);
        adapter.addFragment(PokerStrategySecondFragment_.builder().build(), titlePackets_500);
        adapter.addFragment(PokerStrategyThirdFragment_.builder().build(), titlePacketsMore_500);
        adapter.addFragment(PokerStrategyForthFragment_.builder().build(), titlePacketsLongTerm);
        adapter.addFragment(GipsyFirstFragment_.builder().build(), titleGipsyTill_100);
        adapter.addFragment(GipsySecondFragment_.builder().build(), titleGipsyOver_100);
    }

    @AfterViews
    protected void initUI() {
        mActivity.getToolbarManager().enableToolbarElevation(false);
        vpContent_FCT.setAdapter(tabPagerAdapter);
        tlTabs_FCT.setupWithViewPager(vpContent_FCT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mActivity.getToolbarManager().enableToolbarElevation(true);
    }
}
