package ua.shtain.irina.newbacking.presentation.screens.main.filter;

import java.util.ArrayList;
import java.util.List;

import ua.shtain.irina.newbacking.data.model.Author;
import ua.shtain.irina.newbacking.presentation.base.BasePresenter;
import ua.shtain.irina.newbacking.presentation.base.BaseView;
import ua.shtain.irina.newbacking.presentation.base.content.ContentView;
import ua.shtain.irina.newbacking.presentation.screens.main.filter.adapter.AuthorDH;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

public interface BlackListContract {

    interface View extends BaseView<Presenter>, ContentView {
        void setAuthor(ArrayList<AuthorDH> blackListedAuthors);
        void openDialog();
        void addAuthor(AuthorDH author);

        void removeItem(int position);

        void showNoUsersPlaceholder();
    }

    interface Presenter extends BasePresenter {
        void addAuthor(String name);
        void showAddingDialog();
        void removeUser(AuthorDH item, int pos);
    }

    interface Model {
        boolean addAuthor(String authorName);
        boolean removeAuthor(String authorName);

        List<Author> getUsers();
    }
}
