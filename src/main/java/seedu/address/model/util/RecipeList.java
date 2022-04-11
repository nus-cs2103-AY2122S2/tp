package seedu.address.model.util;

import static seedu.address.model.util.SampleDataUtil.getTagSet;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.recipe.CompletionTime;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.ServingSize;
import seedu.address.model.recipe.Step;


/**
 * Contains the list of recipes for SampleDataUtil.
 */
public class RecipeList {

    private static Recipe recipeForAglioOglio() {
        Ingredient ingredient1 = new Ingredient("Garlic", 3, "Cloves");
        Ingredient ingredient2 = new Ingredient("Spaghetti", 1);
        Ingredient ingredient3 = new Ingredient("Olive oil", 500, "ml");
        Ingredient ingredient4 = new Ingredient("Bacon", 3, "slices");

        Ingredient[] ingredientArr = new Ingredient[]{ingredient1, ingredient2, ingredient3, ingredient4};
        List<Ingredient> ingredients = List.of(ingredientArr);

        Step step1 = new Step("Cook the pasta");
        Step step2 = new Step("Saute the garlic");
        Step step3 = new Step("Toss pasta in the sauce");
        Step step4 = new Step("Toss bacon slices in pasta");
        Step step5 = new Step("Taste and season");

        List<Step> steps = new ArrayList<>(List.of(new Step[]{step1, step2, step3, step4, step5}));

        Recipe recipe = new Recipe(new Name("Aglio Olio"), new CompletionTime(5), new ServingSize(1),
                ingredients, steps, getTagSet("Western", "Italian"));
        return recipe;
    }

    private static Recipe recipeForNasiLemak() {
        Ingredient ingredient1 = new Ingredient("Sambal Ikan Bilis", 2, "teaspoon");
        Ingredient ingredient2 = new Ingredient("Rice", 2, "cups");
        Ingredient ingredient3 = new Ingredient("Hard Boiled Eggs", 2);
        Ingredient ingredient4 = new Ingredient("Cucumber", 1, "slice");
        Ingredient ingredient5 = new Ingredient("Coconut Milk", 1, "Can");

        Ingredient[] ingredientArr = new Ingredient[]{ingredient1, ingredient2, ingredient3, ingredient4, ingredient5};

        List<Ingredient> ingredients = List.of(ingredientArr);

        Step step1 = new Step("rinse your rice and drain. Add the coconut milk, a pinch of salt, and some water. "
                + "Add the pandan leaves into the rice and cook your rice");
        Step step2 = new Step("Heat some oil in a pan, add in the ikan bilis and stir well");
        Step step3 = new Step("Cut the cucumber into slices");
        Step step4 = new Step("Dish up the steamed coconut milk rice and pour some sambal ikan bilis on top "
                + "of the rice.");
        Step step5 = new Step("Serve with cucumber slices, and hard-boiled eggs.");
        List<Step> steps = new ArrayList<>(List.of(new Step[]{step1, step2, step3, step4, step5}));

        Recipe recipe = new Recipe(new Name("Nasi Lemak"), new CompletionTime(30), new ServingSize(1),
                ingredients, steps, getTagSet("Malay", "Asian"));
        return recipe;
    }

    private static Recipe recipeForFriedRice() {
        Ingredient ingredient1 = new Ingredient("Carrots", 3);
        Ingredient ingredient2 = new Ingredient("Eggs", 1);
        Ingredient ingredient3 = new Ingredient("White Rice", 4, "Cups");
        Ingredient ingredient4 = new Ingredient("Olive Oil", 500.0, "ml");

        Ingredient[] ingredientArr = new Ingredient[]{ingredient1, ingredient2, ingredient3, ingredient4};

        List<Ingredient> ingredients = List.of(ingredientArr);

        Step step1 = new Step("Place carrots in saucepan and pour oil onto the bottom of the wok.");
        Step step2 = new Step("Pour the beaten eggs and scramble it with a spatula");
        Step step3 = new Step("Add the rice and stir");
        List<Step> steps = new ArrayList<>(List.of(new Step[]{step1, step2, step3}));

        Recipe recipe = new Recipe(new Name("Fried Rice"), new CompletionTime(30), new ServingSize(1),
                ingredients, steps, getTagSet("Chinese", "Asian"));
        return recipe;
    }

