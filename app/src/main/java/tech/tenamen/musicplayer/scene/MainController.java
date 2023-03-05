package tech.tenamen.musicplayer.scene;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import tech.tenamen.musicplayer.App;
import tech.tenamen.musicplayer.core.player.SoundProvider;

public final class MainController {

    @FXML
    public void onSearchInput(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            final String textField = ((TextField) App.root.lookup("#searchInput")).getText();
            App.SOUNDCLOUD_ENGINE.search(textField);
            final ListView<SoundProvider> listView = (ListView<SoundProvider>) App.root.lookup("#searchResultView");
            listView.setCellFactory(param -> new ListCell<SoundProvider>() {
                @Override
                public void updateItem(SoundProvider provider, boolean empty) {
                    super.updateItem(provider, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(provider.getMusicName());
                        try {
                            setGraphic(new ImageView(new URL(provider.geArtworkData().getURL()).toURI().toString()));
                        } catch (MalformedURLException | URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                    setFont(new Font(getFont().getName(), 20));
                }
            });
            App.SOUNDCLOUD_ENGINE.getSearchList().forEach(r -> {
                listView.getItems().add(r);
            });
        }
    }
    
}
