package com.jalasoft.bdd.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Defines Cucumber Runner.
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.jalasoft.bdd"
)
public class Runner extends AbstractTestNGCucumberTests {

}
