package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhotoLoadPage extends HelperBase {
    public TopToolBar topToolBar = new TopToolBar(driver);
    public ToolBar toolBar = new ToolBar(driver);
    public static final By MIDDLE_PART_OF_PAGE = By.xpath(".//*[@id= 'hook_Block_MiddleColumn']");
    private static final By LOADED_PHOTO =  By.xpath("(.//span[@class = 'photo-card_cnt']/img)");

    public  PhotoLoadPage(WebDriver driver){
        super(driver);
    }
    public void check(){
        //
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(MIDDLE_PART_OF_PAGE));
    }
    public String getPhotoId(){
        Assert.assertTrue("Фото не загружено", isElementPresent(LOADED_PHOTO));
        String id = driver.findElement(LOADED_PHOTO).getAttribute("id");
        return id;
    }

}
