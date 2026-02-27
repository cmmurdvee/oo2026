package ee.murdvee.proovikontrolltoo.dto;

import ee.murdvee.proovikontrolltoo.entity.FilmType;

public record FilmSaveDto(
        String title,
        FilmType type
) {
}
