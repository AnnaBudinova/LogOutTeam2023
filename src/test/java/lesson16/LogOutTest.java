package lesson16;

import io.github.bonigarcia.wdm.WebDriverManager;
import factory.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LogOutTest {
    private WebDriver driver;

    @BeforeSuite
    protected final void setupTestSuite() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
    }

    @BeforeMethod
    protected final void setUpTest() {
        this.driver = new EdgeDriver();
        this.driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    protected final void tearDownTest() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{
                {"anbori@abv.bg", "_Passw0rd", "annabudinova"}, //login with username
        };
    }

    @DataProvider(name = "loginUser")
    public Object[][] loginUsers() {
        return new Object[][]{
                {"anbori@abv.bg", "_Passw0rd"},
        };
    }

    @Test(dataProvider = "getUsers")
    public void testLogOutFromProfile(String user, String password, String name) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login(user, password);

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is not correct!");

        Header header = new Header(driver);
        header.clickProfile();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is not correct!");
        String actualUserName = profilePage.getUsername();
        Assert.assertEquals(actualUserName, name, "The username is incorrect!");
        header.clickLogOut();
        Assert.assertTrue(loginPage.isUrlLoaded(), "The Login URL is not correct!");
    }

    @Test(dataProvider = "loginUser")
    public void testLogOutFromNewPost(String user, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login(user, password);
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is not correct!");
        Header header = new Header(driver);
        header.newPostLink();

        NewPostPage newPostPage = new NewPostPage(driver);
        Assert.assertTrue(newPostPage.isUrlLoaded(), "The Profile URL is not correct!");
        String isTextDisplayed = newPostPage.isTextDisplayed();
        Assert.assertEquals(isTextDisplayed, "Post a picture to share with your awesome followers");
        header.clickLogOut();
        Assert.assertTrue(loginPage.isUrlLoaded(), "The Login URL is not correct!");
    }

    @Test(dataProvider = "loginUser")
    public void testLogOutFromHome(String user, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login(user, password);
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is not correct!");
        Header header = new Header(driver);
        header.clickLogOut();
        Assert.assertTrue(loginPage.isUrlLoaded(), "The Login URL is not correct!");
    }

    @Test(dataProvider = "getUsers")
    public void testLogOutSmallScreen(String user, String password, String name) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login(user, password);

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is not correct!");
        this.driver.manage().window().setSize(new Dimension(400, 626));

        Header header = new Header(driver);
        header.clickSmallLogOut();
        header.clickSmallLogOutIcon();
        Assert.assertTrue(loginPage.isUrlLoaded(), "The Login URL is not correct!");
    }
}
