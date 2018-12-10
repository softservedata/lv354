package com.opencart.data.product;

public interface IProduct {

    String getName();

    String getDescription();

    double getPrice(Currencies currency);
    
    double getPriceExTax(Currencies currency);
    
}
