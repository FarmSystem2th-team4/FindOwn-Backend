package Farm.Team4.findOwn.service.trademark;

import Farm.Team4.findOwn.domain.trademark.Trademark;
import Farm.Team4.findOwn.repository.trademark.TrademarkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrademarkService {
    private final TrademarkRepository trademarkRepository;
    public Long saveTrademark(Trademark trademark){
        Trademark savedTrademark = trademarkRepository.save(trademark);
        return savedTrademark.getId();
    }
}
