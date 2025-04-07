package ru.domain.businesscard.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.domain.businesscard.domain.Card;
import ru.domain.businesscard.domain.User;
import ru.domain.businesscard.dto.CardDto;
import ru.domain.businesscard.requests.CreateCardRequest;
import ru.domain.businesscard.services.CardService;
import ru.domain.businesscard.services.UserService;

@RequestMapping("/api/card")
@RestController
@PreAuthorize("hasAuthority('USER')")
public class CardController {
    private final CardService cardService;
    private final UserService userService;

    @Autowired
    public CardController(CardService cardService, UserService userService) {
        this.cardService = cardService;
        this.userService = userService;
    }

    @Operation(summary = "Создать резюме")
    @PostMapping("/create")
    public ResponseEntity<Card> createCard(@RequestBody @Valid CreateCardRequest request) {
        User currentUser = userService.getCurrentUser();
        return ResponseEntity.ok(cardService.create(request, currentUser));
    }

    @Operation(summary = "Получить резюме пользователя")
    @GetMapping("/")
    public ResponseEntity<CardDto> getCards() {
        User currentUser = userService.getCurrentUser();

        return ResponseEntity.ok(cardService.getCards(currentUser));
    }
}
