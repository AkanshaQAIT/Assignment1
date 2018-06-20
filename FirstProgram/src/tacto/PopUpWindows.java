package tacto;

import java.util.Iterator;
import java.util.Set;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class PopUpWindows {
	public static void main(String[] args){
		System.setProperty("webdriver.chrome.driver","C:\\Users\\akansharastogi\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver web = new ChromeDriver();
		web.get("http://10.0.1.86/tatoc/basic/windows#");
		
		String parentWindowHandler = web.getWindowHandle();
		String subWindowHandler = null;
		
		web.findElements(By.cssSelector("a")).get(1).click();
		Assert.assertTrue(web.findElement(By.cssSelector("span")).getText().equals("The page you are looking for does not exist"), "Verification Failed: Error message is not present.");
		
		web.get("http://10.0.1.86/tatoc/basic/windows#");
		
		web.findElement(By.cssSelector("a")).click();
		Set<String> handles = web.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while(iterator.hasNext()){
			subWindowHandler = iterator.next();
		}
		web.switchTo().window(subWindowHandler);
		web.findElement(By.id("name")).sendKeys("akansha");
		web.findElement(By.id("submit")).click();
		web.switchTo().window(parentWindowHandler);  
		web.findElements(By.cssSelector("a")).get(1).click();
	}
}
