package data.service;

import java.util.List;
import java.util.Map;

public interface UrlService {
    List<Map<String, Object>> getUrls();

    String generateShortUrl(String longurl);

    String getLongUrlByShortUrl(String shorturl);
}
