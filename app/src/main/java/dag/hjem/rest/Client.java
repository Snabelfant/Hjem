package dag.hjem.rest;

import android.net.Uri;
import android.util.Log;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by Dag on 06.03.2015.
 */
public class Client {
    private ObjectMapper mapper;
    private Uri.Builder uriBuilder;
    private String basePath;
    private StringBuilder encodedQueries;

    public Client(String basePath) {
        mapper = new ObjectMapper();
        this.basePath = basePath;
        clear();
    }

    private void clear() {
        uriBuilder = null;
        encodedQueries = null;
    }

    private void prepare() {
        if (uriBuilder == null) {
            uriBuilder = newUriBuilder();
            encodedQueries = new StringBuilder();
        }
    }

    public Client appendPath(String path) {
        prepare();
        uriBuilder = uriBuilder.appendPath(path);
        return this;
    }

    public Client queryParam(String key, String value) {
        prepare();
        uriBuilder = uriBuilder.appendQueryParameter(key, value);
        return this;
    }

    public Client encodedQuery(String query) {
        prepare();
        encodedQueries.append("&").append(query);
        return this;
    }

    public Client queryParam(String key, int value) {
        return queryParam(key, Integer.toString(value));
    }

    public Client queryParam(String key, boolean value) {
        return queryParam(key, Boolean.toString(value));
    }

    public <T> T get(Class<T> c) throws IOException {
        prepare();
        String url = uriBuilder.build().toString();
        url += encodedQueries.toString();
        clear();
        Log.i("hjem", url);
        String content = UrlReader.get(url);
        Log.i("hjem", content);
        return mapper.readValue(content, c);
    }

    private Uri.Builder newUriBuilder() {
        return new Uri.Builder().scheme("http").authority("reisapi.ruter.no").path(basePath);
    }
}
