package ua.shtain.irina.newbacking.data.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import ua.shtain.irina.newbacking.data.db.BackingDatabase;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

@Table(database = BackingDatabase.class)
public class Author extends BaseModel {

    @PrimaryKey
    @Column
    private String userName;

    public Author() {
    }

    public Author(String user) {

        this.userName = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
