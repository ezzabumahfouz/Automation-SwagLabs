package product;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class checkListDesciption {

	public WebDriver driver;

	@BeforeTest

	public void Init() {
		driver = new ChromeDriver();
		//
		driver.get("https://www.saucedemo.com");
		driver.manage().window().maximize();
	}

	@Test

	public void Login() {
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();

	}

	@Test

	public void checkDescipions() {
		List<WebElement> products = driver.findElements(By.className("inventory_item_name"));

		for (int x = 0; x < products.size(); x++) {
			String name = products.get(x).getText();
			if (name == null) {
				System.err.println("Product with Index (" + ") Dont Have Name !!");
			} else {
				continue;
			}
		}

	}

	@AfterTest

	public void logout() {
		driver.close();
	}

}
