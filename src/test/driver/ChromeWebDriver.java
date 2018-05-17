package test.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeWebDriver {
	protected static WebDriver driver = null;
	
	public static WebDriver createWebDriverForPC(WebDriver driver){
		// Be careful! Cookies and Cache and Web Storage data will keep when this test running.
		System.setProperty("webdriver.chrome.driver", "./webdriver/chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}
	
	public static void closeWebDriver(WebDriver driver){
		driver.quit();
	}
}
