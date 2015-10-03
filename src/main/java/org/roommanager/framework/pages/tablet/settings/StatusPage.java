package org.roommanager.framework.pages.tablet.settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.pages.tablet.home.HomePage;

/**
 * Created by Jimmy Vargas on 9/11/2015.
 */
public class StatusPage {
    WebDriver driver;

    //@FindBy(css = ".room-placeholder")
    //TODO: Improve this
    @FindBy(xpath = "//div/label/following::div/span[2]")
    private WebElement workRoom;

    @FindBy(css = ".btn-default")
    private WebElement toggleButton;

    @FindBy(css = ".ng-valid")
    private WebElement searchForRoomField;

    @FindBy(css = ".item-box.ng-scope")
    private WebElement roomItem;

    @FindBy(css = ".btn-primary")
    private WebElement starNowButton;

    public StatusPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getCurrentRoom(){
        new WebDriverWait(driver,60).until(ExpectedConditions
                .visibilityOf(workRoom));
        return workRoom.getText();
    }

    public StatusPage selectRoom(String roomName){
        if(!getCurrentRoom().equals(roomName)){
            clickToogleButton()
            .searchForRoom(roomName)
            .clickRoomItem();
        }
        return this;
    }

    public StatusPage searchForRoom(String roomName){
        new WebDriverWait(driver,60).until(ExpectedConditions
                .visibilityOf(workRoom));
        searchForRoomField.clear();
        searchForRoomField.sendKeys(roomName);
        return this;
    }

    public StatusPage clickRoomItem(){
        new WebDriverWait(driver,60).until(ExpectedConditions
                .visibilityOf(roomItem));
        roomItem.click();
        return this;
    }
    public StatusPage clickToogleButton(){
        new WebDriverWait(driver,60).until(ExpectedConditions
                .visibilityOf(toggleButton));
        toggleButton.click();

        return this;

    }

    public HomePage clickStartButton(){
        new WebDriverWait(driver,60).until(ExpectedConditions
                .visibilityOf(starNowButton));

        starNowButton.click();
        return new HomePage(driver);
    }

}
