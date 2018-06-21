package tatoc_tetsng;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class tatoc_testng {
	WebDriver web;
	static String expectedUrl = null;
	
	@AfterMethod
	public void check(){
		Assert.assertEquals(expectedUrl, web.getCurrentUrl(), "Error Message: Didn't navigate to correct webpage");
	}
	
	@Test
	public void start_page(){
		System.setProperty("webdriver.chrome.driver","C:\\Users\\akansharastogi\\Downloads\\chromedriver_win32\\chromedriver.exe");
		web = new ChromeDriver();
		web.get("http://10.0.1.86/tatoc");
		Assert.assertEquals(web.findElement(By.cssSelector("a")).isDisplayed(), true);
		web.findElement(By.linkText("Basic Course")).click();
		expectedUrl = "http://10.0.1.86/tatoc/basic/grid/gate";
		
	}
	
	@Test(dependsOnMethods = {"start_page"})
	public void grid_gate(){
		Assert.assertEquals(web.findElement(By.className("greenbox")).isDisplayed(), true);
        web.findElement(By.className("greenbox")).click();
        expectedUrl = "http://10.0.1.86/tatoc/basic/frame/dungeon";
	}
	
	@Test(dependsOnMethods = {"grid_gate"})
	public void frame_dungeon(){
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
		expectedUrl = "http://10.0.1.86/tatoc/basic/drag";
	}
	
	@Test(dependsOnMethods = {"frame_dungeon"})
	public void drag_around(){
		Actions act = new Actions(web);
		WebElement drop = web.findElement(By.id("dropbox")); 
	    WebElement drag = web.findElement(By.id("dragbox"));
	    act.dragAndDrop(drag, drop).build().perform();
	    web.findElement(By.cssSelector("a")).click();
	    expectedUrl = "http://10.0.1.86/tatoc/basic/windows";
	}
	
	@Test(dependsOnMethods = {"drag_around"})
	public void pop_up_window(){
		Assert.assertEquals(web.findElement(By.cssSelector("a")).isDisplayed(), true);
		String parentWindowHandler = web.getWindowHandle();
		String subWindowHandler = null;
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
		expectedUrl = "http://10.0.1.86/tatoc/basic/cookie";
	}
	
	@Test(dependsOnMethods = {"pop_up_window"})
	public void cookie_handling(){
		Assert.assertEquals(web.findElement(By.cssSelector("a")).isDisplayed(), true);
		web.findElement(By.cssSelector("a")).click(); 
        String value = web.findElement(By.id("token")).getText().split("Token: ")[1]; 
        web.manage().addCookie(new Cookie("Token", value)); 
        web.findElements(By.cssSelector("a")).get(1).click();
        expectedUrl = "http://10.0.1.86/tatoc/end";
	}
	
}
