package com.felicita.validation;

import com.felicita.model.request.PackageInsertRequest;
import com.felicita.model.request.PackageTerminateRequest;
import com.felicita.model.request.PackageUpdateRequest;

public interface PackageValidationService {
    void validateTerminatePackageRequest(PackageTerminateRequest packageTerminateRequest);

    void validatePackageInsertRequest(PackageInsertRequest packageInsertRequest);

    void validatePackageUpdateRequest(PackageUpdateRequest packageUpdateRequest);
}
