package Farm.Team4.findOwn.service.design;

import Farm.Team4.findOwn.domain.design.Design;
import Farm.Team4.findOwn.dto.design.parsing.Response;
import Farm.Team4.findOwn.dto.design.parsing.body.Item;
import Farm.Team4.findOwn.repository.design.DesignRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DesignService {
    private final DesignRepository designRepository;
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${DESIGN_ENCODED_SERVICE_KEY}")
    private String dataServiceKey;
    @Value("${SEARCH_DESIGN_URL}")
    private String searchDesignUrl;
    public List<Item> findDesign(String articleName) throws JsonProcessingException {
        /*
            제공받은 service key가 url encoding 문제가 있음 -> '/', '+' 같은 특수 기호 때문에 똑바로 인코딩이 안 됨
            그래서 DefaultUriBuilderFactory를 사용하고 DefaultUriBuilderFactory의 setEncodingMode를 사용해서
            restTemplete에서 url 인코딩을 하지 않고 요청을 보내도록 했다.

            -> 상표권과 다른 점은 이거지만 이거 빼면 방식은 똑같다.
         */

        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory();
        defaultUriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE); //  URL encoding 사용

        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8)); // Xml response
        restTemplate.setUriTemplateHandler(defaultUriBuilderFactory);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> request = new HttpEntity<>(headers);
        log.info("Request Message 생성완료");

        String xmlContent = restTemplate.exchange(
                searchDesignUrl +"?serviceKey=" + dataServiceKey + "&articleName=" + URLEncoder.encode(articleName, StandardCharsets.UTF_8),
                HttpMethod.GET,
                request,
                String.class
        ).getBody();
        log.info("공공데이터포털 api 정보 수신 완료");

        ObjectMapper xmlMapper = new XmlMapper();
        Response response = xmlMapper.readValue(xmlContent, Response.class);
        log.info("xml parsing 완료");


        return response.getBody().getItems();
    }
    public List<Design> selectRegisteredTrademark(List<Item> apiResult){
        return apiResult.stream()
                .filter(item -> item.getApplicationStatus().equals("등록"))
                .map(item -> new Design(
                        item.getApplicationNumber(),
                        item.getImagePathLarge(),
                        item.getApplicantName(),
                        item.getDesignNumber(),
                        item.getRegistrationNumber(),
                        item.getApplicationStatus(),
                        item.getDesignMainClassification()
                ))
                .collect(Collectors.toList());
    }
    @Transactional
    public Long saveDesign(Design design){
        Design saveDesign = designRepository.save(design);
        return saveDesign.getId();
    }
}
