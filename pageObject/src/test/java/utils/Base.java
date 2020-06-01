package utils;

import config.TestsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.MainPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class Base {
    protected static WebDriver driver;
    protected final Logger logger = LogManager.getLogger(Base.class);
    protected final TestsConfig cfg = ConfigFactory.create(TestsConfig.class);
    protected WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        initDriver();
    }

    public void initDriver(){
        driver = WebDriverFactory.createNewDriver(cfg.browser());
        wait = new WebDriverWait(driver, 30);
    }

    @AfterTest
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
        logger.info("Завершили работу!");
    }

    protected void click(WebElement element) {
        wait.until(elementToBeClickable(element));
        element.click();
    }

}
