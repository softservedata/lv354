package com.softserve.edu.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.pages.cart.PopupShoppingCartComponent;

public abstract class AHeadPage {
	protected final String TAG_ATTRIBUTE_VALUE = "value";

	protected WebDriver driver;
	//
	private WebElement currency;
	private WebElement myAccount;
	private WebElement wishList;
	private WebElement shoppingCart;
	private WebElement checkout;
	private WebElement logo;
	private WebElement searchProductField;
	private WebElement searchProductButton;
	private WebElement cartButton;
	private List<MainMenuComponent> menuTop;
	//
	private DropdownMenuComponent dropdownOptions;
	private PopupShoppingCartComponent dropdownCart;

	protected AHeadPage(WebDriver driver) {
		this.driver = driver;
		initElements();
	}

	private void initElements() {
		currency = driver.findElement(By.cssSelector(".btn.btn-link.dropdown-toggle"));
		myAccount = driver.findElement(By.cssSelector(".list-inline > li > a.dropdown-toggle"));
		wishList = driver.findElement(By.id("wishlist-total"));
		// Do not Work with CSS ver. 3.x
		// shoppingCart =
		// driver.findElement(By.cssSelector("a:has('.fa.fa-shopping-cart')")); // may
		// be null
		// Do not Work with --headless mode
		// shoppingCart=driver.findElement(By.partialLinkText("Shopping Cart"));
		shoppingCart = driver.findElement(By.cssSelector("a[title='Shopping Cart']"));
		// Do not Work with CSS ver. 3.x
		// checkout = driver.findElement(By.cssSelector("a:has('.fa.fa-share')")); //
		// may be null
		// checkout=driver.findElement(By.partialLinkText("Checkout"));
		checkout = driver.findElement(By.cssSelector("a[title='Checkout']"));
		logo = driver.findElement(By.cssSelector("#logo > a"));
		searchProductField = driver.findElement(By.name("search"));
		searchProductButton = driver.findElement(By.cssSelector(".btn.btn-default.btn-lg"));
		cartButton = driver.findElement(By.cssSelector("#cart > button"));
		//
		// TODO init elements
		// menuTop = driver.findElements(By.cssSelector("ul.nav.navbar-nav > li"));
		// dropdownOptions
		// dropdownCart
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

	// myAccount
	public WebElement getMyAccount() {
		return myAccount;
	}

	public String getMyAccountText() {
		return getMyAccount().getText();
	}

	public void clickMyAccount() {
		getMyAccount().click();
	}

	// wishList
	public WebElement getWishList() {
		return wishList;
	}

	public String getWishListText() {
		return getWishList().getText();
	}

	public void clickWishList() {
		getWishList().click();
	}

	// shoppingCart
	public WebElement getShoppingCart() {
		return shoppingCart;
	}

	public String getShoppingCartText() {
		return getShoppingCart().getText();
	}

	public void clickShoppingCart() {
		getShoppingCart().click();
	}

	// checkout
	public WebElement getCheckout() {
		return checkout;
	}

	public String getCheckoutText() {
		return getCheckout().getText();
	}

	public void clickCheckout() {
		getCheckout().click();
	}

	// logo
	public WebElement getLogo() {
		return logo;
	}

	public void clickLogo() {
		getLogo().click();
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

	// cartButton
	public WebElement getCartButton() {
		return cartButton;
	}

	public String getCartButtonText() {
		return getCartButton().getText();
	}

	public void clickCartButton() {
		getCartButton().click();
	}

	// menuTop
	// dropdownOptions
	// dropdownCart

	// Business Logic

}
