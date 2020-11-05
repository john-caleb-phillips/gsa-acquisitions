package com.reisystems.automation.gsa.acquisitions.steps.general;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.reisystems.automation.gsa.acquisitions.pageobject.general.HomePage;
import com.reisystems.automation.gsa.acquisitions.pageobject.general.NewsPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.general.UpdatePage;
import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
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

    private final HomePage homePage;
    private final NewsPage newsPage;

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

        Type type = new TypeToken<List<NewsPage.NewsItem>>(){}.getType();
        blazeLibrary.assertion().assertThat(homePageNewsItems)
                .as("News items on the homepage did not match those on the news page")
                .usingElementComparator(NewsPage.NewsItem.comparator)
                .containsExactlyElementsOf(gson.fromJson(blazeLibrary.properties().getProperty(valueKey), type));

    }

    @Then("I see the homepage updates match the updates saved as {string}")
    public void verifyHomepageUpdates(String valueKey) {
        List<UpdatePage.UpdateItem> homePageUpdateItems = new ArrayList<>();
        for (BlazeWebElement homePageUpdateItem : blazeLibrary.getElements(By.xpath("//h1[.='FAC Updates']/../div"))) {
            homePageUpdateItems.add(new UpdatePage.UpdateItem(
                    homePageUpdateItem.findElement(By.xpath(".//h3")).getText(),
                    homePageUpdateItem.findElement(By.xpath(".//p")).getText()
            ));
        }

        Type type = new TypeToken<List<UpdatePage.UpdateItem>>(){}.getType();
        blazeLibrary.assertion().assertThat(homePageUpdateItems)
                .as("Update items on the homepage did not match those on the updates page")
                .usingElementComparator(UpdatePage.UpdateItem.comparator)
                .containsExactlyElementsOf(gson.fromJson(blazeLibrary.properties().getProperty(valueKey), type));

    }

    @Then("I see the following oval buttons:")
    public void verifyOvalButtons(List<String> expectedOvalButtons) {
        blazeLibrary.assertion().assertThat(homePage.getOvalButtons())
                .as("Homepage oval buttons were not as expected")
                .containsExactlyElementsOf(expectedOvalButtons);
    }

    @Then("I see the following square buttons:")
    public void verifySquareButtons(List<String> expectedSquareButtons) {
        blazeLibrary.assertion().assertThat(homePage.getSquareButtons())
                .as("Homepage square buttons were not as expected")
                .containsExactlyElementsOf(expectedSquareButtons);
    }

    @Then("I see the following square button pictures:")
    public void verifySquareButtonPictures(Map<String, String> expectedPictures) {
        for (Map.Entry<String, String> entry : expectedPictures.entrySet()) {
            BufferedImage fromPage = homePage.getSquareButtonImage(entry.getKey());
            BufferedImage fromFile = blazeLibrary.images().getFromFile(entry.getValue());
            blazeLibrary.assertion().assertThat(blazeLibrary.images().compareTwoImages(fromFile, fromPage, 0))
                    .withFailMessage("Displayed image for square button '%s' on the homepage did not match the validation file named named %s", entry.getKey(), entry.getValue())
                    .isTrue();
            if (!blazeLibrary.assertion().wasSuccess()) {
                blazeLibrary.report().attachImage(fromPage, "PNG", String.format("Image for '%s' from the homepage", entry.getKey()));
                blazeLibrary.report().attachImage(fromFile, "PNG", String.format("Image for '%s' from file '%s'", entry.getKey(), entry.getValue()));
                blazeLibrary.report().attachImage(blazeLibrary.images().getDiff(fromPage, fromFile), "PNG", "Differences marked in red");
            }
        }
    }

}
