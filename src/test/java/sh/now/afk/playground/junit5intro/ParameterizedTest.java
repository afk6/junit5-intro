package sh.now.afk.playground.junit5intro;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ParamTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void lessThanTen(int n) {
        assertTrue(n < 10);
    }


    @ParameterizedTest
    @MethodSource(value = "addSource")
    void add(int a, int b, int expected) {
        assertEquals(a + b, expected);
    }

    static Stream<Arguments> addSource() {
        return IntStream.rangeClosed(1, 100)
                .boxed()
                .map(n -> Arguments.of(n, n, 2 * n));
    }

    @ParameterizedTest
    @JSONSource("jsondata.json")
    void json(String s) {
        log.info(s);
        assertNotNull(s);
    }

    @ParameterizedTest
    @JSONSource(value = "pojo.json", type = A.class)
    void pojo(A a) {
        log.info("data {}", a);
        assertNotNull(a);
        assertEquals(a.a+a.b, a.c);
    }

    @Data
    static class A{
        Integer a;
        Integer b;
        Integer c;
    }
}
