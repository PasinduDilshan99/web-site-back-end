package com.felicita.model.response;

import com.felicita.model.dto.FooterOtherDto;
import com.felicita.model.dto.FooterSectionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FooterResponse {
    private List<FooterSectionDto> sections;
    private List<SocialMediaResponse> socialMedia;
    private List<FooterOtherDto> others;
}
