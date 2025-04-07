package ru.domain.businesscard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Schema(description = "ДТО статистики резюме")
public class StatisticsDto {
    @Schema(description = "Все просмотры резюме")
    private Long views;

    @Schema(description = "Недавние просмотры резюме")
    private Long recentViews;

    @Schema(description = "Название резюме")
    private Long invites;
}
