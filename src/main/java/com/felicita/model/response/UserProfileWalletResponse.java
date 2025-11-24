package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileWalletResponse {
    // User info
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;

    // Wallet info
    private Long walletId;
    private String walletNumber;
    private Double amount;

    // Wallet status info
    private Long walletStatusId;
    private String walletStatusName;
    private String walletStatusDescription;

    // Dates
    private String walletCreatedAt;
    private String walletUpdatedAt;
}
