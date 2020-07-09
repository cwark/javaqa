package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverFactory;

public class MySelfPage extends Page {

    public MySelfPage(WebDriver driver) {
        super(driver);
    }

    public String getNameValue() {
        By by = By.xpath("//input[@name='fname']");
        return getText(by);
    }

    public String getFamValue() {
        By by = By.xpath("//input[@name='lname']");
        return getText(by);
    }

    public MySelfPage fillNameAndFam(){
        By by;
        by = By.xpath("//input[@name='fname']");
        fillField(by, "Борис");

        by = By.xpath("//input[@name='lname']");
        fillField(by, "Берёзовиков");

        return this;
    }

    public MySelfPage saveAndLater(){
        By by = By.xpath("//button[@title='Сохранить и заполнить позже']");
        click(by);

        by = By.xpath("//div[@class='container__col container__col_3 container__col_md-0 container__col_sm-3 container__col_ssm-0 no-print']//a[@class='button button_md-4 button_blue lk-cv-action-buttons__button']");
        wait.until(ExpectedConditions.presenceOfElementLocated(by));

        return this;
    }

    public MySelfPage clickAddContact(){
        By bAddContact = By.xpath("//button[text()='Добавить']");
        click(bAddContact);

        return this;
    }
}
