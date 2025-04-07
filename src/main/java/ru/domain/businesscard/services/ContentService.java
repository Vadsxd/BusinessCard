package ru.domain.businesscard.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.domain.businesscard.domain.Block;
import ru.domain.businesscard.domain.Card;
import ru.domain.businesscard.domain.Content;
import ru.domain.businesscard.domain.User;
import ru.domain.businesscard.dto.ContentDto;
import ru.domain.businesscard.repos.BlockRepo;
import ru.domain.businesscard.repos.CardRepo;
import ru.domain.businesscard.repos.ContentRepo;
import ru.domain.businesscard.requests.CreateContentRequest;

@Service
public class ContentService {
    private final ContentRepo contentRepo;
    private final BlockRepo blockRepo;
    private final CardRepo cardRepo;

    @Autowired
    public ContentService(ContentRepo contentRepo, BlockRepo blockRepo, CardRepo cardRepo) {
        this.contentRepo = contentRepo;
        this.blockRepo = blockRepo;
        this.cardRepo = cardRepo;
    }

    public void save(Content content) {
        contentRepo.save(content);
    }

    @Transactional
    public Content create(CreateContentRequest request, User user) {
        Card userCard = cardRepo.findCardByUser(user).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Данного резюме не существует"));

        Block block = blockRepo.findBlockByIdAndCard(request.getBlockId(), userCard).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Данного блока не существует"));

        var content = Content.builder()
                .name(request.getName())
                .description(request.getDescription())
                .block(block)
                .build();

        save(content);

        return content;
    }

    public ContentDto getContent(User user, Long contentId) {
        Card userCard = cardRepo.findCardByUser(user).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Данного резюме не существует"));

        Block userBlock = blockRepo.findBlockByCard(userCard).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Данного блока не существует"));

        Content userContent = contentRepo.findContentByBlockAndId(userBlock, contentId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Данного контента не существует"));


        return ContentDto.builder()
                .name(userContent.getName())
                .description(userContent.getDescription())
                .build();
    }
}
