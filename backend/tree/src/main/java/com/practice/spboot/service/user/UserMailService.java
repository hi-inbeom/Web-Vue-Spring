package com.practice.spboot.service.user;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMailService {
	
    private final JavaMailSender mailSender;

    public String sendVerificationEmail(String toEmail) {
        String code = generateVerificationCode();
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("회원가입 인증 코드");
            helper.setText("인증 코드: " + code, true);  // HTML 형식 가능

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("이메일 전송 실패");
        }
        return code;
    }

    private String generateVerificationCode() {
    	int randomNum = (int) (Math.random() * 10000);
		String verifyCode = String.format("%04d", randomNum);
        return verifyCode;
    }

}
