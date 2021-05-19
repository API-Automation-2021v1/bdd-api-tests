package com.jalasoft.bdd;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import java.util.Map;

public class MyStepdefs {

    @When("I login to the app")
    public void iLoginToTheApp() {
        System.out.println("Login to the App.");
    }

    @And("I set the username {string}")
    public void iSetTheUsername(String username) {
        System.out.println("This is the username: " + username);
    }

    @And("I set the code {int}")
    public void iSetTheCode(int code) {
        System.out.println("This is the code: " + code);
    }

    @And("I set the text")
    public void iSetTheText(String text) {
        System.out.println("This is the text: " + text);
    }

    @And("I set the data")
    public void iSetTheData(Map<String, String> data) {
        System.out.println("This is the data: " + data);
    }
}
