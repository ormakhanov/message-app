package kz.askar.receivemessageapplication.repositories;

import kz.askar.receivemessageapplication.models.MessagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<MessagesEntity, Long> {
    List<MessagesEntity> findBySender(String user);

    List<MessagesEntity> findTop10ByOrderByDatetimeDesc();
}
