package kz.askar.receivemessageapplication.services;

import kz.askar.receivemessageapplication.models.MessagesEntity;
import kz.askar.receivemessageapplication.repositories.MessagesRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private KafkaTemplate<String, MessagesEntity> kafkaTemplate;
    private MessagesRepository messagesRepository;

    public MessageService(KafkaTemplate<String, MessagesEntity> kafkaTemplate, MessagesRepository messagesRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.messagesRepository = messagesRepository;
    }

    public void sendMessage(MessagesEntity message) {
        kafkaTemplate.send("message.send", message);
    }

    public List<MessagesEntity> getMessages(String user) {
        List<MessagesEntity> userMessages = messagesRepository.findBySender(user);
        return !userMessages.isEmpty() ? userMessages : messagesRepository.findTop10ByOrderByDatetimeDesc();
    }
}
