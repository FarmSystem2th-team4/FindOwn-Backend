package Farm.Team4.findOwn.service.judgment;

import Farm.Team4.findOwn.domain.judgment.TrademarkJudgment;
import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.domain.trademark.Trademark;
import Farm.Team4.findOwn.dto.judgment.trademark.ShowTrademarkJudgmentResult;
import Farm.Team4.findOwn.dto.judgment.trademark.TrademarkJudgmentResult;
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
    public ShowTrademarkJudgmentResult showTrademarkJudgment(TrademarkJudgmentResult result) throws JsonProcessingException {
        Trademark findTrademark = findAndSelectOne(result.getApplicant());
        Member findMember = findMember(result.getMemberId());
        saveTrademarkJudgment(result.getSimilarity(), findTrademark, findMember);

        return new ShowTrademarkJudgmentResult(result.getSimilarity(), findTrademark);
    }
    @Transactional
    public TrademarkJudgment saveTrademarkJudgment(int similarity, Trademark trademark, Member member){
        return trademarkJudgmentRepository.save(new TrademarkJudgment(similarity, member, trademark));
    }
    private Trademark findAndSelectOne(String applicantName) throws JsonProcessingException {
        return trademarkService.findAndSelectOne(applicantName);
    }
    private Member findMember(String memberId){
        return memberService.findById(memberId);
    }
}
