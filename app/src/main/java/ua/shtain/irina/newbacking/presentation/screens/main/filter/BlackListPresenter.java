package ua.shtain.irina.newbacking.presentation.screens.main.filter;

import java.util.ArrayList;
import java.util.List;

import ua.shtain.irina.newbacking.data.model.Author;
import ua.shtain.irina.newbacking.presentation.screens.main.filter.adapter.AuthorDH;
import ua.shtain.irina.newbacking.presentation.utils.Constants;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

public class BlackListPresenter implements BlackListContract.Presenter {

    private BlackListContract.View view;
    private BlackListContract.Model model;
    private int totalCount = 0;

    public BlackListPresenter(BlackListContract.View view, BlackListContract.Model model) {
        this.view = view;
        this.model = model;

        view.setPresenter(this);
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void subscribe() {
        ArrayList<AuthorDH> authorDHs = prepareList(model.getUsers());
        if (!authorDHs.isEmpty())
            view.setAuthor(authorDHs);
        else
            view.showNoUsersPlaceholder();

    }

    @Override
    public void removeUser(AuthorDH item, int position) {
        if (model.removeAuthor(item.getName())) {
            --totalCount;
            view.removeItem(position);
            view.showMessage(Constants.MessageType.USER_DELETED);
            if (totalCount == 0)
                view.showNoUsersPlaceholder();
        } else
            view.showMessage(Constants.MessageType.UNKNOWN);
    }

    @Override
    public void showAddingDialog() {
        view.openDialog();
    }

    @Override
    public void addAuthor(String name) {
        if (model.addAuthor(name)) {
            ++totalCount;
            view.addAuthor(new AuthorDH(new Author(name)));
            view.showMessage(Constants.MessageType.USER_ADDED);
        } else
            view.showMessage(Constants.MessageType.UNKNOWN);

    }

    private ArrayList<AuthorDH> prepareList(List<Author> authors) {
        ArrayList<AuthorDH> authorDHs = new ArrayList<>();
        for (Author author : authors) {
            authorDHs.add(new AuthorDH(author));
        }
        totalCount = authorDHs.size();
        return authorDHs;
    }
}
