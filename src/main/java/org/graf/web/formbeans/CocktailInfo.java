package org.graf.web.formbeans;

import java.util.Arrays;

public class CocktailInfo {
    private String name;
    private String[] zutaten;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getZutaten() {
        return zutaten;
    }

    public void setZutaten(String[] zutaten) {
        this.zutaten = zutaten;
    }

    @Override
    public String toString() {
        return "CocktailInfo{" +
                "name='" + name + '\'' +
                ", zutaten=" + Arrays.toString(zutaten) +
                '}';
    }
}
