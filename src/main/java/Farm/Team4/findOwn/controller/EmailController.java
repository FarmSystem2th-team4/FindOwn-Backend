package Farm.Team4.findOwn.controller;

import Farm.Team4.findOwn.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    @GetMapping("/mail/send/{userEmail}")
    public String mailConfirm(@PathVariable String userEmail) throws Exception{
        String code = emailService.sendMessage(userEmail);
        return code;
    }

}
