package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EditAlbumPage  extends HelperBase  {

    public TopToolBar topToolBar = new TopToolBar(driver);
    public ToolBar toolBar = new ToolBar(driver);
    private static final By EDIT_ALBUM = By.xpath(".//*[text() = 'Редактировать, изменить порядок']"); //Lena
   // private static final By DELETE_BUTTON = By.xpath(".//*[@id='hook_Block_PhotoCardV2Block867039499749']//child::*[@class = 'photo-widget __del']"); //Lena
    private static final By DELETE_DONE = By.xpath(".//*[@class = 'photo_delete va_target']"); //Lena
    private static final By RECOVERY_PHOTO = By.xpath(".//*[text() = 'Восстановить']"); //Lena

    public static final By FIND_ALBUM_NAME = By.xpath(".//*[contains(@class,'it_w')]//*[@data-module='PhotoEdit']");
    public static final By RETERN_TO_PHOTO_FROM_EDIT = By.xpath("//*[contains(@class,'tico_img ic12')]//parent::span[@class='tico tico__12']//parent::*[@class='al']");
    public static final By EDIT_ALBUM_LOCATOR = By.xpath("//*[contains(@class,'tico_img ic12')]/parent::span[@class='tico tico__12']/parent::a");
    public static final By MIDDLE_PART_OF_PAGE = By.xpath(".//*[@id= 'hook_Block_MiddleColumn']");

    public static final By DELETE_ALBUM = By.linkText("Удалить альбом");
    public static final By CONFIRM_DELETE_ALBUM = By.id("hook_FormButton_button_delete_confirm");
    public static final By TARGET_ALBUM = By.xpath("((.//*[contains(@class,'drop-lst')])/descendant::li[contains(@class, 'custom-isl')])");
    public static final By DROP_DOWN_LIST_ALBUMS = By.xpath("(.//a[contains(@class,'custom-isl_choice')])");
    public static final By FIRST_PHOTO_IN_ALBUM = By.xpath("(.//span[contains(@class,'selectable-card')])[1]");// плохой локатор, тут тоже можно ваппер
    public static final By MOVE_BUTTON = By.xpath("(.//a[contains(@class,'button_move') ])");
    public static final By INFO_PHOTO_MOVED = By.xpath(".//div[starts-with(@class, 'iblock') ]");

    public  EditAlbumPage(WebDriver driver){
        super(driver);
    }
    public void check(){
        //
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(MIDDLE_PART_OF_PAGE));
    }

//    public void clickOnPhoto(){
//        click(FIRST_PHOTO_IN_ALBUM);
//    }
    public void clickOnPhoto(String photoId) {
        By CHOICE = By.xpath(".//*[contains(@id, '" + photoId + "')]");
        Assert.assertTrue("Не найдено фото", isElementPresent(CHOICE) && driver.findElement(CHOICE).isDisplayed());
        click(CHOICE);


    }

    public WebElement choseAlbumFromList (List<WebElement> albumList, String albumName){
        for (WebElement webElement : albumList) {
            if ( webElement.getText().equals(albumName)){
                return webElement;
            }
        }
       // Assert.assertNotNull("Альбом " + albumName + " не найден", null);
        return null;
    }
    public List<WebElement> listAllTargetAlbums(){
        return driver.findElements(TARGET_ALBUM);
    }
//    public void choseTargetAlbum(String albumName){
//        click(DROP_DOWN_LIST_ALBUMS);
//        List<WebElement> albumList = driver.findElements(TARGET_ALBUM);
//        WebElement album = choseAlbumFromList(albumList, albumName);
//        album.click();

//    }
    public void clickDropdownAlbumList(){
        Assert.assertTrue("Не найден списко альбомов",
                explicitWait( ( ExpectedConditions.elementToBeClickable(DROP_DOWN_LIST_ALBUMS)),
                        5, 500) );
        click(DROP_DOWN_LIST_ALBUMS);
    }
    public void clickMoveButton() {
        Assert.assertTrue("Не найдена кнопка \"Перенести фотографии\"",
                explicitWait( ( ExpectedConditions.elementToBeClickable(MOVE_BUTTON)),
                        5, 500) );
        click(MOVE_BUTTON);
    }


    public boolean isPhotoMoved(String albumName){
        if ( isElementPresent(INFO_PHOTO_MOVED)) {
            WebElement info = driver.findElement(INFO_PHOTO_MOVED);
            if (info.getText().contains("Фото перенесены в")){
                return true;
            }
        }
        return false;
    }


    public  void clickDeleteButton (){
        Assert.assertTrue("Не найдена кнопка \"Удалить альбом\"",
                explicitWait( ( ExpectedConditions.elementToBeClickable(DELETE_ALBUM)),
                        5, 500) );
        click(DELETE_ALBUM);

    }
    public AlbumsMainPage confirmAlbumDeletion(){
        Assert.assertTrue("Не найдена кнопка \"Удалить\"",
                explicitWait( ( ExpectedConditions.elementToBeClickable(CONFIRM_DELETE_ALBUM)),
                        5, 500) );
        click(CONFIRM_DELETE_ALBUM);
        return new AlbumsMainPage(driver);
    }

    public boolean isChangeAlbumsName(String name){
        final By NAME_CHANGED = By.xpath(".//*[text() = '" + name + "' ]");
        return isElementPresent(NAME_CHANGED);

    }

    public void clickDelete(String id){ //Lena
        By DELETE_BUTTON = By.xpath(".//*[contains(@href, '" + id.substring(4) + "') and @class = 'photo-widget __del']");
        Assert.assertTrue("Не найдено удалить", isElementPresent(DELETE_BUTTON));
        driver.findElement(DELETE_BUTTON).click();
    }
    public void isDeleted(){ //Lena
        Assert.assertTrue("Не найдено фоторафия удалена", isElementPresent(DELETE_DONE));
        driver.findElement(DELETE_DONE);
    }

    public void editAlbumName(String newAlbumName){
        Assert.assertTrue("Не найдено поле имени альбома", isElementPresent(FIND_ALBUM_NAME));
        click(FIND_ALBUM_NAME);
        type(newAlbumName,FIND_ALBUM_NAME);

    }

    public AlbumPage returnToPhoto() {
        Assert.assertTrue("Не найдена кнопка назад", isElementPresent(RETERN_TO_PHOTO_FROM_EDIT));
        click(RETERN_TO_PHOTO_FROM_EDIT);
        return new AlbumPage(driver);
    }
}
