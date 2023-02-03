package tech.tenamen.musicplayer.core.player.impl;

import tech.tenamen.musicplayer.core.artist.Artist;
import tech.tenamen.musicplayer.core.helper.data.ArtworkData;
import tech.tenamen.musicplayer.core.player.SoundProvider;

public class YouTubeProvider extends SoundProvider {
    
    public YouTubeProvider(final String musicName, final Artist artist, final ArtworkData artworkData, final int likes) {
        super(musicName, artist, artworkData, likes);
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
