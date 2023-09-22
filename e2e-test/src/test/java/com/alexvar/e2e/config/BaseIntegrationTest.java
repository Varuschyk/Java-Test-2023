package com.alexvar.e2e.config;

import com.alexvar.testOverviewApplication.TestOverviewApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = TestOverviewApplication.class)
@Import(TestConfig.class)
//TODO: Make profiles
public class BaseIntegrationTest extends AbstractTestNGSpringContextTests {
}
