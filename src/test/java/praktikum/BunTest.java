package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BunTest {
    // Параметры для тестов
    @Parameterized.Parameter(0)
    public String expectedName;

    @Parameterized.Parameter(1)
    public float expectedPrice;

    // Задаем параметры для тестов
    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                { "Классическая булочка", 5.0f },
                { "Булочка с кунжутом", 10.0f },
                { "Чиабатта", 15.5f },
                { "Белая булочка", 20.0f },
                { "Черная булочка", 25.0f }
        };
    }

    @Test
    public void testBunCreation() {
        // Act
        Bun bun = new Bun(expectedName, expectedPrice);

        // Assert
        assertEquals("Название булочки должно совпадать",expectedName, bun.getName());
        assertEquals("Цена должна совпадать",expectedPrice, bun.getPrice(), 0.01);
    }
}