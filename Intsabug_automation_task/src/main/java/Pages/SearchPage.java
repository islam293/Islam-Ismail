package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


/* This is the class containing all the elements, actions & methods
 that will be done on a specific page "Search page"  */
public class SearchPage extends PageBase {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    //Declare the elements that we are dealing with
    By searchBox = By.xpath("//input[@class='gLFyf gsfi' and @type='text']");
    By searchButton= By.xpath("//input[@name='btnK' and @type='submit']");

    //Creating a method that contains some actions on the elements in page
    public void searchKeyWord(String keyword){
        sendText(searchBox, keyword);
        clickButton(searchButton);

        String url = driver.getCurrentUrl();

        // Assert that we reached the value and the result we want
        Assert.assertTrue(url.contains("https://www.google.com/search?q="));


    }
}
