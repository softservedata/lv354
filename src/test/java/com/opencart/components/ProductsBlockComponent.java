package com.opencart.components;

import com.opencart.data.product.SortingOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductsBlockComponent {

	private WebDriver driver;

	private WebElement listView;
	private WebElement gridView;
	private Select sortByDropDown;
	private Select showItemsPerPage;
	private ProductsListComponent productsListComponent;

	public ProductsBlockComponent(WebDriver driver) {
		this.driver = driver;
		initElements();
	}

	private void initElements(){
		listView = driver.findElement(By.id("list-view"));
		gridView = driver.findElement(By.id("grid-view"));
		sortByDropDown = new Select (driver.findElement(By.id("input-sort")));
		showItemsPerPage = new Select(driver.findElement(By.id("input-limit")));
		productsListComponent = new ProductsListComponent(driver);
	}

	// PageObject Atomic Operation

	public WebElement getListView() {
		return listView;
	}

	public void clickListView() {
		getListView().click();
	}

	public WebElement getGridView() {
		return gridView;
	}

	public void clickGridView() {
		getGridView().click();
	}

	public Select getSortByDropDown() {
		return sortByDropDown;
	}

	public void setSortOption(SortingOptions option) {
		getSortByDropDown().selectByVisibleText(option.toString());
	}

	public Select getShowItemsPerPage() {
		return showItemsPerPage;
	}

	public void setshowItemsPerPage(String option) {
		getShowItemsPerPage().selectByVisibleText(option);
	}

	public ProductsListComponent getProductsListComponent(){
		return productsListComponent;
	}

	// Business Logic
	public ProductsBlockComponent switchToListView(){
		clickListView();
		return new ProductsBlockComponent(driver);
	}

	public ProductsBlockComponent switchToGridView(){
		clickGridView();
		return new ProductsBlockComponent(driver);
	}
	public ProductsBlockComponent searchWithSorting(SortingOptions option){
		setSortOption(option);
		return new ProductsBlockComponent(driver);
	}
}
