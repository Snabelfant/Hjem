package dag.hjem.rest;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

class UrlReader {

    protected static String get(String urlString) throws IOException {
        StringBuilder sb = new StringBuilder();

        Log.i("hjem","Inn='" + urlString+"'");
        URL url = new URL(urlString);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine);
        }
        in.close();

        Log.i("hjem", "Ut='" + sb.toString()+"'");
        return sb.toString();
    }
}
