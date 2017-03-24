package org.graf.web.formbeans;

import java.util.List;

public class CocktailTableInfo {
    private final List<String[]> data;

    public CocktailTableInfo(List<String[]> data) {
        this.data = data;
    }

    public List<String[]> getData() {
        return data;
    }
}
