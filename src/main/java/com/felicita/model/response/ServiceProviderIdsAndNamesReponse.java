package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceProviderIdsAndNamesReponse {
    private Long serviceProviderId;
    private String serviceProviderName;
}
