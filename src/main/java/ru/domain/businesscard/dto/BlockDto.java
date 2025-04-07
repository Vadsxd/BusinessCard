package ru.domain.businesscard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@Schema(description = "ДТО блока с информацией в резюме")
public class BlockDto {
    @Schema(description = "Название блока")
    private String name;

    @Schema(description = "Описание блока")
    private String description;

    @Schema(description = "Список ДТО определенной информации в блоке")
    private List<ContentDto> contentDtos;
}
