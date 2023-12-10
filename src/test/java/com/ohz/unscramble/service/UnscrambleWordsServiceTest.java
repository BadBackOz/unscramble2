package com.ohz.unscramble.service;

import com.ohz.unscramble.model.UnscrambledWordsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.Set;

public class UnscrambleWordsServiceTest {

    @InjectMocks
    UnscrambleWordsService service;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenCharacters_WhenIsWordFromCharactersTrue_ThenReturnTrue(){
        boolean isWord = Boolean.TRUE.equals(ReflectionTestUtils.invokeMethod(service, "isWordFromCharacters", "weed", "azedbwe"));

        Assertions.assertTrue(isWord);
    }

    @Test
    void givenCharacters_WhenUnscrambleWords_ThenReturnUnscrambledWords(){
        UnscrambledWordsResponse response = service.unscrambleWords("azedbwe");

        Assertions.assertNotNull(response);
    }

}
