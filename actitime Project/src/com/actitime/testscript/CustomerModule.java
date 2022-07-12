package com.actitime.testscript;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.actitime.generic.BaseClass;
import com.actitime.pom.EnterTimeTrackPage;
import com.actitime.pom.TaskListPage;
@Listeners(com.actitime.generic.ListenerImpimentationsClass.class)

public class CustomerModule extends BaseClass {
	@Test
	public void testCreateCustomer() throws EncryptedDocumentException, IOException
	{
		
		String CustomerName = f.getExcelData("CreateCustomer",1,2);
		String CustomerDescription = f.getExcelData("CreateCustomer", 1, 3);
		Reporter.log("CreateCustomer",true);
		EnterTimeTrackPage e=new EnterTimeTrackPage(driver);
		e.setTaskTab();
		TaskListPage t=new TaskListPage(driver);
		t.getAddNewBtn().click();
		t.getNewCustOptions().click();
		t.getEnterCustNameTbx().sendKeys(CustomerName);
		t.getEnterCustDescTbx().sendKeys(CustomerDescription);
		t.getSelectCustDD().click();
		t.getOurCompany().click();
		t.getCreateCustBtn().click();
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.textToBePresentInElement(t.getActualCustomer(), CustomerName));
		String actualCustText = t.getActualCustomer().getText();
		Assert.assertEquals(actualCustText, CustomerName);
	}
	

}
