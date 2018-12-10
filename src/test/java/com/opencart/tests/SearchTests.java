package com.opencart.tests;

import com.opencart.components.ProductItemComponent;
import com.opencart.components.ProductsBlockComponent;
import com.opencart.components.ProductsListComponent;
import com.opencart.data.product.Categories;
import com.opencart.data.product.SortingOptions;
import com.opencart.pages.HomePage;
import com.opencart.pages.ProductPage;
import com.opencart.pages.search.SuccessfulSearchPage;
import com.opencart.pages.search.UnsuccessfulSearchPage;
import com.opencart.tools.Application;
import com.opencart.tools.ApplicationTestRunner;
import com.opencart.data.product.Product;
import com.opencart.data.product.ProductRepository;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTests extends ApplicationTestRunner {
    @DataProvider
    public Object[][] inputDescriptionData() {
        return new Object[][]{
                {ProductRepository.iPhone()}
        };
    }

    @DataProvider
    public Object[][] inputCategoryData() {
        return new Object[][]{
                {ProductRepository.HP_Pavilion_x360(), Categories.LAPTOPS_NOTEBOOKS},
                {ProductRepository.iMac(), Categories.DESKTOPS}
        };
    }

    @DataProvider
    public Object[][] searchData() {
        return new Object[][]{
                {ProductRepository.SEARCH_MAC}
        };
    }

    @DataProvider
    public Object[][] searchDataWithSorting() {
        return new Object[][]{
                {ProductRepository.SEARCH_MAC, SortingOptions.NAME_Z_A},
                {ProductRepository.SEARCH_MAC, SortingOptions.PRICE_HIGH_LOW}
        };
    }

    @Test(dataProvider = "inputDescriptionData")
    public void searchByDescription(Product product) {
        HomePage home = Application.get().loadApplication();
        UnsuccessfulSearchPage unsuccessfulSearch = home.performUnsuccessfulSearch(product.getDescription());
        moveToFooter();
        delayExecution(2);
        Assert.assertEquals(unsuccessfulSearch.getNoProductMessageText(), unsuccessfulSearch.NO_PRODUCT_MESSAGE);

        SuccessfulSearchPage page = unsuccessfulSearch.performSuccessfulSearchByDesc();
        moveToFooter();
        delayExecution(1);

        ProductPage productPage = page.openProductPageByName(product.getName());
        moveToFooter();
        delayExecution(2);
        Assert.assertTrue(productPage.getDescriptionText().contains(product.getDescription()));
    }

    @Test(dataProvider = "inputCategoryData")
    public void searchByCategory(Product product, Categories category) {
        HomePage home = Application.get().loadApplication();
        SuccessfulSearchPage successfulSearch = home.performSuccessfulSearch(product.getName());
        Assert.assertEquals(successfulSearch.getProductsBlockComponent().getProductsListComponent()
                .getProductItemByName(product.getName()).getNameText(), product.getName());
        moveToFooter();
        delayExecution(2);

        UnsuccessfulSearchPage unsuccessfulSearch = successfulSearch.performUnsuccessfulSearchWithCategory(category);
        Assert.assertEquals(unsuccessfulSearch.getNoProductMessageText(), unsuccessfulSearch.NO_PRODUCT_MESSAGE);
        moveToFooter();
        delayExecution(2);

        SuccessfulSearchPage searchBySubcategory = unsuccessfulSearch.performSuccessfulSearchBySubcategory();
        Assert.assertEquals(searchBySubcategory.getProductsBlockComponent().getProductsListComponent()
                .getProductItemByName(product.getName()).getNameText(), product.getName());
        moveToFooter();
        delayExecution(2);
    }

    @Test(dataProvider = "searchData")
    public void listGridView(String product) {
        HomePage home = Application.get().loadApplication();
        SuccessfulSearchPage search = home.performSuccessfulSearch(product);
        ProductsBlockComponent productsBlockComponent = search.getProductsBlockComponent().switchToListView();
        moveToFooter();
        delayExecution(2);

        List<ProductItemComponent> products = productsBlockComponent
                .getProductsListComponent().getProductItemComponents();

        for (ProductItemComponent item : products) {
            Assert.assertTrue(item.isProductLayoutList());
        }

        productsBlockComponent = productsBlockComponent.switchToGridView();
        moveToFooter();
        delayExecution(2);

        products = productsBlockComponent.getProductsListComponent().getProductItemComponents();

        for (ProductItemComponent item : products) {
            Assert.assertTrue(item.isProductLayoutGrid());
        }
    }

    @Test(dataProvider = "searchDataWithSorting")
    public void sortByName(String product, SortingOptions option) {
        HomePage home = Application.get().loadApplication();

        ProductsBlockComponent productsBlock = home.performSuccessfulSearch(product)
                .getProductsBlockComponent();
        moveToFooter();
        delayExecution(2);

        ProductsListComponent expectedProducts = productsBlock.getProductsListComponent();
        expectedProducts.getProductItemComponents().sort(option.getComparator());

        List<String> expectedNames = expectedProducts.getProductsNameList();

        List<String> actual = productsBlock.searchWithSorting(option).getProductsListComponent()
                .getProductsNameList();
        moveToFooter();
        delayExecution(2);

        Assert.assertEquals(actual, expectedNames);
    }

}
