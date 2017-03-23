package org.graf.web.formbeans;

import java.util.List;

public class CocktailData {
    private final List<String[]> data;

    public CocktailData(List<String[]> data) {
        this.data = data;
    }

    public List<String[]> getData() {
        return data;
    }
}
