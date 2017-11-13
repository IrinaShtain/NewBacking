package ua.shtain.irina.newbacking.domain;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

import ua.shtain.irina.newbacking.data.db.AuthorDBHelper;
import ua.shtain.irina.newbacking.data.model.Author;
import ua.shtain.irina.newbacking.presentation.screens.main.filter.BlackListContract;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class AuthorRepository implements BlackListContract.Model {
    @Bean
    AuthorDBHelper authorHelper;

    @Override
    public boolean addAuthor(String authorName) {
        return authorHelper.addUser(authorName);
    }

    @Override
    public boolean removeAuthor(String authorName) {
        return authorHelper.removeUser(authorName);
    }

    @Override
    public List<Author> getUsers() {
        return authorHelper.getUsers();
    }
}
