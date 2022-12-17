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

public class Name {

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
	public void checkName() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		//
		List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
		//
		for (int x = 0; x < products.size(); x++) {

			var productNames = driver.findElement(By.xpath("//*[@id=\"item_" + x + "_title_link\"]/div"));
			//
			String pName = productNames.getText();
			productNames.click();
			String pName2 = driver.findElement(By.className("inventory_details_name")).getText();
			Assert.assertEquals(pName, pName2);
			//
			driver.navigate().back();

		}
	}

	@AfterTest

	public void Logout() {
		driver.close();
	}

}
