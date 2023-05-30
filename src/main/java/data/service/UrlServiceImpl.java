package data.service;

import data.mapper.UrlMapper;
import data.util.Base62;
import data.util.UrlTypeValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UrlServiceImpl implements UrlService{
    @Autowired
    private UrlMapper urlMapper;

    @Autowired
    private UrlTypeValid urlTypeValid;

    @Autowired
    private Base62 base62;

    @Override
    public List<Map<String, Object>> getUrls() {
        return urlMapper.getUrls();
    }

    @Override
    public String generateShortUrl(String longurl) {
        if(!urlTypeValid.valid(longurl)){
            throw new IllegalArgumentException("잘못된 URL 타입입니다.");
        }

        longurl = longurl.replace("https://","").replace("http://","");
        Integer id = urlMapper.getUrlIdByLongUrl(longurl);
        String shorturl;
        if(id == null) {
            id = urlMapper.updateLongUrl(longurl);
        }
        shorturl = Base62.encoding(id);

        return "http://localhost:8080/"+shorturl;
    }

    @Override
    public String getLongUrlByShortUrl(String shorturl) {
        int id = Base62.decoding(shorturl);
        String longurl = urlMapper.getLongUrlById(id);

        return longurl;
    }
}
