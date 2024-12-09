package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    @Parameterized.Parameter(0)
    public String name;

    @Parameterized.Parameter(1)
    public IngredientType expectedType;

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"SAUCE", IngredientType.SAUCE},
                {"FILLING", IngredientType.FILLING}
        };
    }

    @Test
    public void testIngredientTypeValueOf() {
        assertEquals(expectedType, IngredientType.valueOf(name));
    }
}