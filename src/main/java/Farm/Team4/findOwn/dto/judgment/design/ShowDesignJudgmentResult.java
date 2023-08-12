package Farm.Team4.findOwn.dto.judgment.design;

import Farm.Team4.findOwn.domain.design.Design;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShowDesignJudgmentResult {
    private int similarity;
    private Design design;
}
