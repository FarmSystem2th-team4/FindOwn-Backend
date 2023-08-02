package Farm.Team4.findOwn.controller.member;

import Farm.Team4.findOwn.domain.member.MemberOwnDesign;
import Farm.Team4.findOwn.domain.member.MemberOwnTrademark;
import Farm.Team4.findOwn.dto.member.right.design.SaveMemberDesignRequestInfo;
import Farm.Team4.findOwn.dto.member.right.trademark.SaveMemberTrademarkRequestInfo;
import Farm.Team4.findOwn.service.member.right.MemberRightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberRightController {
    private final MemberRightService memberRightService;
    @PostMapping("/member/own-design/save")
    public String saveMemberDesign(@RequestBody SaveMemberDesignRequestInfo request){
        Long savedMemberOwnDesignId = memberRightService.saveMemberOwnDesign(request);
        log.info("회원 소유 디자인권 정보 저장 완료, 해당 아이디: " + savedMemberOwnDesignId.toString());

        return "ok";
    }
    @PostMapping("/member/own-trademark/save")
    public String saveMemberTrademark(@RequestBody SaveMemberTrademarkRequestInfo request){
        Long savedMemberOwnTrademarkId = memberRightService.saveMemberOwnTrademark(request);
        log.info("회원 소유 상표권 정보 저장 완료, 해당 아이디: " + savedMemberOwnTrademarkId.toString());
        return "ok";
    }
    @GetMapping("/member/own-design")
    public List<MemberOwnDesign> memberOwnDesignList(@RequestParam String memberId){
        return memberRightService.findMemberOwnDesignList(memberId);
    }
    @GetMapping("/member/own-trademark")
    public List<MemberOwnTrademark> memberOwnTrademarkList(@RequestParam String memberId){
        return memberRightService.findMemberOwnTrademarkList(memberId);
    }

}
