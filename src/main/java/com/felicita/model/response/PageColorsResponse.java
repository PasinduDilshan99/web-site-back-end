package com.felicita.model.response;

import com.felicita.model.dto.PageComponentColorsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageColorsResponse {
    private Long pageId;
    private String pageName;
    private String pageSlug;
    private List<PageComponentColorsDto> components;
}
