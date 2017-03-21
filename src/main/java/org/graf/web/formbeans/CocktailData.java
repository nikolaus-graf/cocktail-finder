package org.graf.web.formbeans;

import java.util.List;

/**
 * Created by Niki on 21.03.2017.
 */
public class CocktailData {
    private final List<String[]> data;

    public CocktailData(List<String[]> data) {
        this.data = data;
    }

    public List<String[]> getData() {
        return data;
    }
}
