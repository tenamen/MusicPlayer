package tech.tenamen.musicplayer.core.player.impl;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayDeque;
import java.util.Queue;

import javax.sound.sampled.Clip;

import tech.tenamen.musicplayer.core.artist.Artist;
import tech.tenamen.musicplayer.core.helper.data.ArtworkData;
import tech.tenamen.musicplayer.core.helper.data.EXTINFData;
import tech.tenamen.musicplayer.core.player.SoundProvider;
import tech.tenamen.musicplayer.core.searcher.impl.SoundCloudSearchEngine;
import tech.tenamen.musicplayer.core.helper.WebHelper;

public class SoundCloudProvider extends SoundProvider {

    private boolean initialized;

    private final String TRACK_ID, TRACK_AUTHORIZATION;

    private final Queue<EXTINFData> EXTINFS;

    public SoundCloudProvider(final String musicName, final Artist artist, final ArtworkData artworkData, final int likes, final String TRACK_ID, final String TRACK_AUTHORIZATION) {
        super(musicName, artist, artworkData, likes);
        this.TRACK_ID = TRACK_ID;
        this.TRACK_AUTHORIZATION = TRACK_AUTHORIZATION;
        this.initialized = false;
        this.EXTINFS = new ArrayDeque<>();
    }

    @Override
    public void pauseMusic() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onPlayMusic() {
        if (!this.initialized) {
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
            EXTINFS.poll();
            System.out.println(EXTINFS.peek().getUrl().toString());
        }
    }

    @Override
    public void onStopMusic() {
        // TODO Auto-generated method stub
        
    }
}
