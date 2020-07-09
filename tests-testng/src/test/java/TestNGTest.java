import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
public class TestNGTest extends Base {



    private boolean checkEven() {
        int n = (int) (Math.random() * 100);
        //return n % 2 == 0;
        return true;
    }

    private void testMethod(){
        Base.driver.get(cfg.url());

        try {
            Thread.sleep((long) (Math.random() * 10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Base.driver.manage().window().setSize(new Dimension(300, 300));

        try {
            Thread.sleep((long) (Math.random() * 10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        logger.info("Открыта страница OTUS!");

        Assert.assertTrue(checkEven(), "Не чётное!");
    }

    @Test(description = "Открытие страницы OTUS")
    public void openPage() {
       testMethod();
    }

    @Test(description = "Открытие страницы OTUS")
    public void openPageTwo() {
        testMethod();
    }

    @Test(description = "Открытие страницы OTUS")
    public void openPageThree() {
        testMethod();
    }




}
