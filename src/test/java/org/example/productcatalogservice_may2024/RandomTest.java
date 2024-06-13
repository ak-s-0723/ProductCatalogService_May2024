package org.example.productcatalogservice_may2024;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
public class RandomTest {

    //@Test
    public void testRandom() {
        Random random = new Random();
        int n =  random.nextInt(10);
        assert(n < 5);

    }
}
