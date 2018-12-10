package com.opencart.data.product;

public enum Categories {
    DESKTOPS("Desktops"),
    LAPTOPS_NOTEBOOKS("Laptops & Notebooks"),
    COMPONENTS("components"),
    TABLETS("Tablets"),
    SOFTWARE("Software"),
    PHONES("Phones & PDAs"),
    CAMERAS("Cameras"),
    MP3PLAYERS("MP3 Players");
    //
    private String name;

    private Categories(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
