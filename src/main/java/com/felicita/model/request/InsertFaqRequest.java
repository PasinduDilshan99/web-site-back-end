package com.felicita.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsertFaqRequest {
    private String ticketNumber;
    private String name;
    private String email;
    private String category;
    private String subject;
    private String message;
    private String ipAddress;
    private long userId;
}
