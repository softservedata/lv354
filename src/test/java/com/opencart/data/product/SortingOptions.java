package com.opencart.data.product;

import com.opencart.components.ProductItemComponent;
import java.util.Comparator;

public enum SortingOptions {
    DEFAULT("Default", defaultComparator()),
    NAME_A_Z("Name (A - Z)", compareProductsNames(SortingOptions.ASC)),
    NAME_Z_A("Name (Z - A)", compareProductsNames(SortingOptions.DESC)),
    PRICE_LOW_HIGH("Price (Low > High)", compareProductsPrices(SortingOptions.ASC)),
    PRICE_HIGH_LOW("Price (High > Low)", compareProductsPrices(SortingOptions.DESC)),
    RATING_HIGHEST("Rating (Highest)", defaultComparator()),
    RATING_LOWEST("Rating (Lowest)", defaultComparator()),
    MODEL_A_Z("Model (A - Z)", defaultComparator()),
    MODEL_Z_A("Model (Z - A)", defaultComparator());

    private String name;
    private Comparator<ProductItemComponent> productComparator;

    private final static int ASC = 1;
    private final static int DESC = -1;

    private SortingOptions(String name, Comparator<ProductItemComponent> productComparator){
        this.name = name;
        this.productComparator = productComparator;
    }

    @Override
    public String toString() {
        return name;
    }

    private static Comparator<ProductItemComponent> defaultComparator(){
        return new Comparator<ProductItemComponent>(){
            public int compare(ProductItemComponent p1, ProductItemComponent p2) {
                return 0;
            }
        };
    }

    public static Comparator<ProductItemComponent> compareProductsNames(int order) {
        return new Comparator<ProductItemComponent>() {
            public int compare(ProductItemComponent p1, ProductItemComponent p2) {
                return p1.getNameText().compareToIgnoreCase(p2.getNameText()) * order;
            }
        };
    }

    public static Comparator<ProductItemComponent> compareProductsPrices(int order) {
        return new Comparator<ProductItemComponent>() {
            public int compare(ProductItemComponent p1, ProductItemComponent p2) {
                if (p1.getPriceAmount() > p2.getPriceAmount())
                    return 1 * order;
                else if (p1.getPriceAmount() < p2.getPriceAmount())
                    return -1 * order;
                else
                    return 0;
            }
        };
    }

    public Comparator<ProductItemComponent> getComparator(){
        return this.productComparator;
    }

}
