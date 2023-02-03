/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package tech.tenamen.musicplayer;

import tech.tenamen.musicplayer.core.PlayList;
import tech.tenamen.musicplayer.core.player.impl.*;
import tech.tenamen.musicplayer.core.searcher.SearchEngine;
import tech.tenamen.musicplayer.core.searcher.impl.*;

public class App {

    private static final PlayList PLAY_LIST = new PlayList();

    private static final SearchEngine<YouTubeProvider> YOUTUBE_ENGINE = new YouTubeSearchEngine();
    private static final SearchEngine<SoundCloudProvider> SOUNDCLOUD_ENGINE = new SoundCloudSearchEngine();

    public static void main(String[] args) {
    }
}
