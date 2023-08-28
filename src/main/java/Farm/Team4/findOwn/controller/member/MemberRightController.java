package Farm.Team4.findOwn.controller.member;

import Farm.Team4.findOwn.domain.member.MemberOwnDesign;
import Farm.Team4.findOwn.domain.member.MemberOwnTrademark;
import Farm.Team4.findOwn.dto.member.right.design.request.DeleteMemberOwnDesign;
import Farm.Team4.findOwn.dto.member.right.design.request.SaveMemberDesignRequestInfo;
import Farm.Team4.findOwn.dto.member.right.design.request.UpdateMemberDesignRequest;
import Farm.Team4.findOwn.dto.member.right.design.response.UpdateMemberOwnDesignResponse;
import Farm.Team4.findOwn.dto.member.right.trademark.SaveMemberTrademarkRequestInfo;
import Farm.Team4.findOwn.service.member.information.MemberService;
import Farm.Team4.findOwn.service.member.right.MemberRightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberRightController {
    private final MemberRightService memberRightService;
    @PostMapping("/own-design")
    public String saveMemberDesign(@RequestBody SaveMemberDesignRequestInfo request){
        Long saveMemberOwnDesignId = memberRightService.saveMemberOwnDesign(request);
        log.info("회원 소유 디자인권 정보 저장 완료, 해당 아이디: " + saveMemberOwnDesignId.toString());
        return "ok";
    }
    @PostMapping("/own-trademark")
    public String saveMemberTrademark(@RequestBody SaveMemberTrademarkRequestInfo request){
        Long savedMemberOwnTrademarkId = memberRightService.saveMemberOwnTrademark(request);
        log.info("회원 소유 상표권 정보 저장 완료, 해당 아이디: " + savedMemberOwnTrademarkId.toString());
        return "ok";
    }
    @GetMapping("/own-designs")
    public List<MemberOwnDesign> memberOwnDesignList(@RequestParam String memberId){
        return memberRightService.findMemberOwnDesignList(memberId);
    }
    @GetMapping("/own-trademarks")
    public List<MemberOwnTrademark> memberOwnTrademarkList(@RequestParam String memberId){
        return memberRightService.findMemberOwnTrademarkList(memberId);
    }
    @GetMapping("/own-design")
    public MemberOwnDesign findMemberOwnDesign(@RequestParam Long ownDesignId){
        return memberRightService.findMyOwnDesign(ownDesignId);
    }
    @GetMapping("/own-trademark")
    public MemberOwnTrademark findMemberOwnTrademark(@RequestParam Long ownTrademarkId){
        return memberRightService.findMyOwnTrademark(ownTrademarkId);
    }
    @PatchMapping("/own-design")
    public UpdateMemberOwnDesignResponse updateMemberOwnDesign(@RequestBody UpdateMemberDesignRequest request){
        MemberOwnDesign memberOwnDesign = memberRightService.updateMemberOwnDesign(request);
        log.info("회원 소유 디자인권 수정 완료");
        return new UpdateMemberOwnDesignResponse(memberOwnDesign);
    }
    @DeleteMapping("/own-design")
    public String deleteMemberOwnDesign(@RequestBody DeleteMemberOwnDesign request){
        return memberRightService.deleteMemberOwnDesign(request);
    }

}
