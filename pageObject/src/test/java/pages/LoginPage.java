package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {

    LoginPage(WebDriver driver){
        super(driver);
    }


    public LoginPage fillLoginAndPwd(String l, String p){
        By fLogin = By.xpath("//div[@class='new-input-line new-input-line_slim new-input-line_relative']/input[@type='text' and @name='email']");
        fillField(fLogin, l);

        By fPassword = By.xpath("//div[@class='new-input-line new-input-line_slim new-input-line_relative']/input[@type='password' and @name='password']");
        fillField(fPassword, p);

        return this;
    }

    public MyPage login(String l, String p){
        fillLoginAndPwd(l, p);

        By bEnter = By.xpath("//div[@class='new-input-line new-input-line_last new-input-line_relative']/button[@type='submit' and contains(text(), 'Войти')]");
        click(bEnter);

        return new MyPage(driver);
    }
}
