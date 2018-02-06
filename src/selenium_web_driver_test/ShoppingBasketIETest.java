package selenium_web_driver_test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingBasketIETest {
	protected static WebDriver driver = null;
	private WebDriverWait wait;
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
			System.setProperty("webdriver.edge.driver", "./webdriver/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			Runtime.getRuntime().addShutdownHook(new Thread(){
				//When Java process is finished, WebDriver will be closed.
				@Override
				public void run(){
					driver.quit();
				}
			});
		}
	}
	
	@Before
	public void setUp(){
		wait = new WebDriverWait(driver, 60);
	}
	
	@Ignore // Edge does not run properly
	@Test
	public void paymentTest() throws InterruptedException {
		
		// Item Page
		givenShopperGoesOnItemPage();
		givenShopperGoesOnShoppingBasket();
		// Shopping basket
		givenShopperGoesOnLogin();
		// Step 1 None member login
		givenShopperGoesOnPaymentWithNoneMember();
		// Credit card check
		element = driver.findElement(By.id("payment_card_add_306273"));
		assertThat(element.getAttribute("value"), is(TestConfigurations.PAYMENT_CREDIT_CARD_ID));
		// Delivery
		element = driver.findElement(By.id("delivery_306273_100"));
		assertThat(element.getAttribute("data-delivery-name"), is("宅配便"));
		// Credit card add
		Select select = new Select(element = driver.findElement(By.id("cardRegist_306273")));
		select.selectByValue("1");
		driver.findElement(By.name("card_number1")).sendKeys("4444");
		driver.findElement(By.name("card_number2")).sendKeys("4444");
		driver.findElement(By.name("card_number3")).sendKeys("4444");
		driver.findElement(By.name("card_number4")).sendKeys("4448");
		select = new Select(element = driver.findElement(By.name("card_exp_month")));
		select.selectByValue("01");
		select = new Select(element = driver.findElement(By.name("card_exp_year")));
		select.selectByValue("2020");
		driver.findElement(By.name("card_owner")).sendKeys("Rakuten");
		driver.findElement(By.name("addNewCard")).click();
	}
	
	@Ignore // Edge does not run properly
	@Test
	public void shippingAddressTest() throws InterruptedException {
		// Item Page
		givenShopperGoesOnItemPage();
		givenShopperGoesOnShoppingBasket();
		// Shopping basket
		givenShopperGoesOnLogin();
		// Step 1 None member login
		givenShopperGoesOnShippingAddressWithNoneMember();
		// Add new address
		
	}
	
	private void givenShopperGoesOnItemPage(){
		driver.get(TestConfigurations.TEST_ITEM_URL_CASE_01);
	}
	
	private void givenShopperGoesOnLogin(){
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[class='big-red-button large-button purchaseButton'")));
		element = driver.findElement(By.cssSelector("input[class='big-red-button large-button purchaseButton'"));
		element.sendKeys(Keys.CONTROL);
		element.click();
	}
	
	private void givenShopperGoesOnShoppingBasket(){
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(TestConfigurations.ITEM_PAGE_ADD_TO_CART_BTN)));
		element = driver.findElement(By.cssSelector(TestConfigurations.ITEM_PAGE_ADD_TO_CART_BTN));
		element.sendKeys(Keys.CONTROL);
		element.click();
	}
	
	public void givenShopperGoesOnPaymentWithNoneMember(){
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
	
	public void givenShopperGoesOnShippingAddressWithNoneMember(){
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
	
	// Getter
	public WebDriver getDriver() {
		return driver;
	}
}
