package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object for Registration Page
 */
public class LoginAndRegistrationPage extends BasePage {

    public LoginAndRegistrationPage() {
        driver.get("https://www.linkedin.com/");
        PageFactory.initElements(driver, this);
        impliciWaitForElement(firstNameField);
    }

    @FindBy(id="first-name")
    private WebElement firstNameField;

    @FindBy(id="last-name")
    private WebElement lastNameField;

    @FindBy(id="join-email")
    private WebElement emailField;

    @FindBy(id="join-password")
    private WebElement passwordField;

    @FindBy(css = ".btn.btn-primary.join-btn")
    private WebElement joinNowButton;

    @FindBy(css=".uno-alert.error.hidden>p>strong")
    private WebElement alertText;

    @FindBy(id = "login-email")
    private WebElement loginEmailField;

    @FindBy(id = "login-password")
    private WebElement loginPasswordField;

    @FindBy(name = "submit")
    private WebElement signInButton;

    /**
     * Fills all fields for registration new user.
     * @param firstName - user First Name
     * @param lastName - user Last Name
     * @param email - user email
     * @param password - user password
     */
    public void fillRegistrationForm ( String firstName, String lastName, String email, String password) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        emailField.clear();
        emailField.sendKeys(email);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    /**
     * Fills login form and switches to new user main page.
     * Initializes this page as new Driver, and send this information to the HomePage.
     * @param email - fill valid email.
     * @param password - fill valid password.
     * @return - Initializes Driver and send to the HomePage.
     */
    public HomePage login(String email, String password){
        loginEmailField.clear();
        loginEmailField.sendKeys(email);
        loginPasswordField.clear();
        loginPasswordField.sendKeys(password);
        signInButton.submit();
        return PageFactory.initElements(driver, HomePage.class); //можно еще return new HomePage
    }


    public void clickJoinNowButton (){
        joinNowButton.click();
    }

    /**
     * Fills all fields for registration new user using method fillRegistrationForm, and submit this
     * information using method clickJoinNowButton.
     * @param firstName - fill user's first name
     * @param lastName -fill user's last name
     * @param email - fill user's email
     * @param password - fill user's password
     */
    public void fillAndSubmitRegistrationForm (String firstName, String lastName, String email, String password){
        fillRegistrationForm(firstName, lastName, email, password);
        clickJoinNowButton();
    }

    /**
     * If information about user was not valid, you will see text.
     * @return - the resulting text
     */
    public String getAlertText () {
        return alertText.getText();
    }

    public void ShutDown(){
        driver.quit();
    }

}