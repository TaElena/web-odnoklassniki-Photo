package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by User on 18.04.2018.
 */
public class DescriptionFactory { //Lena

    private final static By CLEAR_DESC =  By.xpath(".//*[text() = 'Добавить описание']");
    public DescriptionInterface getPage(WebDriver driver) {
        if (isDescEmpty(driver)) {
            return new ClearDescriptionPage(driver);
        } else {
            return new DescriptionPresentPage(driver);
        }
    }

    private boolean isDescEmpty(WebDriver driver) {
//        if (driver.findElement(CLEAR_DESC).isDisplayed()){
//            return  true;
//        }
//        return false;
    return driver.findElement(CLEAR_DESC).isDisplayed();
    }

}
