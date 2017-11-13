package ua.shtain.irina.newbacking.data.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Irina Shtain on 13.11.2017.
 */
@Database(name = BackingDatabase.NAME, version = BackingDatabase.VERSION)
public class BackingDatabase {
    public static final String NAME = "BackingDatabase";

    public static final int VERSION = 1;
}
