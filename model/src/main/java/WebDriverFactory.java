import config.TestsConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    private final static Logger logger = LogManager.getLogger(WebDriverFactory.class);
    private final static TestsConfig cfg = ConfigFactory.create(TestsConfig.class);

    private enum Browsers {
        CHROME,
        FIREFOX
    }

    public static WebDriver createNewDriver(String webDriverName, WebDriver.Options options) {
        if (webDriverName == null) {
            throw new IllegalStateException("Unexpected value: " + cfg.browser().toLowerCase());
        }
        try {
            WebDriver driver;

            Browsers bro = Browsers.valueOf(webDriverName.toUpperCase());

            switch (bro) {
                case CHROME: {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    logger.info("Поднят драйвер Chrome!");
                    break;
                }
                case FIREFOX: {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    logger.info("Поднят драйвер Firefox!");
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + cfg.browser().toLowerCase());
            }
            return driver;

        } catch (IllegalStateException e) {
            throw new IllegalStateException("Unexpected value: " + cfg.browser().toLowerCase());
        }

    }

    public static WebDriver createNewDriver(String webDriverName) {
        return createNewDriver(webDriverName, null);
    }
}
