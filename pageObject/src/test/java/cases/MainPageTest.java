package cases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.MySelfPage;
import utils.Base;

public class MainPageTest extends Base {
    @Test
    public void openPage() {
        new MainPage(driver)
                .open()
                .clickSignInOrUp()
                .login(cfg.login(), cfg.password())
                .clickByProfile()
                .goToLKPage()
                .goToMySelf()
                .fillNameAndFam()
                .saveAndLater();

        setDown();

        setUp();

        MySelfPage sp = new MainPage(driver)
                .open()
                .clickSignInOrUp()
                .login(cfg.login(), cfg.password())
                .clickByProfile()
                .goToLKPage()
                .goToMySelf();

        Assert.assertEquals(sp.getNameValue(), "Борис");
        Assert.assertEquals(sp.getFamValue(), "Берёзовиков");
    }
}