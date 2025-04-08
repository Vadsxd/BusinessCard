package ru.domain.businesscard.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.domain.businesscard.domain.Card;
import ru.domain.businesscard.domain.User;
import ru.domain.businesscard.dto.CardDto;
import ru.domain.businesscard.dto.ProfileDto;
import ru.domain.businesscard.requests.CreateCardRequest;
import ru.domain.businesscard.services.CardService;
import ru.domain.businesscard.services.UserService;

import java.util.List;

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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "Ошибка авторизации")
    })
    @Operation(summary = "Создать резюме")
    @PostMapping("/create")
    public ResponseEntity<Card> createCard(@RequestBody @Valid CreateCardRequest request) {
        User currentUser = userService.getCurrentUser();

        return ResponseEntity.ok(cardService.create(request, currentUser));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "Ошибка авторизации")
    })
    @Operation(summary = "Получить все резюме пользователя")
    @GetMapping("/")
    public ResponseEntity<List<ProfileDto>> getCards() {
        User currentUser = userService.getCurrentUser();

        return ResponseEntity.ok(cardService.getCards(currentUser));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "Ошибка авторизации")
    })
    @Operation(summary = "Получить резюме пользователя")
    @GetMapping("/{id}")
    public ResponseEntity<CardDto> getCard(@PathVariable Long id) {
        User currentUser = userService.getCurrentUser();

        return ResponseEntity.ok(cardService.getCard(currentUser, id));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "Ошибка авторизации")
    })
    @Operation(summary = "Отсортировать блоки в резюме по названию")
    @GetMapping("/{id}/sort")
    public ResponseEntity<CardDto> sortBlocks(@PathVariable Long id) {
        User currentUser = userService.getCurrentUser();

        return ResponseEntity.ok(cardService.sortBlocks(currentUser, id));
    }
}
