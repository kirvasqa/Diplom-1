package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static praktikum.IngredientType.SAUCE;
import static praktikum.IngredientType.FILLING;


@RunWith(Parameterized.class)
public class IngredientTest {

    @Parameterized.Parameter(0)
public IngredientType type;

    @Parameterized.Parameter(1)
    public String name;

    @Parameterized.Parameter(2)
    public float price;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {SAUCE, "Ketchup", 0.99f},
                {FILLING, "Chicken", 1.50f}
        });
    }

    @Test
    public void testIngredientConstructor() {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertNotNull(ingredient);
        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals(price, ingredient.getPrice(), 0.01f);
    }

}