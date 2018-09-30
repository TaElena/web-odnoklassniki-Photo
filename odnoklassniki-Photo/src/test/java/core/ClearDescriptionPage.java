package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by User on 18.04.2018.
 */
public class ClearDescriptionPage extends PhotoPage implements DescriptionInterface { //Lena

    private static final By PHOTO = By.xpath(".//*[contains(@id, '__plpcte_target')]");

    ClearDescriptionPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        Assert.assertTrue("Не дождались прогрузки страницы Фото",
                explicitWait( ( ExpectedConditions.visibilityOfElementLocated(PHOTO)),
                        5, 500) );
    }
}
