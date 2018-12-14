package com.softserve.edu.opencart.pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PopupProductCartComponent {
	private WebElement picture;
	private WebElement name;
	private WebElement item;
	private WebElement price;
	private WebElement button;
	private WebElement popupProductComponentLayout;
	
	
	
	public PopupProductCartComponent (WebElement popupProductComponentLayout) {
		this.popupProductComponentLayout=popupProductComponentLayout;
		init ();
	}
	private void init () {
		picture = popupProductComponentLayout.findElement(By.cssSelector(".text-center >a"));
		name = popupProductComponentLayout.findElement(By.cssSelector(".text-left >a"));
		//item = popupProductComponentLayout.findElement(By.cssSelector(".text-right:first-child"));//TODO
		//price = popupProductComponentLayout.findElement(By.cssSelector(".text-right:last-child"));TODO
		button =popupProductComponentLayout.findElement(By.cssSelector(".btn.btn-danger"));
	}

	
	// PageObject Atomic Operation
	
	public WebElement getPopupProductComponentLayout() {
        return popupProductComponentLayout;
		}
	
	// picture
	public WebElement getPicture() {
        return picture;
		}
	
	public void clickPicture() {
         getPicture().click();
		}	
	
	// name
	public WebElement getName() {
         return name;
		}
	
	public void clickName() {
         getName().click();
		}
	
	public String getNameText() {
	     return getName().getText();
		}
	
	// item
	public WebElement getItem() {
		return item;
		}
	public String getItemText() {
	    return getItem().getText();
		}
	
	// price
	 public WebElement getPrice() {
	     return price;
	    }

	 public String getPriceText() {
	     return getPrice().getText();
	    }
	 //button 
	 public WebElement getButton() {
	     return button;
	    }

	 public void clickButton() {
	     getButton().click();
	    }
	
	// Business Logic

}
