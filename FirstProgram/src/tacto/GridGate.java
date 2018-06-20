package tacto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class GridGate {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\akansharastogi\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver web = new ChromeDriver();
		web.get("http://10.0.1.86/tatoc");
		
		web.findElement(By.linkText("Basic Course")).click();
		web.findElement(By.className("greenbox")).click();
		
		web.navigate().back();
		web.findElement(By.className("redbox")).click();
		Assert.assertTrue(web.findElement(By.cssSelector("span")).getText().equals("The page you are looking for does not exist"), "Verification Failed: Error message is not present.");
	}
}
