package kz.askar.receivemessageapplication.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "messages")
public class MessagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "sender")
    private String sender;
    @Column(name = "message_content")
    private String messageContent;
    @Column(name = "datetime", nullable = false)
    private LocalDateTime datetime = LocalDateTime.now();
}
