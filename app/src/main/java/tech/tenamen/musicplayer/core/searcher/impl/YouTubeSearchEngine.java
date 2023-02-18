package tech.tenamen.musicplayer.core.searcher.impl;

import tech.tenamen.musicplayer.core.artist.Artist;
import tech.tenamen.musicplayer.core.helper.WebHelper;
import tech.tenamen.musicplayer.core.helper.data.ArtworkData;
import tech.tenamen.musicplayer.core.player.impl.YouTubeProvider;
import tech.tenamen.musicplayer.core.searcher.SearchEngine;

public class YouTubeSearchEngine extends SearchEngine <YouTubeProvider> {

    private static final String API_KEY = "AIzaSyCer_ztDdAmZJqLSX1FsNFkb_t1v29A5Ls";

    public YouTubeSearchEngine() {
    }
    
    @Override
    protected void onSearch() {
        final String RES = WebHelper.getURLContent(String.format("https://www.googleapis.com/youtube/v3/search?key=%s&type=video&part=snippet&q=%s&",
            API_KEY,
            this.encodedTitle,
            getNumberPerPage()));
        final String[] datas = RES.split("\"kind\": \"youtube#searchResult\"");
        for (int i = 1, l = datas.length; i < l; i++) {
            final String D = datas[i];
            this.addResult(new YouTubeProvider(WebHelper.clip(D, "\"title\": \"", "\","),
                new Artist(WebHelper.clip(D, "\"channelId\": \"", "\",")),
                new ArtworkData(WebHelper.clip(D, "\"high\": {            \"url\": \"", "\",")),
                -1,
                WebHelper.clip(D, "\"videoId\": \"", "\"      },")));
        }
    }

    @Override
    protected int getNumberPerPage() {
        return 20;
    }
}
