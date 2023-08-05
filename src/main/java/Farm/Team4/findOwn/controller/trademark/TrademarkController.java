package Farm.Team4.findOwn.controller.trademark;
import Farm.Team4.findOwn.dto.trademark.parsing.Response;
import Farm.Team4.findOwn.dto.trademark.parsing.body.Item;
import Farm.Team4.findOwn.service.trademark.TrademarkService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrademarkController {
    private final TrademarkService trademarkService;
    @GetMapping("/find/trademark")
    public List<Item> findTrademark(@RequestParam String searchString) throws JsonProcessingException {
         return trademarkService.findTrademark(searchString);
    }
}
