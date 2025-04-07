package ru.domain.businesscard.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.domain.businesscard.domain.Block;

@Repository
public interface BlockRepo extends JpaRepository<Block, Long> {
}
