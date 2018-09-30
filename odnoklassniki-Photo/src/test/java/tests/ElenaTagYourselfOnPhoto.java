package tests;

import core.*;
import model.TestBot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static java.lang.Thread.sleep;

/**
 * Created by User on 18.04.2018.
 */
public class ElenaTagYourselfOnPhoto extends TestBase{

    String pathname = "C:/Users/User/Desktop/УНИВЕР/Тестирование/man.jpg";
    TestBot testBot = new TestBot("QA18testbot9 ", "QA18testbot");
    final String idImg = "__plpcte_target";
    final String selfId = "plpp_markSelf";
    String fio;
    String idPhoto;

    @Before
    public void beforeTagYourself(){
        idPhoto = HelperTest.loadPhoto(driver, testBot, pathname);
    }

    @Test
    public void tagYourself() throws Exception{
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(testBot);
        fio = userMainPage.getFIO();
        PhotoMainPage photoMainPage  = userMainPage.clickPhotosOnToolbar();

        PhotoPage photoPage = photoMainPage.openPhotoById(idPhoto);
        photoPage.clickMarkFriends();
        photoPage.clickBy(idImg);
        photoPage.clickBy(selfId);
        photoPage.clickDone();
        Assert.assertTrue("Пользователь не отмечен", photoPage.isMarkSelf(fio));
        photoMainPage = photoPage.closePhotoRetPhotoMainP();
        photoMainPage.topToolBar.logout();
    }

    @After
    public void deletePhotoAfterTest(){
        HelperTest.deletePhoto(driver, testBot, idPhoto);
    }
}
