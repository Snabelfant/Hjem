package dag.hjem.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import dag.hjem.Util;

class UrlReader {

    protected static String get(String urlString) throws IOException {
        StringBuilder sb = new StringBuilder();

        Util.log("Inn='" + urlString + "'");
        URL url = new URL(urlString);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine);
        }
        in.close();

        Util.log("Ut='" + sb.toString() + "'");
        return sb.toString();
    }
}
