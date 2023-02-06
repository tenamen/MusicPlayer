package tech.tenamen.musicplayer.core.helper.data;

import java.net.URISyntaxException;
import java.net.URL;

public final class EXTINFData {

    private final int LENGTH;
    private final URL URL;
    private String source;

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

    public String getSource() {
        if (this.source == null) {
            try {
                this.source = this.URL.toURI().toString();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return this.source;
        }
        return this.source;
    }
    
}
