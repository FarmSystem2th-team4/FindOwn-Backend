package Farm.Team4.findOwn.service.judgment;

import Farm.Team4.findOwn.domain.judgment.TrademarkJudgment;
import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.domain.trademark.Trademark;
import Farm.Team4.findOwn.dto.judgment.trademark.response.SaveTrademarkJudgmentResponse;
import Farm.Team4.findOwn.dto.judgment.trademark.request.SaveTrademarkJudgmentRequest;
import Farm.Team4.findOwn.repository.judgment.TrademarkJudgmentRepository;
import Farm.Team4.findOwn.service.member.information.MemberService;
import Farm.Team4.findOwn.service.trademark.TrademarkService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class TrademarkJudgmentService {
    private final MemberService memberService;
    private final TrademarkService trademarkService;
    private final TrademarkJudgmentRepository trademarkJudgmentRepository;
    public SaveTrademarkJudgmentResponse showTrademarkJudgment(SaveTrademarkJudgmentRequest result) throws JsonProcessingException {
        Member findMember = memberService.findById(result.getMemberId());
        Trademark findTrademark = findAndSelectOne(result.getApplicant());
        saveTrademarkJudgment(result.getSimilarity(), findTrademark, findMember);

        return new SaveTrademarkJudgmentResponse(result.getSimilarity(), findTrademark);
    }
    private Trademark findAndSelectOne(String applicantName) throws JsonProcessingException {
        return trademarkService.findAndSelectOne(applicantName);
    }
    @Transactional
    public TrademarkJudgment saveTrademarkJudgment(int similarity, Trademark trademark, Member member){
        return trademarkJudgmentRepository.save(new TrademarkJudgment(similarity, member, trademark));
    }
}
