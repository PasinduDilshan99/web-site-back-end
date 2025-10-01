package com.felicita.repository;

import com.felicita.model.dto.PackageResponseDto;

import java.util.List;

public interface PackageRepository {
    List<PackageResponseDto> getAllPackages();
}
