package com.softserve.edu.opencart.data.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.softserve.edu.opencart.tools.RegexUtils;

interface IName {
	IDescription setName(String name);
}

interface IDescription {
	IPrice setDescription(String description);
}

interface IPrice {
	IPriceExTax setPrice(Map<Currencies, Double> price);
}

interface IPriceExTax {
	IVat setPriceExTax(Map<Currencies, Double> priceExTax);
}

interface IVat {
	IEcoTax setVat(Map<Currencies, Double> vat);
}

interface IEcoTax {
	IBuildProduct setEcoTax(Map<Currencies, Double> ecoTax);
}

interface IBuildProduct {
	IProduct build();
}

public class Product implements IProduct, IName, IDescription, IPrice, IPriceExTax, IVat, IEcoTax, IBuildProduct {
	private final static int COLUMN_EURO = 2;
	private final static int COLUMN_POUND_STERLING = 3;
	private final static int COLUMN_US_DOLLAR = 4;
	private final static int COLUMN_NAME = 0;
	private final static int COLUMN_DESCRIPTION = 1;

	private String name;
	private String description;
	private Map<Currencies, Double> price;
	private Map<Currencies, Double> priceExTax;
	private Map<Currencies, Double> vat;
	private Map<Currencies, Double> ecoTax;

	public IDescription setName(String name) {
		this.name = name;
		return new Product();
	}

	public IPrice setDescription(String description) {
		this.description = description;
		return this;
	}

	public IPriceExTax setPrice(Map<Currencies, Double> price) {
		Iterator<Entry<Currencies, Double>> it = price.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Currencies, Double> entry = (Map.Entry<Currencies, Double>) it.next();
			this.price.put(entry.getKey(), entry.getValue());
		}
		return this;
	}

	public IVat setPriceExTax(Map<Currencies, Double> priceExTax) {
		Iterator<Entry<Currencies, Double>> it = priceExTax.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Currencies, Double> entry = (Map.Entry<Currencies, Double>) it.next();
			this.priceExTax.put(entry.getKey(), entry.getValue());
		}
		return this;
	}

	public IEcoTax setVat(Map<Currencies, Double> vat) {
		Iterator<Entry<Currencies, Double>> it = vat.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Currencies, Double> entry = (Map.Entry<Currencies, Double>) it.next();
			this.vat.put(entry.getKey(), entry.getValue());
		}
		return this;
	}
	
	public IBuildProduct setEcoTax (Map<Currencies, Double> ecoTax) {
		Iterator<Entry<Currencies, Double>> it = ecoTax.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Currencies, Double> entry = (Map.Entry<Currencies, Double>) it.next();
			this.ecoTax.put(entry.getKey(), entry.getValue());
		}
		return this;
	}
	
	public IProduct build () {
		return this;
	}
	
	public Product() {
	}

	// TODO Develop Builder

	public Product(String name, String description) {
		this.name = name;
		this.description = description;
		price = new HashMap<Currencies, Double>();
		priceExTax = new HashMap<Currencies, Double>();
		vat = new HashMap<Currencies, Double>();
		ecoTax = new HashMap<Currencies, Double>();
	}

	public Product addPrice(Currencies currency, double price) {
		getPrice().put(currency, price);
		return this;
	}

	public Product addPriceExTax(Currencies currency, double price) {
		getPriceExTax().put(currency, price);
		return this;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Map<Currencies, Double> getPrice() {
		return price;
	}

	public double getPrice(Currencies currency) {
		return getPrice().get(currency);
	}

	public Map<Currencies, Double> getPriceExTax() {
		return priceExTax;
	}

	public double getPriceExTax(Currencies currency) {
		return getPriceExTax().get(currency);
	}

	public static List<IProduct> getByList(List<List<String>> rows) {
		List<IProduct> result = new ArrayList<IProduct>();
		//
		if (!RegexUtils.isDoubleMatches(rows.get(0).get(COLUMN_EURO))) {
			rows.remove(0);
		}
		for (List<String> currentRow : rows) {
			Product currentProduct = new Product(currentRow.get(COLUMN_NAME), currentRow.get(COLUMN_DESCRIPTION));
			// TODO use cycle
			currentProduct.addPrice(Currencies.EURO, RegexUtils.extractFirstDouble(currentRow.get(COLUMN_EURO)));
			currentProduct.addPrice(Currencies.POUND_STERLING,
					RegexUtils.extractFirstDouble(currentRow.get(COLUMN_POUND_STERLING)));
			currentProduct.addPrice(Currencies.US_DOLLAR,
					RegexUtils.extractFirstDouble(currentRow.get(COLUMN_US_DOLLAR)));
			//
			result.add(currentProduct);
		}
		return result;
	}

	public Product addVat() {
		for (Map.Entry<Currencies, Double> entry : this.getPriceExTax().entrySet()) {
			vat.put(entry.getKey(), entry.getValue() * RegexUtils.VAT);
		}
		return this;
	}

	private double USDtoEUR() {
		return getPrice().get(Currencies.EURO) / getPrice().get(Currencies.US_DOLLAR);
	}

	private double USDtoPOUND() {
		return getPrice().get(Currencies.POUND_STERLING) / getPrice().get(Currencies.US_DOLLAR);
	}

	private double calculateEcoTax(Currencies currency) {
		double eco = 0;
		switch (currency) {
		case US_DOLLAR:
			return eco = RegexUtils.ECOTAX_IN_USD;
		case EURO:
			return eco = RegexUtils.ECOTAX_IN_USD * USDtoEUR();
		case POUND_STERLING:
			return eco = RegexUtils.ECOTAX_IN_USD * USDtoPOUND();
		default:
			return eco = RegexUtils.ECOTAX_IN_USD;
		}
	}

	public Product addEcoTax() {
		for (Map.Entry<Currencies, Double> entry : getPrice().entrySet()) {
			ecoTax.put(entry.getKey(), calculateEcoTax(entry.getKey()));
		}
		return this;
	}

	public Map<Currencies, Double> getEcoTax() {
		return ecoTax;
	}

	public Map<Currencies, Double> getVat() {
		return vat;
	}

}
