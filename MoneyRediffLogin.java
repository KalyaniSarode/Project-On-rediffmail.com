package test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;





public class MoneyRediffLogin
{
	private WebDriver driver;
	private String baseURL;
	private String UserId;
	private String UserPassword;
	
	
	@Before
	public void setUp() throws Exception
	{
		//getting a path for accessing chrome
		System.getProperty("webdriver.chrome.driver", " C:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//Visiting site
		baseURL = "https://money.rediff.com/index.html";
		
		//for maximize site
		driver.manage().window().maximize();
		//wait for next instruction gets execute
		driver.manage().timeouts().implicitlyWait( 59,TimeUnit.SECONDS);
		
	

	}
	@Test
	public void test()
	{
		//calling baseURL to visit Money.Rediff site
		driver.get(baseURL);
		
		//Clicking on sign in option
	    driver.findElement(By.xpath("//*[@id=\'signin_info\']/a[1]")).click();
		
		
		//code for signin
		
		  driver.findElement(By.xpath("//*[@id=\'useremail\']")).sendKeys("kalyaniss98@rediffmail.com");
		  driver.findElement(By.xpath("//*[@id=\'userpass\']")).sendKeys("Kalls2998#");
		 driver.findElement(By.xpath("//*[@id=\'loginsubmit\']")).click();
		 
		 //Writing data into Text File
		 String Module_Name = "Creating Portfolio\n";
			String Test_Result= "Creating Portfolio Successfull\n";
			String Comments= "User Successfully Created Portfolio\n";
			
			//writeText(Module_Name, Test_Result, Comments);
			appendText(Module_Name, Test_Result, Comments);
		 
		 /*String Module_Name="Creating Portfolio";
		  String Test_Result="Creating Portfolio Successfully\n";
		  String Comments="User Successully Created Portfolio\n";
		  */
		 
		
			String strFile="./DataPool/Portfolio.xls";
			String[]Portfolio= Rediff_Read( 1," Portfolio",strFile);
			String strPortfolio = null;
			for( int iter=0;iter<Portfolio.length-2;iter++)
			{
				
		 strPortfolio=Portfolio[ iter];
	
		
    try 
    {
              //  WebElement button = driver.findElement(By.xpath("xpath"));
             //button.click();
            new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"createPortfolio\"]"))).click();
    }
    
    catch(org.openqa.selenium.StaleElementReferenceException ex)
    
    {
	new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"createPortfolio\"]"))).click();
     }
		
		//1.Large Cap Stocks
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'create\']")));
		driver.findElement(By.xpath("//*[@id=\'create\']")).sendKeys(strPortfolio);
		driver.findElement(By.xpath("//*[@id=\"createPortfolioButton\"]")).click();
		
		if( iter==0)
			System.out.print( " Portfolio is Already Exists");
			}
		
			
		
	    // Returning The Array
		
		
		}

	private void appendText(String module_Name, String test_Result, String comments) {
		// TODO Auto-generated method stub
		
	}
	@After
	 public void tearDown() throws Exception
	 {
		
	 }
	public static String[] Rediff_Read( int row,String column,String strFilePath)
	
	{
		//Printing Statement to check on console
		
		System.out.println( " Inside read method");
		//Declaring variables
		Cell c= null;
		int reqCol=0;
		int reqRow=1;
		WorkbookSettings ws = null;
		Workbook workbook = null;
		Sheet sheet = null;
		FileInputStream fs = null;
		
	try
		{
			fs=new FileInputStream( new File( strFilePath));
			ws=new WorkbookSettings();
			
			ws.setLocale(new Locale("en", "EN"));
			String[] data=null;

			// opening the work book and sheet for reading data
			workbook = Workbook.getWorkbook(fs, ws);
			sheet = workbook.getSheet(0);
			data=new String[ sheet.getRows()];

			// Sanitise given data
			String col = column.trim();

			//loop for going through the given row
			for(int j=0; j<sheet.getColumns(); j++)
			{
			Cell cell = (Cell) sheet.getCell(j,0);
			
			if(( cell.getContents().trim()).equalsIgnoreCase(col))
			{
				
           // get Column
			reqCol= cell.getColumn();
			System.out.println("column No:"+reqCol);
			
			//Loop For getting total rows insheets
			
			for( int i=0;i<=sheet.getRows()-2;i++)
			{
				
			c = sheet.getCell(reqCol, reqRow);
			data[i]=c.getContents();
			System.out.println( data[i]);
			fs.close();
			reqRow=reqRow+1;
		
			}
			return data;
			//returning the string[] data in the sheets
			
			}
			
			
			}
			
			
		}
			// Handling the Exceptions
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
		
	public static void writeText(String Module_Name, String Test_Result, String Comments ) throws IOException
	{
						//Creating Text File To Store Test Case Result
						//File f = new File("ResultTestCase.txt");
						
						//Creating Object Of Writing Text File
						FileWriter fw = new FileWriter("ResultTestCase.txt", true);
						
						//Adding Data Into Text File
						fw.write(Module_Name + Test_Result + Comments);
						
						fw.close();
	}
	
	

 public static void main(String Module_Name, String Test_Result, String Comments ) throws IOException
{
					//Creating Text File To Store Test Case Result
					//File f = new File("ResultTestCase.txt");
					
					//Creating Object Of Writing Text File
					FileWriter fw = new FileWriter("ResultTestCase.txt", true);
					
					//Adding Data Into Text File
					fw.write(Module_Name + Test_Result + Comments);
					
					fw.close();
}
}