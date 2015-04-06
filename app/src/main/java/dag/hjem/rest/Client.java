package dag.hjem.rest;

import android.net.Uri;
import android.util.Log;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.ISO8601DateFormat;

import java.io.IOException;

public class Client {
    private ObjectMapper mapper;
    private Uri.Builder uriBuilder;
    private String basePath;
    private StringBuilder encodedQueries;

    public Client(String basePath) {
        mapper = new ObjectMapper();
        mapper.setDateFormat(new ISO8601DateFormat());
        this.basePath = basePath;
        uriBuilder = newUriBuilder();
        encodedQueries = new StringBuilder();
    }

    public Client appendPath(String path) {
        uriBuilder = uriBuilder.appendPath(path);
        return this;
    }

    public Client queryParam(String key, String value) {
        uriBuilder = uriBuilder.appendQueryParameter(key, value);
        return this;
    }

    public Client encodedQuery(String query) {
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
        String url = uriBuilder.build().toString();
        url += encodedQueries.toString();
        Log.i("hjem", url);
        String content = UrlReader.get(url);
        Log.i("hjem", content);
        return mapper.readValue(content, c);
    }

    private Uri.Builder newUriBuilder() {
        return new Uri.Builder().scheme("http").authority("reisapi.ruter.no").path(basePath);
    }
}
