package tech.tenamen.musicplayer.core.player;

import tech.tenamen.musicplayer.core.artist.Artist;
import tech.tenamen.musicplayer.core.helper.data.ArtworkData;

public abstract class SoundProvider {

    private final String MUSIC_NAME;
    private final Artist ARTIST;
    private final ArtworkData ARTWORK_DATA;
    private final int LIKES;

    private boolean playing;

    protected SoundProvider(final String musicName, final Artist artist, final ArtworkData artworkData, final int likes) {
        this.MUSIC_NAME = musicName;
        this.ARTIST = artist;
        this.ARTWORK_DATA = artworkData;
        this.LIKES = likes;
    }

    public abstract void onPlayMusic();
    public abstract void onStopMusic();
    public abstract void pauseMusic();

    public void playOrStop() {
        if (this.playing = !this.playing) {
            this.onPlayMusic();
        } else {
            this.onStopMusic();
        }
    }

    public final boolean isPlaying() {
        return this.playing;
    }

    public final String getMusicName() {
        return this.MUSIC_NAME;
    }

    public final Artist geArtist() {
        return this.ARTIST;
    }

    public ArtworkData geArtworkData() {
        return this.ARTWORK_DATA;
    }

    public final int getLikes() {
        return this.LIKES;
    }

}
