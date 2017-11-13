package ua.shtain.irina.newbacking.presentation.base.content;

import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import ua.shtain.irina.newbacking.R;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

@EFragment
public abstract class RefreshableFragment extends ContentFragment {


    @ViewById
    protected SwipeRefreshLayout swipeContainer_VC;

    protected abstract RefreshablePresenter getPresenter();

    @LayoutRes
    protected int getRootLayoutRes() {
        return R.layout.view_content_refreshable;
    }

    @AfterViews
    protected void initRefreshing() {
        swipeContainer_VC.setEnabled(false);
        swipeContainer_VC.setColorSchemeColors(ContextCompat.getColor(mActivity, R.color.colorPrimaryDark));
        swipeContainer_VC.setEnabled(true);
        swipeContainer_VC.setOnRefreshListener(() -> getPresenter().onRefresh());
    }

    public void enableRefreshing(boolean isEnabled) {
        swipeContainer_VC.setEnabled(isEnabled);
    }

    public boolean isRefreshing() {
        return swipeContainer_VC.isRefreshing();
    }

    @Override
    public void showProgressPagination() {
        super.showProgressPagination();
        enableRefreshing(false);
    }

    public boolean isRefreshingEnabled() {
        return swipeContainer_VC.isEnabled();
    }

    @Override
    public void showProgressMain() {
        super.showProgressMain();
        enableRefreshing(false);
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        enableRefreshing(true);
        if(swipeContainer_VC.isRefreshing()) swipeContainer_VC.setRefreshing(false);
    }
}
