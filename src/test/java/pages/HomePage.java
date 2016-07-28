package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by mykola on 7/19/2016.
 */
public class HomePage extends BasePage{

    @FindBy(css = ".name")
    private WebElement userFullName;

    @FindBy (xpath = "//*[@id='account-nav']/ul/li[4]/a")
    private WebElement accountSettingsTab;

    @FindBy (id = "advanced-search")
    private WebElement advancedButton;

    public HomePage(){
        PageFactory.initElements(driver, this);
        impliciWaitForElement(accountSettingsTab);
    }

    /**
     * Find user name in the user's home page.
     * @return - text from element that was found.
     */
    public  String getUserFullName() {
        return userFullName.getText();
    }

    public boolean isAccountSettingsTabDisplayed(){
        return accountSettingsTab.isDisplayed();
    }

    public AdvancedPage clickAdvancedbutton(){
        advancedButton.click();
        return PageFactory.initElements(driver, AdvancedPage.class);
    }

    public String  getTitle() {
       /// System.out.println(driver.getTitle());
        return driver.getTitle();
    }

    public void shutDown(){
        driver.quit();
    }
}