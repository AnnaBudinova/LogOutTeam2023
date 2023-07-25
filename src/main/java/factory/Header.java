package factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    private final WebDriver driver;
    @FindBy(id = "nav-link-profile")
    private WebElement profileLink;
    @FindBy(id = "nav-link-login")
    private WebElement loginLink;
    @FindBy(id= "nav-link-logout")
    private WebElement clickSmallLogOutIcon;
    @FindBy(id="nav-link-new-post")
    private WebElement newPostLink;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void clickProfile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(profileLink));
        profileLink.click();
    }
    public void clickLogin() {
        loginLink.click();
    }
    public void newPostLink() {
        newPostLink.click();
    }
    public void clickSmallLogOutIcon() {
        clickSmallLogOutIcon.click();
    }
    public void clickSmallLogOut() {
        WebElement clickSmallLogOut = driver.findElement(By.xpath("//*[@class='navbar-toggler']"));
        clickSmallLogOut.click();
    }
    public void clickLogOut() {
        WebElement logoutLink = driver.findElement(By.xpath("//*[@class='fas fa-sign-out-alt fa-lg']"));
        logoutLink.click();
    }
}