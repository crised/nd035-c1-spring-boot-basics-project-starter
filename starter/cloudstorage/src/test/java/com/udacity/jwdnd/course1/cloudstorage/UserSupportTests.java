package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserSupportTests {

    @LocalServerPort
    private int port;

    private static WebDriver driver;

    private static final String username = "crised";
    private static final String password = "pass";

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("Final URL: " + driver.getCurrentUrl());
        driver.quit();
    }

    @Test
    public void accessUnauthorizedUser(){
        driver.get("http://localhost:" + this.port + "/home");
        assertFalse(driver.getCurrentUrl().contains("home"));
    }


    @Test
    public void signUpUser() {
        driver.get("http://localhost:" + this.port + "/signup");
        SignUpPage signUpPage = new SignUpPage(driver);
        assertTrue(signUpPage.signUpUser("Cristian", "Edwards", username, password));
        assertTrue(signUpPage.isSuccessMsgPresent());
        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.loginUser(username, password));
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isHomePageLoaded());
        assertTrue(homePage.logOutUser());
        driver.get("http://localhost:" + this.port + "/home");
        assertFalse(driver.getCurrentUrl().contains("home"));
    }




}
