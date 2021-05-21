package com.jalasoft.bdd;

import java.util.ArrayList;
import java.util.List;

public class Context {

    private List<String> ids;

    public Context() {
        ids = new ArrayList<>();
    }

    public void storeId(String id) {
        ids.add(id);
    }

    public List<String> getIds() {
        return ids;
    }
}
