package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOf(submitButton));
    }

    public boolean loginUser(String username, String password) {
        try {
            Thread.sleep(500);
            inputUsername.sendKeys(username);
            inputPassword.sendKeys(password);
            submitButton.click();
            Thread.sleep(500);
        } catch (InterruptedException e) {
            return false;
        }
        return true;

    }
}
