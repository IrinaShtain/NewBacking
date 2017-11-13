package ua.shtain.irina.newbacking.data.model;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

public class ThemeItem {
    private String title;
    private String link;
    private String user;
    private String views;
    private String comments;

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

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ThemeItem() {
    }
}
