package org.graf.web.formbeans;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class Filter {
    private List<String> zutaten = new ArrayList<>();

    public List<String> getZutaten() {
        return zutaten;
    }

    public Filter setZutaten(List<String> zutaten) {
        this.zutaten = zutaten;
        return this;
    }

    public String filterString() {
        return zutaten.stream()
                .sorted()
                .collect(joining(", "));
    }
}
