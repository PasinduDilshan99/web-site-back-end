package com.felicita.model.response;

import com.felicita.model.dto.ActivityWishResponseDto;
import com.felicita.model.dto.DestinationWishResponseDto;
import com.felicita.model.dto.PackageWishResponseDto;
import com.felicita.model.dto.TourWishResponsesDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishListResponse {
    private List<PackageWishResponseDto> packageWishResponseDtos;
    private List<TourWishResponsesDto> tourWishResponsesDtos;
    private List<DestinationWishResponseDto> destinationWishResponseDtos;
    private List<ActivityWishResponseDto> activityWishResponseDtos;
}
