package kz.askar.messagesend.controllers;

import kz.askar.messagesend.models.ResponsesEntity;
import kz.askar.messagesend.repositories.ResponsesRepository;
import kz.askar.messagesend.services.MessageSendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/responses")
public class ResponseController {
    private ResponsesRepository responsesRepository;
    private MessageSendService messageSendService;

    public ResponseController(ResponsesRepository responsesRepository, MessageSendService messageSendService) {
        this.responsesRepository = responsesRepository;
        this.messageSendService = messageSendService;
    }

    @GetMapping
    public ResponseEntity<List<ResponsesEntity>> getResponses(@RequestParam(required = false) String user) {
        return ResponseEntity.ok(messageSendService.getResponses(user));
    }
}
