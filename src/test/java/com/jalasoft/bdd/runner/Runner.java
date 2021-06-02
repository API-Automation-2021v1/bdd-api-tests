package com.jalasoft.bdd.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

/**
 * Defines Cucumber Runner.
 */
@CucumberOptions(
        plugin = "pretty",
        features = "src/test/resources/features",
        glue = "com.jalasoft.bdd"
)
public class Runner extends AbstractTestNGCucumberTests {

    /**
     * {@inheritDoc}
     */
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    /**
     * Executes actions before all scenarios.
     */
    @BeforeTest
    public void beforeAllScenarios() {
        System.setProperty("dataproviderthreadcount", "2");
    }

    /**
     * Executes actions after all scenarios.
     */
    @AfterTest
    public void afterAllScenarios() {

    }
}
