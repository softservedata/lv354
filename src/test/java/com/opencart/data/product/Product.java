package com.opencart.data.product;

import java.util.HashMap;
import java.util.Map;

interface IName {
    IDescription setName(String name);
}

interface IDescription {
    IBuildProduct setDescription(String description);
}

interface IBuildProduct {
    IBuildProduct addPrice(Currencies currency, double price);

    IBuildProduct addPriceExTax(Currencies currency, double price);

    IProduct build();
}

public class Product implements IName, IDescription, IBuildProduct, IProduct {
    private String name;
    private String description;
    private Map<Currencies, Double> price;
    private Map<Currencies, Double> priceExTax;

    private Product() {
        price = new HashMap<Currencies, Double>();
		priceExTax = new HashMap<Currencies, Double>();
    }

    public static IName get() {
        return new Product();
    }

    public IDescription setName(String name) {
        this.name = name;
        return this;
    }

    public IBuildProduct setDescription(String description) {
        this.description = description;
        return this;
    }

    public IBuildProduct addPrice(Currencies currency, double price) {
        getPrice().put(currency, price);
        return this;
    }

    public IBuildProduct addPriceExTax(Currencies currency, double price) {
        this.getPriceExTax().put(currency, price);
        return this;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<Currencies, Double> getPrice() {
        return this.price;
    }

    public double getPrice(Currencies currency) {
        return getPrice().get(currency);
    }

    public Map<Currencies, Double> getPriceExTax() {
        return this.priceExTax;
    }

    public double getPriceExTax(Currencies currency) {
        return getPriceExTax().get(currency);
    }

    public IProduct build() {
        return this;
    }
}
