package Farm.Team4.findOwn.controller;

import Farm.Team4.findOwn.domain.Member;
import Farm.Team4.findOwn.dto.FindPasswordRequestInfo;
import Farm.Team4.findOwn.dto.MemberRequestInfo;
import Farm.Team4.findOwn.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/member/save")
    public String saveMember(@RequestBody MemberRequestInfo request){
        if (memberService.duplicatedMember(request.getId()))
            throw new IllegalArgumentException("이미 아이디가 존재합니다.");
        Member saveMember = memberService.saveMember(request);
        return saveMember.getId();
    }
    @PostMapping("member/find")
    public Member findMyPassword(@RequestBody FindPasswordRequestInfo request){
        if (!memberService.existedMember(request.getEmail()))
            throw new IllegalArgumentException("해당 이메일로 저장된 회원이 없습니다.");
        /*
            별도의 인증 과정이 필요할 듯
         */
        Member member = memberService.changePassword(request);
        return member;
    }
}
