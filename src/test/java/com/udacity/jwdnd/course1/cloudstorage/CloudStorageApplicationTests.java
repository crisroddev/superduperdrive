package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.By.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
import java.util.List;

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

	// Test for Signup and login
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

	// Tests for notes
	@Test
	@Order(2)
	@DisplayName("Notes Test")
	public void notesTest() throws InterruptedException {
		// Login
		driver.get("http://localhost:" + this.port + "/login");
		// Username
		driver.findElement(By.id("inputUsername")).sendKeys("test");
		// Password
		driver.findElement(By.id("inputPassword")).sendKeys("testTestTest");
		// Click Button
		driver.findElement(By.id("submit-button")).click();

		Thread.sleep(2000);
		boolean noteCreated = false;
		try{
			// Notes TAB
			driver.findElement(By.id("nav-notes-tab")).click();
			Thread.sleep(2000);

			// Create a note
			driver.findElement(By.id("new-note")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("note-title")).sendKeys("New Note Title");
			driver.findElement(By.id("note-description")).sendKeys("New Note Description");
			driver.findElement(By.id("note-submit")).click();
			Thread.sleep(2000);
			noteCreated = true;

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		// Delete Note
		Thread.sleep(2000);
		boolean noteDeleted = false;
		WebElement notes = driver.findElement(By.id("userTable"));
		List<WebElement> notesForDelete = notes.findElements(By.tagName("a"));
		for(WebElement deleteNote : notesForDelete) {
			deleteNote.click();
			noteDeleted = true;
			break;
		}

		Assertions.assertTrue(noteCreated);
		Assertions.assertTrue(noteDeleted);
	}

	// Test for Credentials
	@Test
	@Order(3)
	@DisplayName("Credentials Test")
	public void credentialTest() throws InterruptedException {
		// Login
		driver.get("http://localhost:" + this.port + "/login");
		// Username
		driver.findElement(By.id("inputUsername")).sendKeys("test");
		// Password
		driver.findElement(By.id("inputPassword")).sendKeys("testTestTest");
		// Click Button
		driver.findElement(By.id("submit-button")).click();

		// Credentials
		driver.findElement(By.id("nav-credentials-tab")).click();
		Thread.sleep(2000);

		// Create Credential
		boolean credentialCreated = false;
		try {
			driver.findElement(By.id("new-credential")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("credential-url")).sendKeys("http://test:test/newCredential");
			driver.findElement(By.id("credential-username")).sendKeys("test");
			driver.findElement(By.id("credential-password")).sendKeys("test");
			driver.findElement(By.id("credential-submit")).click();

			Thread.sleep(4000);

			credentialCreated = true;
		} catch(Exception e) {
			System.out.println(e.toString());
		}

		// Delete Credential
		Thread.sleep(4000);
		boolean credentialDelete = false;
		try {
			WebElement credentials = driver.findElement(By.id("credentialTable"));
			List<WebElement> credentialToDelete = credentials.findElements(By.tagName("a"));
			for (WebElement deleteCredential : credentialToDelete) {
				deleteCredential.click();
				credentialDelete = true;
				break;
			}

		} catch (Exception e){
			e.toString();
		}

		Assertions.assertTrue(credentialCreated);
		Assertions.assertTrue(credentialDelete);


	}
}
