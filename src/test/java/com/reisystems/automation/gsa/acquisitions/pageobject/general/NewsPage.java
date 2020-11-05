package com.reisystems.automation.gsa.acquisitions.pageobject.general;

import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.elements.PageObject;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NewsPage extends PageObject {

    public NewsPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary, "HOMEPAGE", "news");
    }

    public List<NewsItem> getNewsItems(Integer numberOfNewsItems) {
        List<NewsItem> newsItems = new ArrayList<>();

        List<BlazeWebElement> newsElements = blazeLibrary.getElements(By.xpath("//div[@class='item-list']//li[contains(@class, 'views-row')]"));

        for (int i = 0; i < numberOfNewsItems; i++) {
            if (i == newsElements.size()) {
                break;
            }
            newsItems.add(new NewsItem(
                    newsElements.get(i).findElement(By.xpath(".//div[@class='day']")).getText(),
                    newsElements.get(i).findElement(By.xpath(".//div[@class='month']")).getText(),
                    newsElements.get(i).findElement(By.xpath(".//div[@class='year']")).getText(),
                    newsElements.get(i).findElement(By.xpath(".//div[contains(@class, 'views-field-title')]")).getText(),
                    newsElements.get(i).findElement(By.xpath(".//div[contains(@class, 'views-field-body')]")).getText()
            ));
        }

        return newsItems;
    }


    public static class NewsItem {
        public final Integer day;
        public final String month;
        public final Integer year;

        public final String title;
        public final String content;

        public NewsItem(String day, String month, String year, String title, String content) {
            this.day = Integer.parseInt(day);
            this.month = month;
            this.year = Integer.parseInt(year);
            this.title = title;
            this.content = content;
        }

        public String toString() {
            return String.format("NewsItem(day='%s',month='%s',year='%s',title='%s',content='%s')",
                    day, month, year, title, content);
        }

        public static final Comparator<NewsItem> comparator = new Comparator<NewsItem>() {
            @Override
            public int compare(NewsPage.NewsItem o1, NewsPage.NewsItem o2) {
                return o1.day.equals(o2.day) && o1.month.equals(o2.month) && o1.title.equals(o2.title) && o1.content.equals(o2.content) ? 0 : 1;
            }

            public String toString() {
                return "field by field comparator on fields [\"day\", \"month\", \"title\", \"content\"]";
            }
        };
    }

}