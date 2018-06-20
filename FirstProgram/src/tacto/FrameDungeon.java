package tacto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class FrameDungeon {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\akansharastogi\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver web = new ChromeDriver();
		web.get("http://10.0.1.86/tatoc/basic/frame/dungeon");
		
		web.switchTo().frame("main");	
		String box1 = web.findElement(By.id("answer")).getAttribute("class");
		
		while(true){
			web.findElement(By.cssSelector("a")).click();
			web.switchTo().frame("child");
			String box2 = web.findElement(By.id("answer")).getAttribute("class");
			web.switchTo().parentFrame();
			if(box1.equals(box2)){
				web.findElements(By.cssSelector("a")).get(1).click();
				break;
			}
		}
		
		web.navigate().back();
		web.switchTo().frame("main");	
		String Box1 = web.findElement(By.id("answer")).getAttribute("class");
		web.switchTo().frame("child");
		String Box2 = web.findElement(By.id("answer")).getAttribute("class");
		web.switchTo().parentFrame();
		if(!Box1.equals(Box2)){
			web.findElements(By.cssSelector("a")).get(1).click();
			Assert.assertTrue(web.findElement(By.cssSelector("span")).getText().equals("The page you are looking for does not exist"), "Verification Failed: Error message is not present.");
		}
	}
}
