package tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class VerificationTestCondition {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String expectederrormsg = "Invalid Login Details test";
  private String actualerrormsg;

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://adactin.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testMyFirstWebDriver1() throws Exception {
    driver.get("http://adactin.com/HotelApp/");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("onetest1");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("P@55word");
    driver.findElement(By.id("login")).click();
   // actualerrormsg = driver.findElement(By.cssSelector(".auth_error>b")).getText();
    
    String spageTitle = driver.getTitle();
    if(spageTitle.equalsIgnoreCase("AdactIn.com - Hotel Reservation System"))
    	System.out.println("Page Title is correct " +spageTitle );
    else
    	System.out.println("Page Title is incorrect" +spageTitle );
 
    //assertEquals("Correct Error Message:", expectederrormsg , actualerrormsg);
  //  new Select(driver.findElement(By.id("location"))).selectByVisibleText("Sydney");
  //  new Select(driver.findElement(By.id("hotels"))).selectByVisibleText("Hotel Sunshine");
  //  driver.findElement(By.id("Submit")).click();
   // driver.findElement(By.linkText("Logout")).click();
 
  }

  private void assertequals(String string, String expectederrormsg2, String actualerrormsg2) {
	// TODO Auto-generated method stub
	
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
