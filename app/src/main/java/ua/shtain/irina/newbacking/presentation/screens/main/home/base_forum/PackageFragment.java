package ua.shtain.irina.newbacking.presentation.screens.main.home.base_forum;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import ua.shtain.irina.newbacking.R;
import ua.shtain.irina.newbacking.domain.ThemeRepository;
import ua.shtain.irina.newbacking.presentation.base.content.RefreshableFragment;
import ua.shtain.irina.newbacking.presentation.base.content.RefreshablePresenter;
import ua.shtain.irina.newbacking.presentation.screens.main.home.adapters.ThemeAdapter;
import ua.shtain.irina.newbacking.presentation.screens.main.home.adapters.ThemeDH;

/**
 * Created by Irina Shtain on 14.11.2017.
 */

@EFragment
public abstract class PackageFragment extends RefreshableFragment implements PackageContract.View {

    @Bean
    protected ThemeAdapter themeAdapter;
    @ViewById
    protected RecyclerView rvListItems_FP;
    @Bean
    protected ThemeRepository repository;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_package;
    }

    private PackageContract.Presenter presenter;

    @Override
    protected RefreshablePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(PackageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initUI() {
        mActivity.getToolbarManager().setTitle(R.string.title_forums);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        rvListItems_FP.setAdapter(themeAdapter);
        rvListItems_FP.setLayoutManager(layoutManager);

        themeAdapter.setOnCardClickListener((view, position, viewType) -> {
            switch (view.getId()) {
                case R.id.tvAuthor_LIT:
                    presenter.authorPressed(themeAdapter.getItem(position));
                    break;
                case R.id.cvRideContainer_LIT:
                    presenter.themePressed(themeAdapter.getItem(position));
                    break;
            }
        });
        presenter.subscribe();
    }

    @Override
    public void setThemes(ArrayList<ThemeDH> themeDHs) {
        themeAdapter.setListDH(themeDHs);
    }

    @Override
    public void open(String url) {
        Uri address = Uri.parse(url);
        Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
        startActivity(openlinkIntent);
    }

    @Override
    public void showAlert(String author) {
        AlertDialog d = new AlertDialog.Builder(getContext(), R.style.DialogTheme)
                .setMessage(getString(R.string.question_want_add_user, author))
                .setPositiveButton(R.string.button_yes, (dialog, which) -> {
                    presenter.addBlackList(author);
                    dialog.dismiss();
                })
                .setNegativeButton(R.string.button_no, null)
                .create();

        d.setOnShowListener(dialog -> d.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(ContextCompat.getColor(getContext(), R.color.textColorGray)));

        d.show();
    }
}
