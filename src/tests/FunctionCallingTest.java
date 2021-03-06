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

import hotelapp_business_functions.HotelappBusinessFunctions;

public class FunctionCallingTest extends HotelappBusinessFunctions {
//  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
//  public Properties prop;
  
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
  //Calling login using Function
    Ha_Bf_Login (driver, "onetest1", "P@55word");
  //Calling Search using Function
    Search_Hotels (driver, "Sydney", "Hotel Creek");
    
  
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
