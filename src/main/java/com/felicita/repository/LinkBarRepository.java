package com.felicita.repository;

import com.felicita.model.response.LinkBarResponse;

import java.util.List;

public interface LinkBarRepository {

    List<LinkBarResponse> getAllLinkBarItems();

}
