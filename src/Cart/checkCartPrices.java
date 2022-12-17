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

public class checkCartPrices {

	public WebDriver driver;

	@BeforeTest

	public void Init() {
		driver = new ChromeDriver();
		//
		driver.get("https://www.saucedemo.com");
		driver.manage().window().maximize();

		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();

	}

	@Test
	public void AddAll() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

		List<WebElement> Buttons = driver.findElements(By.className("btn_small"));
		List<WebElement> products = driver.findElements(By.className("inventory_item_name"));

		for (int x = 0; x < Buttons.size(); x++) {

			Buttons.get(x).click();

			String cartNumber = driver.findElement(By.className("shopping_cart_badge")).getText();
			int cartNumberInt = Integer.parseInt(cartNumber);
			//
			Assert.assertEquals(x + 1, cartNumberInt);

		}

		driver.findElement(By.className("shopping_cart_link")).click();
		//
		List<WebElement> cartProducts = driver.findElements(By.className("inventory_item_name"));
		//
		Assert.assertEquals(products.size(), cartProducts.size());
		//
		String cartNumber = driver.findElement(By.className("shopping_cart_badge")).getText();
		int cartNumberInt = Integer.parseInt(cartNumber);
		//
		Assert.assertEquals(cartProducts.size(), cartNumberInt);

	}

	@Test

	public void checkPrices() {
		List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
		double sum = 0;

		for (int x = 0; x < prices.size(); x++) {
			String textPrice = prices.get(x).getText().replace("$", "");
			double doublePrice = Double.parseDouble(textPrice);
			sum += doublePrice;
		}

		driver.findElement(By.id("checkout")).click();
		//
		driver.findElement(By.id("first-name")).sendKeys("ezzeldeen");
		driver.findElement(By.id("last-name")).sendKeys("abu mahfouz");
		driver.findElement(By.id("postal-code")).sendKeys("11123");
		//
		driver.findElement(By.id("continue")).click();
		//
		String cartPrice = driver.findElement(By.className("summary_subtotal_label")).getText().replace("Item total: $",
				"");
		double doubleCartPrice = Double.parseDouble(cartPrice);
		//
		Assert.assertEquals(sum, doubleCartPrice);

	}

	@AfterTest

	public void logout() {
		driver.close();
	}

}
