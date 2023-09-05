package Farm.Team4.findOwn.controller.member;

import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.dto.member.ConvertMember;
import Farm.Team4.findOwn.dto.member.information.ChangeEmailRequestInfo;
import Farm.Team4.findOwn.dto.member.information.ChangePasswordRequestInfo;
import Farm.Team4.findOwn.dto.member.information.DeleteMemberRequestInfo;
import Farm.Team4.findOwn.dto.member.information.SaveMemberRequestInfo;
import Farm.Team4.findOwn.dto.member.login.MemberLoginRequest;
import Farm.Team4.findOwn.service.member.information.MemberUtils;
import Farm.Team4.findOwn.service.member.information.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;
    private final MemberUtils memberUtils;

    /**
     * 회원 CRUD
     */
    @PostMapping("/member")
    public String saveMember(@Valid @RequestBody SaveMemberRequestInfo request){
        Member saveMember = memberService.saveMember(request);
        return saveMember.getId();
    }
    @GetMapping("/member/find/id")
    public String findMyId(@RequestParam String email){
        return memberService.findByEmail(email).getId();
    }
    @GetMapping("/member/find/password")
    public String findMyPassword(@RequestParam String id){
        return memberService.changeTempPassword(id, memberUtils.createTempPassword());
    }
    @PatchMapping("/member/change/password")
    public String changeMyPassword(@RequestBody ChangePasswordRequestInfo request){
        String originPassword = memberService.findById(request.getId()).getPassword();
        log.info("회원 조회, 회원의 기존 비밀번호 조회 성공");

        return memberService.changeNewPassword(request.getId(), originPassword, request.getNewPassword()); // 비밀번호 변경 지점
    }
    @PatchMapping("/member/change/email")
    public Member changeMyEmail(@RequestBody ChangeEmailRequestInfo request){
        return memberService.changeEmail(request.getOldEmail(), request.getNewEmail());
    }
    @DeleteMapping("/member")
    public String deleteMember(@RequestBody DeleteMemberRequestInfo request){
        Member findMember = memberService.findById(request.getId());
        memberService.deleteMember(findMember,request);
        return "delete complete";
    }
    /**
     *  회원 로그인, 로그아웃
     */
    @PostMapping("/login")
    public ConvertMember login(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                               @RequestBody MemberLoginRequest loginRequest){
        memberService.makeSessionId(httpRequest, httpResponse, loginRequest); // 회원에 대한 session id 생성 및 저장
        HttpSession session = httpRequest.getSession(false);
        if (session == null) return null;

        Member loginMember = (Member) session.getAttribute("loginMember");
        return new ConvertMember(loginMember.getId(), loginMember.getName(), loginMember.getMembershipDate());
    }
    @GetMapping("/logout")
    public void logout(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        memberService.logout(httpRequest, httpResponse);
    }

}
