package selenium_web_driver_test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Pc_test_quickstep_chrome {
	protected static WebDriver driver = null;
	private WebDriverWait wait = new WebDriverWait(driver, 60);
	private WebElement element;
	@BeforeClass
	public static void setUpBeforeClass(){
		// Be careful! Cookies and Cache and Web Storage data will keep when this test running.
		if(driver == null){
			System.setProperty("webdriver.chrome.driver", "./webdriver/chromedriver.exe");
			driver = new ChromeDriver();
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
	public void test(){
		driver.get("https://item.rakuten.co.jp/soukaidrink/9000009984074/");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class='cart-button checkout new-cart-button ']")));
		element = driver.findElement(By.cssSelector("button[class='cart-button checkout new-cart-button ']"));
		element.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-shop-id='306273'")));
		driver.findElement(By.cssSelector("input[data-shop-id='306273'")).click();
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
}
