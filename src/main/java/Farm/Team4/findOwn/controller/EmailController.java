package Farm.Team4.findOwn.controller;

import Farm.Team4.findOwn.domain.Member;
import Farm.Team4.findOwn.dto.VerifyMemberRequestInfo;
import Farm.Team4.findOwn.service.EmailService;
import Farm.Team4.findOwn.service.MemberService;
import Farm.Team4.findOwn.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    private final RedisService redisService;
    private final MemberService memberService;
    @GetMapping("/mail/send/code")
    //localhost:8080/mail/send/code?email=jwl5015@naver.com
    public String mailConfirm(@RequestParam String email) throws Exception{
        String code = emailService.sendMessage(email);
        return code;
    }
    @PostMapping("/mail/verify/code")
    public String verifyCode(@RequestBody VerifyMemberRequestInfo request) {
        String redisEmailAddress = redisService.getData(request.getCode());
        log.info("email address from redis: " + redisEmailAddress);
        if (redisEmailAddress.equals(request.getEmail())){
            redisService.deleteData(request.getCode());
            log.info("요청 이메일 일치 = 인증 성공");
            return "verify email success";
        }
        log.info("요청 이메일 불일치 = 인증 실패");
        redisService.deleteData(request.getCode());
        return "verify email fail";
    }

}
