package com.felicita.service;

import com.felicita.model.response.CommonResponse;
import com.felicita.model.response.ContactMethodResponse;
import java.util.List;

public interface ContactUsService {

    CommonResponse<List<ContactMethodResponse>> getContactMethods();

}
