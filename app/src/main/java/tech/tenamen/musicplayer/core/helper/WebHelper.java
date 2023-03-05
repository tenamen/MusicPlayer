package tech.tenamen.musicplayer.core.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WebHelper {
    
    public static String encodeString(final String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return string;
    }

    public static String clip(String target, String first, String last) {
    	final int startIndex = target.indexOf(first) + first.length();
    	return target.substring(startIndex, target.indexOf(last, startIndex));
    }

    public static String getURLContent(final String urly) {
    	final StringBuffer buffer = new StringBuffer();
		URL url;
        try {
            String line;
            url = new URL(urly);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36 OPR/91.0.4516.72 (Edition GX-CN)");
            final InputStreamReader reader = new InputStreamReader(connection.getInputStream(), "UTF-8");
            final BufferedReader in = new BufferedReader(reader);
            while ((line = in.readLine()) != null) {
            	buffer.append(line);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return buffer.toString();
	}
}
