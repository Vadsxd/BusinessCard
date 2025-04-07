package ru.domain.businesscard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Schema(description = "ДТО определенной информации в блоке")
public class ContentDto {
    @Schema(description = "Заголовок/Название")
    private String name;

    @Schema(description = "Описание")
    private String description;
}
