package core;

import model.TestBot;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginMainPage extends HelperBase{
    public static final By LOGIN_PAGE = By.xpath(".//*[contains(@class , 'anonym_login')]");
    public static final By LOGIN = By.xpath(".//*[contains(@value,'Войти')]");

    public LoginMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        Assert.assertTrue("Не дождались прогрузки стартовой страницы",
                explicitWait( ( ExpectedConditions.presenceOfAllElementsLocatedBy(LOGIN_PAGE)),
                        15, 500) );
    }

    public UserMainPage doLogin(TestBot testBot) {
        type(testBot.getLogin(), By.id("field_email"));
        type(testBot.getPassword(), By.id("field_password"));
        click(LOGIN);
        return new UserMainPage(driver);
    }
}