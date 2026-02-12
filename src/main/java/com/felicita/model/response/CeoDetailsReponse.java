package com.felicita.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CeoDetailsReponse {
    private Long userId;
    private String name;
    private String title;
    private List<String> speech;
    private String imageUrl;
}
