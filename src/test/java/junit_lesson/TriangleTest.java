package junit_lesson;
import junit_lesson.providers.InvalidTriangleProvider;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

public class TriangleTest {
    private static final Logger logger = LoggerFactory.getLogger(TriangleTest.class);


    @BeforeEach
    void setUp() {
        logger.debug("BeforeEach");
    }

    @AfterEach
    void tearDown() {
        logger.debug("AfterEach");
    }

    @BeforeAll
    static void beforeAll() {
        logger.debug("BeforeAll");
    }

    @AfterAll
    static void afterAll() {
        logger.debug("AfterAll");
    }

// Done---
    // тест c junit + assertj проверками на один треугольник, на все остальные - в параметризированном тесте
    @Test
    @DisplayName("Площадь треугольника: прямоугольный треугольник")
    public void countAreaEgyptTriangleTest() {
        Triangle triangle = new Triangle(3, 4, 5);
        double area = triangle.countArea();
        assertEquals(6, area);
        assertThat(area).isEqualTo(6);
    }

    public static Stream<Arguments> trianglesAndArea() {
        return Stream.of(Arguments.of(new Triangle(3, 4, 5), 6),
                Arguments.of(new Triangle(3, 3, 3), 3.9),
                Arguments.of(new Triangle(3, 4, 6), 5.33));
    }
 // проверка junit
    @ParameterizedTest(name = "Площадь треугольника {0} равна {1}")
    @MethodSource("trianglesAndArea")
    public void countAreaTriangleTest(Triangle triangle, double expectedArea) {
        double area = triangle.countArea();
        assertEquals(expectedArea, area);
    }
 // проверка assertj
    @ParameterizedTest(name = "Площадь треугольника {0} равна {1}")
    @MethodSource("trianglesAndArea")
    public void countAreaTriangleAssertJTest(Triangle triangle, double expectedArea){
        double area = triangle.countArea();
        assertThat(area).isEqualTo(expectedArea);
    }

// ---done

    @Test
    @DisplayName("Периметр треугольника: прямоугольный треугольник")
    @Disabled("Перенесены в параметризированный тест")
    public void countPerimeterEgyptTriangleTest(){
        Triangle triangle = new Triangle(3, 4, 5); //Arrange
        int perimeter = triangle.countPerimeter(); //Act
        assertEquals(12, perimeter); //Assert
    }

    @Test
    @DisplayName("Периметр треугольника: равносторонний треугольник")
    @Disabled("Перенесены в параметризированный тест")
    public void countPerimeterTriangleWithEqualsSidesTest() {
        Triangle triangle = new Triangle(3, 3, 3); //Arrange
        int perimeter = triangle.countPerimeter(); //Act
        assertEquals(9, perimeter); //Assert
    }

    @Test
    @DisplayName("Периметр треугольника: тупоугольный треугольник")
    @Disabled("Перенесены в параметризированный тест")
    public void angleGreaterThan90Test() {
        Triangle triangle = new Triangle(3, 4, 6); //Arrange
        int perimeter = triangle.countPerimeter(); //Act
        assertEquals(13, perimeter); //Assert
    }


    public static Stream<Arguments> triangles() {
        return Stream.of(Arguments.of(new Triangle(3, 4, 5), 12),
                Arguments.of(new Triangle(3, 3, 3), 9),
                Arguments.of(new Triangle(3, 4, 6), 13)
        );
    }

    @ParameterizedTest(name = "Периметр треугольника {0} равен {1}")
    @MethodSource("triangles")
    public void countPerimeterTriangleTest(Triangle triangle, int expectedPerimeter) {
        int perimeter = triangle.countPerimeter(); //Act
        assertEquals(expectedPerimeter, perimeter); //Assert
    }

    @ParameterizedTest(name = "Перекрашивание треугольника из {0} в {1}")
    @CsvSource({"RED, GREEN", "GREEN, BLUE"})
    public void paintTriangleWithCsvSourceTest(Triangle.Colour colourBefore, Triangle.Colour colourAfter) {
        Triangle triangle = new Triangle(3, 3, 3, colourBefore); //arrange
        triangle.paint(colourAfter);
        assertEquals(colourAfter, triangle.getColour()); //Assert
    }

    @ParameterizedTest
    @ArgumentsSource(InvalidTriangleProvider.class)
    public void perimeterInvalidTriangleNegativeTestTest(Triangle triangle) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, triangle::countPerimeter);
        assertEquals("The triangle must be valid", illegalArgumentException.getMessage());
    }


    @Nested
    class PaintTriangleTest {
        Triangle triangle;

        @BeforeEach
        void setUp() {
            triangle = new Triangle(3, 3, 3);
        }

        @ParameterizedTest(name = "Перекрашивание треугольника в {0}")
        @EnumSource(Triangle.Colour.class)
        public void paintTriangleTest(Triangle.Colour colour) {
            assumeFalse(colour.equals(triangle.getColour()));
            triangle.paint(colour);
            assertEquals(colour, triangle.getColour()); //Assert
        }

        @ParameterizedTest(name = "Перекрашивание треугольника в {0}")
        @ValueSource(strings = {"RED", "GREEN"})
        public void paintTriangleWithValueSourceTest(String colour) {
            triangle.paint(colour);
            assertEquals(Triangle.Colour.valueOf(colour), triangle.getColour()); //Assert
        }

    }
}
