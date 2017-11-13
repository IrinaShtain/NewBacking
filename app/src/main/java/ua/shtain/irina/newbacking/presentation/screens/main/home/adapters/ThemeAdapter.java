package ua.shtain.irina.newbacking.presentation.screens.main.home.adapters;

import android.support.annotation.NonNull;
import android.view.View;

import com.michenko.simpleadapter.RecyclerAdapter;
import com.michenko.simpleadapter.RecyclerVH;

import org.androidannotations.annotations.EBean;

import ua.shtain.irina.newbacking.R;

/**
 * Created by Irina Shtain on 14.11.2017.
 */

@EBean
public class ThemeAdapter extends RecyclerAdapter<ThemeDH> {
    @NonNull
    @Override
    protected RecyclerVH<ThemeDH> createVH(View view, int viewType) {
        return new ThemeVH(view);
    }

    @Override
    protected int getLayoutRes(int viewType) {
        return R.layout.list_item_view_theme;
    }
}
