package com.shop.userservice.user.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private static int authNumber;
    private final Map<String, Integer> checkEmailMap = new HashMap<>();

    public void createKey() {
        authNumber = (int) (Math.random() * 899999) + 100000;
    }

    public MimeMessage createEmailForm(String email) {
        createKey(); // 인증 코드 생성

        // 메일 형식
        String setFrom = "alex.yb.dev@gmail.com";
        String toMail = email;
        String title = "프리오더 이메일 인증 번호";
        String content = "";
        content += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
        content += "<h1>" + authNumber + "</h1>";
        content += "<h3>" + "감사합니다." + "</h3>";

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom); // 발신자 이메일
            helper.setTo(toMail); // 수신자 이메일
            helper.setSubject(title); // 이메일 제목
            helper.setText(content, true); // 이메일 내용

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }

    public int sendEmail(String toEmail) {
        MimeMessage emailForm = createEmailForm(toEmail);
        javaMailSender.send(emailForm);

        checkEmailMap.put(toEmail, authNumber); // 수신자 이메일과 인증번호 매핑

        return authNumber;
    }

    // 인증 번호 일치 여부
    public boolean isAuthNumber(String email, int inputNumber) {
        Integer storeNumber = checkEmailMap.get(email);
        if (storeNumber == null) {
            return false;
        }

        return storeNumber == inputNumber;
    }
}
