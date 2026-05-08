package com.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BibleDto {

    @JsonProperty("bible_id")
    private Long bibleId;

    private String language;
    private String version;
}
