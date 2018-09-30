package tests;

import core.*;
import model.TestBot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static core.AlbumPage.newAlbumName;
import static core.PhotoMainPage.OPEN_ALBUM;

/**
 * Created by таня on 18.04.2018.
 */
public class TatyanaChangeAlbumsName extends TestBase{
    TestBot testBot = new TestBot("QA18testbot20 ", "QA18testbot1");
    String oldName = "Старое имя";
    String newName = "Новое имя";

    @Before
    public void beforeChangeAlbumsName(){
        HelperTest.createAlbum(driver, testBot, oldName);
    }

    @Test
    public void changeAlbumsName() throws Exception{
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(testBot);
        PhotoMainPage photoMainPage = userMainPage.clickPhotosOnToolbar();
        List<AlbumWrapper> albums = new PhotoMainPage(driver).getAllAlbums();
        AlbumWrapper album = photoMainPage.findAlbumByName(albums, oldName);
        Assert.assertNotNull("Альбом " + oldName + " не найден", album);
        AlbumPage albumPage = photoMainPage.clickOnAlbum(album);
        EditAlbumPage editAlbumPage = albumPage.clickEdit();
        editAlbumPage.editAlbumName(newName);
        albumPage = editAlbumPage.returnToPhoto();
        Assert.assertTrue("Имя альбома не изменено", editAlbumPage.isChangeAlbumsName(newName));
        albumPage.topToolBar.logout();
    }

    @After
    public void afterChangeAlbumsName() throws Exception{
        HelperTest.deleteAlbum(driver, testBot,newName);
    }
}
