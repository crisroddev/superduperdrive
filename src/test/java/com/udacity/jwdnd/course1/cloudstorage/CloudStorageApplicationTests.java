package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	// Test for SignUp
	@Test
	@Order(1)
	@DisplayName("Sign Up Test")
	public  void signInTest() throws InterruptedException {
		// URL for SignUp
		driver.get("http://localhost:" + this.port + "/signup");
		// First Name
		driver.findElement(By.id("inputFirstName")).sendKeys("test");
		// Last Name
		driver.findElement(By.id("inputLastName")).sendKeys("test");
		// Username
		driver.findElement(By.id("inputUsername")).sendKeys("test");
		// Password
		driver.findElement(By.id("inputPassword")).sendKeys("testTestTest");
		// Click Button
		Thread.sleep(4000);
		driver.findElement(By.id("submit-button")).click();

		// URL for Login
		driver.get("http://localhost:" + this.port + "/login");
		// Username
		driver.findElement(By.id("inputUsername")).sendKeys("test");
		// Password
		driver.findElement(By.id("inputPassword")).sendKeys("testTestTest");
		// Click Button
		driver.findElement(By.id("submit-button")).click();
		Thread.sleep(4000);

		// Result
		Assertions.assertEquals("Home", driver.getTitle());
	}

	// Test for Login

	// Tests for notes

	// Test for Credentials
}
