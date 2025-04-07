package ru.domain.businesscard.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.domain.businesscard.domain.Block;
import ru.domain.businesscard.domain.Card;

import java.util.Optional;

@Repository
public interface BlockRepo extends JpaRepository<Block, Long> {
    Optional<Block> findBlockByIdAndCard(Long blockId, Card card);
    Optional<Block> findBlockByCard(Card card);
}
