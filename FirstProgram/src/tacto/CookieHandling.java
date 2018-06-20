package tacto;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


public class CookieHandling {
	public static void main(String[] args){
		System.setProperty("webdriver.chrome.driver","C:\\Users\\akansharastogi\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver web = new ChromeDriver();
		web.get("http://10.0.1.86/tatoc/basic/cookie#");
		
		web.findElement(By.cssSelector("a")).click(); 
        String value = web.findElement(By.id("token")).getText().split("Token: ")[1]; 
        web.manage().addCookie(new Cookie("Token", value)); 
        web.findElements(By.cssSelector("a")).get(1).click();
        
        Assert.assertTrue(web.findElement(By.cssSelector("span")).getText().equals("Obstacle Course is Complete!"), "Message: Cookie is not set properly.");	
	}
}
