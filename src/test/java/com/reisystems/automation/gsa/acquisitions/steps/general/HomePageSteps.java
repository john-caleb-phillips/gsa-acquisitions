package com.reisystems.automation.gsa.acquisitions.steps.general;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.reisystems.automation.gsa.acquisitions.pageobject.general.HomePage;
import com.reisystems.automation.gsa.acquisitions.pageobject.general.NewsPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.general.UpdatePage;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import java.awt.image.BufferedImage;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class HomePageSteps {

    HomePage homePage;
    NewsPage newsPage;

    private final BlazeLibrary blazeLibrary;

    public HomePageSteps(BlazeLibrary blazelibrary, HomePage homePage, NewsPage newsPage) {
        this.blazeLibrary = blazelibrary;
        this.homePage = homePage;
        this.newsPage = newsPage;
    }

    @When("I click on homepage oval button {string}")
    public void clickOvalButton(String buttonText) {
        homePage.clickOvalButton(buttonText);
    }

    @When("I click on homepage square button {string}")
    public void clickSquareButton(String buttonText) {
        homePage.clickSquareButton(buttonText);
    }

    private final static Gson gson = new Gson();

    @Then("I see the homepage news items match the news items saved as {string}")
    public void verifyHomepageNewsItems(String valueKey) {
        // TODO: Make sure that are on homepage

        List<NewsPage.NewsItem> homePageNewsItems = new ArrayList<>();
        for (BlazeWebElement homePageNewsItem : blazeLibrary.getElements(By.xpath("//div[contains(@class, 'front-news')]"))) {
            homePageNewsItems.add(new NewsPage.NewsItem(
                    homePageNewsItem.findElement(By.xpath(".//div[contains(@class, 'day')]")).getText(),
                    homePageNewsItem.findElement(By.xpath(".//div[contains(@class, 'month')]")).getText(),
                    "0",
                    homePageNewsItem.findElement(By.xpath(".//h3")).getText(),
                    homePageNewsItem.findElement(By.xpath(".//p")).getText()
            ));
        }

        Type type = new TypeToken<List<NewsPage.NewsItem>>() {
        }.getType();
        blazeLibrary.assertion().assertThat(homePageNewsItems)
                .usingElementComparator(new Comparator<>() {
                    @Override
                    public int compare(NewsPage.NewsItem o1, NewsPage.NewsItem o2) {
                        return o1.day.equals(o2.day) && o1.month.equals(o2.month) && o1.title.equals(o2.title) && o1.content.equals(o2.content) ? 0 : 1;
                    }

                    public String toString() {
                        return "field by field comparator on fields [\"day\", \"month\", \"title\", \"content\"]";
                    }
                })
                .containsExactlyElementsOf(gson.fromJson(blazeLibrary.properties().getProperty(valueKey), type));

    }

    @Then("I see the homepage updates match the updates saved as {string}")
    public void verifyHomepageUpdates(String valueKey) {
        // TODO: Make sure that are on homepage

        List<UpdatePage.UpdateItem> homePageUpdateItems = new ArrayList<>();
        for (BlazeWebElement homePageUpdateItem : blazeLibrary.getElements(By.xpath("//h1[.='FAC Updates']/../div"))) {
            homePageUpdateItems.add(new UpdatePage.UpdateItem(
                    homePageUpdateItem.findElement(By.xpath(".//h3")).getText(),
                    homePageUpdateItem.findElement(By.xpath(".//p")).getText()
            ));
        }

        Type type = new TypeToken<List<UpdatePage.UpdateItem>>() {}.getType();
        blazeLibrary.assertion().assertThat(homePageUpdateItems)
                .usingElementComparator(new Comparator<>() {
                    @Override
                    public int compare(UpdatePage.UpdateItem o1, UpdatePage.UpdateItem o2) {
                        return (o1.section.contains(o2.section) || o2.section.contains(o1.section)) && o1.caseNumber.equals(o2.caseNumber) ? 0 : 1;
                    }

                    public String toString() {
                        return "case numbers are the same and homepage sections are contained in update page sections";
                    }
                })
                .containsExactlyElementsOf(gson.fromJson(blazeLibrary.properties().getProperty(valueKey), type));

    }

    @Then("I see the following oval buttons:")
    public void verifyOvalButtons(List<String> expectedOvalButtons) {
        blazeLibrary.assertion().assertThat(homePage.getOvalButtons())
                .as("Verifying homepage oval buttons")
                .containsExactlyElementsOf(expectedOvalButtons);
    }

    @Then("I see the following square buttons:")
    public void verifySquareButtons(List<String> expectedSquareButtons) {
        blazeLibrary.assertion().assertThat(homePage.getSquareButtons())
                .as("Verifying homepage oval buttons")
                .containsExactlyElementsOf(expectedSquareButtons);
    }

    @Then("I see the following square button pictures:")
    public void verifySquareButtonPictures(Map<String, String> expectedPictures) {
        for (Map.Entry<String, String> entry : expectedPictures.entrySet()) {
            BufferedImage fromPage = homePage.getSquareButtonImage(entry.getKey());
            BufferedImage fromFile = blazeLibrary.images().getFromFile(entry.getValue());
            blazeLibrary.assertion().assertThat(blazeLibrary.images().compareTwoImages(fromFile, fromPage, 0))
                    .as("Comparing image src for %s on the page to the file named %s", entry.getKey(), entry.getValue())
                    .isTrue();
        }
    }

}
