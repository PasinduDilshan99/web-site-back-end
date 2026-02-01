package com.felicita.repository;

import com.felicita.model.response.OurStoryAndValuesResponse;
import java.util.List;

public interface OurStoryRepository {

    List<OurStoryAndValuesResponse.Timeline> getOurStoryDetails();

    List<OurStoryAndValuesResponse.CoreValue> getOurCoreValues();

}
