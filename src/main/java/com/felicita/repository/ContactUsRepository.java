package com.felicita.repository;

import com.felicita.model.response.ContactMethodResponse;
import java.util.List;

public interface ContactUsRepository {

    List<ContactMethodResponse> getContactMethods();

}
