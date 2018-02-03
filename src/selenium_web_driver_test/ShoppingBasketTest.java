package selenium_web_driver_test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingBasketTest extends BaseTest {
	protected static WebDriver driver = null;
	private WebDriverWait wait = new WebDriverWait(driver, 60);
	private WebElement element;
	@BeforeClass
	public static void setUpBeforeClass(){
		
		// Be careful! Cookies and Cache and Web Storage data will keep when this test running.
		if(driver == null){
			// DesiredCapabilities Wiki : https://code.google.com/p/selenium/wiki/DesiredCapabilities
			//Proxy proxy = new Proxy();
			//proxy.setHttpProxy("localhost:8080");
			//DesiredCapabilities capabilities = new DesiredCapabilities();
			//capabilities.setCapability(CapabilityType.PROXY, proxy);
			System.setProperty("webdriver.gecko.driver", "./webdriver/geckodriver.exe");
			driver = new FirefoxDriver();
			Runtime.getRuntime().addShutdownHook(new Thread(){
				//When Java process is finished, WebDriver will be closed.
				@Override
				public void run(){
					driver.quit();
				}
			});
		}
	}
	
	@Test
	public void test() throws InterruptedException {
		// Item Page
		givenShopperGoesOnItemPage();
		givenShopperGoesOnShoppingBasket(wait, driver);
		// Shopping basket
		givenShopperGoesOnLogin();
		// Step 1 None member login
		givenShopperGoesOnPaymentWithNoneMember(driver);
		// Credit card check
		element = driver.findElement(By.id("payment_card_add_306273"));
		assertThat(element.getAttribute("value"), is("10000"));
		// Delivery
		element = driver.findElement(By.id("delivery_306273_100"));
		assertThat(element.getAttribute("data-delivery-name"), is("宅配便"));
	}
	
	private void givenShopperGoesOnItemPage(){
		driver.get(TestConfigurations.TEST_ITEM_URL_CASE_01);
	}
	
	private void givenShopperGoesOnLogin(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-shop-id='306273'")));
		driver.findElement(By.cssSelector("input[data-shop-id='306273'")).click();
	}
	
	// Getter
	public WebDriver getDriver() {
		return driver;
	}
}
