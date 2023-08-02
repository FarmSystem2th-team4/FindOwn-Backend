package Farm.Team4.findOwn.controller.member;

import Farm.Team4.findOwn.dto.member.right.design.SaveMemberDesignRequestInfo;
import Farm.Team4.findOwn.dto.member.right.trademark.SaveMemberTrademarkRequestInfo;
import Farm.Team4.findOwn.service.member.right.MemberRightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberRightController {
    private final MemberRightService memberRightService;
    @PostMapping("/member/own-design/save")
    public String saveMemberDesign(@RequestBody SaveMemberDesignRequestInfo request){
        memberRightService.saveMemberOwnDesign(request);
        return "ok";
    }
    @PostMapping("/member/own-trademark/save")
    public String saveMemberTrademark(@RequestBody SaveMemberTrademarkRequestInfo request){
        memberRightService.saveMemberOwnTrademark(request);
        return "ok";
    }

}
