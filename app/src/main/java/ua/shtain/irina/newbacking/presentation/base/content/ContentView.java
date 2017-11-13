package ua.shtain.irina.newbacking.presentation.base.content;

import ua.shtain.irina.newbacking.presentation.utils.Constants;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

public interface ContentView {
    void showProgressMain();
    void showProgressPagination();
    void hideProgress();
    void showMessage(Constants.MessageType messageType);
    void showCustomMessage(String msg, boolean isDangerous);
    void showPlaceholder(Constants.PlaceholderType placeholderType);
}
