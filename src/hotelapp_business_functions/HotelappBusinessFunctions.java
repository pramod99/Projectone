package hotelapp_business_functions;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class HotelappBusinessFunctions {
	
	public static Properties prop;
	public static WebDriver driver;
	
public void Ha_Bf_Login (WebDriver driver, String sUserName, String sPassword)
{
    //Login user name By.cssSelector("#username")- user_name_field_login_page
    driver.findElement(By.cssSelector(prop.getProperty("user_name_field_login_page"))).clear();
    driver.findElement(By.cssSelector(prop.getProperty("user_name_field_login_page"))).sendKeys(sUserName);
    
    //Password field By xpath for Password field (".//*[@id='password")-password_field_login_page
    driver.findElement(By.xpath(prop.getProperty("password_field_login_page"))).clear();
    driver.findElement(By.xpath(prop.getProperty("password_field_login_page"))).sendKeys(sPassword);
    
    //Login button By Name ("login")-Submit_button
    driver.findElement(By.name(prop.getProperty("Submit_button"))).click();
}

public void Search_Hotels (WebDriver driver, String location, String hotel)
{
    //Location Enter By.cssSelector - enter_location = #location
     driver.findElement(By.cssSelector(prop.getProperty("enter_location"))).sendKeys(location);
    
    //Password field By name - enter_hotel = hotels
      driver.findElement(By.name(prop.getProperty("enter_hotel"))).sendKeys(hotel);
    
    //Search button By class ("login")-search_click = reg_button
    driver.findElement(By.className(prop.getProperty("search_click"))).click();
    
}



//This function is used to read data from specified cell of Excel sheet once you give Excelsheet name and path



	public static String HA_GF_readXL (int row, String column, String strFilePath) 

	{

			jxl.Cell c= null;

			int reqCol=0;

			WorkbookSettings ws = null;

			Workbook workbook = null;

			Sheet sheet = null;

			FileInputStream fs = null;

	try{

		fs = new FileInputStream(new File(strFilePath));

		ws = new WorkbookSettings();

		ws.setLocale(new Locale("en", "EN"));

				

		// opening the work book and sheet for reading data

		workbook = Workbook.getWorkbook(fs, ws);

		sheet = workbook.getSheet(0);

		

		// Sanitise given data

		String col = column.trim();

		

		//loop for going through the given row

		for(int j=0; j<sheet.getColumns(); j++)

		{

			jxl.Cell cell = sheet.getCell(j,0);

			if((cell.getContents().trim()).equalsIgnoreCase(col))

			{	

				reqCol= cell.getColumn();

				//System.out.println("column No:"+reqCol);

				c = sheet.getCell(reqCol, row);

				fs.close();

				return c.getContents();

			}

		}

	}

	catch(BiffException be)

	{

		

		System.out.println("The given file should have .xls extension.");

	}

	catch(Exception e)

	{

		e.printStackTrace();

		

	}

	System.out.println("NO MATCH FOUND IN GIVEN FILE: PROBLEM IS COMING FROM DATA FILE");

	

	return null;

	}

	

	

	

	public static int HA_GF_XLRowCount (String strFilePath, String sColumn)

	{

		int k;

		for (k = 1; k < 999; k++)

		{

		

		String sParamVal = 	HA_GF_readXL(k,sColumn,strFilePath);

			if (sParamVal.equals("ENDOFROW"))

				{

					break;

				}

		

		}

		

		return k;

		
	}
	}