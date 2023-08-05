package Farm.Team4.findOwn.service.trademark;

import Farm.Team4.findOwn.domain.trademark.Trademark;
import Farm.Team4.findOwn.dto.trademark.parsing.Response;
import Farm.Team4.findOwn.dto.trademark.parsing.body.Item;
import Farm.Team4.findOwn.repository.trademark.TrademarkRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrademarkService {
    private final TrademarkRepository trademarkRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${SERVICE_KEY}")
    private String dataServiceKey;
    @Value("${SEARCH_TRADEMARK_URL}")
    private String searchTrademarkUrl;
    public List<Item> findTrademark(String dest) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> request = new HttpEntity(headers);
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8)); // Xml response UTF-8 encoding
        String xmlContent =  restTemplate.exchange(
                searchTrademarkUrl +"?serviceKey=" + dataServiceKey + "&searchString=" + dest,
                HttpMethod.GET,
                request,
                String.class
        ).getBody();
        ObjectMapper xmlMapper = new XmlMapper();
        Response response = xmlMapper.readValue(xmlContent, Response.class);
        List<Item> items = response.getBody().getItems();
        return items;
    }

    public Long saveTrademark(Trademark trademark){
        log.info("tradeService 진입 성공");
        Trademark savedTrademark = trademarkRepository.save(trademark);
        return savedTrademark.getId();
    }
}
