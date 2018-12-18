package com.softserve.edu.opencart.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.softserve.edu.opencart.data.product.Currencies;

public class AHeadFactoryPage {
	protected final String TAG_ATTRIBUTE_VALUE = "value";
	
	protected WebDriver driver;
	//
	//@CacheLookup
	@FindBy(css = ".btn.btn-link.dropdown-toggle")
	private WebElement currency;
	//
	@FindBy(name = "search")
	private WebElement searchProductField;
	//
	@FindBy(css = ".btn.btn-default.btn-lg")
	private WebElement searchProductButton;
	//
	@FindBy(css = "div.btn-group.open ul.dropdown-menu li")
	private List<WebElement> dropdownCurrencies;

	protected AHeadFactoryPage(WebDriver driver) {
		this.driver = driver;
		//PageFactory.initElements(driver, this); // 1st item|
		//initElements(); // Not Use
		checkElements();
	}

	private void checkElements() {
		getCurrency();
		getSearchProductField();
		getSearchProductButton();
	}

	// PageObject Atomic Operation

	// currency
	public WebElement getCurrency() {
		return currency;
	}

	public String getCurrencyText() {
		return getCurrency().getText().substring(0, 1);
	}

	public void clickCurrency() {
		getCurrency().click();
	}

	// searchProductField
	public WebElement getSearchProductField() {
		return searchProductField;
	}

	public String getSearchProductFieldText() {
		return getSearchProductField().getAttribute(TAG_ATTRIBUTE_VALUE);
	}

	public void setSearchProductField(String text) {
		getSearchProductField().sendKeys(text);
	}

	public void clearSearchProductField() {
		getSearchProductField().clear();
	}

	public void clickSearchProductField() {
		getSearchProductField().click();
	}

	// searchProductButton
	public WebElement getSearchProductButton() {
		return searchProductButton;
	}

	public void clickSearchProductButton() {
		getSearchProductButton().click();
	}

	// dropdownCurrencies
	public List<WebElement> getDropdownCurrencies() {
		//System.out.println("dropdownCurrencies.size() = " + dropdownCurrencies.size());
		//System.out.println(dropdownCurrencies);
		return dropdownCurrencies;
	}

	// Functional Operations

	// currency
	protected void clickCurrencyByPartialName(Currencies optionName) {
		clickSearchProductField();
		clickCurrency();
		for (WebElement current : getDropdownCurrencies()) {
			//System.out.println("optionName = " + optionName + "   current.getText().toLowerCase() = " + current.getText().toLowerCase());
			if (current.getText().toLowerCase().contains(optionName.toString().toLowerCase())) {
				current.click();
				break;
			}
		}
		//clickCurrency(); // ERROR With @CacheLookup
	}
	
	// Business Logic
	
}
