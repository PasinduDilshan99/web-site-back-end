package com.felicita.model.response;

import com.felicita.model.dto.ActivityResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityWithParamsResponse {
    private int activityCount;
    private List<ActivityResponseDto> activityResponseDtos;
}
