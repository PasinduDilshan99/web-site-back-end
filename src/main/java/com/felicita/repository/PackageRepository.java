package com.felicita.repository;

import com.felicita.model.response.PackageDetailsResponse;

import java.util.List;

public interface PackageRepository {
    List<PackageDetailsResponse> getAllPackages();
}
