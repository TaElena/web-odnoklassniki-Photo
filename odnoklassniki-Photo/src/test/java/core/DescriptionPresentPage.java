package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by User on 18.04.2018.
 */
public class DescriptionPresentPage extends PhotoPage implements DescriptionInterface { //Lena

    private static final By ADD_DESCRIPTION = By.xpath(".//*[contains(@id, 'plp_descrChgLnk')]");
    private static final By PHOTO = By.xpath(".//*[contains(@id, '__plpcte_target')]");

    public DescriptionPresentPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        Assert.assertTrue("Не дождались прогрузки страницы Фото",
                explicitWait( ( ExpectedConditions.visibilityOfAllElements(driver.findElements(PHOTO))),
                        5, 500) );
        /*(new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(PHOTO));*/
    }

    @Override
    public void clickAddDescription() {
        Assert.assertTrue("Не найдено описание", isElementPresent(ADD_DESCRIPTION));
        driver.findElement(ADD_DESCRIPTION).click();
    }
}
