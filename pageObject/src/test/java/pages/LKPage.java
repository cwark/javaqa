package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LKPage extends Page {

    public LKPage(WebDriver driver) {
        super(driver);
    }

    public MySelfPage goToMySelf(){
        By lMySelf = By.xpath("//div[@class='nav nav_mobile-fix no-print js-overflow-scroll']//a[@title='О себе']");

        click(lMySelf);

        return new MySelfPage(driver);
    }
}
