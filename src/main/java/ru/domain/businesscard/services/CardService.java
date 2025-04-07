package ru.domain.businesscard.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.domain.businesscard.domain.Card;
import ru.domain.businesscard.domain.User;
import ru.domain.businesscard.dto.CardDto;
import ru.domain.businesscard.repos.CardRepo;
import ru.domain.businesscard.requests.CreateCardRequest;

import java.util.List;

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
                .build();

        cardRepo.save(card);

        return card;
    }

    public CardDto getCards(User user) {
        List<Card> userCards = cardRepo.findAllByUser(user);

        return CardDto.builder()
                .cards(userCards)
                .build();
    }
}
