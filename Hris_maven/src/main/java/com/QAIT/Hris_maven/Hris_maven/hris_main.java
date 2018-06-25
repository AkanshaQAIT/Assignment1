package com.QAIT.Hris_maven.Hris_maven;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Hello world!
 *
 */
public class hris_main
{
	WebDriver web;
	hris_methods login_form;
	
	@Test
    public void attempt_Login_With_Incorrect_Password_Should_Render_Error_Message(){
        Assert.assertTrue(login_form
                .loginWithIncorrectCredentials("invalid_username", "invalid_password").contains("Invalid Login"));
    }
    
    @Test
    public void attempt_Login_With_correct_Password_Should_Take_U_To_Timesheet_Page(){
        Assert.assertTrue(login_form
                .loginWithCorrectCredentials("akansharastogi", "Akansha@321#").getDisplay());
    }
    
    @Test
    public void attempt_Login_With_No_Password_Should_Annotate_Blank_Password_Field(){
        login_form.login("akansharastogi", "");
        Assert.assertTrue(login_form.isPasswordEntryAnnotated());  
    }
    
    @BeforeMethod
    public void launchBrowser(){
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\akansharastogi\\Downloads\\chromedriver_win32\\chromedriver.exe");
 	    web = new ChromeDriver();
        web.get("https://hris.qainfotech.com");
        login_form = new hris_methods();
    }
    
    @AfterMethod
    public void closeBrowser(){
        web.quit();
    }
}