    private static Recipe recipeForDuckRice() {
        Ingredient ingredient1 = new Ingredient("Duck meat", 2, "kg");
        Ingredient ingredient2 = new Ingredient("Rice", 4, "cups");
        Ingredient ingredient3 = new Ingredient("Eggs", 1.0);
        Ingredient ingredient4 = new Ingredient("Dark soy sauce", 150, "ml");
        Ingredient ingredient5 = new Ingredient("Cucumber", 1);

        Ingredient[] ingredientArr = new Ingredient[]{ingredient1, ingredient2, ingredient3, ingredient4, ingredient5};

        List<Ingredient> ingredients = List.of(ingredientArr);

        Step step1 = new Step("Cook Duck in a large pot, fill it up with water and boil it");
        Step step2 = new Step("Once boiling, bring down to a gentle simmer, leave on a stovetop to braise for an hour");
        Step step3 = new Step("Place the duck on a roasting tray and place in an oven for 10 to 15 mins or "
                + "until the skin starts to brown");
        Step step4 = new Step("Cook the rice ");
        Step step5 = new Step("Carve the duck and add the rice");
        Step step6 = new Step("Pour over the braising liquid with shavings of cucumbers");
        List<Step> steps = new ArrayList<>(List.of(new Step[]{step1, step2, step3, step4, step5, step6}));

        Recipe recipe = new Recipe(new Name("Singaporean Duck Rice"), new CompletionTime(150), new ServingSize(1),
                ingredients, steps, getTagSet("Chinese", "Asian"));
        return recipe;
    }

    private static Recipe recipeForSushi() {
        Ingredient ingredient1 = new Ingredient("Rice", 4, "cups");
        Ingredient ingredient2 = new Ingredient("Seaweed", 1, "large piece");
        Ingredient ingredient3 = new Ingredient("Cucumber", 1);

        Ingredient[] ingredientArr = new Ingredient[]{ingredient1, ingredient2, ingredient3};

        List<Ingredient> ingredients = List.of(ingredientArr);

        Step step1 = new Step("Cook Rice");
        Step step2 = new Step("Spread rice over nori");
        Step step3 = new Step("Add cucumbers to the center of the rice");
        Step step4 = new Step("Gently lift the bottom of the mat up and over the sushi");
        Step step5 = new Step("Press and shape the ingredients into a tube");
        Step step6 = new Step("Roll with pressure");
        Step step7 = new Step("Slice the sushi roll");
        List<Step> steps = new ArrayList<>(List.of(new Step[]{step1, step2, step3, step4, step5, step6, step7}));

        Recipe recipe = new Recipe(new Name("Sushi"), new CompletionTime(30), new ServingSize(1),
                ingredients, steps, getTagSet("Japanese", "Asian"));
        return recipe;
    }

