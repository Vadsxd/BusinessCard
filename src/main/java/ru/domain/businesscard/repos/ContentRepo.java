package ru.domain.businesscard.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.domain.businesscard.domain.Content;

@Repository
public interface ContentRepo extends JpaRepository<Content, Long> {
}
