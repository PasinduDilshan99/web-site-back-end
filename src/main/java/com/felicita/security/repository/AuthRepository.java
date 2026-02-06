package com.felicita.security.repository;

import com.felicita.model.dto.SecretQuesionsAnswersDto;
import com.felicita.model.request.SecretQuestionsUpdateRequest;
import com.felicita.model.response.SecretQuestionResponse;
import com.felicita.security.model.RegisterUser;
import com.felicita.security.model.User;
import java.util.List;
import java.util.Optional;

public interface AuthRepository {

    void signup(RegisterUser registerUser);

    Optional<User> getUserByUsername(String username);

    List<SecretQuesionsAnswersDto> getSecretQuestionsAndAnswersByUsername(String username);

    void resetPassword(String username, String encode);

    void changePassword(Long userId, String encode, String newPassword);

    void addSecretQuestions(Long userId, List<SecretQuestionsUpdateRequest.SecretQuestion> addQuestions);

    void updateSecretQuestions(Long userId, List<SecretQuestionsUpdateRequest.SecretQuestion> updateQuestions);

    void removeSecretQuestions(Long userId, List<Long> removeQuestionsIds);

    List<SecretQuestionResponse> getActiveScretQuestions();

    String getPasswordByUsername(String username);
}
