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
    public Long saveMemberOwnDesign(SaveMemberDesignRequestInfo request){
        Design ownDesign = request.changeToDesign();
        designService.saveDesign(ownDesign);
        log.info("디자인권 정보 저장 완료");

        Member findMember = memberService.findById(request.getMemberId());
        if (!memberService.existedMember(findMember.getEmail()))
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        log.info("회원 찾기 완료");

        MemberOwnDesign savedInfo = memberOwnDesignRepository.save(new MemberOwnDesign(ownDesign, findMember));
        log.info("멤버 소유 디자인권 저장 완료");
        return savedInfo.getId();
    }
    public Long saveMemberOwnTrademark(SaveMemberTrademarkRequestInfo request){
        Trademark ownTrademark = request.changeToTrademark();
        trademarkService.saveTrademark(ownTrademark);
        log.info("상표권 정보 저장 완료");

        Member findMember = memberService.findById(request.getMemberId());
        if (!memberService.existedMember(findMember.getEmail()))
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        log.info("회원 찾기 완료");

        MemberOwnTrademark savedInfo = memberOwnTrademarkRepository.save(new MemberOwnTrademark(ownTrademark, findMember));
        log.info("멤버 소유 상표권 저장완료");
        return savedInfo.getId();
    }
    public List<MemberOwnDesign> memberOwnDesignList(String memberId){
        List<MemberOwnDesign> memberOwnDesignByMemberId = memberOwnDesignRepository.findAllByMember_Id(memberId);
        return memberOwnDesignByMemberId;
    }
}
