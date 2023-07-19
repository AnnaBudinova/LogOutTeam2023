import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LogOut {
    private WebDriver driver;
    @BeforeSuite
    protected final void setupTestSuite() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
    }
    @BeforeMethod
    protected final void setUpTest() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @AfterMethod
    protected final void tearDownTest() {
        if (this.driver != null) {
            this.driver.close();
        }
    }
    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{ //login with username
                {"anbori@abv.bg", "_Passw0rd", "annabudinova"},
        };
    }

    @Test(dataProvider = "getUsers")
    public void testLogOutSmallScreen(String user, String password, String name){
        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        WebElement signInElement = driver.findElement(By.xpath("//*[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInElement));

        WebElement userNameField = driver.findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys(user);

        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys(password);

        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-in-button")));
        signInButton.click();

        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-profile")));
        profileLink.click();

        wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/users/"));

        Boolean isTextDisplayed = wait.until(ExpectedConditions.textToBe(By.tagName("h2"), name));
        Assert.assertTrue(isTextDisplayed, "The username is not displayed!");

        this.driver.manage().window().setSize(new Dimension(400, 626));
        WebElement logoutLinkSmallIcon = driver.findElement(By.xpath("//*[@class='navbar-toggler']"));
        logoutLinkSmallIcon.click();

        WebElement logoutLink = driver.findElement(By.id("nav-link-logout"));
        logoutLink.click();

        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        signInElement = driver.findElement(By.xpath("//*[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInElement));
    }

    @Test(dataProvider = "getUsers")
    public void testLogOutFromProfile(String user, String password, String name){
        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        WebElement signInElement = driver.findElement(By.xpath("//*[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInElement));

        WebElement userNameField = driver.findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys(user);

        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys(password);

        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-in-button")));
        signInButton.click();

        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-profile")));
        profileLink.click();

        wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/users/"));

        Boolean isTextDisplayed = wait.until(ExpectedConditions.textToBe(By.tagName("h2"), name));
        Assert.assertTrue(isTextDisplayed, "The username is not displayed!");

        WebElement logoutLink = driver.findElement(By.xpath("//*[@class='fas fa-sign-out-alt fa-lg']"));
        logoutLink.click();

        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        signInElement = driver.findElement(By.xpath("//*[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInElement));
    }

    @Test(dataProvider = "getUsers")
    public void testLogOutFromNewPost(String user, String password, String name){
        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        WebElement signInElement = driver.findElement(By.xpath("//*[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInElement));

        WebElement userNameField = driver.findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys(user);

        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys(password);

        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-in-button")));
        signInButton.click();

        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-new-post")));
        profileLink.click();

        wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/posts/create"));

        WebElement isTextDisplayed = driver.findElement(By.xpath("//*[@class='text-center']"));
        wait.until(ExpectedConditions.visibilityOf(isTextDisplayed));

        WebElement logoutLink = driver.findElement(By.xpath("//*[@class='fas fa-sign-out-alt fa-lg']"));
        logoutLink.click();

        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        signInElement = driver.findElement(By.xpath("//*[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInElement));
    }

    @Test(dataProvider = "getUsers")
    public void testLogOutFromHome(String user, String password, String name){
        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        WebElement signInElement = driver.findElement(By.xpath("//*[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInElement));

        WebElement userNameField = driver.findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys(user);

        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys(password);

        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-in-button")));
        signInButton.click();

        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-home")));
        profileLink.click();

        wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/posts/all"));

        WebElement isTextDisplayed = driver.findElement(By.id("nav-link-home"));
        wait.until(ExpectedConditions.visibilityOf(isTextDisplayed));

        WebElement logoutLink = driver.findElement(By.xpath("//*[@class='fas fa-sign-out-alt fa-lg']"));
        logoutLink.click();

        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        signInElement = driver.findElement(By.xpath("//*[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInElement));
    }
}
