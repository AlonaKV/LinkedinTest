package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by shtrih on 24.07.16.
 */
public class AdvancedPage extends BasePage {

    public AdvancedPage() {
        PageFactory.initElements(driver, this);
        impliciWaitForElement(searchForm);
    }

    @FindBy(id = "advs")
    private WebElement searchForm;

    @FindBy(id = "advs-keywords")
    private WebElement keywordsField;

    @FindBy(name = "submit")
    private WebElement searchButton;

    @FindBy(css = ".suggested-search.bd>a")
    private WebElement hrLists;

    @FindBy(css = ".suggested-search.bd>a")
    private WebElement allResultsForSearch;

    @FindBy(xpath = ".//*[@id='results-pagination']/ul/li")
    private WebElement locator;
    List<String> hr = new ArrayList<String>();

    /**
     * Send search word in the field, return the selection results, create List, and check that
     * all elements in the list include "HR".
     */

    public void filladvsKeywordsAndSubmit() {
        keywordsField.sendKeys("HR");
        searchButton.submit();
        impliciWaitForElement(allResultsForSearch);
        allResultsForSearch.click();

        //Indicates the value of the visible page via the variable "i" and pass current variable in xpath of the button.

        List<WebElement> allPages = driver.findElements(By.xpath(".//*[@id='results-pagination']/ul/li"));
        impliciWaitForElement(locator);
        for (int i = 1; i <= 6; i++) {
            driver.findElement(By.xpath(".//*[@id='results-pagination']/ul/li[" + i + "]")).click();

            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Create List of the people.
            List<WebElement> hrList = driver.findElements(By.cssSelector(".mod.result.people"));
            for (int j = 0; j < hrList.size(); j++) {

                int k = j+1;
                String position = driver.findElement(By.xpath(".//*[@id='results']/li[" + k + "]/div/div[1]")).getText();
                hr.add(position);
                System.out.println(position);
            }
            //After clicking next button their index is increased by 1.
            if(i == 2){
                i ++;
            }
        }
    }

    public List<String> getHr(){
        return hr;
    }
}