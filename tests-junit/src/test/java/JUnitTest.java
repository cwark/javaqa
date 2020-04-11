import config.TestsConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.influxdb.dto.Point;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;


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

    @Rule
    public TestWatcher watcher = new TestWatcher() {
        private long startMillis = new Date().getTime();

        @Override
        protected void succeeded(Description description) {
            sendTestMethodStatus(description, "succeeded".toUpperCase());
            logger.info("succeeded");
        }

        @Override
        protected void failed(Throwable e, Description description) {
            sendTestMethodStatus(description, "failed".toUpperCase());
            logger.info("failed");
        }

        @Override
        protected void skipped(AssumptionViolatedException e, Description description) {
            sendTestMethodStatus(description, "skipped".toUpperCase());
            logger.info("skipped");
        }

        @Override
        protected void starting(Description description) {
            logger.info("starting");

        }

        @Override
        protected void finished(Description description) {
            logger.info("finished");
        }

        private void sendTestMethodStatus(Description d, String status) {
            Point point = Point.measurement("testmethod")
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .tag("testclass", d.getTestClass().getName())
                    .tag("name", d.getMethodName())
                    .tag("description", d.getDisplayName())
                    .tag("result", status)
                    .addField("duration", (new Date().getTime() - startMillis))
                    .build();
            ResultSender.send(point);
        }

    };

    private boolean checkEven() {
        int n = (int) (Math.random() * 100);
        return n % 2 == 0;
    }

    private void testMethod(){
        driver.get(cfg.url());

        try {
            Thread.sleep((long) (Math.random() * 10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Открыта страница OTUS!");

        Assert.assertTrue("Не чётное!", checkEven());
    }

    @Test
    public void openPage(){
       testMethod();
    }

    @Test
    public void openPageTwo(){
        testMethod();
    }

    @Test
    public void openPageThree(){
        testMethod();
    }

    @After
    public void setDown(){
        if (driver!=null){
            driver.quit();
        }
    }


}
