package tests;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AdvancedPage;
import pages.HomePage;
import pages.LoginAndRegistrationPage;

/**
 * All possible tests for user Registration
 */
public class LoginAndRegistrationTests {


    @Test
    public void registrationWithEmptyFieldsTest() {
        LoginAndRegistrationPage registrationPage = new LoginAndRegistrationPage();
        registrationPage.clickJoinNowButton();
        String alterText = registrationPage.getAlertText();
        Assert.assertTrue(alterText.contains("Please enter"));
        registrationPage.ShutDown();
    }

    @DataProvider(name = "emptyFieldsValuesCombination")
    public static Object[][] createData() {
        return new Object[][]{
                {"", "Last", "a@gmail.com", "P@ssword"},
                {"First", "", "a@gmail.com", "Pssword"},
                {"First", "Last", "", "Pssword"},
                {"First", "Last", "a@gmail.com", ""},
                {"", "", "", ""},
        };
    }

    @Test(dataProvider = "emptyFieldsValuesCombination")
    public void registrationAllEmptyFieldsCombinationTest(String first, String last, String email, String password) {
        LoginAndRegistrationPage registrationPage = new LoginAndRegistrationPage();
        registrationPage.fillAndSubmitRegistrationForm(first, last, email, password);
        String alterText = registrationPage.getAlertText();
        Assert.assertTrue(alterText.contains("Please enter"));
        registrationPage.ShutDown();
    }
    @Test
    public void successfulLoginTest () {
        LoginAndRegistrationPage loginAndRegistrationPage = new LoginAndRegistrationPage();
        Assert.assertNotNull(loginAndRegistrationPage, "Account Setting tab is not displayed on LoginAndRegistrationPage");
        HomePage userPage = loginAndRegistrationPage.login("alona_kolesnikova@mail.ru", "Qwerty+15987");
        userPage.getUserFullName();
        Assert.assertNotNull(userPage, "Account Setting tab is not displayed on HomePage");
        Assert.assertTrue(userPage.isAccountSettingsTabDisplayed(), "The current doesn't display on the page");
        AdvancedPage advanced = userPage.clickAdvancedbutton();
        Assert.assertNotNull(advanced, "Account Setting tab is not displayed on AdvancePage");
        advanced.filladvsKeywordsAndSubmit();
        Assert.assertTrue(advanced.getHr().contains("HR"), "Look's like that some from the list doesn't match the search");
        Assert.assertEquals(advanced.getHr().size(), 50, "The espected ");
    }
}