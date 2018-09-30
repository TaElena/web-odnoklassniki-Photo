package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AlbumWrapper {


    private WebElement element;
    private WebDriver driver;

    private static final By ALBUN_TITLE = By.xpath(".//*[contains(@class,'photo-album_t')]");

    public AlbumWrapper(WebElement element, WebDriver driver) {
        this.driver = driver;
        this.element = element;
    }


    public void clickAlbum(){
        element.click();
    }

    public String getAlbumName(){

        (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.visibilityOfElementLocated(ALBUN_TITLE));
        //WebElement albumName = element.findElement(ALBUN_TITLE);

        return element.findElement(ALBUN_TITLE).getText();
    }



}
