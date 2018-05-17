package selenium_web_driver_test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.data.ItemData;
import test.driver.ChromeWebDriver;
import test.util.GivenUserGoesOnTargetPageUtil;

public class Pc_test_quickstep_chrome {
	protected static WebDriver driver = null;
	private WebDriverWait wait = new WebDriverWait(driver, 60);
	private WebElement element;
	private GivenUserGoesOnTargetPageUtil givenUserGoesOnTargetPageUtil = new GivenUserGoesOnTargetPageUtil();
	@BeforeClass
	public static void setUpBeforeClass(){
		// Be careful! Cookies and Cache and Web Storage data will keep when this test running.
		driver = ChromeWebDriver.createWebDriverForPC(driver);
	}
	
	@Test
	public void chromeBrowserTest(){
		// Given shopper goes on shopping cart.
		givenUserGoesOnTargetPageUtil.givenUserGoesOnShoppingCartWithItem(driver, ItemData.NORMAL_ITEM);
		// Given shopper goes on login page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='u'")));
		driver.findElement(By.cssSelector("input[name='u'")).sendKeys("");
		driver.findElement(By.cssSelector("input[name='p'")).sendKeys("");
		driver.findElement(By.id("login_submit")).click();
		// Expected : given shopper goes on step 4
		// Login again > Expected : skip login.
		driver.get("https://basket.step.rakuten.co.jp/rms/mall/bs/cartall/");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-shop-id='306273'")));
		driver.findElement(By.cssSelector("input[data-shop-id='306273'")).click();	
	}
	
	@AfterClass
	public void closeWebBrowser(){
		ChromeWebDriver.closeWebDriver(driver);
	}
}
