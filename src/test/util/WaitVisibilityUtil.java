package test.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitVisibilityUtil {
	private WebDriverWait wait;
	public void waitUntilVisible(WebDriver driver, int second, String target){
		wait = new WebDriverWait(driver, second);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(target)));
	}
}
