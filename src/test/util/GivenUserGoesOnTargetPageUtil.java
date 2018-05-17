package test.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import test.configuration.ElementConfiguration;
import test.data.ShopData;

public class GivenUserGoesOnTargetPageUtil {
	private WaitVisibilityUtil waitVisibilityUtil = new WaitVisibilityUtil();
	
	public void givenUserGoesOnShoppingCartWithItem(WebDriver driver, String itemUrl){
		driver.get(itemUrl);
		waitVisibilityUtil.waitUntilVisible(driver, 60, ElementConfiguration.ITEM_PAGE_ADD_TO_CART_BTN);
		driver.findElement(By.cssSelector(ElementConfiguration.ITEM_PAGE_ADD_TO_CART_BTN)).click();
	}
	
	public void givenUserGoesOnStep1FromShoppingBasket(WebDriver driver){
		waitVisibilityUtil.waitUntilVisible(driver, 60, ElementConfiguration.SHOPPING_BASKET_BTN+ShopData.SHOP_A_ID);
		driver.findElement(By.cssSelector(ElementConfiguration.SHOPPING_BASKET_BTN+ShopData.SHOP_A_ID)).click();
	}
}
