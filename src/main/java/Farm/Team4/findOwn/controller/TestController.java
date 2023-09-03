package Farm.Team4.findOwn.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("/test")
    public void makeSession(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        log.info(session.getId());
    }
}
