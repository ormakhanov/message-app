package kz.askar.receivemessageapplication.controllers;

import kz.askar.receivemessageapplication.models.MessagesEntity;
import kz.askar.receivemessageapplication.repositories.MessagesRepository;
import kz.askar.receivemessageapplication.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private MessagesRepository messagesRepository;
    private MessageService messageService;

    public MessageController(MessagesRepository messagesRepository, MessageService messageService) {
        this.messagesRepository = messagesRepository;
        this.messageService = messageService;
    }

    @PostMapping(consumes = "application/xml")
    public ResponseEntity<Void> receiveMessage(@RequestBody MessagesEntity message) {
        messagesRepository.save(message);
        messageService.sendMessage(message);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<MessagesEntity>> getMessages(@RequestParam(required = false) String user) {
        return ResponseEntity.ok(messageService.getMessages(user));
    }
}
