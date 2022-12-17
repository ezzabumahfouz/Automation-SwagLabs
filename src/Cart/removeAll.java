package Cart;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class removeAll {

	public WebDriver driver;

	@BeforeTest

	public void Init() {
		driver = new ChromeDriver();
		//
		driver.get("https://www.saucedemo.com");
		driver.manage().window().maximize();
		//
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();

	}

	@Test
	public void AddAll() {

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(9000));

		List<WebElement> Buttons = driver.findElements(By.className("btn_small"));

		for (int x = 0; x < Buttons.size(); x++) {

			Buttons.get(x).click();

			String cartNumber = driver.findElement(By.className("shopping_cart_badge")).getText();
			int cartNumberInt = Integer.parseInt(cartNumber);
			//
			Assert.assertEquals(x + 1, cartNumberInt);

		}

	}

	@Test

	public void RemoveAll() {

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		driver.findElement(By.className("shopping_cart_link")).click();
		//
		List<WebElement> removeButtons = driver.findElements(By.className("btn_secondary"));
		//
		for (int x = 0; x < removeButtons.size(); x++) {
			removeButtons.get(x).click();
		}
	}

	@AfterTest

	public void logout() {
		driver.close();
	}

}
