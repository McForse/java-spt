package ru.mirea.pr21.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class EmailServiceImpl implements EmailService {
    public JavaMailSender emailSender;

    private final String email;

    @Autowired
    public EmailServiceImpl(@Qualifier("email") String email) {
        this.email = email;
    }

    @Async
    public void sendEmail(Gameable item) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Item insert in DB");
        message.setText("Item {id = " + item.getId() + ", name = " + item.getName() + "} inserted");
        this.emailSender.send(message);
        log.info("Email send");
    }
}
