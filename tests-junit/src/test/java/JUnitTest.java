import config.TestsConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class JUnitTest {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(JUnitTest.class);
    private TestsConfig cfg = ConfigFactory.create(TestsConfig.class);

    private final String chrome = "chrome";
    private final String firefox = "firefox";

    @Before
    public void setUp(){
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

    @Test
    public void openPage(){
        driver.get(cfg.url());
        logger.info("Открыта страница OTUS!");
    }

    @After
    public void setDown(){
        if (driver!=null){
            driver.quit();
        }
    }


}
