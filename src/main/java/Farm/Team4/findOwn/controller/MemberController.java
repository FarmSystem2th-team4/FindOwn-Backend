package Farm.Team4.findOwn.controller;

import Farm.Team4.findOwn.domain.Member;
import Farm.Team4.findOwn.dto.FindPasswordRequestInfo;
import Farm.Team4.findOwn.dto.SaveMemberRequestInfo;
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
    public String saveMember(@RequestBody SaveMemberRequestInfo request){
        if (memberService.duplicatedMember(request.getId()))
            throw new IllegalArgumentException("이미 아이디가 존재합니다.");
        Member saveMember = memberService.saveMember(request);
        return saveMember.getId();
    }
    @PostMapping("member/find")
    public Member findMyPassword(@RequestBody FindPasswordRequestInfo request){
        if (!memberService.existedMember(request.getEmail()))
            throw new IllegalArgumentException("해당 이메일로 저장된 회원이 없습니다.");

        String originPassword = memberService.findByEmail(request.getEmail()).getPassword();

        if (originPassword != request.getOldPassword())
            throw new IllegalArgumentException("기존 비밀번호가 일치하지 않습니다.");

        memberService.changePassword(request); // 비밀번호 변경 지점
        return memberService.findByEmail(request.getEmail());
    }
}
