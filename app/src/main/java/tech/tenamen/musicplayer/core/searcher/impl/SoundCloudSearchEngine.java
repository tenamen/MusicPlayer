package tech.tenamen.musicplayer.core.searcher.impl;

import tech.tenamen.musicplayer.core.artist.Artist;
import tech.tenamen.musicplayer.core.helper.WebHelper;
import tech.tenamen.musicplayer.core.helper.data.ArtworkData;
import tech.tenamen.musicplayer.core.player.impl.SoundCloudProvider;
import tech.tenamen.musicplayer.core.searcher.SearchEngine;

public class SoundCloudSearchEngine extends SearchEngine <SoundCloudProvider> {

    private static String clientId;

    public SoundCloudSearchEngine() {
    }

    @Override
    protected void onSearch() {
        if (clientId == null) {
            final String a = WebHelper.getURLContent("https://soundcloud.com/").
                    split("<script crossorigin src=\"https://a-v2.sndcdn.com/assets/")[5];
            clientId = WebHelper.clip(WebHelper.getURLContent(String.format("https://a-v2.sndcdn.com/assets/%s", a.substring(0, a.indexOf("\"")))),
            "client_id=",
            "\"");
        }
		final String data = WebHelper.getURLContent(
            String.format("https://api-v2.soundcloud.com/search?q=%s&client_id=%s&limit=%d&offset=%d",
            this.encodedTitle.replaceAll(" ", "%20"),
            clientId,
            getNumberPerPage(),
            this.searchIndex * getNumberPerPage())).trim();
		int tabCount = -1, startIndex = -1, endIndex = -1;
		for (int i = 0, l = data.length(); i < l; i++) {
			final char current = data.charAt(i);
			switch (current) {
			case '{':
				if (tabCount++ == 0) startIndex = i;
				break;
			case '}':
				if (--tabCount == 0) endIndex = i;
				break;
			}
			if (startIndex != -1 && endIndex != -1) {
                final String contentData = data.substring(startIndex, endIndex);
                int likes = -1;
                final String likeString = WebHelper.clip(contentData, "\"likes_count\":", ",");
                try {
                    likes = Integer.parseInt(likeString);
                } catch (NumberFormatException e) {
                    e.printStackTrace();;
                }
                this.addResult(new SoundCloudProvider(
                    WebHelper.clip(contentData, "\"title\":\"", "\","),
                    new Artist(WebHelper.clip(contentData, "\"username\":\"", "\",")),
                    new ArtworkData(WebHelper.clip(contentData, "\"artwork_url\":\"", "\",")),
                    likes,
                    WebHelper.clip(data, "\"media\":{\"transcodings\":[{\"url\":\"https://api-v2.soundcloud.com/media/soundcloud:tracks:", "/stream/hls"),
                    WebHelper.clip(data, "\",\"track_authorization\":\"", "\",")
                ));
				startIndex = -1;
				endIndex = -1;
			}
		}
    }
    
    @Override
    protected int getNumberPerPage() {
        return 20;
    }

    public static String getClientID() {
        return clientId;
    }
}
