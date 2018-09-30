package tests;

import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


/**
 * Created by User on 18.04.2018.
 */
public class ElenaDeletePhoto extends TestBase {

    String pathname = "C:/Users/User/Desktop/УНИВЕР/Тестирование/bo.jpg";
    TestBot testBot = new TestBot("QA18testbot9 ", "QA18testbot");
    String idPhoto;
    final String albumName = "Личные фото";

    @Before
    public void beforeDelPhoto(){
        idPhoto = HelperTest.loadPhoto(driver, testBot, pathname);
    }

    @Test
    public void deletePhoto() throws Exception{
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(testBot);
        PhotoMainPage photoMainPage = userMainPage.clickPhotosOnToolbar();

        List<AlbumWrapper> albums = new PhotoMainPage(driver).getAllAlbums();
        AlbumWrapper album = photoMainPage.findAlbumByName(albums, albumName);
        Assert.assertNotNull("Альбом " + albumName + " не найден", album);
        AlbumPage albumPage = photoMainPage.clickOnAlbum(album);
        EditAlbumPage editAlbumPage = albumPage.clickEdit();
        editAlbumPage.clickDelete(idPhoto);
        editAlbumPage.isDeleted();
        editAlbumPage.topToolBar.logout();
    }
}
