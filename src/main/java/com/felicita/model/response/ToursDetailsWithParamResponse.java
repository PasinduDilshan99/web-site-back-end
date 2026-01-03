package com.felicita.model.response;

import com.felicita.model.dto.TourResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToursDetailsWithParamResponse {
    int totalTours;
    List<TourResponseDto> tourResponseDtoList;
}
