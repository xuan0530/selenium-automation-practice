import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.SearchResultPage;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class FirstSeleniumTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        System.out.println("正在执行 @BeforeEach: 初始化浏览器...");
        WebDriverManager.chromedriver().setup();

        // 2. 创建一个 ChromeOptions 对象
        ChromeOptions options = new ChromeOptions();

        // 3. 添加启动参数
        options.addArguments("--headless"); // 关键参数：启用无头模式
        options.addArguments("--disable-gpu"); // 官方推荐，在某些系统上避免bug
        options.addArguments("--window-size=1920,1080"); // 设置一个窗口大小，避免有些元素因窗口太小而找不到
        options.addArguments("--no-sandbox"); // 在Linux环境下运行，绕过沙箱模型的一些问题
        options.addArguments("--disable-dev-shm-usage"); // 解决某些Linux环境下/dev/shm空间不足的问题

        // 4. 将配置好的 options 传入 ChromeDriver 的构造函数
        driver = new ChromeDriver(options);

        // 隐式等待等其他设置保持不变
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
