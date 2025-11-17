package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*Data is valid - login success - test pass - logout
 * data is valid -- login failed - test fail
 * 
 * data is invalid - login success - test fail - logout
 * data is invalid -- login failed - test pass
 */

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider = "LoginData" , dataProviderClass=DataProviders.class,groups="Datadriven")
	public void verify_loginDDT(String email, String pwd,String exp) throws InterruptedException {
		
		logger.info("**** stating TC_003_LoginDDT*****");
		
		try {
		        //HomePage
				HomePage hp = new HomePage(driver);
				hp.clickMyAccount();
				hp.clickLogin();
				
				//Login
				LoginPage lp=new LoginPage(driver);
				lp.setEmail(email);
				lp.setPassword(pwd);
				lp.clickLogin();
				
				//MyAccount
				MyAccountPage ma = new MyAccountPage(driver);
				boolean targetPage = ma.isMyAccountPageExists();
				
				if(exp.equalsIgnoreCase("Valid")) {
					if(targetPage==true) {
						ma.clickLogout();
						Assert.assertTrue(true);
					}
					else {
						Assert.assertTrue(false);
					}
				}
				if(exp.equalsIgnoreCase("Invalid")) {
					if(targetPage==true) {
						ma.clickLogout();
						Assert.assertTrue(false);
					}
					else {
						Assert.assertTrue(true);
					}
				}
				
		}catch(Exception e){
			Assert.fail();
		}
		Thread.sleep(3000);
				logger.info("**** Finished TC_003_LoginDDT*****");
	}

}
