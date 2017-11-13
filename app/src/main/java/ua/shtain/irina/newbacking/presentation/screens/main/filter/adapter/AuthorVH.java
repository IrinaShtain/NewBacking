package ua.shtain.irina.newbacking.presentation.screens.main.filter.adapter;

import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;

import ua.shtain.irina.newbacking.R;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

public class AuthorVH extends RecyclerVH<AuthorDH> {
    private TextView tvAuthorName_LIT;

    public AuthorVH(View itemView) {
        super(itemView);
        tvAuthorName_LIT = findView(R.id.tvAuthorName_LIT);
    }

    @Override
    public void setListeners(OnCardClickListener listener) {

    }

    @Override
    public void bindData(AuthorDH data) {
        tvAuthorName_LIT.setText(data.getName());
    }
}
