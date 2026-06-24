package com.jjang051.security.member.service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final StringRedisTemplate stringRedisTemplate;
    private static final SecureRandom secureRandom = new SecureRandom();
    //    public void sendMail(String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("jjang051@naver.com");
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        mailSender.send(message);
//    }
    public void sendMail(String to, String subject, String text) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            message.setFrom("kevin8899@naver.com");
            message.setRecipients(Message.RecipientType.TO, to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendMailHtml(String to, String subject, String text) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("kevin8899@naver.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text,true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendAuthCode(String email) {
        //String code =  UUID.randomUUID().toString().replace("-","").substring(0, 10);
        String code = String.format("%06d",secureRandom.nextInt(1000000));
        System.out.println(code);
        stringRedisTemplate.opsForValue().set("mail:auth:"+email, code, Duration.ofMinutes(3));
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
            helper.setFrom("kevin8899@naver.com");
            helper.setTo(email);
            helper.setSubject("회원가입 인증번호");
            helper.setText("""
                    <div>
                        <h2>회원가입 인증반호</h2>
                        <p>아래 인증 번호를 입려하세요.</p>
                        <h2>%s</h2>
                        <p>이 인증번호는 3분 동안만 유효합니다.</p>
                    </div>
                    """.formatted(code),true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendResetPasswordCode(String email) {
        //String code =  UUID.randomUUID().toString().replace("-","").substring(0, 10);
        String code = String.format("%06d",secureRandom.nextInt(1000000));
        System.out.println(code);
        stringRedisTemplate.opsForValue().set("mail:auth:"+email, code, Duration.ofMinutes(3));
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
            helper.setFrom("kevin8899@naver.com");
            helper.setTo(email);
            helper.setSubject("비밀번호 변경 인증번호");
            helper.setText("""
                    <div style="text-align:center;">
                        <h2>비밀번호 변경 인증번호</h2>
                        <p>아래 인증 번호를 입려하세요.</p>
                        <h2>%s</h2>
                        <p>이 인증번호는 3분 동안만 유효합니다.</p>
                    </div>
                    """.formatted(code),true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean verifiedAuthCode(String email, String code) {
        String key =  "mail:auth:"+email;
        String redisCode = stringRedisTemplate.opsForValue().get(key);
        if(redisCode==null) {
            return false;
        }
        boolean result = redisCode.equals(code);
        if(result){
            stringRedisTemplate.delete(key);
        }
        return result;
    }

    public boolean isVerified(String email) {
        return Boolean.TRUE.equals(
                stringRedisTemplate.hasKey("mail:verified:" + email)
        );
    }
}