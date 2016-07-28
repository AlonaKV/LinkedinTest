package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    public static WebDriver driver = new FirefoxDriver();


    public BasePage() {

    }
    public void impliciWaitForElement(WebElement element){
        WebDriverWait implicitWaitTime = new WebDriverWait(driver, 45);
        implicitWaitTime.until(ExpectedConditions.elementToBeClickable(element)); // implicit Wait
    }
}

//public void waitForOllListInThePage(WebElement element){
//    WebDriverWait impliciWaitForList= new WebDriverWait(driver, 45);
//    impliciWaitForList.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) element)); // implicit Wait
//}