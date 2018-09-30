package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class AlbumPage extends HelperBase {
    public TopToolBar topToolBar = new TopToolBar(driver);
    public ToolBar toolBar = new ToolBar(driver);

    private static final By EDIT_ALBUM = By.xpath(".//*[text() = 'Редактировать, изменить порядок']"); //Lena
    private static final By DELETE_BUTTON = By.xpath(".//*[@id='hook_Block_PhotoCardV2Block867039499749']//child::*[@class = 'photo-widget __del']"); //Lena
    private static final By DELETE_DONE = By.xpath(".//*[@class = 'photo_delete va_target']"); //Lena
    private static final By RECOVERY_PHOTO = By.xpath(".//*[text() = 'Восстановить']"); //Lena

    public static final String newAlbumName = "NewName";

    public static final By FIND_ALBUM_NAME = By.xpath(".//*[contains(@class,'it_w')]//*[@data-module='PhotoEdit']");
   // public static final By RETERN_TO_PHOTO_FROM_EDIT = By.xpath("//*[contains(@class,'tico_img ic12')]//parent::span[@class='tico tico__12']//parent::*[@class='al']");
    public static final By EDIT_ALBUM_LOCATOR = By.xpath("//*[contains(@class,'tico_img ic12')]/parent::span[@class='tico tico__12']/parent::a");
    public static final By MIDDLE_PART_OF_PAGE = By.xpath(".//*[@id= 'hook_Block_MiddleColumn']");

    public static final By DELETE_ALBUM = By.linkText("Удалить альбом");
    public static final By CONFIRM_DELETE_ALBUM = By.id("hook_FormButton_button_delete_confirm");
    public static final By TARGET_ALBUM = By.xpath("((.//*[contains(@class,'drop-lst')])/descendant::li[contains(@class, 'custom-isl')])");
    public static final By DROP_DOWN_LIST_ALBUMS = By.xpath("(.//a[contains(@class,'custom-isl_choice')])");
    public static final By FIRST_PHOTO_IN_ALBUM = By.xpath("(.//span[contains(@class,'selectable-card')])[1]");// плохой локатор, тут тоже можно ваппер
    public static final By MOVE_BUTTON = By.xpath("(.//a[contains(@class,'button_move') ])");
    public static final By INFO_PHOTO_MOVED = By.xpath(".//div[starts-with(@class, 'iblock') ]");

    public static final By PHOTOS = By.xpath("(.//a[@class = 'photo-card_cnt'])");

    public AlbumPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(MIDDLE_PART_OF_PAGE));
    }

    //////////lena///////////
    //Метод теперь возвраает страницу редактирования альбома
    public EditAlbumPage clickEdit() { //Lena
        Assert.assertTrue("Не найдено редактировать", isElementPresent(EDIT_ALBUM));
        driver.findElement(EDIT_ALBUM).click();
        return new EditAlbumPage(driver);
    }

    public void clickEditAlbum() {
        click(EDIT_ALBUM_LOCATOR);
    }

    //    public PhotoPage clickOnPhoto(String photoId) {
//        By PHOTO= By.xpath("(.//a[@class = 'photo-card_cnt'])[1]");
//        (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.visibilityOfElementLocated(PHOTO));
//        Assert.assertTrue("Не отображается фото", isElementVisible(PHOTO));
//        driver.findElement(PHOTO).click();
//        return new PhotoPage(driver);
//
//        //List<PhotoElement>
//    }

    public PhotoPage openPhotoById(String photoId) {
        By OPEN = By.xpath(".//*[contains(@id, '" + photoId + "')]");
        Assert.assertTrue("Не найдено фото", isElementPresent(OPEN) && driver.findElement(OPEN).isDisplayed());
        click(OPEN);

        return new PhotoPage(driver);
    }
    public boolean isPhotoPresent(String photoId){
        By PHOTO = By.xpath(".//*[contains(@id, '" + photoId + "')]");
        return isElementPresent(PHOTO);

    }

}
