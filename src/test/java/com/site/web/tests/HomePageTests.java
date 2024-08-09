package com.site.web.tests;

import org.testng.annotations.Test;

import com.site.web.base.BaseTest;
import com.site.web.pages.HomePage;
import com.site.web.pages.ProductDetailsPage;

import io.qameta.allure.Description;

public class HomePageTests extends BaseTest {

	@Test
	@Description("Verifying the Menu Selection Functionality")
	public void verifyMenuSelection() {

		HomePage homePage = new HomePage(driver);

		homePage.selectMenu("Women", "Bottoms", "Pants");
	}

	@Test
	@Description("Verifying the Add to Cart Functionality")
	public void verifyAddToCartFunctionality() throws Exception {

		HomePage homePage = new HomePage(driver);
		ProductDetailsPage prodcutPage = new ProductDetailsPage(driver);

		String productToPurchase = "Hero Hoodie";

		homePage.goToProductDetailsPage(productToPurchase);
		prodcutPage.verifyProductTitle(productToPurchase);
		prodcutPage.selectProductSize("S");
		prodcutPage.selectProductColor("Black");
		prodcutPage.addToCart();
	}

	@Test
	@Description("Verifying the Promotion shopping link button Functionality")
	public void verifyPromoBannerShopButtonFunctionality() {

		HomePage homePage = new HomePage(driver);
		homePage.clickOnShopNewYogaPromotionButton();
	}

}
