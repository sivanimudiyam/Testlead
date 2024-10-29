package com.Hrms.tests;



import org.testng.annotations.Test;


import com.Hrms.base.BasePage;
import com.Hrms.pages.LoginPage;
import com.Hrms.pages.PIMPage;
import com.Hrms.utils.CommonUtils;
import com.Hrms.utils.TestNGUtility;

public class TestPIMPage extends BasePage{
	
	//----------------Page class objects Implementations---------
	
	LoginPage loginPage = new LoginPage();
	PIMPage pimPage = new PIMPage();
	
	@Test(description="add And Verify Employee Name",priority=0)
	public void addAndVerifyEmployee() throws Exception {
	
	loginPage.login();
	CommonUtils.hardWait(5);
	TestNGUtility.assertTrue(CommonUtils.getElementText(loginPage.getWelcomePage()),"Welcome selenium");
	CommonUtils.moveToElement(PIMPage.getPim());
	CommonUtils.clickElement(PIMPage.getAddEmp());
	CommonUtils.hardWait(5);
	CommonUtils.switchToFrame(PIMPage.getFrame());
	CommonUtils.enterValue(PIMPage.getFirstName(), "Sai",true);
	CommonUtils.enterValue(PIMPage.getLastName(), "P",true);
	CommonUtils.clickElement(PIMPage.getSave());
	CommonUtils.clickElement(PIMPage.getBackButton());
	TestNGUtility.assertTrue("Sai P",CommonUtils.getElementText(PIMPage.getEmpName()));
	
	}
	
	@Test(description="search And Verify Employee",priority=1)
	public void searchAndVerifyEmployee() throws Exception {
	
	
	}
	
	@Test(description="Verify Employee deletion",priority=2)
	public void verifyEmployeeDelete() throws Exception {
	
	
	}

	
	
}
