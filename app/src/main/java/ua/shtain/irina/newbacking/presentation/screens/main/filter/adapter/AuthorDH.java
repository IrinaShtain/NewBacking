package ua.shtain.irina.newbacking.presentation.screens.main.filter.adapter;

import com.michenko.simpleadapter.RecyclerDH;

import ua.shtain.irina.newbacking.data.model.Author;

/**
 * Created by Irina Shtain on 13.11.2017.
 */
public class AuthorDH implements RecyclerDH {
    private Author author;

    public AuthorDH(Author author) {
        this.author = author;
    }

    public String getName() {
        return author.getUserName();
    }
}
