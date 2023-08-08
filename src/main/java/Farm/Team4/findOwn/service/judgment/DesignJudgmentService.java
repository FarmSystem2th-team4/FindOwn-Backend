package Farm.Team4.findOwn.service.judgment;

import Farm.Team4.findOwn.domain.design.Design;
import Farm.Team4.findOwn.domain.judgment.DesignJudgment;
import Farm.Team4.findOwn.domain.judgment.TrademarkJudgment;
import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.dto.judgment.design.DesignJudgmentResult;
import Farm.Team4.findOwn.dto.judgment.design.ShowDesignJudgmentResult;
import Farm.Team4.findOwn.repository.judgment.DesignJudgmentRepository;
import Farm.Team4.findOwn.repository.judgment.TrademarkJudgmentRepository;
import Farm.Team4.findOwn.service.design.DesignService;
import Farm.Team4.findOwn.service.member.information.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class DesignJudgmentService {
    private final MemberService memberService;
    private final DesignService designService;
    private final DesignJudgmentRepository designJudgmentRepository;
    public ShowDesignJudgmentResult showDesignJudgmentResult(DesignJudgmentResult result) throws IOException {
        Member findMember = findMember(result.getMemberId());
        Design findDesign = findAndSelectOne(result.getApplicant());

        saveTrademarkJudgment(result.getSimilarity(), findMember, findDesign);
        log.info("디자인 판단 결과 저장 완료");

        return new ShowDesignJudgmentResult(result.getSimilarity(), findDesign);
    }
    @Transactional
    public DesignJudgment saveTrademarkJudgment(int similarity, Member member, Design design){
        return designJudgmentRepository.save(new DesignJudgment(similarity, member, design));
    }
    private Member findMember(String memberId){
        return memberService.findById(memberId);
    }
    private Design findAndSelectOne(String articleName) throws IOException {
        return designService.findAndSelectOne(articleName);
    }

}
