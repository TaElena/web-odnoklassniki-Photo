package tests;

import core.*;
import model.TestBot;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class HelperTest {
    private HelperTest(){

    }
/**
 * Методы для подготовки ботов (Before)
 * Выполнения действий, проверяется только ассертами (нет глобальных проверок
 * тк они должны быть в других тестах)
 */
     public static void createAlbum(WebDriver driver, TestBot testBot, String albumName) {
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(testBot);
        PhotoMainPage photoMainPage = userMainPage.clickPhotosOnToolbar();
        photoMainPage.clickCreateAlbum();
        photoMainPage.typeAlbumName(albumName);
        photoMainPage.clickCreateButton();
        Assert.assertTrue("Альбом не создан", photoMainPage.isCreationAlbum(albumName));
        photoMainPage.topToolBar.logout();
     }

    public static String loadPhoto(WebDriver driver, TestBot testBot, String pathName) {
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(testBot);
        PhotoMainPage photoMainPage = userMainPage.clickPhotosOnToolbar();
        PhotoLoadPage photoLoadPage= photoMainPage.addPhoto(pathName);
        String photoId = photoLoadPage.getPhotoId();
        //проверка, что фото добавлено не нужна тк это метод before, а не теста
        photoLoadPage.topToolBar.logout();
        return photoId;
    }

    public static void deletePhoto(WebDriver driver, TestBot testBot, String photoId){
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(testBot);
        PhotoMainPage photoMainPage = userMainPage.clickPhotosOnToolbar();
        PhotoPage photoPage = photoMainPage.openPhotoById(photoId);
        photoPage.deletePhoto();
        photoMainPage = photoPage.closePhotoRetPhotoMainP();
        Assert.assertTrue("Фото не удалено", photoMainPage.isPhotoPresent(photoId));
        photoMainPage.topToolBar.logout();

//        photoMainPage.clickPersonalPhoto();
//        AlbumPage albumPage = new AlbumPage(driver);
//        EditAlbumPage editAlbumPage = albumPage.clickEdit();
//        editAlbumPage.clickDelete(id);
//        editAlbumPage.isDeleted();
    }
    public static void deleteAlbum(WebDriver driver, TestBot testBot, String albumName) {
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(testBot);
        PhotoMainPage photoMainPage = userMainPage.clickPhotosOnToolbar();
        List<AlbumWrapper> albums = new PhotoMainPage(driver).getAllAlbums();
        AlbumWrapper album = photoMainPage.findAlbumByName(albums, albumName);
        AlbumPage albumPage = photoMainPage.clickOnAlbum(album);
        EditAlbumPage editAlbumPage = albumPage.clickEdit();
        editAlbumPage.clickDeleteButton();
        AlbumsMainPage albumsMainPage = editAlbumPage.confirmAlbumDeletion();
        albumsMainPage.topToolBar.logout();
    }
}
