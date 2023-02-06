package tech.tenamen.musicplayer.core.helper.data;

import java.net.URL;

public final class EXTINFData {

    private final int LENGTH;
    private final URL URL;

    public EXTINFData(int LENGTH, URL URL) {
        this.LENGTH = LENGTH;
        this.URL = URL;
    }

    public int getLength() {
        return this.LENGTH;
    }

    public URL getUrl() {
        return this.URL;
    }
    
}
