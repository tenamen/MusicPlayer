package tech.tenamen.musicplayer.core.player.impl;

import tech.tenamen.musicplayer.core.artist.Artist;
import tech.tenamen.musicplayer.core.helper.data.ArtworkData;
import tech.tenamen.musicplayer.core.player.SoundProvider;

public class SoundCloudProvider extends SoundProvider {

    private final String TRACK_ID, TRACK_AUTHORIZATION;

    public SoundCloudProvider(final String musicName, final Artist artist, final ArtworkData artworkData, final int likes, final String TRACK_ID, final String TRACK_AUTHORIZATION) {
        super(musicName, artist, artworkData, likes);
        this.TRACK_ID = TRACK_ID;
        this.TRACK_AUTHORIZATION = TRACK_AUTHORIZATION;
    }

    @Override
    public void pauseMusic() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onPlayMusic() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onStopMusic() {
        // TODO Auto-generated method stub
        
    }
}
