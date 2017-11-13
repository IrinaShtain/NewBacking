package ua.shtain.irina.newbacking.presentation.base;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SystemService;

import ua.shtain.irina.newbacking.presentation.utils.ToolbarManager;


/**
 * Created by Irina Shtain on 13.11.2017.
 */
@EActivity
public abstract class BaseActivity extends AppCompatActivity {

    @SystemService
    protected InputMethodManager inputMethodManager;
    @Bean
    protected ToolbarManager toolbarManager;

    @IdRes
    protected abstract int getContainer();

    protected abstract Toolbar getToolbar();

    @AfterViews
    public void initToolbar() {
        toolbarManager.init(this, getToolbar());
    }

    public void replaceFragment(BaseFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(getContainer(), fragment)
                .commit();
    }

    public void addFragment(BaseFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(getContainer(), fragment)
                .commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        hideKeyboard();
    }

    protected void hideKeyboard() {
        if (getCurrentFocus() != null)
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    public ToolbarManager getToolbarManager() {
        return toolbarManager;
    }
}

