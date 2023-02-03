package tech.tenamen.musicplayer.core;

import java.util.ArrayList;
import java.util.List;

import tech.tenamen.musicplayer.core.player.SoundProvider;

public final class PlayList {

    private final List<SoundProvider> playList;

    public PlayList() {
        this.playList = new ArrayList<>();
    }
}
