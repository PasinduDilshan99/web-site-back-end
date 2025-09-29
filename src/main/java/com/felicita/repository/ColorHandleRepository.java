package com.felicita.repository;

import com.felicita.model.response.PageColorsResponse;

public interface ColorHandleRepository {
    PageColorsResponse getHomePageColors(String pageName);
}
