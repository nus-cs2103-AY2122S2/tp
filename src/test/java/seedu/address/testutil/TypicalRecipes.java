package seedu.address.testutil;

import java.util.List;

import seedu.address.model.RecipeBook;
import seedu.address.model.recipe.Recipe;

/**
 * A utility class containing a list of {@code Recipe} objects to be used in tests.
 */
public class TypicalRecipes {

    public static final Recipe AGLIO_OLIO = new RecipeBuilder()
            .withName("Aglio Olio")
            .withCompletionTime(5)
            .withIngredients("Garlic 3.0 Cloves",
                    "Spaghetti 1.0",
                    "Olive Oil 500.0 ml",
                    "Bacon 3.0 slices")
            .withServingSize(1)
            .withSteps("Cook the pasta",
                    "Saute the garlic",
                    "Toss pasta in the sauce",
                    "Toss bacon slices in pasta",
                    "Taste and season")
            .withTags("Western", "Italian").build();

    public static final Recipe FRIED_RICE = new RecipeBuilder()
            .withName("Fried Rice")
            .withCompletionTime(30)
            .withIngredients("Carrots 3.0", "Eggs 1.0", "White Rice 4.0 Cups")
            .withServingSize(1)
            .withSteps("Place carrots in saucepan and cover with water", "Heat wok")
            .withTags("Chinese", "Asian").build();

    public static final Recipe DUCK_RICE = new RecipeBuilder()
            .withName("Duck Rice")
            .withCompletionTime(30)
            .withIngredients("Duck meat 1.0 pound", "Rice 4.0 Cups", "Eggs 1.0")
            .withServingSize(1)
            .withSteps("Cook Duck", "Heat wok")
            .withTags("Chinese", "Asian").build();

    public static final Recipe SUSHI = new RecipeBuilder()
            .withName("Sushi")
            .withCompletionTime(10)
            .withIngredients("Rice 4 Cups", "Seaweed 1.0 large piece", "Cucumber 1.0")
            .withServingSize(1)
            .withSteps("Cook Rice",
                    "Spread rice over nori",
                    "Add cucumbers to the center of the rice",
                    "Gently lift the bottom of the mat up and over the sushi",
                    "Press and shape the ingredients into a tube",
                    "Roll with pressure",
                    "Slice the sushi roll")
            .withTags("Japanese", "Asian").build();

    public static final Recipe BEEF_TACO = new RecipeBuilder()
            .withName("Beef Taco")
            .withCompletionTime(10)
            .withIngredients("Lean Ground Beef 1.0 pound",
                    "Ground cumin 1.0 teaspoon",
                    "Tortilla 1.0")
            .withServingSize(1)
            .withSteps("Heat up your tortillas",
                    "Assemble the avocado and lettuce atop of tortilla")
            .withTags("Mexican").build();

    public static final Recipe SHOYU_RAMEN = new RecipeBuilder()
            .withName("Shoyu Ramen")
            .withCompletionTime(30)
            .withIngredients("Ramen Noodle 4.0 oz",
                    "Chicken Broth 8.0 Cups",
                    "Shiitake Mushrooms 16.0",
                    "Eggs 2",
                    "Chashu Pork 1 pounds")
            .withServingSize(1)
            .withSteps("Get the soup", "Cook the noodles", "Cook the ingredients")
            .withTags("Japanese", "Asian").build();

    public static final Recipe FISH_CHIPS = new RecipeBuilder()
            .withName("Fish and Chips")
            .withCompletionTime(30)
            .withIngredients("Fish 1 pound", "Fries 5 Cups")
            .withServingSize(1)
            .withSteps("Deep fry fish and fries")
            .withTags("Western").build();

    public static final Recipe BOLOGNESE = new RecipeBuilder()
            .withName("Bolognese")
            .withCompletionTime(30)
            .withIngredients("Garlic 3.0 Cloves",
                    "Spaghetti 1.0",
                    "Olive Oil 500.0 ml",
                    "Bacon 3.0 slices")
            .withServingSize(1)
            .withSteps("Cook the pasta",
                    "Saute the garlic",
                    "Toss pasta in the sauce",
                    "Toss bacon slices in pasta",
                    "Taste and season")
            .withTags("Western", "Italian").build();


    //Manually added
    public static final Recipe CHICKEN_RICE = new RecipeBuilder()
            .withName("Chicken Rice")
            .withCompletionTime(30)
            .withIngredients("Chicken 1.0 pounds", "Rice 4 Cups", "Cucumber 1 slice")
            .withServingSize(1)
            .withSteps("Place cucumber in saucepan and cover with water", "Heat wok").build();

    public static final Recipe VEGAN_TACOS = new RecipeBuilder()
            .withName("Vegan Taco")
            .withCompletionTime(10)
            .withIngredients("Tortillas 1 wrap",
                    "Salsa 4.0 Cups",
                    "Chopped Avocado 4.0 slices",
                    "Shredded Lettuce 4.0 slices")
            .withServingSize(1)
            .withSteps("Heat up your tortillas",
                    "Assemble the avocado and lettuce atop of tortilla")
            .withTags("Mexican", "Vegan").build();


    private TypicalRecipes() {
    } //prevents instantiation

    /**
     * Returns an {@code RecipeBook} with all the typical recipes.
     */
    public static RecipeBook getTypicalRecipeBook() {
        RecipeBook rb = new RecipeBook();
        for (Recipe recipe : getTypicalRecipes()) {
            rb.addRecipe(recipe);
        }
        return rb;
    }

    public static List<Recipe> getTypicalRecipes() {
        return List.of(AGLIO_OLIO, FRIED_RICE, DUCK_RICE, SUSHI, BEEF_TACO, SHOYU_RAMEN, FISH_CHIPS, BOLOGNESE);
    }
}
