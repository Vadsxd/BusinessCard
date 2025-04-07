package ru.domain.businesscard.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.domain.businesscard.domain.Block;
import ru.domain.businesscard.domain.Content;

import java.util.Optional;

@Repository
public interface ContentRepo extends JpaRepository<Content, Long> {
    Optional<Content> findContentByBlockAndId(Block block, Long id);
}
