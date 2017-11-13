package ua.shtain.irina.newbacking.presentation.screens.splash;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import ua.shtain.irina.newbacking.R;
import ua.shtain.irina.newbacking.presentation.screens.main.MainActivity_;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

@EActivity(R.layout.activity_splash)
public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    private SplashContract.Presenter presenter;
    private AnimatorSet animatorSet;

    @ViewById
    protected TextView tvLogo_AS;
    @ViewById
    protected ImageView ivLogo_AS;


    @AfterInject
    @Override
    public void initPresenter() {
        new SplashPresenter(this);
    }

    @AfterViews
    protected void init() {
        presenter.subscribe();
    }

    public void setPresenter(SplashContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void runSplashAnimation() {
        animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(tvLogo_AS, View.ALPHA, 0, 1),
                ObjectAnimator.ofFloat(ivLogo_AS, View.ALPHA, 0, 1),
                ObjectAnimator.ofFloat(ivLogo_AS, View.SCALE_X, 0, 1),
                ObjectAnimator.ofFloat(tvLogo_AS, View.SCALE_X, 0, 1));
        animatorSet.setDuration(1000);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                presenter.startNextScreen();
            }
        });
        animatorSet.start();
    }


    @Override
    public void openHome() {
        MainActivity_.intent(this).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
        if (animatorSet.isRunning()) animatorSet.cancel();
    }
}
