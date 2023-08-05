package Farm.Team4.findOwn.service.trademark;

import Farm.Team4.findOwn.domain.trademark.Trademark;
import Farm.Team4.findOwn.repository.trademark.TrademarkRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    public String findTrademark(String dest) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> request = new HttpEntity(headers);
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8)); // 추가한 부분
        String xmlContent =  restTemplate.exchange(
                searchTrademarkUrl +"?serviceKey=" + dataServiceKey + "&searchString=" + dest,
                HttpMethod.GET,
                request,
                String.class
        ).getBody();
        return xmlContent;
    }
    public Long saveTrademark(Trademark trademark){
        log.info("tradeService 진입 성공");
        Trademark savedTrademark = trademarkRepository.save(trademark);
        return savedTrademark.getId();
    }
}
