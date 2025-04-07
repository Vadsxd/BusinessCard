package ru.domain.businesscard.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.domain.businesscard.domain.Card;
import ru.domain.businesscard.domain.User;
import ru.domain.businesscard.dto.BlockDto;
import ru.domain.businesscard.dto.CardDto;
import ru.domain.businesscard.dto.ContentDto;
import ru.domain.businesscard.dto.ProfileDto;
import ru.domain.businesscard.dto.StatisticsDto;
import ru.domain.businesscard.repos.CardRepo;
import ru.domain.businesscard.requests.CreateCardRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {
    private final CardRepo cardRepo;

    @Autowired
    public CardService(CardRepo cardRepo) {
        this.cardRepo = cardRepo;
    }

    @Transactional
    public Card create(CreateCardRequest request, User user) {
        var card = Card.builder()
                .user(user)
                .name(request.getName())
                .description(request.getDescription())
                .build();
        cardRepo.save(card);

        return card;
    }

    public CardDto getCard(User user, Long cardId) {
        Card userCard = cardRepo.findCardByUserAndId(user, cardId);

        return CardDto.builder()
                .name(userCard.getName())
                .blockDtos(userCard.getBlocks().stream()
                        .map(block -> BlockDto.builder()
                                .name(block.getName())
                                .description(block.getDescription())
                                .contentDtos(block.getContents().stream()
                                        .map(content -> ContentDto.builder()
                                                .name(content.getName())
                                                .description(content.getDescription())
                                                .build())
                                        .collect(Collectors.toList()))
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public List<ProfileDto> getCards(User user) {

        return cardRepo.findAllByUser(user).stream()
                .map(card -> ProfileDto.builder()
                        .name(card.getName())
                        .description(card.getDescription())
                        .statisticsDto(StatisticsDto.builder()
                                .invites(card.getInvites())
                                .recentViews(card.getRecentViews())
                                .views(card.getViews())
                                .build()
                        )
                        .build())
                .collect(Collectors.toList());
    }
}
