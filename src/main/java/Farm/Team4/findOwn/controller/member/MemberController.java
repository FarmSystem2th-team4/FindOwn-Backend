package Farm.Team4.findOwn.controller.member;

import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.dto.member.information.ChangeEmailRequestInfo;
import Farm.Team4.findOwn.dto.member.information.ChangePasswordRequestInfo;
import Farm.Team4.findOwn.dto.member.information.DeleteMemberRequestInfo;
import Farm.Team4.findOwn.dto.member.information.SaveMemberRequestInfo;
import Farm.Team4.findOwn.service.member.information.MemberUtils;
import Farm.Team4.findOwn.service.member.information.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberUtils memberUtils;
    @PostMapping("/member/save")
    public String saveMember(@Valid @RequestBody SaveMemberRequestInfo request){
        Member saveMember = memberService.saveMember(request);
        return saveMember.getId();
    }
    @GetMapping("/member/find")
    public Member findMyPassword(@RequestParam String email){
        if(!memberService.existedMemberByEmail(email))
            throw new IllegalArgumentException("존재하지 않는 회원입니다");

        return memberService.changePassword(email, memberUtils.createTempPassword());
    }
    @PostMapping("/member/change/password")
    public Member changeMyPassword(@RequestBody ChangePasswordRequestInfo request){
        // 회원 중복 검사
        if (!memberService.existedMemberByEmail(request.getEmail()))
            throw new IllegalArgumentException("해당 이메일로 저장된 회원이 없습니다.");

        String originPassword = memberService.findByEmail(request.getEmail()).getPassword();

        //기존 비밀번호 입력, 기존 비밀번호 일치 여부 확인
        if (!originPassword.equals(request.getOldPassword()))
            throw new IllegalArgumentException("기존 비밀번호가 일치하지 않습니다.");

        memberService.changePassword(request.getEmail(), request.getNewPassword()); // 비밀번호 변경 지점
        return memberService.findByEmail(request.getEmail());
    }
    @PostMapping("/member/change/email")
    public Member changeMyEmail(@RequestBody ChangeEmailRequestInfo request){
        return memberService.changeEmail(request.getOldEmail(), request.getNewEmail());
    }
    @DeleteMapping("/member/delete")
    public String deleteMember(@RequestBody DeleteMemberRequestInfo request){
        Member findMember = memberService.findById(request.getId());
        memberService.deleteMember(findMember);
        return "delete complete";
    }

}
