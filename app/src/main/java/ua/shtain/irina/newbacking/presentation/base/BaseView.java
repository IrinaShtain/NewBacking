package ua.shtain.irina.newbacking.presentation.base;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

public interface BaseView<T extends BasePresenter> {
    void initPresenter();
    void setPresenter(T presenter);
}

