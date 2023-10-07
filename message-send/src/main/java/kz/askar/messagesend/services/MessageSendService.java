package kz.askar.messagesend.services;

import kz.askar.messagesend.models.MessagesEntity;
import kz.askar.messagesend.models.ResponsesEntity;
import kz.askar.messagesend.repositories.ResponsesRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageSendService {

    @Value("${email}")
    private String emailAddress;
    @Value("${spring.mail.username}")
    private String fromEmail;
    private ResponsesRepository responsesRepository;

    private JavaMailSender mailSender;

    public MessageSendService(ResponsesRepository responsesRepository, JavaMailSender mailSender) {
        this.responsesRepository = responsesRepository;
        this.mailSender = mailSender;
    }

    @KafkaListener(topics = "message.send", groupId = "myGroup")
    public void receiveMessage(MessagesEntity message) {
        String emailSendingStatus = sendEmail(message);
        saveResponse(message, emailSendingStatus);
    }

    private String sendEmail(MessagesEntity message) {
        try {
            MimeMessage mailMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
            mailMessage.setContent(message.getMessageContent(), "text/html; charset=utf-8");
            helper.setSubject("Message-app");
            helper.setTo(emailAddress);
            helper.setFrom(fromEmail);
            mailSender.send(mailMessage);
            return "success";
        } catch (MailException e) {
            Pattern pattern = Pattern.compile("(4\\d\\d|5\\d\\d)-\\d\\.\\d\\.\\d");
            Matcher matcher = pattern.matcher(e.getMessage());

            if (matcher.find()) {
                return matcher.group();
            }
            return "failed code not found";
        } catch (MessagingException e) {
            Pattern pattern = Pattern.compile("(4\\d\\d|5\\d\\d)-\\d\\.\\d\\.\\d");
            Matcher matcher = pattern.matcher(e.getMessage());

            if (matcher.find()) {
                return matcher.group();
            }
            return "failed code not found";
        }
    }

    private void saveResponse(MessagesEntity message, String emailSendingStatus) {
        ResponsesEntity response = new ResponsesEntity();
        response.setMessagesEntity(message);
        response.setResponseCode(emailSendingStatus);

        responsesRepository.save(response);
    }

    public List<ResponsesEntity> getResponses(String user) {
        List<ResponsesEntity> responses = responsesRepository.findByMessagesEntity_Sender(user);
        return !responses.isEmpty() ? responses : responsesRepository.findAll();
    }
}
