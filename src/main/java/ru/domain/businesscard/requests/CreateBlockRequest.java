package ru.domain.businesscard.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на создание резюме")
public class CreateBlockRequest {
    @Schema(description = "Идентификатор резюме, в котором будет данный блок")
    @NotBlank(message = "Идентификатор не может отсутствовать")
    private Long cardId;

    @Schema(description = "Название блока")
    @Size(min = 5, message = "Название должно содержать минимум 5 символов")
    @NotBlank(message = "Название не может быть пустыми")
    private String name;

    @Schema(description = "Описание блока")
    @Size(min = 10, message = "Описание должно быть минимум 10 символов")
    @NotBlank(message = "Описание не может отсутствовать")
    private String description;
}
