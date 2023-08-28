package Farm.Team4.findOwn.service.member.right;

import Farm.Team4.findOwn.domain.design.Design;
import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.domain.member.MemberOwnDesign;
import Farm.Team4.findOwn.domain.member.MemberOwnTrademark;
import Farm.Team4.findOwn.domain.trademark.Trademark;
import Farm.Team4.findOwn.dto.design.UpdateDesignRequest;
import Farm.Team4.findOwn.dto.member.right.design.request.DeleteMemberOwnDesign;
import Farm.Team4.findOwn.dto.member.right.design.request.SaveMemberDesignRequestInfo;
import Farm.Team4.findOwn.dto.member.right.design.request.UpdateMemberDesignRequest;
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
        return memberService.findById(memberId).getOwnDesigns();
    }
    public List<MemberOwnTrademark> findMemberOwnTrademarkList(String memberId){
        return memberService.findById(memberId).getOwnTrademarks();
    }
    public MemberOwnDesign findMyOwnDesign(Long ownDesignId){
        return memberOwnDesignRepository.findById(ownDesignId)
                .orElseThrow(() -> new IllegalArgumentException("회원 소유 디자인권에 대한 아이디 값이 올바르지 않습니다."));
    }
    public MemberOwnTrademark findMyOwnTrademark(Long ownTrademarkId){
        return memberOwnTrademarkRepository.findById(ownTrademarkId)
                .orElseThrow(()-> new IllegalArgumentException("회원 소유 상표권에 대한 아이디 값이 올바르지 않습니다."));
    }
    @Transactional
    public MemberOwnDesign updateMemberOwnDesign(UpdateMemberDesignRequest request){
        MemberOwnDesign findMemberOwnDesign = findMyOwnDesign(request.getMemberOwnDesignId());
        log.info("사용자 소유 디자인권 조회 성공");

        if (!findMemberOwnDesign.getMember().getId().equals(request.getMemberId()))
            throw new IllegalArgumentException("소유자와 수정자가 일치하지 않습니다. 동일한 사람만 수정이 가능합니다.");

        Design updatedDesign = designService.updateDesign(request);
        log.info("회원 소유 디자인권 내용 수정 완료");
        return findMemberOwnDesign.updateDesign(updatedDesign);
    }
    @Transactional
    public String deleteMemberOwnDesign(DeleteMemberOwnDesign request){
        MemberOwnDesign findMemberOwnDesign = memberService.findById(request.getMemberId()).getOwnDesigns().stream()
                .filter(memberOwnDesign -> memberOwnDesign.getId().equals(request.getMemberOwnDesignId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 소유 디자인입니다."));
        memberOwnDesignRepository.delete(findMemberOwnDesign);
        log.info("회원 소유 디자인권 삭제 완료");
        return "ok";
    }
}
