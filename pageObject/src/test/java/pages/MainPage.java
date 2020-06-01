package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class MainPage extends Page {



    public MainPage(WebDriver driver){
        super(driver);
    }

    public MainPage open(){
        driver.get("https://otus.ru/");
        return this;
    }

    public LoginPage clickSignInOrUp(){
        By buttonEnterOrReg = By.xpath("//button[@class='header2__auth js-open-modal']");
        click(buttonEnterOrReg);
        return new LoginPage(driver);
    }



}