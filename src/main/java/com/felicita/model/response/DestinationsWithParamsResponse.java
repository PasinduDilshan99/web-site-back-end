package com.felicita.model.response;

import com.felicita.model.dto.DestinationResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DestinationsWithParamsResponse {
    private int destinationCount;
    private List<DestinationResponseDto> destinationResponseDtos;
}
