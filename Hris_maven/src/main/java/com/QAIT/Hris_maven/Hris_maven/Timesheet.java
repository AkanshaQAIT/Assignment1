package com.QAIT.Hris_maven.Hris_maven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Timesheet {
	WebDriver web;
	public Boolean getDisplay(){
		return web.findElement(By.className("ng-binding")).isDisplayed();
	}
}
