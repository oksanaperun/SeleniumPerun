package lesson6;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Oksana on 09.11.2014.
 */
public class StylusSearchText {
    WebDriver driver;

    //Preconditions
    @BeforeClass
    public void setUp() {

        //Initializes a browser
        driver = new FirefoxDriver();

        //Sets implicit wait
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Maximizes a window size
        driver.manage().window().maximize();

        //Opens a url
        driver.get("http://stylus.com.ua/");

        //Verifies that page is opened
        WebElement searchField = driver.findElement(By.name("search_text"));
        Assert.assertTrue(searchField.isDisplayed());
    }

    @Test
    public void openProductPageTest(String searchText) {

        //Finds search field
        WebElement searchField = driver.findElement(By.name("search_text"));

        //Sends search text into search field
        searchField.sendKeys(searchText);

        //Click on search field
        searchField.sendKeys(Keys.ENTER);

        //Decomposed search text into separate words
        String searchTextArray[] = searchText.split(" ");

        //Finds first link with founded product
        WebElement productLink = driver.findElement(By.xpath(".//tbody/tr/td/h4/a"));

        //Verifies a result
        int i;
        for (i = 0; i < searchTextArray.length - 1; i++)
            Assert.assertTrue(productLink.getText().toString().contains(searchTextArray[i]));

        //Open product page
        productLink.sendKeys(Keys.ENTER);

        //Finds element to verify that product page is opened
        WebElement productPageElement = driver.findElement(By.xpath(".//div/h3/a/span[@itemprop = 'name']"));

        //Verifies a result
        for (i = 0; i < searchTextArray.length - 1; i++)
            Assert.assertTrue(productPageElement.getText().toString().contains(searchTextArray[i]));
    }

    @Test
    public void verifyCharacteristicOnProductPageTest() {
        //Open product age
        openProductPageTest("Sony Z2");

        //Finds characteristics tab
        WebElement characteristics = driver.findElement(By.xpath(".//div/ul/li/a[@title = 'Характеристики']"));

        //Click to open characteristics tab
        characteristics.sendKeys(Keys.ENTER);

        //Finds input of Internet Access characteristic
        WebElement characteristicsInternetAccessValue = driver.findElement(By.xpath(".//tbody/tr/" +
                "td[@class = 'property property-name'][contains(text(), 'Интернет-доступ')]/" +
                "following-sibling::*[@class = 'property property-value']/div"));

        //Verifies a result
        Assert.assertTrue(characteristicsInternetAccessValue.getText().toString().contains("HTML, HTML5, Adobe " +
                "Flash, RSS"));

    }

    @AfterClass
    public void tearDown() {
        //Closes a browser
        driver.quit();
    }
}
