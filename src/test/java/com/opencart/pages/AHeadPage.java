package com.opencart.pages;

import java.util.List;

import com.opencart.components.DropdownMenuComponent;
import com.opencart.components.MainMenuComponent;
import com.opencart.pages.search.SuccessfulSearchPage;
import com.opencart.pages.search.UnsuccessfulSearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.opencart.data.head.LoggedMyAccount;
import com.opencart.data.head.UnloggedMyAccount;
import com.opencart.data.product.Currencies;
import com.opencart.pages.cart.PopupShoppingCartComponent;
import com.opencart.pages.right.AccountLogoutPage;
import com.opencart.pages.right.LoginPage;
import com.opencart.tools.Application;
import com.opencart.tools.RegexUtils;

public abstract class AHeadPage {
	protected final String TAG_ATTRIBUTE_VALUE = "value";
	protected final String OPTION_NULL_MESSAGE = "DropdownOption is null";
	protected final String OPTION_NOT_FOUND_MESSAGE = "Option %s not found in %s";
	protected final String ERROR_LOGIN_MESSAGE = "Current user is %s logged";
	protected final String EMPTY_MESSAGE = "";
	protected final String NOT_MESSAGE = "not";
	//
	protected final String LIST_CURENCIES_BYCSS = "div.btn-group.open ul.dropdown-menu li";
	protected final String DROPDOWN_MENU_MYACCOUNT_BYCSS = ".dropdown-menu-right li";
	//
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
	//
	private List<MainMenuComponent> menuTop;
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
		shoppingCart = driver.findElement(By.cssSelector("a[title='Shopping Cart']"));
		checkout = driver.findElement(By.cssSelector("a[title='Checkout']"));
		logo = driver.findElement(By.cssSelector("#logo > a"));
		searchProductField = driver.findElement(By.name("search"));
		searchProductButton = driver.findElement(By.cssSelector(".btn.btn-default.btn-lg"));
		cartButton = driver.findElement(By.cssSelector("#cart > button"));
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

	// Functional Operations
	
	// wishList
	 public int getWishListItemsCount() {
		 return RegexUtils.extractFirstNumber(getWishListText());
	}
	
	// cartButton
    public int getCartButtonItemsCount() {
        return RegexUtils.extractFirstNumber(getCartButtonText());
    }
	
	// dropdownOptions
	protected DropdownMenuComponent getDropdownOptions() {
		if (dropdownOptions == null) {
			// TODO Develop Custom Exception
			throw new RuntimeException(OPTION_NULL_MESSAGE);
		}
		return dropdownOptions;
	}

	private DropdownMenuComponent createDropdownOptions(By searchLocator) {
		dropdownOptions = new DropdownMenuComponent(driver, searchLocator);
		return getDropdownOptions();
	}

	private boolean findDropdownOptionByPartialName(String optionName) {
		boolean isFound = false;
		for (String current : getDropdownOptions().getListOptionsText()) {
			if (current.toLowerCase().contains(optionName.toLowerCase())) {
				isFound = true;
			}
		}
		return isFound;
	}

	private void clickDropdownOptionByPartialName(String optionName) {
		if (!findDropdownOptionByPartialName(optionName)) {
			// TODO Develop Custom Exception
			throw new RuntimeException(String.format(OPTION_NOT_FOUND_MESSAGE, optionName,
					dropdownOptions.getListOptionsText().toString()));
		}
		getDropdownOptions().clickDropdownOptionByPartialName(optionName);
		dropdownOptions = null;
	}

	// currency
	protected void clickCurrencyByPartialName(Currencies optionName) {
		clickSearchProductField();
		clickCurrency();
		createDropdownOptions(By.cssSelector(LIST_CURENCIES_BYCSS));
		clickDropdownOptionByPartialName(optionName.toString());
	}

	// myAccount
	private void clickDropdownMenuMyAccountByPartialName(String componentName) {
		clickSearchProductField();
		clickMyAccount();
		createDropdownOptions(By.cssSelector(DROPDOWN_MENU_MYACCOUNT_BYCSS));
		clickDropdownOptionByPartialName(componentName);
	}

	protected void clickUnloggedMyAccountByPartialName(UnloggedMyAccount optionName) {
		if (Application.get().getLoginStatus()) {
			// TODO Develop Custom Exception
			throw new RuntimeException(String.format(ERROR_LOGIN_MESSAGE,
							Application.get().getLoginStatus() ? EMPTY_MESSAGE : NOT_MESSAGE));
		}
		clickDropdownMenuMyAccountByPartialName(optionName.toString());
	}

	protected void clickLoggedMyAccountByPartialName(LoggedMyAccount optionName) {
		if (!Application.get().getLoginStatus()) {
			// TODO Develop Custom Exception
			throw new RuntimeException(String.format(ERROR_LOGIN_MESSAGE,
							Application.get().getLoginStatus() ? EMPTY_MESSAGE : NOT_MESSAGE));
		}
		clickDropdownMenuMyAccountByPartialName(optionName.toString());
	}

	//Search Field
	protected void enterSearchCriteria(String searchCriteria){
		clickSearchProductField();
		clearSearchProductField();
		setSearchProductField(searchCriteria);
	}
	
	// Business Logic
	public LoginPage gotoLogin() {
		clickUnloggedMyAccountByPartialName(UnloggedMyAccount.LOGIN);
		return new LoginPage(driver);	
	}
	
	public AccountLogoutPage gotoLogout() {
		clickLoggedMyAccountByPartialName(LoggedMyAccount.LOGOUT);
		Application.get().getBrowser().getTestStatus().setLogged(false);
		return new AccountLogoutPage(driver);	
	}

	public HomePage gotoHome() {
		clickLogo();
		return new HomePage(driver);
	}

	public SuccessfulSearchPage performSuccessfulSearch(String searchCriteria){
		enterSearchCriteria(searchCriteria);
		clickSearchProductButton();
		return new SuccessfulSearchPage(driver);
	}

	public UnsuccessfulSearchPage performUnsuccessfulSearch(String searchCriteria){
		enterSearchCriteria(searchCriteria);
		clickSearchProductButton();
		return new UnsuccessfulSearchPage(driver);
	}
}
