package ua.shtain.irina.newbacking.presentation.screens.main.home.adapters;

import com.michenko.simpleadapter.RecyclerDH;

import ua.shtain.irina.newbacking.data.model.ThemeItem;

/**
 * Created by Irina Shtain on 14.11.2017.
 */

public class ThemeDH implements RecyclerDH {
    private ThemeItem mThemeItem;

    public ThemeDH(ThemeItem themeItem) {
        mThemeItem = themeItem;
    }

    public String getTitle() {
        return mThemeItem.getTitle();
    }

    public String getQuantityVisitors() {
        return String.valueOf(mThemeItem.getViews());
    }

    public String getUrl() {
        return mThemeItem.getLink();
    }

    public String getAuthor() {
        return mThemeItem.getUser() == null ? "No data" : mThemeItem.getUser();
    }

    public String getQuantityComments() {
        return mThemeItem.getComments() == null ? "No data" : mThemeItem.getComments();
    }
}
