package ru.domain.businesscard.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.domain.businesscard.domain.Card;
import ru.domain.businesscard.domain.User;

import java.util.List;

@Repository
public interface CardRepo extends JpaRepository<Card, Long> {
    List<Card> findAllByUser(User user);
}
