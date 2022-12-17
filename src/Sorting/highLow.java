package Sorting;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class highLow {

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

	public void Sort() {

		driver.findElement(By.className("product_sort_container")).click();
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[4]")).click();

	}

	@Test

	public void check() {

		List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
		List<Double> doublePrices = new ArrayList<>();

		for (int x = 0; x < prices.size(); x++) {
			String textPrice = prices.get(x).getText().replace("$", "");
			double convertedprice = Double.parseDouble(textPrice);
			doublePrices.add(convertedprice);

		}
		int lastIndex = prices.size();
		double fIndex = doublePrices.get(0);
		double lIndex = doublePrices.get(lastIndex - 1);

		System.out.println(fIndex);
		System.out.println(lIndex);

		Assert.assertEquals(fIndex > lIndex, true);

	}

}
