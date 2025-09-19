package com.felicita.repository;

import com.felicita.model.response.NavBarResponse;

import java.util.List;

public interface NavBarRepository {
    List<NavBarResponse> getAllNavBarItems();
}
