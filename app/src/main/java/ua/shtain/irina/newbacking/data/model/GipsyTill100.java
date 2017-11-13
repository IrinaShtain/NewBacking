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
public class GipsyTill100 extends BaseModel {
    @PrimaryKey
    @Column
    private String title;
    @Column
    private String link;
    @Column
    private String user;

    public GipsyTill100(String title, String link, String user) {
        this.title = title;
        this.link = link;
        this.user = user;
    }

    public GipsyTill100() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

