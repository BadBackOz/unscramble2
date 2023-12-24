package com.ohz.unscramble.requestvalidator;

import com.ohz.unscramble.exception.UnscrambleException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
class GetWordsRequestValidatorTest {

    @Autowired
    GetWordsRequestValidator getWordsRequestValidator;

    @Test
    void givenInvalidRequest_WhenValidateUnscrambleWordsRequest_ThenThrowException() {
        UnscrambleException exceptionThrown = Assertions.assertThrows(UnscrambleException.class, () -> getWordsRequestValidator.validateUnscrambleWordsRequest("214"));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exceptionThrown.getHttpStatus());
        Assertions.assertEquals("Invalid Request: Only Alphabetic characters and '*' allowed.", exceptionThrown.getMessage());
    }

    @Test
    void givenValidRequest_WhenValidateUnscrambleWordsRequest_ThenDoNotThrowException() {
        Assertions.assertDoesNotThrow(() -> getWordsRequestValidator.validateUnscrambleWordsRequest("*ett"));
    }

}
