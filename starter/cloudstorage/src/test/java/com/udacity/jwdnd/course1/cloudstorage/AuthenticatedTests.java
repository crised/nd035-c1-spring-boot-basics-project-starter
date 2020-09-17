package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticatedTests {

    @LocalServerPort
    private int port;

    private static WebDriver driver;

    private static final String username = "crised";
    private static final String password = "pass";
    private static final String noteDescription = "One, Two, Three";
    private static final String credentialURL = "superDuperDrive.com";

    private static String noteTitle = "new note";


    private static boolean isSigned;
    private static boolean isNoteCreated;
    private static boolean isCredentialCreated;


    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Final URL: " + driver.getCurrentUrl());
        driver.quit();
    }

    @BeforeEach
    public void beforeEach() {
        userFlow();
    }

    private void userFlow() {
        System.out.println("User signed: " + this.isSigned);
        if (this.isSigned == true) return;
        driver.get("http://localhost:" + this.port + "/signup");
        SignUpPage signUpPage = new SignUpPage(driver);
        assertTrue(signUpPage.signUpUser("Cristian", "Edwards", username, password));
        assertTrue(signUpPage.isSuccessMsgPresent());
        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.loginUser(username, password));
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isHomePageLoaded());
        this.isSigned = true;
    }

    private void createNoteMethod() {
        System.out.println("Note created: " + this.isNoteCreated);
        if (this.isNoteCreated == true) return;
        driver.get("http://localhost:" + this.port + "/home");
        assertTrue(driver.getCurrentUrl().contains("home")); // authenticated.
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.createNote(noteTitle, noteDescription));
        this.isNoteCreated = true;
    }

    private void createCredentialMethod() {
        System.out.println("Credential created: " + this.isCredentialCreated);
        if (this.isCredentialCreated == true) return;
        driver.get("http://localhost:" + this.port + "/home");
        assertTrue(driver.getCurrentUrl().contains("home")); // authenticated.
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.createCredential(credentialURL, username, password));
        this.isCredentialCreated = true;

    }

    @Test
    public void createNote() {
        createNoteMethod();
        HomePage homePage = new HomePage(driver);
        Note note = homePage.getNoteByTitle(noteTitle);
        assertNotNull(note);
        assertEquals(noteTitle, note.getNotetitle());
        assertEquals(noteDescription, note.getNotedescription());
    }

    @Test
    public void editNote() {
        createNoteMethod();
        HomePage homePage = new HomePage(driver);
        String newTitle = "Edited title";
        assertTrue(homePage.editNoteByTitle(noteTitle, newTitle));
        homePage = new HomePage(driver);
        Note note = homePage.getNoteByTitle(newTitle);
        assertNotNull(note);
        assertEquals(newTitle, note.getNotetitle());
        noteTitle = newTitle;
    }

    @Test
    public void deleteNote() {
        createNoteMethod();
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.deleteNoteByTitle(noteTitle));
        homePage = new HomePage(driver);
        assertNull(homePage.getNoteByTitle(noteTitle));
    }

    @Test
    public void createCredential() {
        createCredentialMethod();
        HomePage homePage = new HomePage(driver);

    }

}
