package ua.shtain.irina.newbacking.presentation.screens.main.filter.add_author_dialog;

import ua.shtain.irina.newbacking.presentation.base.BasePresenter;
import ua.shtain.irina.newbacking.presentation.base.BaseView;

/**
 * Created by Irina Shtain on 13.11.2017.
 */
public interface AddAuthorContract {
    interface View extends BaseView<Presenter> {
        void closeDialog(String amount);
        void showErrorMsg();
    }
    interface Presenter extends BasePresenter {
        void addAuthor(String s);
    }

}
