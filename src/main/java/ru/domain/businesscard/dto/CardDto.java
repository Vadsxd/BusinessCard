package ru.domain.businesscard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import ru.domain.businesscard.domain.Card;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@Schema(description = "ДТО резюме пользователя")
public class CardDto {
    @Schema(description = "Резюме пользователя")
    private List<Card> cards;
}