    private static Recipe recipeForBeefTaco() {
        Ingredient ingredient1 = new Ingredient("Lean Ground Beef", 1.0, "pound");
        Ingredient ingredient2 = new Ingredient("Ground cumin", 1.0, "teaspoon");
        Ingredient ingredient3 = new Ingredient("Chilli Powder", 0.5, "teaspoon");
        Ingredient ingredient4 = new Ingredient("Salt", 0.5, "teaspoon");
        Ingredient ingredient5 = new Ingredient("Smoked Paprika", 0.5, "teaspoon");
        Ingredient ingredient6 = new Ingredient("Pepper", 0.5, "teaspoon");
        Ingredient ingredient7 = new Ingredient("Cherry Tomatoes", 1, "pint");
        Ingredient ingredient8 = new Ingredient("Cilantro", 0.25, "cup");
        Ingredient ingredient9 = new Ingredient("Sweet Onion", 0.5, "diced");


        Ingredient[] ingredientArr = new Ingredient[]{ingredient1, ingredient2, ingredient3, ingredient4, ingredient5,
            ingredient6, ingredient7, ingredient8, ingredient9};

        List<Ingredient> ingredients = List.of(ingredientArr);

        Step step1 = new Step("Heat a skillet over medium heat");
        Step step2 = new Step("Once the skillet it hot, add the ground beef and break it apart with a wooden spoon");
        Step step3 = new Step("Cook, stirring and breaking apart the beef often, until it begins to brown");
        Step step4 = new Step("Add in the cumin, paprika, garlic powder, chili powder, salt and pepper");
        Step step5 = new Step("Stir well to disperse all the seasonings. Cook until the beef is just browned");
        Step step6 = new Step("Serve the ground beef in tacos (tortillas in the oven for a few minutes), "
                + "on nachos or in salads");
        Step step7 = new Step("Top with fresh cilantro, diced avocado");
        List<Step> steps = new ArrayList<>(List.of(new Step[]{step1, step2, step3, step4, step5, step6, step7}));

        Recipe recipe = new Recipe(new Name("Beef Taco"), new CompletionTime(60), new ServingSize(1),
                ingredients, steps, getTagSet("Mexican", "Western"));
        return recipe;
    }

    private static Recipe recipeForShoyuRamen() {
        Ingredient ingredient1 = new Ingredient("Ramen Noodle", 4.0, "oz");
        Ingredient ingredient2 = new Ingredient("Chicken Broth", 8.0, "Cups");
        Ingredient ingredient3 = new Ingredient("Shiitake Mushrooms", 16);
        Ingredient ingredient4 = new Ingredient("Eggs", 0.5, "teaspoon");
        Ingredient ingredient5 = new Ingredient("Chashu Pork", 1, "pound");


        Ingredient[] ingredientArr = new Ingredient[]{ingredient1, ingredient2, ingredient3, ingredient4, ingredient5};

        List<Ingredient> ingredients = List.of(ingredientArr);

        Step step1 = new Step("Heat sesame oil in a deep pan over medium heat");
        Step step2 = new Step("Sauté the chopped ginger and garlic in the pan for about a minute");
        Step step3 = new Step("Lower the heat and add the chicken soup stock and kombu dashi soup stock to the pan");
        Step step4 = new Step("Bring to a boil");
        Step step5 = new Step("Add the soy sauce, sake, sugar, and salt to the soup and bring to a boil again");
        Step step6 = new Step("Boil water in a large pot. Add the chukamen noodles to the boiling water and cook "
                + "for a few minutes");
        Step step7 = new Step("Place a fine-mesh strainer over a bowl and pour the soup through the strainer");
        Step step8 = new Step("Pour hot soup into bowls and drain the noodles");
        Step step9 = new Step("Add toppings, Chashu Pork, Shiitake Mushrooms, Eggs");
        List<Step> steps = new ArrayList<>(List.of(new Step[]{step1, step2, step3, step4, step5, step6, step7, step8,
            step9}));

        Recipe recipe = new Recipe(new Name("Shoyu Ramen"), new CompletionTime(30), new ServingSize(1),
                ingredients, steps, getTagSet("Japanese", "Asian"));
        return recipe;
    }

