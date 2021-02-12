package com.mediamonks;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"com/mediamonks/steps"},
        plugin = { "pretty", "html:target/test-output",
                "json:target/cucumber-report/cucumber.json"
        },
        monochrome = true
)
public class TestRunner {
}