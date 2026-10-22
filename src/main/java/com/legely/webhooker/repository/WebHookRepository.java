package com.legely.webhooker.repository;


import com.legely.webhooker.entity.WebHookEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WebHookRepository extends JpaRepository<WebHookEvent, Long> {
    Optional<WebHookEvent> findByMessage(String message);
    Optional<WebHookEvent> findBySender(String sender);




}
