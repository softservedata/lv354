package com.softserve.edu.opencart.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.softserve.edu.opencart.data.product.Currencies;
import com.softserve.edu.opencart.data.product.Product;

public final class RegexUtils {

	private final static String DOUBLE_UI_SEPARATOR = ",";
	private final static String PATTERN_UNSIGNED_NUMBER = "\\d+";
	private final static String PATTERN_UNSIGNED_DOUBLE = "\\d+(,\\d+)*\\.\\d{2}";
	public final static String DELETE_SERVER = "http://[^/]+/";
	private final static String EXTRACT_NUMBER_MESSAGE = "NumberFormatException for pattern =  %s text =  %s";
	public final static int ECOTAX_IN_USD = 2;
	public final static double VAT = 0.2;

	private RegexUtils() {
	}

	public static boolean isTextMatches(String pattern, String text) {
		return Pattern.compile(pattern).matcher(text).matches();
	}

	public static String extractFirstString(String pattern, String text) {
		String result = new String();
		Matcher matcher = Pattern.compile(pattern).matcher(text);
		if (matcher.find()) {
			result = text.substring(matcher.start(), matcher.end());
		}
		return result;
	}

	public static int extractFirstNumber(String text) {
		int result = -1;
		String extractText = extractFirstString(PATTERN_UNSIGNED_NUMBER, text);
		if (!extractText.isEmpty()) {
			try {
				result = Integer.parseUnsignedInt(extractText);
			} catch (NumberFormatException e) {
				// TODO Develop Custom Exception
				throw new RuntimeException(String.format(EXTRACT_NUMBER_MESSAGE, PATTERN_UNSIGNED_NUMBER, text));
			}
		}
		return result;
	}

	public static double extractFirstDouble(String text) {
		double result = -1;
		String extractText = extractFirstString(PATTERN_UNSIGNED_DOUBLE, text);
		if (!extractText.isEmpty()) {
			try {
				result = Double.parseDouble(commaRemover(extractText));

			} catch (NumberFormatException e) {
				// TODO Develop Custom Exception
				throw new RuntimeException(String.format(EXTRACT_NUMBER_MESSAGE, PATTERN_UNSIGNED_DOUBLE, text));
			}
		}
		return result;
	}

	public static boolean isDoubleMatches(String text) {
		return isTextMatches(PATTERN_UNSIGNED_DOUBLE, text);
	}

	public static String extractPathWithoutServer(String pattern, String text) {
		String result = text;
		Matcher matcher = Pattern.compile(pattern).matcher(text);
		if (matcher.find()) {
			result = text.substring(matcher.end() - 1);
		}
		return result;
	}

	private static String commaRemover(String text) {
		return text.replaceAll(DOUBLE_UI_SEPARATOR, new String());
	}

	public static double getTotalProducts(Currencies currency, Product... productArray) {

		double total = 0;
		for (Product a : productArray) {
			total += a.getPrice().get(currency);
		}
		return total;
	}

	public static double getSubTotalProducts(Currencies currency, Product... productArray) {

		double total = 0;
		for (Product a : productArray) {
			total += a.getPriceExTax().get(currency);
		}
		return total;
	}
	public static double getEcoTaxProducts(Currencies currency, Product... productArray) {

		double total = 0;
		for (Product a : productArray) {
			total += a.getEcoTax().get(currency);
		}
		return total;
	}
	
	public static double getVatProducts(Currencies currency, Product... productArray) {

		double total = 0;
		for (Product a : productArray) {
			total += a.getVat().get(currency);
		}
		return total;
	}
	public static int getProductsItems(Product... productArray) {

		int total = 0;
		for (Product a : productArray) {
			total ++;
		}
		return total;
	}
}
