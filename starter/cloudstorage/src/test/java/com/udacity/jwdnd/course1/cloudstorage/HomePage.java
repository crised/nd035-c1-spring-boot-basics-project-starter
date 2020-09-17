package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {

    @FindBy(id = "logOutButton")
    private WebElement logOutButton;

    @FindBy(id = "logOutButtonForm")
    private WebElement logOutButtonForm;

    @FindBy(id = "nav-files-tab")
    private WebElement filesTab;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialTab;

    @FindBy(id = "uploadButton")
    private WebElement uploadButton;

    @FindBy(id = "addNoteButton")
    private WebElement addNoteButton;

    @FindBy(id = "addCredentialButton")
    private WebElement addCredentialButton;

    @FindBy(id = "noteTitleTable")
    private List<WebElement> noteTitleTable;

    @FindBy(id = "noteDescriptionTable")
    private List<WebElement> noteDescriptionTable;

    @FindBy(id = "editNoteButtonTable")
    private List<WebElement> editNoteButtonTable;

    @FindBy(id = "noteDeleteButtonTable")
    private List<WebElement> noteDeleteButtonTable;

    @FindBy(id = "credentialEditButtonTable")
    private List<WebElement> credentialEditButtonTable;

    @FindBy(id = "credentialDeleteButtonTable")
    private List<WebElement> credentialDeleteButtonTable;

    @FindBy(id = "credentialUrlTable")
    private List<WebElement> credentialUrlTable;

    @FindBy(id = "credentialUsernameTable")
    private List<WebElement> credentialUsernameTable;

    @FindBy(id = "credentialPasswordTable")
    private List<WebElement> credentialPasswordTable;

    @FindBy(id = "note-title")
    private WebElement noteTitleInput;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionInput;

    @FindBy(id = "noteSaveChanges")
    private WebElement noteSaveChanges;

    @FindBy(id = "credential-id")
    private WebElement credentialIdInput;

    @FindBy(id = "credential-url")
    private WebElement credentialUrlInput;

    @FindBy(id = "credential-username")
    private WebElement credentialUserNameInput;

    @FindBy(id = "credential-password")
    private WebElement credentialPasswordInput;

    @FindBy(id = "credentialSaveChanges")
    private WebElement credentialSaveChanges;


    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, 5);
    }

    public boolean isHomePageLoaded() {
        return uploadButton != null;
    }

    public boolean logOutUser() {
        wait.until(ExpectedConditions.visibilityOf(logOutButtonForm));
        logOutButtonForm.submit();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            return false;
        }
        return true;
    }

    public boolean createNote(String title, String content) {

        try {
            Thread.sleep(500);
            notesTab.click();
            Thread.sleep(500);
            addNoteButton.click();
            Thread.sleep(500);
            noteTitleInput.sendKeys(title);
            noteDescriptionInput.sendKeys(content);
            wait.until(ExpectedConditions.elementToBeClickable(noteSaveChanges));
            noteSaveChanges.click();
            Thread.sleep(500);
            notesTab.click();
            Thread.sleep(500);
            return true;

        } catch (InterruptedException e) {
            return false;
        }
    }

    public Note getNoteByTitle(String title) {
        try {
            Thread.sleep(500);
            int n = noteTitleTable.size();
            System.out.println(n);
            for (int i = 0; i < n; i++) {
                String noteTitle = noteTitleTable.get(i).getText();
                System.out.println("Note text: " + noteTitle);
                if (noteTitle.equals(title)) {
                    String noteContent = noteDescriptionTable.get(i).getText();
                    int userNumber = 1;
                    return new Note(noteTitle, noteContent, userNumber);
                }
            }
        } catch (InterruptedException e) {
            return null;
        }
        return null;
    }

    public boolean editNoteByTitle(String title, String newTitle) {
        try {
            Thread.sleep(500);
            int n = noteTitleTable.size();
            System.out.println(n);
            for (int i = 0; i < n; i++) {
                String noteTitle = noteTitleTable.get(i).getText();
                System.out.println("Note text: " + noteTitle);
                if (noteTitle.equals(title)) {
                    editNoteButtonTable.get(i).click();
                    Thread.sleep(500);
                    noteTitleInput.clear();
                    noteTitleInput.sendKeys(newTitle);
                    wait.until(ExpectedConditions.elementToBeClickable(noteSaveChanges));
                    noteSaveChanges.click();
                    Thread.sleep(500);
                    notesTab.click();
                    Thread.sleep(500);
                    return true;
                }
            }
        } catch (InterruptedException e) {
            return false;
        }
        return false;
    }

    public boolean deleteNoteByTitle(String title) {
        try {
            Thread.sleep(500);
            for (int i = 0; i < noteTitleTable.size(); i++) {
                String noteTitle = noteTitleTable.get(i).getText();
                System.out.println("Note text: " + noteTitle);
                if (noteTitle.equals(title)) {
                    noteDeleteButtonTable.get(i).click();
                    Thread.sleep(500);
                    notesTab.click();
                    Thread.sleep(500);
                    return true;
                }
            }
        } catch (InterruptedException e) {
            return false;
        }
        return false;
    }

    public boolean createCredential(String url, String username, String plainPassword) {
        try {
            Thread.sleep(500);
            credentialTab.click();
            Thread.sleep(500);
            addCredentialButton.click();
            Thread.sleep(500);
            credentialUrlInput.sendKeys(url);
            credentialUserNameInput.sendKeys(username);
            credentialPasswordInput.sendKeys(plainPassword);
            wait.until(ExpectedConditions.elementToBeClickable(credentialSaveChanges));
            credentialSaveChanges.click();
            Thread.sleep(500);
            credentialTab.click();
            Thread.sleep(500);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }

    public Credential getCredentialByUrl(String givenUrl) {
        try {
            Thread.sleep(500);
            for (int i = 0; i < credentialUrlTable.size(); i++) {
                String url = credentialUrlTable.get(i).getText();
                if (url.equals(givenUrl)) {
                    Credential credential = new Credential();
                    credential.setUrl(credentialUrlTable.get(i).getText());
                    credential.setUsername(credentialUsernameTable.get(i).getText());
                    credential.setPassword(credentialPasswordTable.get(i).getText());
                    return credential;
                }
            }
        } catch (InterruptedException e) {
            return null;
        }
        return null;
    }

    public boolean editCredentialByUrl(String givenUrl, String newUserName) {
        try {
            Thread.sleep(500);
            for (int i = 0; i < credentialUrlTable.size(); i++) {
                String url = credentialUrlTable.get(i).getText();
                if (url.equals(givenUrl)) {
                    credentialEditButtonTable.get(i).click();
                    Thread.sleep(500);
                    credentialUserNameInput.clear();
                    credentialUserNameInput.sendKeys(newUserName);
                    wait.until(ExpectedConditions.elementToBeClickable(credentialSaveChanges));
                    credentialSaveChanges.click();
                    Thread.sleep(500);
                    credentialTab.click();
                    Thread.sleep(500);
                    return true;
                }
            }
        } catch (InterruptedException e) {
            return false;
        }
        return false;
    }



}