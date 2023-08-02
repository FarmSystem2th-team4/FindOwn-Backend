package Farm.Team4.findOwn.controller.member;

import Farm.Team4.findOwn.dto.member.right.SaveMemberDesignRequestInfo;
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
    @PostMapping("/member/own-right/save")
    public String saveMemberDesign(@RequestBody SaveMemberDesignRequestInfo request){
        memberRightService.saveMemberOwnDesign(request);
        return "ok";
    }

}
