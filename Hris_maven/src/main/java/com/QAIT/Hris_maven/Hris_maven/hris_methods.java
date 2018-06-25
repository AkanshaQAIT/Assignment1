package com.QAIT.Hris_maven.Hris_maven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class hris_methods {
	WebDriver web;
	
	private WebElement getUserNameEntry(){
		return web.findElement(By.id("txtUserName"));	
	}
	private WebElement getPasswordEntry(){
		return web.findElement(By.id("txtPassword"));	
	}
	private WebElement getErrorMessage(){
		return web.findElement(By.cssSelector(".alert-error"));	
	}
	void login(String username, String password){
        getUserNameEntry().clear();
        getUserNameEntry().sendKeys(username);
        getPasswordEntry().clear();
        getPasswordEntry().sendKeys(password);
        getPasswordEntry().submit();
    }
	public String loginWithIncorrectCredentials(String username, String password){
        login(username, password);
        return getErrorMessage().getText();
    }
	public Timesheet loginWithCorrectCredentials(String username, String password){
        login(username, password);
	    return new Timesheet();
	}
	public Boolean isErrorMessage(String expectedMessage){
        return getErrorMessage().getText()
                .contains(expectedMessage);
    }
    public Boolean isPasswordEntryAnnotated(){
        return getPasswordEntry().getAttribute("style").contains("red");
    }
}
