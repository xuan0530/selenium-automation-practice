package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultPage {
    private WebDriver driver;

    private By headerLocator = By.className("lighter");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeaderText() {
        System.out.println("getHeaderText");
        WebElement header = driver.findElement(headerLocator);
        return header.getText();
    }

}
