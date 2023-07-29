package Farm.Team4.findOwn.controller;

import Farm.Team4.findOwn.domain.Member;
import Farm.Team4.findOwn.service.EmailService;
import Farm.Team4.findOwn.service.MemberService;
import Farm.Team4.findOwn.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    private final RedisService redisService;
    private final MemberService memberService;
    @GetMapping("/mail/send/code/{userEmail}")
    public String mailConfirm(@PathVariable String userEmail) throws Exception{
        String code = emailService.sendMessage(userEmail);
        return code;
    }
    @GetMapping("/mail/verify")
    // localhost:8080/mail/verify?code=숫자
    public String verifyCode(@RequestParam String code) {
        String emailAddress = redisService.getData(code);
        log.info("redis에서 가져온 요청한 이메일 주소:" + emailAddress);
        if ("jwl5015@naver.com".equals(emailAddress))
            return "OK";
        //Member findMember = memberService.findByEmail(redisService.getData(code));
        /*
        if (findMember == null)
            throw new IllegalArgumentException("잘못된 요청입니다.");

         */
        redisService.deleteData(code);
        return "tlqkf..";
    }

}
