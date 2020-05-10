import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class MarketTest extends Base {

    private final String PAGE_TITLE = "Мобильные телефоны — купить на Яндекс.Маркете";
    private final String PAGE_URL = "https://market.yandex.ru/catalog--mobilnye-telefony/54726/list?hid=91491";
    private final String HUAWEI = "//div[@id='search-prepack']//span[text()='HUAWEI']";
    private final String XIAOMI = "//div[@id='search-prepack']//span[text()='Xiaomi']";

    private enum Direction {
        ASC,
        DESC
    }

    @Test
    public void marketTest() {
        driver.get(PAGE_URL);
//ждём закрузки страницы и параметров фильтрации
        wait.until(titleIs(PAGE_TITLE));
        wait.until(visibilityOf(driver.findElement(By.xpath("//div[@class='search-layout']"))));
//выбираем Huawei в качестве производителя
        filterByMaker(HUAWEI);
//выбираем Xiaomi в качестве производителя
        filterByMaker(XIAOMI);
//сортируем по цене по убыванию
        sortByPrice(Direction.ASC);
//добавляем в стравнение
        addToCompare("Xiaomi");
        addToCompare("HUAWEI");
//переходим на страницу стравннеия товаров
        goToCompare();
//сравниваем товары
        compare();
    }

    private void filterByMaker(String pro) {
        String bodyData = driver.findElement(By.xpath("//body")).getAttribute("data-reqid-chain");

        WebElement element = driver.findElement(By.xpath(pro));
        click(element);

        wait.until(elementToBeSelected(By.xpath(pro + "/../../input")));
        wait.until(not(attributeToBe(By.xpath("//body"), "data-reqid-chain", bodyData)));
    }

    private void sortByPrice(Direction d) {
        String bodyData = driver.findElement(By.xpath("//body")).getAttribute("data-reqid-chain");

        WebElement element = driver.findElement(By.xpath("//a[text()='по цене']"));
        click(element);

        wait.until(urlContains("aprice"));
        wait.until(presenceOfElementLocated(By.cssSelector("div.n-filter-sorter_sort_asc")));
        wait.until(not(attributeToBe(By.xpath("//body"), "data-reqid-chain", bodyData)));
    }

    private void addToCompare(String maker) {
        String xpath = "//div[@class='n-snippet-cell2__brand-name' and text()='" + maker + "']/../..";
        wait.until(presenceOfElementLocated(By.xpath(xpath)));

        WebElement mainElement = driver.findElement(By.xpath(xpath));

        WebElement titleElement = mainElement.findElement(By.xpath(".//h3[@class='n-snippet-cell2__title']/a"));
        String title = titleElement.getAttribute("title");

        WebElement hoverElement = mainElement.findElement(By.xpath(".//div[@class='n-snippet-cell2__hover']"));

        Actions action = new Actions(driver);
        action.moveToElement(hoverElement, 1, 1).build().perform();

        WebElement compareElement = hoverElement.findElement(By.xpath(".//span[text()='Сравнить']/.."));
        click(compareElement);

        By locator = By.xpath("//div[@class='popup-informer__title']");
        wait.until(presenceOfElementLocated(locator));
        mainElement = driver.findElement(locator);

        Assert.assertEquals(mainElement.getText(), "Товар " + title + " добавлен к сравнению");

        mainElement = driver.findElement(By.xpath("//div[@class='popup-informer__close image image_name_close']"));
        click(mainElement);
        wait.until(invisibilityOfElementLocated(locator));
    }

    private void goToCompare() {
        WebElement element = driver.findElement(By.xpath("//span[text()='Сравнение']"));
        click(element);
        wait.until(presenceOfElementLocated(By.xpath("//h1[text()='Сравнение товаров']")));
    }

    private void compare() {
        int size = driver.findElements(By.xpath("//div[@class='n-compare-head__image']/img")).size();
        Assert.assertEquals(size, 2);

        WebElement element = driver.findElement(By.xpath("//span[@class='link__inner' and text()='все характеристики']"));
        click(element);

        wait.until(presenceOfElementLocated(By.xpath("//div[@class='n-compare-row-name i-bem' and text()='Операционная система']")));

        element = driver.findElement(By.xpath("//span[@class='link__inner' and text()='различающиеся характеристики']"));
        click(element);

        wait.until(invisibilityOfElementLocated(By.xpath("//div[@class='n-compare-row-name i-bem' and text()='Операционная система']")));
    }

    private void click(WebElement element) {
        wait.until(elementToBeClickable(element));
        element.click();
    }
}
