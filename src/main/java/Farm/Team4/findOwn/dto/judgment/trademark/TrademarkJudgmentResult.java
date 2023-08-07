package Farm.Team4.findOwn.dto.judgment.trademark;

import lombok.Data;

@Data
public class TrademarkJudgmentResult {
    private Long applicationNumber; // 출원번호
    private String applicant; // 출원인
    private int similarity; // 유사도
    private String memberId;
}
