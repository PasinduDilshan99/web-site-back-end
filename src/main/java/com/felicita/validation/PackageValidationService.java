package com.felicita.validation;

import com.felicita.model.request.PackageTerminateRequest;

public interface PackageValidationService {
    void validateTerminatePackageRequest(PackageTerminateRequest packageTerminateRequest);
}
