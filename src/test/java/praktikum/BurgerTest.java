package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerTest {
    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    private final float bunPrice;
    private final float ingredientPrice;
    private final float expectedPrice;
    private final String bunName;
    private final String ingredientName;
    private final IngredientType ingredientType;
    private final String expectedReceipt;

    public BurgerTest(float bunPrice, float ingredientPrice, float expectedPrice,
                      String bunName, String ingredientName, IngredientType ingredientType, String expectedReceipt) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
        this.bunName = bunName;
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
        this.expectedReceipt = expectedReceipt;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();


        when(mockBun.getPrice()).thenReturn(bunPrice);
        when(mockBun.getName()).thenReturn(bunName);
        when(mockIngredient.getPrice()).thenReturn(ingredientPrice);
        when(mockIngredient.getName()).thenReturn(ingredientName);
        when(mockIngredient.getType()).thenReturn(ingredientType);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 2.50f, 1.50f, 6.50f, "Sourdough", "Tomato", IngredientType.FILLING,
                        "(==== Sourdough ====)\n" +
                                "= filling Tomato =\n" +
                                "(==== Sourdough ====)\n\n" +
                                "Price: 6,500000\n" },
                { 3.00f, 2.00f, 8.00f, "Whole Grain", "Avocado", IngredientType.FILLING,
                        "(==== Whole Grain ====)\n" +
                                "= filling Avocado =\n" +
                                "(==== Whole Grain ====)\n\n" +
                                "Price: 8,000000\n" },
                { 1.80f, 1.20f, 4.80f, "Bagel", "Cream Cheese", IngredientType.FILLING,
                        "(==== Bagel ====)\n" +
                                "= filling Cream Cheese =\n" +
                                "(==== Bagel ====)\n\n" +
                                "Price: 4,800000\n"}
        });
    }

    @Test
    public void testGetPrice() {
        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.01f);
    }

    @Test
    public void testGetReceipt() {
        String actualReceipt = burger.getReceipt();

        String normalizedExpectedReceipt = expectedReceipt.replaceAll("\r\n", "\n");
        String normalizedActualReceipt = actualReceipt.replaceAll("\r\n", "\n");

        assertEquals(normalizedExpectedReceipt, normalizedActualReceipt);
    }

    @Test
    public void testAddIngredient() {
        Ingredient newIngredient = mock(Ingredient.class);
        when(newIngredient.getName()).thenReturn("Tomato");
        when(newIngredient.getPrice()).thenReturn(1.0f);
        when(newIngredient.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(newIngredient);
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        burger.removeIngredient(0);
        System.out.println("Number of Ingredients After Removal: " + burger.ingredients.size());

        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredient() {
        Ingredient anotherIngredient = mock(Ingredient.class);
        when(anotherIngredient.getName()).thenReturn("Onion");
        when(anotherIngredient.getPrice()).thenReturn(0.5f);
        when(anotherIngredient.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(anotherIngredient);
        burger.moveIngredient(1, 0);

        assertEquals(anotherIngredient, burger.ingredients.get(0));
        assertEquals(mockIngredient, burger.ingredients.get(1));
    }

}