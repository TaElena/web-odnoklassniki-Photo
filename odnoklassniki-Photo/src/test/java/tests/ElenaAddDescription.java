package tests;

import core.*;
import model.TestBot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * Created by User on 18.04.2018.
 */
import org.junit.Assert;

public class ElenaAddDescription extends TestBase{

    String pathname = "C:/Users/User/Desktop/УНИВЕР/Тестирование/car.jpg";
    TestBot testBot = new TestBot("QA18testbot9 ", "QA18testbot");
    String idPhoto;
    String description = "Описание...";


    @Before
    public void beforeAddDescription() throws InterruptedException {
        idPhoto = HelperTest.loadPhoto(driver, testBot, pathname);
    }

    @Test
    public void addDescription() throws Exception{
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(testBot);
        PhotoMainPage photoMainPage = userMainPage.clickPhotosOnToolbar();
        PhotoPage photoPage = photoMainPage.openPhotoById(idPhoto);

        DescriptionInterface photoInterface = new DescriptionFactory().getPage(driver);
        photoInterface.clickAddDescription();
        photoInterface.typeDescrName(description);
        photoInterface.clickSaveDescription();

        Assert.assertTrue( "Описание не добавлено", photoPage.isAddDescription(description));
        photoMainPage = photoPage.closePhotoRetPhotoMainP();
        photoMainPage.topToolBar.logout();
    }

    @After
    public void deletePhotoAfterTest(){
        HelperTest.deletePhoto(driver, testBot, idPhoto);
    }
}
