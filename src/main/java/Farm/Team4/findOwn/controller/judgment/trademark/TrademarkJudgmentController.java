package Farm.Team4.findOwn.controller.judgment.trademark;

import Farm.Team4.findOwn.dto.judgment.trademark.response.SaveTrademarkJudgmentResponse;
import Farm.Team4.findOwn.dto.judgment.trademark.request.SaveTrademarkJudgmentRequest;
import Farm.Team4.findOwn.service.judgment.TrademarkJudgmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class TrademarkJudgmentController {
    private final TrademarkJudgmentService trademarkJudgmentService;
    @PostMapping("/show/judgment/trademark")
    public SaveTrademarkJudgmentResponse showJudgmentResult(@RequestBody SaveTrademarkJudgmentRequest result) throws JsonProcessingException {
        return trademarkJudgmentService.showTrademarkJudgment(result);
    }
}
