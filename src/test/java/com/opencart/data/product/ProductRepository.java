package com.opencart.data.product;

//Using static methods
public final class ProductRepository {

	public static final String SEARCH_MAC = "Mac";

	private ProductRepository() {
	}

    public static IProduct defaultProduct() {
        return macBook();
    }

    public static IProduct macBook() {
        return Product.get()
				.setName("MacBook")
				.setDescription("Intel Core 2 Duo processor Powered by an Intel Core 2 Duo processor at speeds up to 2.1..")
        		.addPrice(Currencies.EURO, 472.33)
        		.addPrice(Currencies.POUND_STERLING, 368.73)
        		.addPrice(Currencies.US_DOLLAR, 602.00)
        		.addPriceExTax(Currencies.EURO, 392.30)
        		.addPriceExTax(Currencies.POUND_STERLING, 306.25)
        		.addPriceExTax(Currencies.US_DOLLAR, 500.00)
				.build();
    }

    public static IProduct iPhone() {
        return Product.get()
				.setName("iPhone")
				.setDescription("email")
        		.addPrice(Currencies.EURO, 96.66)
        		.addPrice(Currencies.POUND_STERLING, 75.46)
        		.addPrice(Currencies.US_DOLLAR, 123.20)
        		.addPriceExTax(Currencies.EURO, 79.24)
        		.addPriceExTax(Currencies.POUND_STERLING, 61.86)
        		.addPriceExTax(Currencies.US_DOLLAR, 101.00)
				.build();
    }

	public static IProduct iMac() {
		return Product.get().setName("iMac")
				.setDescription("Just when you thought iMac had everything, now there´s even more. More powerful Intel Core 2 Duo processors. And more memory standard. Combine this with Mac OS X Leopard and iLife ´08, and it´s more all-in-one than ever. iMac packs amazing performance into a stunningly slim space.")
				.addPrice(Currencies.EURO, 95.72)
				.addPrice(Currencies.POUND_STERLING, 74.73)
				.addPrice(Currencies.US_DOLLAR, 122.00)
				.addPriceExTax(Currencies.EURO, 78.46)
				.addPriceExTax(Currencies.POUND_STERLING, 61.25)
				.addPriceExTax(Currencies.US_DOLLAR, 100.00)
				.build();
	}

	public static IProduct HP_Pavilion_x360() {
		return Product.get().setName("HP - Pavilion x360 2-in-1 14\" Touch-Screen Laptop")
				.setDescription("14.0-inch diagonal HD SVA WLED-backlit multitouch-enabled edge-to-edge glass, No Optical drive")
				.addPrice(Currencies.EURO, 455.07)
				.addPrice(Currencies.POUND_STERLING, 355.25)
				.addPrice(Currencies.US_DOLLAR, 580.20)
				.addPriceExTax(Currencies.EURO, 455.07)
				.addPriceExTax(Currencies.POUND_STERLING, 355.25)
				.addPriceExTax(Currencies.US_DOLLAR, 580.00)
				.build();
	}
}
