package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.LinkBarResponse;
import java.util.List;

public interface LinkBarService {

    CommonResponse<List<LinkBarResponse>> getAllLinkBarData();

    CommonResponse<List<LinkBarResponse>> getActiveLinkBarData();

}
