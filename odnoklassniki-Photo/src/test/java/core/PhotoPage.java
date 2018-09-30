package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;

/**
 * Created by User on 15.04.2018.
 */
public class PhotoPage extends HelperBase {

    private static final By ADD_DESCRIPTION = By.xpath(".//*[contains(@class, 'tico_txt')]");  //Lena
    private static final By PHOTO = By.xpath(".//*[contains(@id, '__plpcte_target')]"); //Lena
    private static final By SAVE_DESCRIPTION = By.xpath(".//*[contains(@value, 'Сохранить')]"); //Lena
    private static final String INPUT_DESCRIPTION = "plp_descrInp"; //Lena
    private static final By CLOSE_PHOTO = By.xpath(".//*[contains(@class, 'js-photoLayerClose ic photo-layer_close')]"); //Lena
    private static final By CLICK_LIKE = By.xpath("//*[contains(@class,'photo-layer_klass_link')]");

    private static final By MARK_FRIENDS = By.xpath(".//*[text() = 'Отметить друзей']"); //Lena
    private static final By CHOOSE_SELF = By.xpath(".//*[@id = 'plpp_markSelf']"); //Lena
    private static final By DONE = By.xpath(".//*[@class = 'js-cancelEditMode button-pro']"); //Lena

    private static final By ADD_COMMENT = By.xpath(".//*[contains(@class,'itx js-comments_add')]");
    private static final By CLICK_ADD_COMMENT = By.xpath(".//button[contains(@class,'button-pro form-actions_yes')]");
    //private static final By CHECK_LIKE = By.xpath("//*[contains(@data-flags, 'react')]//child::*[contains(@class, 'widget  __active __no-o __wide-count')]");
    private static final By CHECK_LIKE = By.xpath("//*[contains(@class, 'photo-layer_klass __active __anim')]");
    private static final By DELETE_COM = By.xpath("(//a[@title='Удалить'])[last()]");
    private static final By WAIT_FOR_DEL_COM = By.xpath("(//*[@class='photo-layer_bottom_l'])");
    private static final By MAKE_PHOTO_MAIN = By.xpath(".//*[text() = 'Сделать главной']");
    private static final By SET = By.xpath(".//*[text() = 'Установить']");
    private static final By DELETE_PHOTO = By.xpath(".//*[text() = 'Удалить фотографию']");

//    private static final By MAKE_PHOTO_MAIN =By.xpath("(.//*[@class='u-menu']/descendant::span[@class='tico'])[3]");

    public PhotoPage(WebDriver driver) {
        super(driver);
    }

    protected void check() { //Lena
        Assert.assertTrue("Не дождались прогрузки страницы Фото",
                explicitWait( ( ExpectedConditions.visibilityOfElementLocated(PHOTO)),
                        5, 500) );
    }

    public void clickAddDescription() { //Lena
        Assert.assertTrue("Не найдено добавление описания", isElementPresent(ADD_DESCRIPTION));
        driver.findElement(ADD_DESCRIPTION).click();
    }

    public void typeDescrName(String description) { //Lena
        type(description, By.id(INPUT_DESCRIPTION));
    }

    public void clickSaveDescription() { //Lena
        Assert.assertTrue("Не найдена кнопка сохраннения описания", isElementPresent(SAVE_DESCRIPTION));
        driver.findElement(SAVE_DESCRIPTION).click();
    }

    public boolean isAddDescription(String description) { //Lena
        final By DESCRIPTION_CREATED = By.xpath(".//*[text() = '" + description + "' ]");
        return isElementPresent(DESCRIPTION_CREATED);
    }

    public PhotoMainPage closePhotoRetPhotoMainP(){ //Lena
        Assert.assertTrue("Не дождались прогрузки раздела Фото",
                explicitWait( ( ExpectedConditions.visibilityOfAllElements(driver.findElements(CLOSE_PHOTO))),
                        10, 500) );
        driver.findElement(CLOSE_PHOTO).click();
        return new PhotoMainPage(driver);
    }
    public AlbumPage closePhotoRetAlbumP(){ //Lena
        Assert.assertTrue("Не дождались прогрузки раздела Фото",
                explicitWait( ( ExpectedConditions.visibilityOfAllElements(driver.findElements(CLOSE_PHOTO))),
                        10, 500) );
        driver.findElement(CLOSE_PHOTO).click();
        return new AlbumPage(driver);
    }


    public void clickMarkFriends() { //Lena
        Assert.assertTrue("Не дождались прогрузки раздела Фото",
                explicitWait( ( ExpectedConditions.elementToBeClickable(MARK_FRIENDS)),
                        10, 500) );
        driver.findElement(MARK_FRIENDS).click();
    }
//    public void clickOnSelf() { //Lena
//        Assert.assertTrue("Не найдено отметить себя", isElementPresent(CHOOSE_SELF));
//        driver.findElement(CHOOSE_SELF).click();
//    }
    public void clickDone() { //Lena
        Assert.assertTrue("Не найдено готово", isElementPresent(DONE));
        driver.findElement(DONE).click();
    }

    public boolean isMarkSelf(String fio) { //Lena
        final By MARK_SELF_CREATED = By.xpath(("//*[contains(@class, 'tag')]//child::*[contains(text(), '" + fio + "')]"));
        return isElementPresent(MARK_SELF_CREATED);
    }

    public void setAddComment(String comText){ //Таня
        Assert.assertTrue("Не найдено поле добавления коммента", isElementPresent(ADD_COMMENT));
        type(comText,ADD_COMMENT);
    }

    public void clickSetAddComment(){ // Таня
        Assert.assertTrue("Не найдена кнопка добавления коммента", isElementPresent(CLICK_ADD_COMMENT));
        click(CLICK_ADD_COMMENT);
    }

    public boolean isAddComment(String com) {
        final By COMMENT_CREATED = By.xpath(".//*[text() = '" + com + "' ]");
        return isElementPresent(COMMENT_CREATED);
    }

    public ClickLikePromise clickLike(WebDriver driver, PhotoPage photoPage){
        Assert.assertTrue("Не найдена кнопка лайка", isElementPresent(CLICK_LIKE));
        click(CLICK_LIKE);
        return new ClickLikePromise(driver, photoPage);
    }

    public void makePhotoMain(){
        Assert.assertTrue("Кнопка \"Сделать главной\" не найдена",
                explicitWait( ( ExpectedConditions.elementToBeClickable(MAKE_PHOTO_MAIN)),
                        10, 500) );
        click(MAKE_PHOTO_MAIN);


        Assert.assertTrue("Кнопка \"Установить\" не найдена",
                explicitWait( ( ExpectedConditions.elementToBeClickable(SET)),
                        10, 500) );
        click(SET);
    }
    public void deletePhoto(){
        Assert.assertTrue("Кнопка \"Удалить фотографию\" не найдена",
                explicitWait( ( ExpectedConditions.elementToBeClickable(DELETE_PHOTO)),
                        10, 500) );
        click(DELETE_PHOTO);
    }
}

