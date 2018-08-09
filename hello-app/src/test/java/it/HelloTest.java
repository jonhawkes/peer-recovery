package it;

import org.junit.Test;

public class HelloTest extends EndpointTest {

    @Test
    public void testHello() {
      testEndpoint("/peer-recovery-test/hello", 200, "Hello, world");
    }
}
