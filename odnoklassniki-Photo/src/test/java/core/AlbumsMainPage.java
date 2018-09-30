package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;

public class AlbumsMainPage extends HelperBase{
    public TopToolBar topToolBar = new TopToolBar(driver);
    public ToolBar toolBar = new ToolBar(driver);
    public static final By MIDDLE_PART_OF_PAGE = By.xpath(".//*[@id= 'hook_Block_MiddleColumn']");
    public static final By ALL_ALBUMS = By.xpath(".//*[contains(@id, 'UserAlbumStreamBlock')]/descendant::li[@class = 'ugrid_i']");
    public AlbumsMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(MIDDLE_PART_OF_PAGE));
    }
    public List<AlbumWrapper> getAllAlbums() {
        if (isElementPresent(ALL_ALBUMS)) {
            return AlbumTransformer.wrap(driver.findElements(ALL_ALBUMS), driver);
        }
        return Collections.emptyList();
    }

    public AlbumWrapper findAlbumByName(List<AlbumWrapper> albums, String albumName) {
        for (AlbumWrapper album : albums) {
            if (album.getAlbumName().equals(albumName)) {
                return album;
            }
        }

        //Assert.assertNotNull("Альбом " + albumName + " не найден", null);
        return null;
    }

}
