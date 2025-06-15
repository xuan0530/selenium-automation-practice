import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.SearchResultPage;

import java.time.Duration;

public class FirstSeleniumTest {
    private WebDriver driver;

    @BeforeEach
    void setUp()  {
        System.out.println("Setting up WebDriver");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();//设置最大化
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("打开电商网站并验证标题")
    void testOpenSiteAndCheckTitle(){
        String url = "http://www.automationpractice.pl/index.php";
        System.out.println("正在执行@Test：访问" + url);

        driver.get(url);

        String actualTitle = driver.getTitle();
        System.out.println("title:" + actualTitle);

        String expectedTitle = "My Shop";

        Assertions.assertEquals(expectedTitle, actualTitle,"页面标题不匹配");
        System.out.println("测试成功");
    }

    @AfterEach
    void tearDown(){
        System.out.println("正在关闭浏览器");
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("在首页搜索T-shirt并验证结果")
    void testSearchForTshirt(){
        driver.get("http://www.automationpractice.pl/index.php");
        //定位元素
        WebElement searchBox = driver.findElement(By.id("search_query_top"));
        WebElement searchButton = driver.findElement(By.name("submit_search"));

        System.out.println("在搜索框中输入Tshirt");
        searchBox.sendKeys("T-shirt");

        System.out.println("点击搜索按钮");
        searchButton.click();

        WebElement resultsHeader = driver.findElement(By.className("lighter"));

        String headerText = resultsHeader.getText();
        System.out.println("headerText:" + headerText);

        Assertions.assertTrue(headerText.contains("T-SHIRT"), "结果不符合");
        System.out.println("success");
    }

    @Test
    @DisplayName("[pom]")
    void testSearchForTshirtWithPom(){
        driver.get("http://www.automationpractice.pl/index.php");

        HomePage homePage = new HomePage(driver);
        homePage.searchFor("T-shirt");

        SearchResultPage searchResultPage = new SearchResultPage(driver);
        String headerText = searchResultPage.getHeaderText();

        Assertions.assertTrue(headerText.contains("T-SHIRT"),"搜索结果不符合");
        System.out.println("success");
    }
}
