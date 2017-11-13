package ua.shtain.irina.newbacking.presentation.utils;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import ua.shtain.irina.newbacking.R;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

public abstract class Constants {
    public static final int CLICK_DELAY = 600;
    public static final int TIME_DELAY = 20;
    public static final int ERROR_TIME_DELAY = 10;

    public static final int POKER_STRATEGY = 100;
    public static final int GIPSY_TEAM = 101;
    public static final int REQUEST_CODE_ADD_AUTHOR = 103;

    public static final String POKER_STRATEGY_TILL_50 = "https://ru.pokerstrategy.com/forum/board.php?boardid=413";
    public static final String POKER_STRATEGY_FROM_50_TO_500 = "https://ru.pokerstrategy.com/forum/board.php?boardid=387";
    public static final String POKER_STRATEGY_FROM_OVER_500 = "https://ru.pokerstrategy.com/forum/board.php?boardid=388";
    public static final String POKER_STRATEGY_LONG_TERM = "https://ru.pokerstrategy.com/forum/board.php?boardid=389";
    public static final String GIPSY_TEAM_TIL_100 = "http://forum.gipsyteam.ru/backing/forum?fid=43";
    public static final String GIPSY_TEAM_OVER_100 = "http://forum.gipsyteam.ru/backing/forum?fid=75";

    public static final String KEY_AUTHOR_NAME = "author_name";


    public enum MessageType {
        CONNECTION_PROBLEMS(R.string.err_msg_connection_problem, true),
        UNKNOWN(R.string.err_msg_something_goes_wrong, true),
        USER_ADDED(R.string.err_msg_user_has_been_added, false),
        USER_DELETED(R.string.err_msg_user_has_been_deleted, true);

        @StringRes
        private int messageRes;
        private boolean isDangerous;

        MessageType(@StringRes int messageRes, boolean isDangerous) {
            this.messageRes = messageRes;
            this.isDangerous = isDangerous;
        }

        public int getMessageRes() {
            return messageRes;
        }

        public boolean isDangerous() {
            return isDangerous;
        }
    }

    public enum PlaceholderType {
        NETWORK(R.string.err_msg_connection_problem, R.drawable.ic_cloud_off),
        UNKNOWN(R.string.err_msg_something_goes_wrong, R.drawable.ic_sentiment_dissatisfied),
        EMPTY(R.string.err_msg_no_data, R.drawable.ic_no_data);

        @StringRes
        private int messageRes;
        @DrawableRes
        private int iconRes;

        PlaceholderType(@StringRes int messageRes, @DrawableRes int iconRes) {
            this.messageRes = messageRes;
            this.iconRes = iconRes;
        }

        public int getMessageRes() {
            return messageRes;
        }

        public int getIconRes() {
            return iconRes;
        }
    }
}
