package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage {
    private WebDriver driver ;

    private By searchBoxLocator = By.id("search_query_top");
    private By searchBtuttonLocator = By.name("submit_search");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchFor(String keyword) {
        System.out.println("在首页执行搜索，关键词" + keyword);
        WebElement searchBox = driver.findElement(searchBoxLocator);
        WebElement searchButton = driver.findElement(searchBtuttonLocator);

        searchBox.sendKeys(keyword);
        searchButton.click();
    }
}
