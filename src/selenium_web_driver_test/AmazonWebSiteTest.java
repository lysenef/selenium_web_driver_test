package selenium_web_driver_test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonWebSiteTest {
	protected static WebDriver driver = null;
	
	@BeforeClass
	public void setUpBeforeClass(){
		
		// Be careful! Cookies and Cache and Web Storage data will keep when this test running.
		//if(driver == null){
			// DesiredCapabilities Wiki : https://code.google.com/p/selenium/wiki/DesiredCapabilities
			//Proxy proxy = new Proxy();
			//proxy.setHttpProxy("localhost:8080");
			//DesiredCapabilities capabilities = new DesiredCapabilities();
			//capabilities.setCapability(CapabilityType.PROXY, proxy);
			//driver = new FirefoxDriver(capabilities);
			//Runtime.getRuntime().addShutdownHook(new Thread(){
				//When Java process is finished, WebDriver will be closed.
				//@Override
				//public void run(){
				//	driver.quit();
				//}
			//});
		//}
	}
	
	@Before
	public void setUp(){
		
	}
	@Ignore("Ignore this test case")
	@Test
	public void test() throws IOException {
		/*
		 * By.id
		 * By.name
		 * By.tagName
		 * By.className
		 * By.linkText : a tag text "equals" matcher
		 * By.partialLinkText : a tag text "like as" matcher
		 * By.cssSelector
		 * By.xpath
		 */
		WebElement element = driver.findElement(By.id("nav-cart"));
		// Click element id nav-cart.
		element.click();
		List<WebElement> elementList = driver.findElements(By.className("main"));
		assertThat(elementList.size(), is(4));
		elementList = driver.findElements(By.tagName("img"));
		assertThat(elementList.size(), is(4));
		assertThat(elementList.get(4).getAttribute("alt"), is("Valentine's Day Gift Shop"));
		// a tag in ID is nav-cart
		element = driver.findElement(By.cssSelector("#nav-cart > a"));
		element = driver.findElement(By.xpath("//div[@id='nav-cart']/a"));
		element = driver.findElement(By.id("nav-cart")).findElement(By.cssSelector("a"));
		// Text box
		element = driver.findElement(By.name("userName"));
		// Text box value clear.
		element.clear();
		// Input value
		element.sendKeys("James");
		// Delete key
		element.sendKeys(Keys.DELETE);
		// Enter key
		element.sendKeys(Keys.ENTER);
		// Tab key
		element.sendKeys(Keys.TAB);
		// F5 key
		element.sendKeys(Keys.F5);
		// Input ABC and then input back space key
		element.sendKeys("ABC", Keys.BACK_SPACE);
		// For check box
		element = driver.findElement(By.className("checkbox"));
		// If check box is not checked, click check box then check box will be checked.
		if(!element.isSelected()){
			element.click();
		}
		// Radio button
		element = driver.findElement(By.cssSelector("input[name='radio'][value='0']"));
		if(element.isEnabled()){
			element.click();
		}
		// Select box
		Select select = new Select(element = driver.findElement(By.className("selectbox")));
		select.selectByValue("ja");
		select.selectByVisibleText("Japanese");
		select.selectByIndex(0);
		// Release all selected status
		select.deselectAll();
		// Displayed or not
		boolean isDisplayed = element.isDisplayed();
		if(isDisplayed){
			boolean isEnabled = element.isEnabled();
			if(isEnabled){
				// Submit
				driver.findElement(By.id("submit")).submit();
			}
		}
		// Value is Home delivery
		//assertThat(element.getAttribute("value"),is(Configurations.HOME_DELIVERY_JAPANESE));
		// Get height, get width
		element.getSize().getHeight();
		element.getSize().getWidth();
		// Location
		element.getLocation().getX();
		element.getLocation().getY();
		// Get Page URL
		driver.getCurrentUrl();
		// Browser size change
		driver.manage().window().setSize(new Dimension(100, 200));
		// Pull size window
		driver.manage().window().maximize();
		// Cookie
		Set<Cookie> cookies = driver.manage().getCookies();
		for(Cookie cookie : cookies){
			assertThat(cookie.getDomain(), is("https://basket.rakuten.co.jp"));
		}
		// Session ID
		Cookie sessionCookie = driver.manage().getCookieNamed("seesionId");
		assertThat(sessionCookie.getExpiry(), is(-1));
		// Create cookie
		sessionCookie = new Cookie("sessionId", "");
		driver.manage().addCookie(sessionCookie);
		// Delete cookie
		driver.manage().deleteAllCookies();
		driver.manage().deleteCookie(sessionCookie);
		driver.manage().deleteCookieNamed("sessionId");
		// Capture
		File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//FileUtils.moveFile(tempFile, new File("C:\\..png"));
		// Javascript
		Object result = ((JavascriptExecutor) driver).executeScript("return $('#user').text()");
		assertThat((String)result, is("success"));
		// Browser back
		driver.navigate().back();
		// Forward
		driver.navigate().forward();
		// Refresh
		driver.navigate().refresh();
		// 60 seconds wait until target is appeared.
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-cart")));
		// Alert dialog click OK.
		driver.switchTo().alert().accept();
		// Confirm dialog click OK.
		driver.switchTo().alert().accept();
		// Cancel
		driver.switchTo().alert().dismiss();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
}
