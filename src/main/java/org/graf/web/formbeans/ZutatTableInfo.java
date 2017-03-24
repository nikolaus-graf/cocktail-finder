package org.graf.web.formbeans;

import java.util.List;

public class ZutatTableInfo {
    private final List<String[]> data;

    public ZutatTableInfo(List<String[]> data) {
        this.data = data;
    }

    public List<String[]> getData() {
        return data;
    }


}
