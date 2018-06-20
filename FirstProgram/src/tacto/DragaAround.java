package tacto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class DragaAround {
	public static void main(String[] args){
		System.setProperty("webdriver.chrome.driver","C:\\Users\\akansharastogi\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver web = new ChromeDriver();
		web.get("http://10.0.1.86/tatoc/basic/drag");
		
		Actions act = new Actions(web);
		WebElement drop = web.findElement(By.id("dropbox")); 
	    WebElement drag = web.findElement(By.id("dragbox"));
	    act.dragAndDrop(drag, drop).build().perform();
	    web.findElement(By.cssSelector("a")).click();
	    
	    web.navigate().back();
	    
	    WebElement drop2 = web.findElement(By.cssSelector("h3")); 
	    WebElement drag2 = web.findElement(By.id("dragbox"));
	    act.dragAndDrop(drag2, drop2).build().perform();
	    web.findElement(By.cssSelector("a")).click();
	    Assert.assertTrue(web.findElement(By.cssSelector("span")).getText().equals("The page you are looking for does not exist"), "Verification Failed: Error message is not present.");
	}
}
