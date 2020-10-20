package com.reisystems.automation.gsa.acquisitions.pageobject.general;

import com.reisystems.blaze.elements.HasBlazeLibrary;
import com.reisystems.blaze.elements.PageObject;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.controller.BlazeLibrary;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class NewsPage extends HasBlazeLibrary {

    public NewsPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
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
    }

}