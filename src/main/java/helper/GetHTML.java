package helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetHTML {

    public GetHTML(String url) {
    }

    public static String getHTML(String url) {

        URL u;
        try {
            u = new URL(url);

            URLConnection conn = u.openConnection();
            //   conn.setRequestProperty("User-Agent", "BBot/1.0");
            //  conn.setRequestProperty("Accept-Charset", "UTF-8");

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            String html = "";
            while ((line = reader.readLine()) != null) {
                html += line + "\n";
            }
            html = html.trim();

            return html;
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }
}
