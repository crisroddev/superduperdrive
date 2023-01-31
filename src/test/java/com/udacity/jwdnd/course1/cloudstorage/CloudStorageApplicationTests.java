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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	public Logger log = LoggerFactory.getLogger(CloudStorageApplicationTests.class);

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
	@DisplayName("Notes Tests")
	public void noteTests() throws InterruptedException{
		notesTestCreate();
	}

	public void notesTestEdit() throws InterruptedException {
		// Login
		driver.get("http://localhost:" + this.port + "/login");
		// Username
		driver.findElement(By.id("inputUsername")).sendKeys("test");
		// Password
		driver.findElement(By.id("inputPassword")).sendKeys("testTestTest");
		// Click Button
		driver.findElement(By.id("submit-button")).click();

		Thread.sleep(2000);
		try{
			boolean noteEdited = false;
			List<WebElement> notes = driver.findElements(By.id("td"));
			for (WebElement noteRow : notes){
				WebElement edit;
				edit = noteRow.findElement(By.id("button"));
				edit.click();
				Thread.sleep(2000);
				driver.findElement(By.id("note-title")).sendKeys("Editing Title");
				driver.findElement(By.id("note-description")).sendKeys("Editing Description");
				driver.findElement(By.id("note-submit")).click();
				Thread.sleep(2000);
				noteEdited = true;
				Assertions.assertEquals("Home", driver.getTitle());
			}
			Assertions.assertTrue(noteEdited);
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}

	public void notesTestCreate() throws InterruptedException {
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
		Assertions.assertTrue(noteCreated);
	}

	public void notesTestDelete() throws InterruptedException {
		// Login
		driver.get("http://localhost:" + this.port + "/login");
		// Username
		driver.findElement(By.id("inputUsername")).sendKeys("test");
		// Password
		driver.findElement(By.id("inputPassword")).sendKeys("testTestTest");
		// Click Button
		driver.findElement(By.id("submit-button")).click();

		Thread.sleep(2000);
		boolean noteDeleted = false;

		try{
			// Notes TAB
			driver.findElement(By.id("nav-notes-tab")).click();
			Thread.sleep(2000);

			WebElement notes = driver.findElement(By.id("userTable"));
			List<WebElement> notesForDelete = notes.findElements(By.tagName("a"));
			for(WebElement deleteNote : notesForDelete) {
				deleteNote.click();
				noteDeleted = true;
				break;
			}

		} catch (Exception e) {
			System.out.println(
					e.toString());
		}
		Assertions.assertTrue(noteDeleted);
	}



	// Test for Credentials
//	@Test
//	@Order(5)
//	@DisplayName("Credentials Test Create")
//	public void credentialTestCreate() throws InterruptedException {
//		// Login
//		driver.get("http://localhost:" + this.port + "/login");
//		// Username
//		driver.findElement(By.id("inputUsername")).sendKeys("test");
//		// Password
//		driver.findElement(By.id("inputPassword")).sendKeys("testTestTest");
//		// Click Button
//		driver.findElement(By.id("submit-button")).click();
//
//		// Credentials
//		driver.findElement(By.id("nav-credentials-tab")).click();
//		Thread.sleep(2000);
//
//		// Create Credential
//		boolean credentialCreated = false;
//		try {
//			driver.findElement(By.id("new-credential")).click();
//			Thread.sleep(2000);
//			driver.findElement(By.id("credential-url")).sendKeys("http://test:test/newCredential");
//			driver.findElement(By.id("credential-username")).sendKeys("test");
//			driver.findElement(By.id("credential-password")).sendKeys("test");
//			driver.findElement(By.id("credential-submit")).click();
//
//			Thread.sleep(4000);
//
//			credentialCreated = true;
//		} catch(Exception e) {
//			System.out.println(e.toString());
//		}
//
//		// Delete Credential
//		Thread.sleep(4000);
//		boolean credentialDelete = false;
//		try {
//			WebElement credentials = driver.findElement(By.id("credentialTable"));
//			List<WebElement> credentialToDelete = credentials.findElements(By.tagName("a"));
//			for (WebElement deleteCredential : credentialToDelete) {
//				deleteCredential.click();
//				credentialDelete = true;
//				break;
//			}
//
//		} catch (Exception e){
//			e.toString();
//		}
//
//		Assertions.assertTrue(credentialCreated);
//		Assertions.assertTrue(credentialDelete);
//
//
//	}

//	@Test
//	@Order(6)
//	@DisplayName("Credentials Test Edit")
//	public void credentialTestEdit() throws InterruptedException {
//		// Login
//		driver.get("http://localhost:" + this.port + "/login");
//		// Username
//		driver.findElement(By.id("inputUsername")).sendKeys("test");
//		// Password
//		driver.findElement(By.id("inputPassword")).sendKeys("testTestTest");
//		// Click Button
//		driver.findElement(By.id("submit-button")).click();
//
//		// Credentials
//		driver.findElement(By.id("nav-credentials-tab")).click();
//		Thread.sleep(2000);
//
//		// Create Credential
//		boolean credentialCreated = false;
//		try {
//			driver.findElement(By.id("new-credential")).click();
//			Thread.sleep(2000);
//			driver.findElement(By.id("credential-url")).sendKeys("http://test:test/newCredential");
//			driver.findElement(By.id("credential-username")).sendKeys("test");
//			driver.findElement(By.id("credential-password")).sendKeys("test");
//			driver.findElement(By.id("credential-submit")).click();
//
//			Thread.sleep(4000);
//
//			credentialCreated = true;
//		} catch(Exception e) {
//			System.out.println(e.toString());
//		}
//
//		// Delete Credential
//		Thread.sleep(4000);
//		boolean credentialDelete = false;
//		try {
//			WebElement credentials = driver.findElement(By.id("credentialTable"));
//			List<WebElement> credentialToDelete = credentials.findElements(By.tagName("a"));
//			for (WebElement deleteCredential : credentialToDelete) {
//				deleteCredential.click();
//				credentialDelete = true;
//				break;
//			}
//
//		} catch (Exception e){
//			e.toString();
//		}
//
//		Assertions.assertTrue(credentialCreated);
//		Assertions.assertTrue(credentialDelete);
//
//
//	}

//	@Test
//	@Order(6)
//	@DisplayName("Credentials Test Delete")
//	public void credentialTestDelete() throws InterruptedException {
//		// Login
//		driver.get("http://localhost:" + this.port + "/login");
//		// Username
//		driver.findElement(By.id("inputUsername")).sendKeys("test");
//		// Password
//		driver.findElement(By.id("inputPassword")).sendKeys("testTestTest");
//		// Click Button
//		driver.findElement(By.id("submit-button")).click();
//
//		// Credentials
//		driver.findElement(By.id("nav-credentials-tab")).click();
//		Thread.sleep(2000);
//
//		// Create Credential
//		boolean credentialCreated = false;
//		try {
//			driver.findElement(By.id("new-credential")).click();
//			Thread.sleep(2000);
//			driver.findElement(By.id("credential-url")).sendKeys("http://test:test/newCredential");
//			driver.findElement(By.id("credential-username")).sendKeys("test");
//			driver.findElement(By.id("credential-password")).sendKeys("test");
//			driver.findElement(By.id("credential-submit")).click();
//
//			Thread.sleep(4000);
//
//			credentialCreated = true;
//		} catch(Exception e) {
//			System.out.println(e.toString());
//		}
//
//		// Delete Credential
//		Thread.sleep(4000);
//		boolean credentialDelete = false;
//		try {
//			WebElement credentials = driver.findElement(By.id("credentialTable"));
//			List<WebElement> credentialToDelete = credentials.findElements(By.tagName("a"));
//			for (WebElement deleteCredential : credentialToDelete) {
//				deleteCredential.click();
//				credentialDelete = true;
//				break;
//			}
//
//		} catch (Exception e){
//			e.toString();
//		}
//
//		Assertions.assertTrue(credentialCreated);
//		Assertions.assertTrue(credentialDelete);
//
//
//	}
}
