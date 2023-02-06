package tech.tenamen.musicplayer.core.player.impl;

import java.net.URL;
import java.util.ArrayDeque;
import java.util.Queue;

import javafx.scene.media.AudioClip;
import tech.tenamen.musicplayer.core.artist.Artist;
import tech.tenamen.musicplayer.core.helper.data.ArtworkData;
import tech.tenamen.musicplayer.core.helper.data.EXTINFData;
import tech.tenamen.musicplayer.core.player.SoundProvider;
import tech.tenamen.musicplayer.core.searcher.impl.SoundCloudSearchEngine;
import tech.tenamen.musicplayer.core.helper.WebHelper;

public class SoundCloudProvider extends SoundProvider {

    private final String TRACK_ID, TRACK_AUTHORIZATION;

    private final Queue<EXTINFData> EXTINFS;

    public SoundCloudProvider(final String musicName, final Artist artist, final ArtworkData artworkData, final int likes, final String TRACK_ID, final String TRACK_AUTHORIZATION) {
        super(musicName, artist, artworkData, likes);
        this.TRACK_ID = TRACK_ID;
        this.TRACK_AUTHORIZATION = TRACK_AUTHORIZATION;
        this.EXTINFS = new ArrayDeque<>();
    }

    @Override
    public void pauseMusic() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onPlayMusic() {
        EXTINFS.clear();
        final String[] extinf_datas = WebHelper.getURLContent(WebHelper.clip(WebHelper.getURLContent(String.format(
            "https://api-v2.soundcloud.com/media/soundcloud:tracks:%s/stream/hls?client_id=%s&track_authorization=%s",
            this.TRACK_ID,
            SoundCloudSearchEngine.getClientID(),
            this.TRACK_AUTHORIZATION)),
            "{\"url\":\"",
            "\"}"
            )).split("#EXTINF:");
        for (int i = 1, l = extinf_datas.length; i < l; i++) {
            final String s = extinf_datas[i];
            try {
                final int sharpIndex = s.indexOf('#');
                EXTINFS.add(new EXTINFData((int) (1000f * Float.parseFloat(s.substring(0, 8))), new URL(s.substring(9, sharpIndex == -1 ? s.length() : sharpIndex))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.playingThread = new SoundCloudPlayingThread(this.EXTINFS, this);
        this.playingThread.run();
    }

    @Override
    public void onStopMusic() {
        // TODO Auto-generated method stub
        
    }

    private static final class SoundCloudPlayingThread extends Thread {

        private final Queue<EXTINFData> EXTINFS;
        private final SoundCloudProvider PARENT;

        private SoundCloudPlayingThread(final Queue<EXTINFData> EXTINFS, final SoundCloudProvider PARENT) {
            this.EXTINFS = EXTINFS;
            this.PARENT = PARENT;
        }

        @Override
        public void run() {
            while (!EXTINFS.isEmpty()) {
                final EXTINFData ED = EXTINFS.poll();
                new Thread(() -> {
                    this.PARENT.currentClip = new AudioClip(ED.getSource());
                    this.PARENT.currentClip.play();
                    if (!EXTINFS.isEmpty()) EXTINFS.peek().getSource();
                }).start();
                try {
                    super.sleep(ED.getLength() - 35);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            super.run();
        }
    }
 }
