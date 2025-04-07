package ru.domain.businesscard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@Schema(description = "ДТО конкретного резюме")
public class CardDto {
    @Schema(description = "Заголовок в резюме")
    private String name;

    @Schema(description = "Список ДТО блоков в резюме")
    private List<BlockDto> blockDtos;
}
