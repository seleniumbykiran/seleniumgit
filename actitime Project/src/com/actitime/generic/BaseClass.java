package com.actitime.generic;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.actitime.pom.EnterTimeTrackPage;
import com.actitime.pom.LoginPage;

public class BaseClass {
	static
	{
		System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe" );
	}
	
	public static WebDriver driver;
	public FileLib f= new FileLib();
	
	
	@BeforeClass
	public void OpenBrowsers()
	{
		Reporter.log("OpenBrowser",true);
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverCommonLib w= new WebDriverCommonLib();
		w.waitForPageLoadImplicit(driver);
		driver.get("https://demo.actitime.com");		
	}	
	@AfterClass
	public void closeBrowser()
	{
		Reporter.log("CloseBrowsers",true);
		driver.close();		
	}		
	@BeforeMethod
	public void login() throws IOException
	{
		Reporter.log("login",true);
		FileLib f = new FileLib();
        String url = f.getPropertyData("url");
        String un =  f.getPropertyData("username");
        String pw =  f.getPropertyData("password");
	    driver.get(url);
	  	LoginPage l = new LoginPage(driver);
		l.setLogin(un,pw);		
	}	
	@AfterMethod
	public void logout() throws InterruptedException
	{
		Thread.sleep(4000);
		Reporter.log("Logout",true);
		EnterTimeTrackPage e= new EnterTimeTrackPage(driver);
		e.setLogout();
	}
	

}
