package Farm.Team4.findOwn.controller.judgment.design;

import Farm.Team4.findOwn.dto.judgment.design.DesignJudgmentResult;
import Farm.Team4.findOwn.dto.judgment.design.ShowDesignJudgmentResult;
import Farm.Team4.findOwn.dto.judgment.trademark.ShowTrademarkJudgmentResult;
import Farm.Team4.findOwn.dto.judgment.trademark.TrademarkJudgmentResult;
import Farm.Team4.findOwn.service.judgment.DesignJudgmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class DesignJudgmentController {
    private final DesignJudgmentService designJudgmentService;
    @PostMapping("/show/judgment/design")
    public ShowDesignJudgmentResult showDesignJudgmentResult(@RequestBody DesignJudgmentResult result) throws IOException {
        return designJudgmentService.showDesignJudgmentResult(result);
    }
}
