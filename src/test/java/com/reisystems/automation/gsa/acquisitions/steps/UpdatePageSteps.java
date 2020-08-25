package com.reisystems.automation.gsa.acquisitions.steps;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.reisystems.automation.gsa.acquisitions.pageobject.UpdatePage;
import com.reisystems.blaze.controller.BlazeDriver;
import io.cucumber.java.en.When;

import java.lang.reflect.Type;
import java.util.List;

public class UpdatePageSteps {

    private static final Gson gson = new Gson();

    private final UpdatePage updatePage;

    public UpdatePageSteps(UpdatePage updatePage) {
        this.updatePage = updatePage;
    }

    @When("I save the top {int} updates as {string}")
    public void qwe(Integer numberOfUpdates, String saveValue) {
        Type type = new TypeToken<List<UpdatePage.UpdateItem>>(){}.getType();
        BlazeDriver.properties().setProperty(saveValue, gson.toJson(updatePage.getUpdates(numberOfUpdates), type));
    }
}
