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
import ru.domain.businesscard.domain.Block;
import ru.domain.businesscard.domain.User;
import ru.domain.businesscard.dto.BlockDto;
import ru.domain.businesscard.requests.CreateBlockRequest;
import ru.domain.businesscard.services.BlockService;
import ru.domain.businesscard.services.UserService;

@RequestMapping("/api/card/block")
@RestController
@PreAuthorize("hasAuthority('USER')")
public class BlockController {
    private final UserService userService;
    private final BlockService blockService;

    @Autowired
    public BlockController(UserService userService, BlockService blockService) {
        this.userService = userService;
        this.blockService = blockService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "Ошибка авторизации")
    })
    @Operation(summary = "Создать блок в резюме")
    @PostMapping("/create")
    public ResponseEntity<Block> createBlock(@RequestBody @Valid CreateBlockRequest request) {
        User currentUser = userService.getCurrentUser();

        return ResponseEntity.ok(blockService.create(request, currentUser));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "Ошибка авторизации")
    })
    @Operation(summary = "Получить блок")
    @GetMapping("/{id}")
    public ResponseEntity<BlockDto> getBlock(@PathVariable Long id) {
        User currentUser = userService.getCurrentUser();

        return ResponseEntity.ok(blockService.getBlock(currentUser, id));
    }
}
