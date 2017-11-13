package ua.shtain.irina.newbacking.presentation.screens.main;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import org.androidannotations.annotations.EActivity;

import ua.shtain.irina.newbacking.R;
import ua.shtain.irina.newbacking.presentation.base.DrawerActivity;
import ua.shtain.irina.newbacking.presentation.screens.main.filter.BlackListFragment_;
import ua.shtain.irina.newbacking.presentation.screens.main.settings.SettingsFragment_;

@EActivity(R.layout.activity_drawer)
public class MainActivity extends DrawerActivity {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout_AD.closeDrawers();

        if (item.isChecked()) {
            return false;
        }

        switch (item.getItemId()) {
            case R.id.menuHome:
                //replaceFragment(HomeFragment_.builder().build());
                break;
            case R.id.menuFilter:
                replaceFragment(BlackListFragment_.builder().build());
                break;
            case R.id.menuSettings:
                replaceFragment(SettingsFragment_.builder().build());
                break;

        }
        return true;
    }
}