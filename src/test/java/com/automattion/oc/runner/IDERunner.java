package com.automattion.oc.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"setup","com.automation.oc.steps","com.automation.rei.govgrantsSteps","eyethink.automation.bots.bdd","eyethink.automation.bots.Register"},
        features = {"src/test/resources/features"},
        plugin = {"json:target/cucumber-report/cucumber.json","pretty","html:target/cucumber-html"},
        tags = {"@internalOrganizationCreate"},
        monochrome = true
)

public class IDERunner {

}

