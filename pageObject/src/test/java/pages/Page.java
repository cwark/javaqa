package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Page extends AbstractPage {
    public Page(WebDriver driver) {
        super(driver);
    }

    public void click(By byXPath){
        wait.until(presenceOfElementLocated(byXPath));
        driver.findElement(byXPath).click();
    }

    public void fillField(By byXPath, String value){
        wait.until(presenceOfElementLocated(byXPath));
        driver.findElement(byXPath).clear();
        driver.findElement(byXPath).sendKeys(value);
    }

    public String getText(By byXPath){
        return driver.findElement(byXPath).getAttribute("value");
    }
}
