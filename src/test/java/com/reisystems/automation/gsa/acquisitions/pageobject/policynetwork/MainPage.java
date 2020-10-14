package com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork;

import com.reisystems.blaze.elements.HasBlazeLibrary;
import com.reisystems.blaze.elements.PageObject;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.controller.BlazeLibrary;
import org.openqa.selenium.By;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

public class MainPage extends HasBlazeLibrary {
    public MainPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
    }

    public void clickHeaderLink(String blockName) {
        blazeLibrary.getElement(By.xpath("""
                //div[contains(@class, 'cao-boxes')]//h3[contains(., '(%s)')]\
                 | \
                //div[contains(@class, 'cao-boxes')]//img[@alt='%s Logo']\
                 | \
                //div[contains(@class, 'cao-boxes')]//h3[.='%s']"""
                .formatted(blockName, blockName, blockName)
        )).click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public void clickOtherPolicyLink(String linkText) {
        blazeLibrary.getElement(By.xpath("//div[contains(@class, 'cao-boxes')]//h3[.='Other Policy Resources']/..//a[text()='%s']".formatted(linkText)
        )).click(blazeLibrary.clickResults().OPEN_WINDOW_OR_TAB);
    }

    public boolean blockHasHeaderImage(String desiredBlock) {
        return blazeLibrary.getElement(By.xpath("""
                //div[contains(@class, 'cao-boxes')]//h3[contains(., '(%s)')]/../..//img\
                 | \
                //div[contains(@class, 'cao-boxes')]//img[@alt='%s Logo']/../..//img\
                 | \
                //div[contains(@class, 'cao-boxes')]//h3[.='%s']/..//img"""
                .formatted(desiredBlock, desiredBlock, desiredBlock)
        )).isPresent();
    }

    public BufferedImage getBlockHeaderImage(String desiredBlock) {
        return blazeLibrary.images().getFromImgTag(By.xpath("""
                //div[contains(@class, 'cao-boxes')]//h3[contains(., '(%s)')]/../..//img\
                 | \
                //div[contains(@class, 'cao-boxes')]//img[@alt='%s Logo']/../..//img\
                 | \
                //div[contains(@class, 'cao-boxes')]//h3[.='%s']/..//img"""
                .formatted(desiredBlock, desiredBlock, desiredBlock)
        ));
    }

    public boolean blockHasHeaderText(String desiredBlock) {
        return blazeLibrary.getElement(By.xpath("""
                //div[contains(@class, 'cao-boxes')]//h3[contains(., '(%s)')]/../..//h3\
                 | \
                //div[contains(@class, 'cao-boxes')]//img[@alt='%s Logo']/../..//h3\
                 | \
                //div[contains(@class, 'cao-boxes')]//h3[.='%s']/..//h3"""
                .formatted(desiredBlock, desiredBlock, desiredBlock)
        )).isPresent();
    }

    public String getBlockHeaderText(String desiredBlock) {
        return blazeLibrary.getElement(By.xpath("""
                //div[contains(@class, 'cao-boxes')]//h3[contains(., '(%s)')]/../..//h3\
                 | \
                //div[contains(@class, 'cao-boxes')]//img[@alt='%s Logo']/../..//h3\
                 | \
                //div[contains(@class, 'cao-boxes')]//h3[.='%s']/..//h3"""
                .formatted(desiredBlock, desiredBlock, desiredBlock)
        )).getText();
    }

    public boolean blockHasText(String desiredBlock) {
        return blazeLibrary.getElement(By.xpath("""
                //div[contains(@class, 'cao-boxes')]//h3[contains(., '(%s)')]/../..//p\
                 | \
                //div[contains(@class, 'cao-boxes')]//img[@alt='%s Logo']/../..//p\
                 | \
                //div[contains(@class, 'cao-boxes')]//h3[.='%s']/..//p"""
                .formatted(desiredBlock, desiredBlock, desiredBlock)
        )).isPresent();
    }

    public String getBlockText(String desiredBlock) {
        return blazeLibrary.getElements(By.xpath("""
                //div[contains(@class, 'cao-boxes')]//h3[contains(., '(%s)')]/../..//p\
                 | \
                //div[contains(@class, 'cao-boxes')]//img[@alt='%s Logo']/../..//p\
                 | \
                //div[contains(@class, 'cao-boxes')]//h3[.='%s']/..//p"""
                .formatted(desiredBlock, desiredBlock, desiredBlock)
        )).stream().map(BlazeWebElement::getText).collect(Collectors.joining("\n\n"));
    }

    public boolean blockHasLinks(String desiredBlock) {
        return blazeLibrary.getElement(By.xpath("""
                //div[contains(@class, 'cao-boxes')]//h3[contains(., '(%s)')]/../..//li/a\
                 | \
                //div[contains(@class, 'cao-boxes')]//img[@alt='%s Logo']/../..//li/a\
                 | \
                //div[contains(@class, 'cao-boxes')]//h3[.='%s']/..//li/a"""
                .formatted(desiredBlock, desiredBlock, desiredBlock)
        )).isPresent();
    }

    public List<String> getBlockLinks(String desiredBlock) {
        return blazeLibrary.getElements(By.xpath("""
                //div[contains(@class, 'cao-boxes')]//h3[contains(., '(%s)')]/../..//li/a\
                 | \
                //div[contains(@class, 'cao-boxes')]//img[@alt='%s Logo']/../..//li/a\
                 | \
                //div[contains(@class, 'cao-boxes')]//h3[.='%s']/..//li/a"""
                .formatted(desiredBlock, desiredBlock, desiredBlock)
        )).stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }
}
