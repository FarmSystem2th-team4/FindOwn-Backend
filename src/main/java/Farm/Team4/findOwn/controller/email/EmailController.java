package Farm.Team4.findOwn.controller.email;

import Farm.Team4.findOwn.dto.member.information.VerifyMemberRequest;
import Farm.Team4.findOwn.service.email.EmailService;
import Farm.Team4.findOwn.service.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmailController {
    private final EmailService emailService;
    private final RedisService redisService;
    @GetMapping("/mail")
    public String mailConfirm(@RequestParam String email) throws Exception{
        log.info("email: " + email);
        return emailService.sendMessage(email);
    }
    @PostMapping("/mail")
    public String verifyCode(@RequestBody VerifyMemberRequest request) {
        String redisEmailAddress = redisService.getCode(request.getCode());
        log.info("email address from redis: " + redisEmailAddress);
        if (redisEmailAddress.equals(request.getEmail())){
            redisService.deleteCode(request.getCode());
            log.info("요청 이메일 일치 = 인증 성공");
            return "email verification success";
        }
        log.info("요청 이메일 불일치 = 인증 실패");
        redisService.deleteCode(request.getCode());
        return "email verification fail";
    }

}
