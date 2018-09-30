package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 23.04.2018.
 */
public class TopToolBar extends HelperBase{

    public static final By TOOLBAR_USER_DROPDOWN = By.xpath(".//*[@class = 'ucard-mini_cnt_i']");
    public static final By LOGOUT = By.linkText("Выйти");
    public static final By LOGOFF = By.id("hook_FormButton_logoff.confirm_not_decorate");


    public TopToolBar(WebDriver driver) {
        super(driver);
    }

    protected void check(){
        Assert.assertTrue("Не дождались прогрузки тулбара",
                explicitWait( ( ExpectedConditions.elementToBeClickable(TOOLBAR_USER_DROPDOWN)),
                        5, 500) );
    }
    public void logout(){
//
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(TOOLBAR_USER_DROPDOWN));


        click(TOOLBAR_USER_DROPDOWN);
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(LOGOUT));
        click(LOGOUT);
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(LOGOFF));
        click(LOGOFF);
    }
}