    private static Recipe recipeForFishAndChips() {
        Ingredient ingredient1 = new Ingredient("Fish Fillets", 4);
        Ingredient ingredient2 = new Ingredient("Potatoes", 4);
        Ingredient ingredient3 = new Ingredient("Baking Powder", 1, "teaspoon");
        Ingredient ingredient4 = new Ingredient("Salt", 1, "teaspoon");
        Ingredient ingredient5 = new Ingredient("Vegetable Oil", 1, "quart");
        Ingredient ingredient6 = new Ingredient("Milk", 1, "cup");

        Ingredient[] ingredientArr = new Ingredient[]{ingredient1, ingredient2, ingredient3, ingredient4, ingredient5,
            ingredient6};

        List<Ingredient> ingredients = List.of(ingredientArr);

        Step step1 = new Step("Place potatoes in a medium-size bowl of cold water");
        Step step2 = new Step("In a separate medium-size mixing bowl, mix together flour, baking powder, salt, "
                + "and pepper");
        Step step3 = new Step("Stir in the milk and egg; stir until the mixture is smooth. "
                + "Let mixture stand for 20 minutes");
        Step step4 = new Step("Preheat the oil in a large pot or electric skillet to 350 degrees F (175 degrees C)");
        Step step5 = new Step("Fry the potatoes in the hot oil until they are tender. Drain them on paper towels");
        Step step6 = new Step("Dredge the fish in the batter, one piece at a time, and place them in the hot oil");
        Step step7 = new Step("Fry until the fish is golden brown. Drain well on paper towels");
        Step step8 = new Step("Fry the potatoes again for 1 to 2 minutes for added crispness");
        List<Step> steps = new ArrayList<>(List.of(new Step[]{step1, step2, step3, step4, step5, step6, step7, step8}));

        Recipe recipe = new Recipe(new Name("Fish and Chips"), new CompletionTime(30), new ServingSize(1),
                ingredients, steps, getTagSet("Western"));
        return recipe;
    }

    private static Recipe recipeForBolognese() {
        Ingredient ingredient1 = new Ingredient("Garlic", 3, "Cloves");
        Ingredient ingredient2 = new Ingredient("Spaghetti", 1);
        Ingredient ingredient3 = new Ingredient("Olive Oil", 500, "ml");
        Ingredient ingredient4 = new Ingredient("Rashers smoked streaky bacon", 4, "chopped");
        Ingredient ingredient5 = new Ingredient("Medium onions", 2, "chopped");
        Ingredient ingredient6 = new Ingredient("Garlic", 2, "chopped");
        Ingredient ingredient7 = new Ingredient("Beef mince", 500, "grams");
        Ingredient ingredient8 = new Ingredient("Bolognese Sauce", 500, "ml");

        Ingredient[] ingredientArr = new Ingredient[]{ingredient1, ingredient2, ingredient3, ingredient4, ingredient5,
            ingredient6, ingredient7, ingredient8};

        List<Ingredient> ingredients = List.of(ingredientArr);

        Step step1 = new Step("Put a large saucepan on a medium heat and add 1 tbsp olive oil");
        Step step2 = new Step("Add 4 finely chopped bacon rashers and fry for 10 mins until golden and crisp");
        Step step3 = new Step("Reduce the heat and add the 2 onions, 2 garlic cloves then fry for 10 mins");
        Step step4 = new Step("Stir the veg often until it softens");
        Step step5 = new Step("Increase the heat to medium-high, add 500g beef mince and cook stirring for 3-4 mins "
                + "until the meat is browned all over");
        Step step6 = new Step("Cook the spaghetti");
        Step step7 = new Step("Drain the spaghetti and either stir into the bolognese sauce");
        List<Step> steps = new ArrayList<>(List.of(new Step[]{step1, step2, step3, step4, step5, step6, step7}));

        Recipe recipe = new Recipe(new Name("Bolognese"), new CompletionTime(30), new ServingSize(1),
                ingredients, steps, getTagSet("Western", "Italian"));
        return recipe;
    }





    public static Recipe[] getListOfSampleRecipes() {
        return new Recipe[] {recipeForAglioOglio(), recipeForNasiLemak(), recipeForFriedRice(), recipeForDuckRice(),
        recipeForSushi(), recipeForBeefTaco(), recipeForShoyuRamen(), recipeForFishAndChips(), recipeForBolognese()};
    }
}
