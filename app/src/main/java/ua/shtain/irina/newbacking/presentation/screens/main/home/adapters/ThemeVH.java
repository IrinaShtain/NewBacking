package ua.shtain.irina.newbacking.presentation.screens.main.home.adapters;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;

import java.util.concurrent.TimeUnit;

import ua.shtain.irina.newbacking.R;
import ua.shtain.irina.newbacking.presentation.utils.Constants;

/**
 * Created by Irina Shtain on 14.11.2017.
 */

public class ThemeVH extends RecyclerVH<ThemeDH> {
    private TextView tvTitle_LIT;
    private TextView tvViewsCount_LIT;
    private TextView tvAuthor_LIT;
    private TextView tvCommentsCount_LIT;
    private CardView cvRideContainer_LIT;

    public ThemeVH(View itemView) {
        super(itemView);

        tvTitle_LIT = findView(R.id.tvTitle_LIT);
        tvViewsCount_LIT = findView(R.id.tvViewsCount_LIT);
        tvAuthor_LIT = findView(R.id.tvAuthor_LIT);
        tvCommentsCount_LIT = findView(R.id.tvCommentsCount_LIT);
        cvRideContainer_LIT = findView(R.id.cvRideContainer_LIT);
    }

    @Override
    public void setListeners(OnCardClickListener listener) {
        RxView.clicks(cvRideContainer_LIT)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe(o -> listener.onClick(cvRideContainer_LIT, getAdapterPosition(), getItemViewType()));
        RxView.clicks(tvAuthor_LIT)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe(o -> listener.onClick(tvAuthor_LIT, getAdapterPosition(), getItemViewType()));
    }

    @Override
    public void bindData(ThemeDH data) {
        tvTitle_LIT.setText(data.getTitle());
        tvAuthor_LIT.setText(data.getAuthor());
        tvViewsCount_LIT.setText(data.getQuantityVisitors());
        tvCommentsCount_LIT.setText(data.getQuantityComments());
    }
}
