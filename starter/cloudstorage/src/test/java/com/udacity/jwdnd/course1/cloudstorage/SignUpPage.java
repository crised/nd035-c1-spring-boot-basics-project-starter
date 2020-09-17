package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "signUpButton")
    private WebElement signUpButton;

    @FindBy(id = "success-msg")
    private WebElement successMsg;

    private WebDriverWait wait;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOf(signUpButton));
    }

    public boolean signUpUser(String firstName, String lastName, String username, String password) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            return false;
        }
        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        signUpButton.click();
        return true;
    }

    public boolean isSuccessMsgPresent() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            return false;
        }
        return successMsg != null;
    }


}
