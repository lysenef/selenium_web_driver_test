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

public class ShoppingBasketTest {
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
		givenShopperGoesOnShoppingBasket();
		// Shopping basket
		givenShopperGoesOnLogin();
		// Step 1 None member login
		givenShopperGoesOnPaymentWithNoneMember();
		// Credit card check
		element = driver.findElement(By.id("payment_card_add_306273"));
		assertThat(element.getAttribute("value"), is("10000"));
		// Delivery
		element = driver.findElement(By.id("delivery_306273_100"));
		assertThat(element.getAttribute("data-delivery-name"), is("宅配便"));
	}
	
	private void givenShopperGoesOnItemPage(){
		driver.get("https://item.rakuten.co.jp/soukaidrink/9000009984074/");
	}
	
	private void givenShopperGoesOnShoppingBasket(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class='cart-button checkout new-cart-button ']")));
		element = driver.findElement(By.cssSelector("button[class='cart-button checkout new-cart-button ']"));
		element.click();
	}
	
	private void givenShopperGoesOnLogin(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-shop-id='306273'")));
		driver.findElement(By.cssSelector("input[data-shop-id='306273'")).click();
	}
	
	private void givenShopperGoesOnPaymentWithNoneMember(){
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
	
	// Getter
	public WebDriver getDriver() {
		return driver;
	}
}
