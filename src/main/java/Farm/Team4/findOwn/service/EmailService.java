package Farm.Team4.findOwn.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final String authCode = createCode();
    @Value("${MAIL_USERNAME}")
    private String id;
    public String sendMessage(String dest) throws Exception{
        try{
            javaMailSender.send(createMessage(dest));
        }catch (MailException em){
            em.printStackTrace();
            throw new IllegalArgumentException("메일 전송 실패");
        }
        return authCode;
    }
    public MimeMessage createMessage(String dest) throws MessagingException, UnsupportedEncodingException{
        log.info("받을 사람: " + dest);
        log.info("인증코드: " + authCode);
        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, dest);

        message.setSubject(dest + "님의 FindOwn 회원 가입 인증 코드입니다."); // 회원 가입 메일 제목
        // 메일 내용 기입
        String content = "";
        content += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
        content += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 회원가입 화면에서 입력해주세요.</p>";
        content += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
        content += authCode;
        content += "</td></tr></tbody></table></div>";
        // 메일 내용 기입 완료

        message.setText(content, "utf-8", "html"); //내용, charset타입, subtype
        message.setFrom(new InternetAddress(id,"FindOwn")); //보내는 사람의 메일 주소, 보내는 사람 이름

        log.info("message 생성 성공");

        return message;
    }
    private String createCode(){
        String code = "";
        Random generateNum = new Random();
        for(int i=0; i<6; i++){
            code += (char)(generateNum.nextInt(10)+48);
        }
        log.info("인증 코드 생성 성공 code = " + code);
        return code;
    }
}
