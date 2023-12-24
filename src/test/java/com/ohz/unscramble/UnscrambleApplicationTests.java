package com.ohz.unscramble;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.mockito.Mockito.mockStatic;

@SpringBootTest
class UnscrambleApplicationTests {

    @Test
    void contextLoads() {
        try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {
            mocked.when(() -> {SpringApplication.run(UnscrambleApplication.class);})
                    .thenReturn(Mockito.mock(ConfigurableApplicationContext.class));

            UnscrambleApplication.main(new String[]{});

            mocked.verify(() -> {SpringApplication.run(UnscrambleApplication.class);});
        }
    }
}
