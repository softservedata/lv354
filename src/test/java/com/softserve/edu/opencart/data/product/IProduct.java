package com.softserve.edu.opencart.data.product;

import java.util.Map;

public interface IProduct {

    String getName();

    String getDescription();

    Map<Currencies, Double> getPrice();
    
    Map<Currencies, Double> getPriceExTax();

    double getPrice(Currencies currency);
    
    Map<Currencies, Double> getEcoTax();
    
    Map<Currencies, Double> getVat();
    
}
