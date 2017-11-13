package ua.shtain.irina.newbacking.presentation.utils;

import android.content.res.Resources;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import org.androidannotations.annotations.EBean;

import ua.shtain.irina.newbacking.presentation.base.BaseActivity;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

@EBean
public class ToolbarManager {

    private ActionBar actionBar;
    private Toolbar toolbar;
    private View.OnClickListener mNavigationClickListener;
    private float pxToolbarElevation;

    /**
     * Should be called after UI initialized
     */
    public void init(BaseActivity activity, Toolbar toolbar) {
        this.toolbar = toolbar;
        activity.setSupportActionBar(toolbar);
        actionBar = activity.getSupportActionBar();

        mNavigationClickListener = v -> activity.onBackPressed();

        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        Resources r = activity.getResources();
        pxToolbarElevation = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, r.getDisplayMetrics());
    }

    public void showHomeButton(boolean show) {
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(show);
        }
    }

    public void showHomeAsUp(boolean isShow) {
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(isShow);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    public View.OnClickListener getNavigationClickListener(boolean need) {
        return need ? mNavigationClickListener : null;
    }

    public void setTitle(@StringRes int title) {
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    public void setTitle(CharSequence title) {
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    public void enableToolbarElevation(boolean isEnabled) {
        actionBar.setElevation(isEnabled ? pxToolbarElevation : 0);
    }

}
