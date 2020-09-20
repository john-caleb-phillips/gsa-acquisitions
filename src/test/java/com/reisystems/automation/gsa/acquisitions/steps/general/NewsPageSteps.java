package com.reisystems.automation.gsa.acquisitions.steps.general;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.reisystems.automation.gsa.acquisitions.pageobject.general.NewsPage;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.en.When;

import java.lang.reflect.Type;
import java.util.List;

public class NewsPageSteps {

    private static final Gson gson = new Gson();
    private final BlazeLibrary blazeLibrary;
    private final NewsPage newsPage;

    public NewsPageSteps(BlazeLibrary blazelibrary, NewsPage newsPage) {
        this.blazeLibrary = blazelibrary;
        this.newsPage = newsPage;
    }

    @When("I save the top {int} news items as {string}")
    public void qwe(Integer numberOfNewsItems, String saveValue) {
        Type type = new TypeToken<List<NewsPage.NewsItem>>(){}.getType();
        blazeLibrary.properties().setProperty(saveValue, gson.toJson(newsPage.getNewsItems(numberOfNewsItems), type));
    }
}
