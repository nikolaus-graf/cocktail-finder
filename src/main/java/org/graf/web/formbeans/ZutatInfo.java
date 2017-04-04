package org.graf.web.formbeans;

public class ZutatInfo {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ZutatInfo{" +
                "name='" + name + '\'' +
                '}';
    }
}
