package Sorting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ZtoA {

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
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[1]")).click();

	}

	@Test

	public void check() {

		List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
		List<String> Productnames = new ArrayList<>();
		List<String> sortedProductnames = new ArrayList<>();

		for (int x = 0; x < products.size(); x++) {
			String textPrice = products.get(x).getText().replace("$", "");
			sortedProductnames.add(textPrice);
			Productnames.add(textPrice);

		}
		Collections.sort(sortedProductnames);

		int lastIndex1 = Productnames.size() - 1;
		int lastIndex2 = sortedProductnames.size() - 1;

		String fIndex1 = sortedProductnames.get(lastIndex1);
		String fIndex2 = Productnames.get(lastIndex2);

		System.out.println(fIndex1);
		System.out.println(fIndex2);

		Assert.assertEquals(fIndex1, fIndex2);

	}

}
