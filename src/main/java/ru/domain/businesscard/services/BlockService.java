package ru.domain.businesscard.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.domain.businesscard.domain.Block;
import ru.domain.businesscard.domain.Card;
import ru.domain.businesscard.domain.User;
import ru.domain.businesscard.dto.BlockDto;
import ru.domain.businesscard.dto.ContentDto;
import ru.domain.businesscard.repos.BlockRepo;
import ru.domain.businesscard.repos.CardRepo;
import ru.domain.businesscard.requests.CreateBlockRequest;

import java.util.stream.Collectors;

@Service
public class BlockService {
    private final BlockRepo blockRepo;
    private final CardRepo cardRepo;

    @Autowired
    public BlockService(BlockRepo blockRepo, CardRepo cardRepo) {
        this.blockRepo = blockRepo;
        this.cardRepo = cardRepo;
    }

    public void save(Block block) {
        blockRepo.save(block);
    }

    @Transactional
    public Block create(CreateBlockRequest request, User user) {
        Card userCard = cardRepo.findCardByUserAndId(user, request.getCardId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Данного резюме у пользователя не существует"));

        var block = Block.builder()
                .card(userCard)
                .name(request.getName())
                .description(request.getDescription())
                .build();

        save(block);

        return block;
    }

    public BlockDto getBlock(User user, Long blockId) {
        Block userBlock = blockRepo.findById(blockId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Данного блока не существует"));

        Card userCard = cardRepo.findCardByUserAndId(user, userBlock.getCard().getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Данного резюме у пользователя не существует"));

        return BlockDto.builder()
                .name(userBlock.getName())
                .description(userBlock.getDescription())
                .contentDtos(userBlock.getContents().stream()
                        .map(content -> ContentDto.builder()
                                .name(content.getName())
                                .description(content.getDescription())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
