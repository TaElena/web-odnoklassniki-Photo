package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AlbumTransformer {


        public static List<AlbumWrapper> wrap(List<WebElement> elements, WebDriver driver) {
            if (elements.isEmpty()) {
                return Collections.<AlbumWrapper>emptyList();
            }

            List<AlbumWrapper> albums = new ArrayList<AlbumWrapper>();
            for (WebElement album : elements) {
                albums.add(new AlbumWrapper(album, driver));
            }
            return albums;
        }


}
