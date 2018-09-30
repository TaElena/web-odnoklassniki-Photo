package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserMainPage extends HelperBase{
    public TopToolBar topToolBar = new TopToolBar(driver);
   //public static final By TOOLBAR_USER_DROPDOWN =  By.xpath("//div[@id='hook_Block_ToolbarUserDropdown']/div/div/div/div");
    public static final By TOOLBAR_USER_DROPDOWN = By.xpath(".//*[@class = 'ucard-mini_cnt_i']");
    public static final By LOGOUT = By.linkText("Выйти");
    public static final By LOGOFF = By.id("hook_FormButton_logoff.confirm_not_decorate");
    public static final By PHOTOS = By.xpath(".//*[contains(@data-l,'userPhotos')]");
    public static final By FIO = By.xpath(".//*[contains(@class, 'nav-side_i ellip __with-ic')]/child::*[@class = 'tico']");
    public static final By SHOW_MORE = By.xpath(".//*[text() = 'Показать ещё']");
    public static final By MAIN_PHOTO = By.xpath(".//*[@class = 'entity-avatar']/descendant::a");

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(PHOTOS ));
    }

    public PhotoMainPage clickPhotosOnToolbar() {
        Assert.assertTrue("Не дождались прогрузки кнопки \"Фото\"",
                explicitWait((ExpectedConditions.elementToBeClickable(driver.findElement(PHOTOS))),
                        15, 500));
        click(PHOTOS);

    if ( isElementPresent(SHOW_MORE)){
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(SHOW_MORE));
//        Assert.assertTrue("Не дождались прогрузки кнопки \"Показать ещё\"",
//                explicitWait( ( ExpectedConditions.elementToBeClickable(driver.findElement(SHOW_MORE))),
//                        15, 500) );
        click(SHOW_MORE);
    }

        return new PhotoMainPage(driver);
    }
// Больше не используется
//    public void clickLogout(){
////
//        (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.elementToBeClickable(TOOLBAR_USER_DROPDOWN));
//
//
//
//        click(TOOLBAR_USER_DROPDOWN);
//        (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.elementToBeClickable(LOGOUT));
//        click(LOGOUT);
//        (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.elementToBeClickable(LOGOFF));
//        click(LOGOFF);
//    }
    public String getFIO() {
        String fio;
        fio = driver.findElement(FIO).getText();
        return fio;
    }
    public String getMainPhoto(){
        String mainPhotoId = driver.findElement(MAIN_PHOTO).getAttribute("href");
        return mainPhotoId;
    }
}