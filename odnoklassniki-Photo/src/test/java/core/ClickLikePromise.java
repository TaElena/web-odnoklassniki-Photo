package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

import java.util.logging.Logger;

/**
 * Created by таня on 18.04.2018.
 */
public class ClickLikePromise{
    private final WebDriver driver;
    private  final PhotoPage photoPage;
    public static final By CHECK_LIKE = By.xpath("//*[contains(@class, 'photo-layer_klass __active __anim')]");


    public ClickLikePromise(WebDriver driver, PhotoPage photoPage){
        this.driver = driver;
        this.photoPage = photoPage;
    }

    public PhotoPage andClickLikeOn(){
        Assert.assertTrue("Лайк не активен", photoPage.isElementPresent(CHECK_LIKE));
        System.out.println("Лайк был поставлен"); // обавить ожидание или ассерт
        return photoPage;
    }

    public PhotoPage andClickLikeOff(){
        Assert.assertFalse("Лайк активен", photoPage.isElementPresent(CHECK_LIKE));
        System.out.println("Лайк был снят");
        return photoPage;
    }
}
