import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class JUnitTest {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(JUnitTest.class);

    @BeforeEach
    private void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("JUNIT Поднят драйвер Chrome!");
    }

    @Test
    public void openPage(){
        driver.get("https://otus.ru");
        logger.info("JUNIT Открыта страница OTUS!");
    }

    @AfterEach
    private void setDown(){
        if (driver!=null){
            driver.quit();
        }
    }


}
