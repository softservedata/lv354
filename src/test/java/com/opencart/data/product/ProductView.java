package com.opencart.data.product;

public enum ProductView {
    LIST("list"),
    GRID("grid");

    private String name;

    private ProductView(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
