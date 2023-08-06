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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Item> findTrademark(String searchString) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> request = new HttpEntity(headers);
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8)); // Xml response UTF-8 encoding
        String xmlContent =  restTemplate.exchange(
                searchTrademarkUrl +"?serviceKey=" + dataServiceKey + "&searchString=" + searchString,
                HttpMethod.GET,
                request,
                String.class
        ).getBody();
        log.info("공공데이터포털 api 데이터 수신 완료");

        ObjectMapper xmlMapper = new XmlMapper();
        Response response = xmlMapper.readValue(xmlContent, Response.class);
        log.info("xml parsing 완료");

        List<Item> items = response.getBody().getItems();
        return items;
    }
    public List<Trademark> selectRegisteredTrademark(List<Item> apiResult){
        List<Trademark> trademarks = apiResult.stream()
                .filter(item -> item.getApplicationStatus().equals("등록"))
                .map(item -> new Trademark(
                        item.getApplicationNumber(),
                        item.getBigDrawing(),
                        item.getApplicantName(),
                        item.getApplicationNumber(),
                        item.getApplicationStatus(),
                        item.getClassificationCode()))
                .collect(Collectors.toList());
        log.info("등록 데이터만 가져오기 성공");
        return trademarks;


    }
    @Transactional
    public Long saveTrademark(Trademark trademark){
        log.info("tradeService 진입 성공");
        Trademark savedTrademark = trademarkRepository.save(trademark);
        return savedTrademark.getId();
    }
}
