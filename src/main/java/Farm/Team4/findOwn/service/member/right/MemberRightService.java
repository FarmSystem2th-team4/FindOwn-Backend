package Farm.Team4.findOwn.service.member.right;

import Farm.Team4.findOwn.domain.design.Design;
import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.domain.member.MemberOwnDesign;
import Farm.Team4.findOwn.domain.member.MemberOwnTrademark;
import Farm.Team4.findOwn.domain.trademark.Trademark;
import Farm.Team4.findOwn.dto.member.right.design.SaveMemberDesignRequestInfo;
import Farm.Team4.findOwn.dto.member.right.trademark.SaveMemberTrademarkRequestInfo;
import Farm.Team4.findOwn.repository.member.MemberOwnDesignRepository;
import Farm.Team4.findOwn.repository.member.MemberOwnTrademarkRepository;
import Farm.Team4.findOwn.service.design.DesignService;
import Farm.Team4.findOwn.service.member.information.MemberService;
import Farm.Team4.findOwn.service.trademark.TrademarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberRightService {
    private final MemberService memberService;
    private final DesignService designService;
    private final TrademarkService trademarkService;
    private final MemberOwnDesignRepository memberOwnDesignRepository;
    private final MemberOwnTrademarkRepository memberOwnTrademarkRepository;
    @Transactional
    public Long saveMemberOwnDesign(SaveMemberDesignRequestInfo request){
        Design ownDesign = request.changeToDesign();
        designService.saveDesign(ownDesign);
        log.info("디자인권 정보 저장 완료");

        Member findMember = memberService.findById(request.getMemberId());
        log.info("회원 찾기 완료");

        MemberOwnDesign savedInfo = memberOwnDesignRepository.save(new MemberOwnDesign(ownDesign, findMember));
        log.info("멤버 소유 디자인권 저장 완료");
        return savedInfo.getId();
    }
    @Transactional
    public Long saveMemberOwnTrademark(SaveMemberTrademarkRequestInfo request){
        Trademark ownTrademark = request.changeToTrademark();
        trademarkService.saveTrademark(ownTrademark);
        log.info("상표권 정보 저장 완료");

        Member findMember = memberService.findById(request.getMemberId());
        log.info("회원 찾기 완료");

        MemberOwnTrademark savedInfo = memberOwnTrademarkRepository.save(new MemberOwnTrademark(ownTrademark, findMember));
        log.info("멤버 소유 상표권 저장완료");
        return savedInfo.getId();
    }
    public List<MemberOwnDesign> findMemberOwnDesignList(String memberId){
        return memberOwnDesignRepository.findMemberOwnDesignByMember_Id(memberId);
    }
    public List<MemberOwnTrademark> findMemberOwnTrademarkList(String memberId){
        return memberOwnTrademarkRepository.findMemberOwnTrademarksByMember_Id(memberId);
    }
    public MemberOwnDesign findMyOwnDesign(String ownDesignId){
        return memberOwnDesignRepository.findById(ownDesignId)
                .orElseThrow(() -> new IllegalArgumentException("회원 소유 디자인권에 대한 아이디 값이 올바르지 않습니다."));
    }
}
