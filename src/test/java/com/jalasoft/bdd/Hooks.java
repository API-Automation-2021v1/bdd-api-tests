package com.jalasoft.bdd;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    private Context context;

    public Hooks(Context context) {
        this.context = context;
    }

    @Before
    public void openTheApp() {
        System.out.println("Opening the App...");
    }

    @Before(value = "@createUserPrecondition", order = 10)
    public void createUserPreconditionHook() {
        System.out.println("Creating user...");
        context.storeId("489302922");
    }

    @Before(value = "@createUser2Precondition", order = 20)
    public void createUser2PreconditionHook() {
        System.out.println("Creating user 2...");
    }

    @After(value = "@cleanDBPostcondition")
    public void cleanDBPostconditionHook() {
        System.out.println("Cleaning DB");
    }
}
