package com.reisystems.automation.gsa.acquisitions.steps.policynetwork;

import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.PolicyNetworkPages;
import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PolicyNetworkSteps {

    private final BlazeLibrary blazeLibrary;
    private final PolicyNetworkPages policyNetworkPage;

    public PolicyNetworkSteps(BlazeLibrary blazeLibrary, PolicyNetworkPages policyNetworkPage) {
        this.blazeLibrary = blazeLibrary;
        this.policyNetworkPage = policyNetworkPage;
    }

    @When("I click the header link in policy network block {string}")
    public void clickHeader(String desiredBlock) {
        policyNetworkPage.main().clickHeaderLink(desiredBlock);
    }

    @When("I click the link for other policy resource {string}")
    public void clickLink(String desiredLink) {
        policyNetworkPage.main().clickOtherPolicyLink(desiredLink);
    }

    @Then("I see the following policy network blocks:")
    public void checkForBlocks(List<String> expectedBlocks) {
        List<String> actualBlocks = new ArrayList<>();
        List<BlazeWebElement> elements = blazeLibrary.getElements(
                By.xpath("//div[contains(@class, 'cao-boxes')]/div//h3 | //div[contains(@class, 'cao-boxes')]/div//img//../..//strong")
        );
        for (String qwe : elements.stream().map(BlazeWebElement::getText).collect(Collectors.toList())) {
            actualBlocks.add(qwe.split("\\(")[0].trim());
        }

        blazeLibrary.assertion().assertThat(actualBlocks)
                .as("The Policy Network main page content blocks were not as expected")
                .containsExactlyElementsOf(expectedBlocks);
    }

    @Then("I see that policy network block {string} has header image {string}")
    public void verifyBlockHeaderImage(String desiredBlock, String expectedImage) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.main().blockHasHeaderImage(desiredBlock)).as("Block %s should have a header image", desiredBlock).isTrue();
        if (blazeLibrary.assertion().wasSuccess()) {
            BufferedImage fromFile = blazeLibrary.images().getFromFile(expectedImage);
            BufferedImage fromPage = policyNetworkPage.main().getBlockHeaderImage(desiredBlock);

            blazeLibrary.assertion().assertThat(blazeLibrary.images().compareTwoImages(fromFile, fromPage, 0))
                    .withFailMessage("Displayed image for block '%s' on Policy Network main page did not match the validation file named named %s", desiredBlock, expectedImage)
                    .isTrue();

            if (!blazeLibrary.assertion().wasSuccess()) {
                blazeLibrary.report().attachImage(fromPage, "PNG", String.format("Image for '%s' from the Policy Network main page", desiredBlock));
                blazeLibrary.report().attachImage(fromFile, "PNG", String.format("Image for '%s' from file '%s'", desiredBlock, expectedImage));
                blazeLibrary.report().attachImage(blazeLibrary.images().getDiff(fromPage, fromFile), "PNG", "Differences marked in red");
            }
        }
    }

    @Then("I see that policy network block {string} has the following header:")
    public void verifyBlockHeaderText(String desiredBlock, String expectedHeader) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.main().blockHasHeaderText(desiredBlock)).as("Block %s should have header text", desiredBlock).isTrue();
        if (blazeLibrary.assertion().wasSuccess()) {
            blazeLibrary.assertion().assertThat(policyNetworkPage.main().getBlockHeaderText(desiredBlock))
                    .as("Header for block '%s' on the Policy Network main page was not as expected", desiredBlock)
                    .isEqualTo(expectedHeader);
        }
    }

    @Then("I see that policy network block {string} has the following text:")
    public void verifyBlockText(String desiredBlock, String expectedText) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.main().blockHasText(desiredBlock)).as("Block %s should have text", desiredBlock).isTrue();
        if (blazeLibrary.assertion().wasSuccess()) {
            blazeLibrary.assertion().assertThat(policyNetworkPage.main().getBlockText(desiredBlock))
                    .as("Text for block '%s' on the Policy Network main page was not as expected", desiredBlock)
                    .isEqualTo(expectedText);
        }
    }

    @Then("I see that policy network block {string} has the following links:")
    public void verifyBlockLinks(String desiredBlock, List<String> expectedLinks) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.main().blockHasLinks(desiredBlock)).as("Block %s should have text", desiredBlock).isTrue();
        if (blazeLibrary.assertion().wasSuccess()) {
            blazeLibrary.assertion().assertThat(policyNetworkPage.main().getBlockLinks(desiredBlock))
                    .as("Links for block '%s' on the Policy Network main page were not as expected", desiredBlock)
                    .containsExactlyElementsOf(expectedLinks);
        }
    }
}
