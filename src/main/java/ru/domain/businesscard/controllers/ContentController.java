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
import ru.domain.businesscard.domain.Content;
import ru.domain.businesscard.domain.User;
import ru.domain.businesscard.dto.ContentDto;
import ru.domain.businesscard.requests.CreateContentRequest;
import ru.domain.businesscard.services.ContentService;
import ru.domain.businesscard.services.UserService;

@RequestMapping("/api/card/block/content")
@RestController
@PreAuthorize("hasAuthority('USER')")
public class ContentController {
    private final UserService userService;
    private final ContentService contentService;

    @Autowired
    public ContentController(UserService userService, ContentService contentService) {
        this.userService = userService;
        this.contentService = contentService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "Ошибка авторизации")
    })
    @Operation(summary = "Создать контент")
    @PostMapping("/create")
    public ResponseEntity<Content> createContent(@RequestBody @Valid CreateContentRequest request) {
        User currentUser = userService.getCurrentUser();
        return ResponseEntity.ok(contentService.create(request, currentUser));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "Ошибка авторизации")
    })
    @Operation(summary = "Получить контент из блока")
    @GetMapping("/{id}")
    public ResponseEntity<ContentDto> getContent(@PathVariable Long id) {
        User currentUser = userService.getCurrentUser();

        return ResponseEntity.ok(contentService.getContent(currentUser, id));
    }
}
