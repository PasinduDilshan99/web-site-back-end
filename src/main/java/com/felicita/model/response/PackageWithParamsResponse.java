package com.felicita.model.response;

import com.felicita.model.dto.PackageResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageWithParamsResponse {
    private int packageCount;
    private List<PackageResponseDto> packageResponseDtos;
}
