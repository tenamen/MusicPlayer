package tech.tenamen.musicplayer.core.player.impl;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.scene.media.AudioClip;
import tech.tenamen.musicplayer.core.artist.Artist;
import tech.tenamen.musicplayer.core.helper.WebHelper;
import tech.tenamen.musicplayer.core.helper.data.ArtworkData;
import tech.tenamen.musicplayer.core.player.SoundProvider;

public class YouTubeProvider extends SoundProvider {

    private final String VIDEP_ID;
    
    public YouTubeProvider(final String musicName, final Artist artist, final ArtworkData artworkData, final int likes, final String videoId) {
        super(musicName, artist, artworkData, likes);
        this.VIDEP_ID = videoId;
    }

    @Override
    public void pauseMusic() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onPlayMusic() {
        String[] a = WebHelper.getURLContent(String.format("https://www.yt-download.org/api/button/mp3/%s", this.VIDEP_ID)).split("<a href=");
        this.playingThread = new YouTubePlayingThread(this, a[4].substring(1, a[3].indexOf(" ")-1));
        this.playingThread.run();
    }

    @Override
    public void onStopMusic() {
        // TODO Auto-generated method stub
        
    }

    private static final class YouTubePlayingThread extends Thread {

        private final YouTubeProvider PARENT;
        private final String URL;

        public YouTubePlayingThread(YouTubeProvider parent, String url) {
            this.PARENT = parent;
            this.URL = url;
        }

        @Override
        public void run() {
            String stream = null;
            try {
                try {
                    stream = new URL(URL).toURI().toString();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();;
            }
            this.PARENT.currentClip = new AudioClip(stream);
            this.PARENT.currentClip.play();
            try {
                super.sleep(40000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.run();
        }
    }
}
