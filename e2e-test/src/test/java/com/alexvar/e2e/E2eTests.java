package com.alexvar.e2e;

import static org.testng.Assert.assertEquals;

import com.alexvar.e2e.config.BaseIntegrationTest;
import com.alexvar.e2e.test.data.OrderTestScenarios;
import com.alexvar.e2e.test.model.TestData;
import jakarta.annotation.Nonnull;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class E2eTests extends BaseIntegrationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  /**
   * Here specified hardcoded {@link OrderTestScenarios} as example of generic e2e test case, that
   * tests any endpoints. Initially data for scenarios is getting from json files and provides to the
   * scenario as expected result. So, if we replace {@link OrderTestScenarios} to util class that provides all
   * scenarios to our test, this test case will test any endpoint that you want.
   * Sure, better scenarios make as json too, because application will growth and code rows will too.
   * See also other example of reading data to test endpoints
   *
   * @see OrderTestScenarios
   * @see com.alexvar.e2e.test.util.JsonDeserializer deserializer for any entities from json files for tests.
   * @see com.alexvar.e2e.test.util.FileReader to reading all files in tree. (any path on your machine will read files).
   * @see com.alexvar.e2e.test.util.OrderTestContext to containg corresponding json files and providing to scenarios.
   * @return scenarios data to test.
   */
  @DataProvider(name = "testData")
  public Object[] testData() {
    return Stream.of(
        OrderTestScenarios.values()).map(OrderTestScenarios::getTestData)
        .toArray();
  }

  @Test(dataProvider = "testData")
  public void testEndpoints(@Nonnull final TestData testData) {
    final var response =
        restTemplate.exchange(
            testData.getUri(),
            testData.getHttpMethod(),
            testData.getHttpEntity(),
            testData.getResponseType());

    assertEquals(response.getStatusCode(), testData.getExpectedResponseStatus());

    if (testData.isCheckBody()) {
      assertEquals(response.getBody(), testData.getExpectedResult());
    }
  }
}
