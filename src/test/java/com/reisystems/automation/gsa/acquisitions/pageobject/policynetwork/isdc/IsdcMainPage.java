package com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.isdc;

import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.elements.PageObject;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class IsdcMainPage extends PageObject {
    public IsdcMainPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary, "HOMEPAGE", "isdc-home");
    }

    public BufferedImage getHeaderImage() {
        return blazeLibrary.images().getFromImgTag(By.xpath("//img[@alt='ISDC Seal']"));
    }

    public String getHeader() {
        return blazeLibrary.getElement(By.xpath("//section//h1")).getText();
    }

    public String getContent() {
        return blazeLibrary.getElements(By.xpath("//div[contains(@class, 'field-items')]/div/*[local-name() = 'p' or local-name() = 'ul' or local-name() = 'h2' or local-name()='h3' or (local-name()='div' and not(@style))]"))
                .stream().map(BlazeWebElement::getText).collect(Collectors.joining("\n"));
    }

    public List<BlazeWebElement> getContentLinks() {
        return blazeLibrary.getElements(By.xpath("//div[@id='block-system-main']//a[@href and not(ancestor::table)]"));
    }

    public List<String> getFooterLinks() {
        return blazeLibrary.getElements(By.xpath("//div[@id='block-menu-menu-about-the-isdc']//li"))
                .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }

    public String getContentLinkHref(String linkText) {
        return blazeLibrary.getElement(By.xpath(String.format("//div[@id='block-system-main']//a[@href][not(ancestor::table)][.='%s']", linkText))).getAttribute("href");
    }

    public void clickContentLink(String linkText) {
        blazeLibrary.getElement(By.xpath(String.format("//div[@id='block-system-main']//a[@href][not(ancestor::table)][.='%s']", linkText))).click(blazeLibrary.clickResults().REFRESH_PAGE, blazeLibrary.clickResults().OPEN_WINDOW_OR_TAB);
    }

    public void clickFooterLink(String linkText) {
        blazeLibrary.getElement(By.xpath(String.format("//div[@id='block-menu-menu-about-the-isdc']//li//a[.='%s']", linkText))).click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public List<List<String>> getDebarringOfficialsTable() {
        return blazeLibrary.getElements(By.xpath("//div[@about='/isdc-debarring-officials']//table//tr"))
                .stream().map(row -> row.findElements(By.xpath(".//td"))
                        .stream().map(cell -> StringUtils.normalizeSpace(cell.getText())).collect(Collectors.toList())
                ).collect(Collectors.toList());
    }

    public List<List<String>> getRegulationsCitationsTable() {
        return blazeLibrary.getElements(By.xpath("//div[@about='/isdc-debarment-regulations']//table//tr"))
                .stream().map(row -> row.findElements(By.xpath(".//td"))
                        .stream().map(cell -> StringUtils.normalizeSpace(cell.getText())).collect(Collectors.toList())
                ).collect(Collectors.toList());
    }

    public List<List<String>> getCompellingReasonsDeterminationsTable() {
        return blazeLibrary.getElements(By.xpath("//div[@about='/isdc-compelling-reasons']//table//tr"))
                .stream().map(row -> row.findElements(By.xpath(".//td"))
                        .stream().map(cell -> StringUtils.normalizeSpace(cell.getText())).collect(Collectors.toList())
                ).collect(Collectors.toList());
    }

    public List<DownloadLink> getReportingLinks() {
        return blazeLibrary.getElements(By.xpath("//div[@about='/isdc-reporting']//a"))
                .stream().map(link -> {
                    try {
                        return new DownloadLink(new URL(link.getAttribute("href")), link.getText());
                    } catch (MalformedURLException e) {
                        return new DownloadLink(null, link.getText());
                    }
                }).collect(Collectors.toList());
    }

    public List<DownloadLink> getCompellingReasonsDeterminationLinks() {
        return blazeLibrary.getElements(By.xpath("//div[@about='/isdc-compelling-reasons']//table//a"))
                .stream().map(link -> {
                    try {
                        return new DownloadLink(new URL(link.getAttribute("href")), link.getText());
                    } catch (MalformedURLException e) {
                        return new DownloadLink(null, link.getText());
                    }
                }).collect(Collectors.toList());
    }

    public static class DownloadLink {
        public final URL url;
        public final String name;

        private DownloadLink(URL url, String name) {
            this.url = url;
            this.name = name;
        }
    }

}
