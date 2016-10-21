package tests;

import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SharedUiMapTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public Properties prop;
  
  @Before
  public void setUp() throws Exception {
	  prop = new Properties();
	  prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
    driver = new FirefoxDriver();
    baseUrl = "http://adactin.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testMyFirstWebDriver1() throws Exception {
    driver.get("http://adactin.com/HotelApp/");
    //Login user name By.cssSelector("#username")- user_name_field_login_page
    driver.findElement(By.cssSelector(prop.getProperty("user_name_field_login_page"))).clear();
    driver.findElement(By.cssSelector(prop.getProperty("user_name_field_login_page"))).sendKeys("onetest1");
    
    //Password field By xpath for Password field (".//*[@id='password")-password_field_login_page
    driver.findElement(By.xpath(prop.getProperty("password_field_login_page"))).clear();
    driver.findElement(By.xpath(prop.getProperty("password_field_login_page"))).sendKeys("P@55word");
    
    //Login button By Name ("login")-Submit_button
    driver.findElement(By.name(prop.getProperty("Submit_button"))).click();
    
    //new Select(driver.findElement(By.id("location"))).selectByVisibleText("Sydney");
    //new Select(driver.findElement(By.id("hotels"))).selectByVisibleText("Hotel Sunshine");
    //driver.findElement(By.id("Submit")).click();
    //driver.findElement(By.linkText("Logout")).click();
    
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
