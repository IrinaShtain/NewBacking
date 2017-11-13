package ua.shtain.irina.newbacking.presentation.base;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import ua.shtain.irina.newbacking.R;
import ua.shtain.irina.newbacking.presentation.screens.main.home.HomeFragment_;

/**
 * Created by Irina Shtain on 02.10.2017.
 */

@EActivity
public abstract class DrawerActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @ViewById
    protected Toolbar toolbar_AD;
    @ViewById
    protected DrawerLayout drawerLayout_AD;
    @ViewById
    protected NavigationView nvDrawer_AD;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected int getContainer() {
        return R.id.flFragmentContent_AD;
    }

    @Override
    protected Toolbar getToolbar() {
        return toolbar_AD;
    }

    @AfterViews
    protected void initUI() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout_AD, toolbar_AD, R.string.drawer_open, R.string.drawer_close);
        drawerLayout_AD.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        nvDrawer_AD.setNavigationItemSelectedListener(this);

        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            addFragment(HomeFragment_.builder().build());
            nvDrawer_AD.getMenu().getItem(0).setChecked(true);
        }
    }
}

