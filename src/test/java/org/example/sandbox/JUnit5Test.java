package org.example.sandbox;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName(value = "Testing new JUnit5 features.")
public class JUnit5Test {

    // @Test - методы, которые являются тестовыми ( public void methodName() )
    // @ParametrizedTest - тесты с параметрами
    // @RepeatedTest - повторяющиеся тесты
    // @BeforeEach, @AfterEach - методы, которые будут отрабатывать до и после КАЖДОГО теста
    // @BeforeAll, @AfterAll - методы, которые будут отрабатывать до и после ВСЕХ тестов
    // @DisplayName

    @BeforeAll
    public static void initAll() {
        System.out.println("Init before all tests.");
    }

    @BeforeEach
    public void init() {
        System.out.println("Init before each test.");
    }

    @Disabled(value = "This bug has been fixed.")
    @DisplayName(value = "Testing Math.abs().")
    @Test
    public void testJavaCore() {
        // Math.abs()
        double expected = Math.abs(-1.0);
        double actual = 1.0;

        assertEquals(expected, actual, "This message prints if there is error.");
    }

    @Test
    @DisplayName(value = "Testing String.")
    public void testString() {
        String str = "abc";
        // assertNotNull
        // assertTrue
        assertNotNull(str, "Variable 'str' is null.");
        assertTrue(str.contains("ab"), "Substring is not found.");
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Test
    @DisplayName(value = "Testing fail().")
    public void testRandom() {
        if (getRandomNumber(-10, 0) < 0) {
            fail(() -> "Integer is negative.");
        }
    }

}
