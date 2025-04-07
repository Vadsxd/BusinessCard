package ru.domain.businesscard.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на создание контента в блоке")
public class CreateContentRequest {
    @Schema(description = "Идентификатор блока, в котором будет данный контент")
    @NotBlank(message = "Идентификатор не может отсутствовать")
    private Long blockId;

    @Schema(description = "Название/Заголовок")
    @Size(min = 5, message = "Название должно содержать минимум 5 символов")
    @NotBlank(message = "Название не может быть пустыми")
    private String name;

    @Schema(description = "Описание")
    @Size(min = 10, message = "Описание должно быть минимум 10 символов")
    @NotBlank(message = "Описание не может отсутствовать")
    private String description;
}
