package ru.domain.businesscard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Schema(description = "ДТО всех резюме пользователя")
public class ProfileDto {
    @Schema(description = "Название резюме")
    private String name;

    @Schema(description = "Описание резюме")
    private String description;

    @Schema(description = "ДТО статистики резюме")
    private StatisticsDto statisticsDto;
}
