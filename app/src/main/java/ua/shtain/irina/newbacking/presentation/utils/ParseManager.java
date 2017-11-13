package ua.shtain.irina.newbacking.presentation.utils;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import ua.shtain.irina.newbacking.data.model.ThemeItem;

/**
 * Created by Irina Shtain on 14.11.2017.
 */

public abstract class ParseManager {

    public static ArrayList<ThemeItem> parseStrategyForum(String url) {
        ArrayList<ThemeItem> themes = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).timeout(10 * 1000).get();//can wait for 10 sec
            Log.i("mLog", "Tread" + Thread.currentThread().getName() + "=========== Connection has " + url);
            Elements elements = document
                    .select(".forumthreads__content").last()
                    .select(".titleBox");
            for (Element element : elements) {
                Element elementUser = element
                        .select(".forum--board--thread-overview-threadStarter").first();
                Element elementComments = element.getElementsByClass("forum--boardOverview-threadAnswers").first();
                Element elementTitle = element.select("a").first();
                Element elementLink = element.select("a[href]").first();
                Element elementViews = element.select("p").first();

                if (elementTitle != null) {
                    String[] views = elementViews.text().trim().split(" ");
                    String[] comments = elementComments.text().trim().split(" ");
                    ThemeItem themeItem = new ThemeItem();
                    themeItem.setTitle(elementTitle.text());
                    themeItem.setLink(elementLink.attr("abs:href"));
                    themeItem.setUser(elementUser.text());
                    themeItem.setViews(views[0]);
                    themeItem.setComments(comments[0]);
                    themes.add(themeItem);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("mLog", "Tread" + Thread.currentThread().getName() + "===========Connection lost " + url);
        }
        return themes;
    }

    public static ArrayList<ThemeItem> parseGipsyForum(String url) {
        ArrayList<ThemeItem> themes = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).timeout(10 * 1000).get(); //can wait for 10 sec
            Log.i("mLog", "Tread" + Thread.currentThread().getName() + "===========Connection has " + url);
            Elements elements = document.select(".new");
            for (Element element : elements) {
                Element elementTitle = element.select("h2").first().select("a").first();
                Element elementUser = element.select(".user").first();
                Element elementViews = element.select(".read").first();
                Element elementComments = element.select(".num_posts").first();
                String[] stringsViews = elementViews.text().trim().split(" ");
                String[] stringsComments = elementComments.text().trim().split(" ");

                ThemeItem themeItem = new ThemeItem();
                themeItem.setTitle(elementTitle.text());
                themeItem.setLink(elementTitle.attr("abs:href"));
                themeItem.setUser(elementUser.text());
                themeItem.setViews(stringsViews[0]);
                themeItem.setComments(stringsComments[0]);

                themes.add(themeItem);
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.i("mLog", "Tread" + Thread.currentThread().getName() + "===========Connection lost " + url);
        }
        return themes;
    }

}
