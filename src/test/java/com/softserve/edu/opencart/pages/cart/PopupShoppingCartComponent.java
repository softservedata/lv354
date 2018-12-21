package com.softserve.edu.opencart.pages.cart;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.pages.AHeadPage;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.ProductComponent;
import com.softserve.edu.opencart.tools.RegexUtils;

public class PopupShoppingCartComponent {

	private List<PopupProductCartComponent> popupProductCartComponents;
	private List<WebElement> productsList;
	private WebDriver driver;
	private WebElement subtotal;
	private WebElement total;
	private WebElement vat;
	private WebElement ecoTax;
	
	
	private static final String SUBTOTAL_BY_XPATH ="//table[@class='table table-bordered']/tbody/child::tr[1]/td[2]";
	private static final String ECOTAX_BY_XPATH ="//table[@class='table table-bordered']/tbody/child::tr[2]/td[2]";
	private static final String VAT_BY_XPATH ="//table[@class='table table-bordered']/tbody/child::tr[3]/td[2]";
	private static final String TOTAL_BY_XPATH ="//table[@class='table table-bordered']/tbody/child::tr[4]/td[2]";
	
	public PopupShoppingCartComponent(WebDriver driver) {
		this.driver = driver;
		initElements();
	}
		public void initElements(){
			System.out.println("initElements start");
			popupProductCartComponents = new ArrayList<>();
			productsList = driver.findElements(By.cssSelector(".table-striped tbody tr"));
			for (WebElement current : productsList) {
				popupProductCartComponents.add(new PopupProductCartComponent(current));
			}
			subtotal=driver.findElement(By.xpath(SUBTOTAL_BY_XPATH));
			total=driver.findElement(By.xpath(TOTAL_BY_XPATH));
			vat=driver.findElement(By.xpath(VAT_BY_XPATH));
			ecoTax=driver.findElement(By.xpath(ECOTAX_BY_XPATH));
			System.out.println("initElements finish");
			
		}
			
	// PageObject Atomic Operation
		
		// productList
		public List<PopupProductCartComponent> getPopupProductCartComponentsList() {
			System.out.println ("getPopupProductCartComponentsList") ;
			return popupProductCartComponents;
		}
    	//WebElementList
		public List<WebElement> getPopupProductCartElementsList() {
			System.out.println("getPopupProductCartElementsList() ");
			return productsList;
		}
		
		public WebElement getElement () {
			return total;
		}
		public void clickElement () {
			getElement ().click();
		}
		
		//subtotal
		public String getSubTotalText () {
			return subtotal.getText();
		}
		
		//total
		public String getTotalText () {
			return total.getText();
		}
		
		//ecoTax
		public String getEcoTaxText () {
			return ecoTax.getText();
		}	
		
		//VAT
		public String getVatText () {
			return vat.getText();
		}	
		
		//functional operations
		
		public double extractTotal() {
			return RegexUtils.extractFirstDouble(getTotalText ());			
		}
		
		public double extractSubTotal() {
			return RegexUtils.extractFirstDouble(getSubTotalText ());			
		}
		
		public double extractVat() {
			return RegexUtils.extractFirstDouble(getVatText ());			
		}
		
		public double extractEcoTax() {
			return RegexUtils.extractFirstDouble(getEcoTaxText ());			
		}
		
		public HomePage closePopUpShoppingCartComponent () {
			clickElement();
			return new HomePage (driver);
		}
		
		
		// Business Logic
		
	
}
