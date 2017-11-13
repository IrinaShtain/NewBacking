package ua.shtain.irina.newbacking.data.db;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.androidannotations.annotations.EBean;

import java.util.List;

import ua.shtain.irina.newbacking.data.model.Author;

/**
 * Created by Irina Shtain on 13.11.2017.
 */
@EBean(scope = EBean.Scope.Singleton)
public class AuthorDBHelper {

    public boolean addUser(String authorName) {
        Author author = new Author(authorName);
        return author.save();
    }

    public boolean removeUser(String authorName) {
        Author author = new Author(authorName);
        return author.delete();
    }

    public List<Author> getUsers() {
        return SQLite.select().
                from(Author.class).
                queryList();
    }
}
