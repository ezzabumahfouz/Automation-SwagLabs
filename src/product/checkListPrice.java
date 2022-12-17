package product;

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

public class checkListPrice {

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

	public void checkPrices() {

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(9000));
		//
		List<WebElement> products = driver.findElements(By.className("inventory_item_price"));

		for (int x = 0; x < products.size(); x++) {
			String price = products.get(x).getText().replace("$", "");
			//
			Assert.assertEquals(price == "", false);
			//
			double doublePrice = Double.parseDouble(price);
			if (doublePrice == 0) {
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
