package kz.askar.messagesend.repositories;

import kz.askar.messagesend.models.ResponsesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponsesRepository extends JpaRepository<ResponsesEntity, Long> {
    List<ResponsesEntity> findByMessagesEntity_Sender(String user);
}
