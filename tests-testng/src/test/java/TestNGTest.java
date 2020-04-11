import config.TestsConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Random;


@Listeners(TestNGListener.class)
public class TestNGTest {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(TestNGTest.class);
    private TestsConfig cfg = ConfigFactory.create(TestsConfig.class);

    private final String chrome = "chrome";
    private final String firefox = "firefox";

    @BeforeTest
    private void setUp(){
        switch (cfg.browser().toLowerCase()){
            case chrome: {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                logger.info("Поднят драйвер Chrome!");
                break;
            }
            case firefox: {
                WebDriverManager.firefoxdriver();
                driver = new FirefoxDriver();
                logger.info("Поднят драйвер Firefox!");
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + cfg.browser().toLowerCase());
        }
    }

    @Test(description = "Открытие страницы OTUS")
    public void openPage(){
        driver.get(cfg.url());

        try {
            Thread.sleep((long) (Math.random() * 10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Открыта страница OTUS!");
    }


    @AfterTest
    private void setDown(){
        if (driver!=null){
            driver.quit();
        }
        logger.info("Завершили работу!");
    }


}
