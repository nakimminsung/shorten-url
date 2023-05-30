package data.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UrlMapper {
    List<Map<String,Object>> getUrls();

    Integer getUrlIdByLongUrl(String longurl);

    Integer updateLongUrl(String longurl);

    String getLongUrlById(int id);
}
