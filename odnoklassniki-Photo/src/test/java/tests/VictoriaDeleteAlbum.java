package tests;

import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.lang.Thread.sleep;

public class VictoriaDeleteAlbum extends TestBase {

    //тест-кейс 6  Виктория
    // Удаление албома
    //У бота должен быть хотя бы один альбом помимо личных фотографий
    String albumName = "d";
    TestBot testBot = new TestBot("QA18testbot57 ", "QA18testbot");

    @Before
    public void prepareForDeletingAlbum(){
        HelperTest.createAlbum(driver, testBot, albumName);
    }


    @Test
    public void deleteingAlbum() throws Exception {

        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(testBot);
        PhotoMainPage photoMainPage = userMainPage.clickPhotosOnToolbar();
        List<AlbumWrapper> albums = new PhotoMainPage(driver).getAllAlbums();
        AlbumWrapper album = photoMainPage.findAlbumByName(albums, albumName);
        Assert.assertNotNull("Альбом " + albumName + " не найден", album);
        AlbumPage albumPage = photoMainPage.clickOnAlbum(album);
        EditAlbumPage editAlbumPage = albumPage.clickEdit();
        editAlbumPage.clickDeleteButton();
        AlbumsMainPage albumsMainPage = editAlbumPage.confirmAlbumDeletion();
        albums = albumsMainPage.getAllAlbums();
        Assert.assertNull("Альбом не удален или cуществует еще один с таким же именем",
                            albumsMainPage.findAlbumByName(albums, albumName));
        albumsMainPage.topToolBar.logout();


    }

}
