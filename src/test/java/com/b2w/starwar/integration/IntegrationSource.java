package com.b2w.starwar.integration;

import com.b2w.starwar.StarwarApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Classe criada por mpinho
 * E-mail: marcosjava2008@gmail.com
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = StarwarApplication.class
)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
public class IntegrationSource {
}