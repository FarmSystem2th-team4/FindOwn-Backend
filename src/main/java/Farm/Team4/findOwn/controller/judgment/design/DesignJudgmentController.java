package Farm.Team4.findOwn.controller.judgment.design;

import Farm.Team4.findOwn.dto.judgment.design.request.SaveDesignJudgmentResultRequest;
import Farm.Team4.findOwn.dto.judgment.design.response.SaveDesignJudgmentResultResponse;
import Farm.Team4.findOwn.service.judgment.DesignJudgmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class DesignJudgmentController {
    private final DesignJudgmentService designJudgmentService;
    @PostMapping("/member/design-judgment")
    public SaveDesignJudgmentResultResponse showDesignJudgmentResult(@RequestBody SaveDesignJudgmentResultRequest result) throws IOException {
        return designJudgmentService.showDesignJudgmentResult(result);
    }
}
