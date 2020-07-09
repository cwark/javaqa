package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyPage extends Page {
    MyPage(WebDriver driver){
        super(driver);
    }

    public MyPage clickByProfile(){
        By lProfile = By.xpath("//p[@class='header2-menu__item-text header2-menu__item-text__username']");
        click(lProfile);
        return this;
    }


    public LKPage goToLKPage(){
        By lLK = By.xpath("//a[@title='Личный кабинет']");
        click(lLK);
        return new LKPage(driver);
    }
}
