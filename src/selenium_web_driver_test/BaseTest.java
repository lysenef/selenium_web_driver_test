package selenium_web_driver_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
	private WebElement element;
	
	public void givenShopperGoesOnPaymentWithNoneMember(WebDriver driver){
		element = driver.findElement(By.id("lname"));
		element.sendKeys("楽天");
		element = driver.findElement(By.id("fname"));
		element.sendKeys("たろう");
		element = driver.findElement(By.id("lname_kana"));
		element.sendKeys("ラクテン");
		element = driver.findElement(By.id("fname_kana"));
		element.sendKeys("タロウ");
		element = driver.findElement(By.id("zip1"));
		element.sendKeys("140");
		element = driver.findElement(By.id("zip2"));
		element.sendKeys("0001");
		element = driver.findElement(By.id("search_address_button"));
		element.click();
		element = driver.findElement(By.id("street"));
		element.sendKeys("20-20");
		element = driver.findElement(By.id("tel1"));
		element.sendKeys("080");
		element = driver.findElement(By.id("tel2"));
		element.sendKeys("9090");
		element = driver.findElement(By.id("tel3"));
		element.sendKeys("0909");
		element = driver.findElement(By.id("email"));
		element.sendKeys("rakuten@rakuraku.com");
		element = driver.findElement(By.id("confirm_email"));
		element.sendKeys("rakuten@rakuraku.com");
		element = driver.findElement(By.id("deliver_to11"));
		if(!element.isSelected()){
			element.click();
		}
		element = driver.findElement(By.id("memberRegist_select_off"));
		if(!element.isSelected()){
			element.click();
		}
		element = driver.findElement(By.id("submitBtn"));
		element.click();
	}
	
	public void givenShopperGoesOnShippingAddressWithNoneMember(WebDriver driver){
		element = driver.findElement(By.id("lname"));
		element.sendKeys("楽天");
		element = driver.findElement(By.id("fname"));
		element.sendKeys("たろう");
		element = driver.findElement(By.id("lname_kana"));
		element.sendKeys("ラクテン");
		element = driver.findElement(By.id("fname_kana"));
		element.sendKeys("タロウ");
		element = driver.findElement(By.id("zip1"));
		element.sendKeys("140");
		element = driver.findElement(By.id("zip2"));
		element.sendKeys("0001");
		element = driver.findElement(By.id("search_address_button"));
		element.click();
		element = driver.findElement(By.id("street"));
		element.sendKeys("20-20");
		element = driver.findElement(By.id("tel1"));
		element.sendKeys("080");
		element = driver.findElement(By.id("tel2"));
		element.sendKeys("9090");
		element = driver.findElement(By.id("tel3"));
		element.sendKeys("0909");
		element = driver.findElement(By.id("email"));
		element.sendKeys("rakuten@rakuraku.com");
		element = driver.findElement(By.id("confirm_email"));
		element.sendKeys("rakuten@rakuraku.com");
		element = driver.findElement(By.id("deliver_to12"));
		if(!element.isSelected()){
			element.click();
		}
		element = driver.findElement(By.id("memberRegist_select_off"));
		if(!element.isSelected()){
			element.click();
		}
		element = driver.findElement(By.id("submitBtn"));
		element.click();
	}
	
	public void givenShopperGoesOnShoppingBasket(WebDriverWait wait, WebDriver driver){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(TestConfigurations.ITEM_PAGE_ADD_TO_CART_BTN)));
		element = driver.findElement(By.cssSelector(TestConfigurations.ITEM_PAGE_ADD_TO_CART_BTN));
		element.click();
	}
	
	public void givenShopperGoesOnShoppingBasketForSP(WebDriverWait wait, WebDriver driver){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(TestConfigurations.ITEM_PAGE_ADD_TO_CART_BTN_SP)));
		element = driver.findElement(By.cssSelector(TestConfigurations.ITEM_PAGE_ADD_TO_CART_BTN_SP));
		element.submit();
	}
}
