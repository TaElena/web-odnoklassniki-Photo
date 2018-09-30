package tests;

import core.*;
import model.TestBot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static tests.HelperTest.deletePhoto;

public class VictoriaMakePhotoMain extends TestBase {

    TestBot testBot = new TestBot("QA18testbot57 ", "QA18testbot");
    String albumName = "Личные фото";
    String pathName = "C:/JavaProjects/myPhoto.jpg";
            //"C:/Users/User/Desktop/УНИВЕР/Тестирование/bo.jpg";
            //"C:/Users/таня/Pictures/Penguins.jpg";
    String photoId;



    @Before
    public void prepareForMakePhotoMain(){
        photoId = HelperTest.loadPhoto(driver, testBot, pathName);
    }

    @Test
    public void makePhotoMain() {

        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(testBot);
        PhotoMainPage photoMainPage = userMainPage.clickPhotosOnToolbar();
        List<AlbumWrapper> albums = new PhotoMainPage(driver).getAllAlbums();
        AlbumWrapper album = photoMainPage.findAlbumByName(albums, albumName);
        Assert.assertNotNull("Альбом " + albumName + " не найден", album);
        AlbumPage albumPage = photoMainPage.clickOnAlbum(album);
        PhotoPage photoPage = albumPage.openPhotoById(photoId);
        photoPage.makePhotoMain();
        photoMainPage = photoPage.closePhotoRetPhotoMainP();
        userMainPage = photoMainPage.openUserMainPage();
        String mainPhotoId = userMainPage.getMainPhoto();
        Assert.assertFalse("Фото не утановлено", photoId.equals(mainPhotoId));
        userMainPage.topToolBar.logout();


    }
    @After
    public void deleteLoadedPhotos(){
        HelperTest.deletePhoto(driver, testBot, photoId);
    }

}
