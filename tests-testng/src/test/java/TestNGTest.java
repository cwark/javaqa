import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TestNGTest {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(TestNGTest.class);

    @BeforeTest
    private void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Поднят драйвер Chrome!");
    }

    @Test
    public void openPage(){
        driver.get("https://otus.ru");
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
