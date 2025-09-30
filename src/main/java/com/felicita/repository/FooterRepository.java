package com.felicita.repository;


import com.felicita.model.dto.FooterOtherDto;
import com.felicita.model.dto.FooterSectionDto;

import java.util.List;

public interface FooterRepository {
    List<FooterSectionDto> getAllFooterData();
    List<FooterOtherDto> getAllFooterOthersData();
}
