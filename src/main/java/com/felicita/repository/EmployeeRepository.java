package com.felicita.repository;

import com.felicita.model.response.EmployeeWithSocialMediaResponse;

import java.util.List;

public interface EmployeeRepository {
    List<EmployeeWithSocialMediaResponse> getEmployeeWithSocailMedia();
}
