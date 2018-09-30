package tests;

import core.*;
import model.TestBot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static core.PhotoMainPage.OPEN_PHOTO_FOR_COM;
import static java.lang.Thread.sleep;

/**
 * Created by таня on 18.04.2018.
 */
public class TatyanaAddComment extends TestBase{
    String pathname = "C:/Users/таня/Pictures/4tqpkF_iGC4.jpg";
    TestBot testBot = new TestBot("QA18testbot20 ", "QA18testbot1");
    String idPhoto;
    String com = "Новый коммент";

    @Before
    public void beforeAddComments() throws Exception{
        idPhoto = HelperTest.loadPhoto(driver, testBot, pathname);
    }

    @Test
    public void addComment() throws Exception{
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(testBot);
        PhotoMainPage photoMainPage = userMainPage.clickPhotosOnToolbar();
        PhotoPage photoPage = photoMainPage.openPhotoById(idPhoto);
        photoPage.setAddComment(com);
        photoPage.clickSetAddComment();
        Assert.assertTrue("Коммент не добавлен", photoPage.isAddComment(com));
        photoMainPage = photoPage.closePhotoRetPhotoMainP();
        photoMainPage.topToolBar.logout();

    }

    @After
    public void afterAddComments() throws Exception{
        HelperTest.deletePhoto(driver, testBot, idPhoto);
    }
}
