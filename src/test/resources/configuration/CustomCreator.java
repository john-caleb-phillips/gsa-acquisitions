package com.framework.automation.steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"setup","com.automation.oc.steps","com.automation.rei.govgrantsSteps","eyethink.automation.bots.bdd","eyethink.automation.bots.Register"},
        features = {"target/parallel/features/[CUCABLE:FEATURE].feature"},
        plugin = {"json:target/cucumber-report/[CUCABLE:RUNNER].json","pretty"},
        tags = {"not @wip"},
        monochrome = true
)
public class CustomCreator {

}

