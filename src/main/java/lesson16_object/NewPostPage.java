package lesson16_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NewPostPage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/posts/create";
    private final WebDriver driver;

    public NewPostPage(WebDriver driver) {
        this.driver = driver;
    }

    public String isTextDisplayed() {
        WebElement isTextDisplayed = driver.findElement(By.xpath("//*[@class='text-center']"));
        return isTextDisplayed.getText();
    }
    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlContains(NewPostPage.PAGE_URL));
    }
}
