package com.reisystems.automation.gsa.acquisitions.steps;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.reisystems.automation.gsa.acquisitions.pageobject.HomePage;
import com.reisystems.automation.gsa.acquisitions.pageobject.NewsPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.UpdatePage;
import com.reisystems.blaze.blazeElement.BlazeWebElement;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import java.awt.image.BufferedImage;
import java.io.IOException;
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

    @When("I am on the homepage")
    public void qwe() {
        homePage.goToHomePage();
    }

    @When("I click on homepage oval button {string}")
    public void clickOvalButton(String buttonText) {
        // TODO: Make sure that are on homepage

        blazeLibrary.getElement(homePage.ovalButtonLocator(buttonText)).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    @When("I click on homepage square button {string}")
    public void clickSquareButton(String buttonText) {
        // TODO: Make sure that are on homepage

        blazeLibrary.getElement(homePage.squareButtonLocator(buttonText)).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    private final static Gson gson = new Gson();

    @Then("I see the homepage news items match the news items saved as {string}")
    public void test(String valueKey) {
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
                        return "all fields match, ignoring the year";
                    }
                })
                .containsExactlyElementsOf(gson.fromJson(blazeLibrary.properties().getProperty(valueKey), type));

    }

    @Then("I see the homepage updates match the updates saved as {string}")
    public void asd(String valueKey) {
        // TODO: Make sure that are on homepage

        List<UpdatePage.UpdateItem> homePageUpdateItems = new ArrayList<>();
        for (BlazeWebElement homePageUpdateItem : blazeLibrary.getElements(By.xpath("//h1[.='FAC Updates']/../div"))) {
            homePageUpdateItems.add(new UpdatePage.UpdateItem(
                    homePageUpdateItem.findElement(By.xpath(".//h3")).getText(),
                    homePageUpdateItem.findElement(By.xpath(".//p")).getText()
            ));
        }

        Type type = new TypeToken<List<UpdatePage.UpdateItem>>() {
        }.getType();
        blazeLibrary.assertion().assertThat(homePageUpdateItems)
                .usingElementComparator(new Comparator<>() {
                    @Override
                    public int compare(UpdatePage.UpdateItem o1, UpdatePage.UpdateItem o2) {
                        return o2.section.contains(o1.section) && o1.caseNumber.equals(o2.caseNumber) ? 0 : 1;
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
    public void verifySquareButtonPictures(Map<String, String> expectedPictures) throws IOException {
        for (Map.Entry<String, String> entry : expectedPictures.entrySet()) {
            BufferedImage fromFile = blazeLibrary.images().getFromFile(entry.getValue());
            BufferedImage fromPage = blazeLibrary.images().getFromImgTag(By.xpath(
                    String.format(
                            "//div[contains(@class, 'middle-content')]//div[contains(@class, 'col-lg-5') and .//h4[text()='%s']]//img",
                            entry.getKey()
                    )
            ));

            blazeLibrary.assertion().assertThat(blazeLibrary.images().compareTwoImages(fromFile, fromPage, 0))
                    .as("Comparing image src for %s on the page to the file named %s", entry.getKey(), entry.getValue())
                    .isTrue();
        }
    }

}
