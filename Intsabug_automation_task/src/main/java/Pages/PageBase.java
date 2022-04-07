package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/*This is the abstract page base class  where all other classes are inherited from.
      It contains all the methods and variables which are common and will be used on all pages.*/
public class PageBase {


    // Declaration the webdriver and the wait time that will be used.
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Initialize the driver and set the explicit wait time
    public PageBase(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,20);
    }

    //A method to click on a button but after checking its visibility and clickability
    protected void clickButton(By buttonName) {
        WebElement button = driver.findElement(buttonName);
        wait.until(ExpectedConditions.visibilityOf(button));
        wait.until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }

    // A method to type text in a field after clearing the initial text if exists
    protected void sendText (By textFieldName, String value) {
        WebElement textField = driver.findElement(textFieldName);
        wait.until(ExpectedConditions.visibilityOf(textField));
        textField.clear();
        textField.sendKeys(value);
    }

}
