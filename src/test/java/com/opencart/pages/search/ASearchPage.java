package com.opencart.pages.search;

import com.opencart.data.product.Categories;
import com.opencart.pages.BreadcrumbPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public abstract class ASearchPage extends BreadcrumbPage {

	private Select categoryDropdown;
	private WebElement searchInSubCategories;
	private WebElement searchInDescription;
	private WebElement searchButton;


	protected ASearchPage(WebDriver driver) {
		super(driver);
		initElements();
	}

	private void initElements() {
		categoryDropdown = new Select(driver.findElement(By.xpath("//select[@name = 'category_id']")));
		searchInSubCategories = driver.findElement(By.xpath("//input[@name = 'sub_category']"));
		searchInDescription = driver.findElement(By.id("description"));
		searchButton = driver.findElement(By.id("button-search"));
	}

	// PageObject Atomic Operation
	//CategoryDropdown
	public Select getCategoryDropdown() {
		return categoryDropdown;
	}

	public void setCategoryDropdown(String option) {
		getCategoryDropdown().selectByVisibleText(option);
	}

	public void selectCategory(Categories category){
		setCategoryDropdown(category.toString());
	}

	//SearchInSubCategories
	public WebElement getSearchInSubCategories(){
		return searchInSubCategories;
	}

	public void clickSearchInSubCategories(){
		getSearchInSubCategories().click();
	}

	//SearchInDescription
	public WebElement getSearchInDescription(){
		return searchInDescription;
	}

	public void clickSearchInDescription(){
		getSearchInDescription().click();
	}

	//SearchButton
	public WebElement getSearchButton(){
		return searchButton;
	}

	public String getsearchButtonText() {
		return getSearchButton().getText();
	}

	public void clickSearchButton(){
		getSearchButton().click();
	}

	// Business Logic
	public SuccessfulSearchPage performSuccessfulSearchByDesc(){
		clickSearchInDescription();
		clickSearchButton();
		return new SuccessfulSearchPage(driver);
	}

	public UnsuccessfulSearchPage performUnsuccessfulSearchWithCategory(Categories category){
		selectCategory(category);
		clickSearchButton();
		return new UnsuccessfulSearchPage(driver);
	}

	public SuccessfulSearchPage performSuccessfulSearchBySubcategory() {
		clickSearchInSubCategories();
		clickSearchButton();
		return new SuccessfulSearchPage(driver);
	}
}
