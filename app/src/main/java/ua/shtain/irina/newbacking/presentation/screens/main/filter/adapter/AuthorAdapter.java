package ua.shtain.irina.newbacking.presentation.screens.main.filter.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.michenko.simpleadapter.RecyclerAdapter;
import com.michenko.simpleadapter.RecyclerVH;

import org.androidannotations.annotations.EBean;

import ua.shtain.irina.newbacking.R;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

@EBean
public class AuthorAdapter extends RecyclerAdapter<AuthorDH> {
    @NonNull
    @Override
    protected RecyclerVH<AuthorDH> createVH(View view, int viewType) {
        return new AuthorVH(view);
    }

    @Override
    protected int getLayoutRes(int viewType) {
        return R.layout.list_item_viev_author;
    }

}
