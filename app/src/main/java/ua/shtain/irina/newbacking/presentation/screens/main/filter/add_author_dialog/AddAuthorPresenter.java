package ua.shtain.irina.newbacking.presentation.screens.main.filter.add_author_dialog;

import android.text.TextUtils;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

public class AddAuthorPresenter implements AddAuthorContract.Presenter {
    private AddAuthorContract.View view;

    public AddAuthorPresenter(AddAuthorContract.View view) {
        this.view = view;

        view.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void addAuthor(String s) {
        if (TextUtils.isEmpty(s.trim()))
            view.showErrorMsg();
        else
            view.closeDialog(s);
    }
}
