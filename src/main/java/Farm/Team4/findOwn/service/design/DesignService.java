package Farm.Team4.findOwn.service.design;

import Farm.Team4.findOwn.domain.design.Design;
import Farm.Team4.findOwn.repository.design.DesignRepository;
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

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@RequiredArgsConstructor
public class DesignService {
    private final DesignRepository designRepository;
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${DESIGN_SERVICE_KEY}")
    private String dataServiceKey;
    @Value("${SEARCH_DESIGN_URL}")
    private String searchDesignUrl;
    public ResponseEntity<String> findDesign(String articleName) {
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

        System.out.println(dataServiceKey);
        return restTemplate.exchange(
                searchDesignUrl +"?serviceKey=" + "B7y6mnXwYiqT8J24zK9IAyF7EGDjLktqZMwR%2F0h8yg6Tq6Scw%2Fj5naZkQIInZ6b2iM%2B6Ndvk%2FFrG4qoTK3gK1Q%3D%3D" + "&articleName=" + URLEncoder.encode(articleName, StandardCharsets.UTF_8),
                HttpMethod.GET,
                request,
                String.class
        );
    }
    @Transactional
    public Long saveDesign(Design design){
        Design saveDesign = designRepository.save(design);
        return saveDesign.getId();
    }
}
