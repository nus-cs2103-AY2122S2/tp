package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.recipe.CompletionTime;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.ServingSize;
import seedu.address.model.recipe.Step;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Recipe objects.
 */
public class RecipeBuilder {
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final int DEFAULT_COMPLETION_TIME = 5;
    public static final int DEFAULT_SERVING_SIZE = 2;
    public static final String DEFAULT_STEP = "Cook the spaghetti!";
    public static final String DEFAULT_TAG = "Italian";
    public static final String DEFAULT_INGREDIENT_NAME = "Bacon";
    public static final int DEFAULT_INGREDIENT_QTY = 3;
    public static final String DEFAULT_INGREDIENT_QUANTIFIER = "slices";

    private Name name;
    private CompletionTime completionTime;
    private List<Ingredient> ingredients;
    private ServingSize servingSize;
    private List<Step> steps;
    private Set<Tag> tags;

    /**
     * Creates a {@code RecipeBuilder} with the default details.
     */
    public RecipeBuilder() {
        name = new Name(DEFAULT_NAME);
        completionTime = new CompletionTime(DEFAULT_COMPLETION_TIME);
        servingSize = new ServingSize(DEFAULT_SERVING_SIZE);
        Step step = new Step(DEFAULT_STEP);
        Tag tag = new Tag(DEFAULT_TAG);
        Ingredient ingredient = new Ingredient(DEFAULT_INGREDIENT_NAME,
                DEFAULT_INGREDIENT_QTY, DEFAULT_INGREDIENT_QUANTIFIER);

        steps = new ArrayList<>();
        steps.add(step);

        tags = new HashSet<>();
        tags.add(tag);

        ingredients = new ArrayList<>();
        ingredients.add(ingredient);
    }

    /**
     * Initializes the RecipeBuilder with the data of {@code recipeToCopy}.
     */
    public RecipeBuilder(Recipe recipeToCopy) {
        name = recipeToCopy.getName();
        completionTime = recipeToCopy.getCompletionTime();
        servingSize = recipeToCopy.getServingSize();
        steps = recipeToCopy.getSteps();
        ingredients = recipeToCopy.getIngredients();
        tags = new HashSet<>(recipeToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code CompletionTime} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withCompletionTime(int completionTime) {
        this.completionTime = new CompletionTime(completionTime);
        return this;
    }

    /**
     * Sets the {@code ServingSize} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withServingSize(int servingSize) {
        this.servingSize = new ServingSize(servingSize);
        return this;
    }

    /**
     * Parses the {@code ingredients} into a {@code ArrayList<Ingredient>}
     * and set it to the {@code Recipe} that we are
     * building.
     */
    public RecipeBuilder withIngredients(String... ingredient) {
        try {
            this.ingredients = SampleDataUtil.getIngredientList(ingredient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Parses the {@code steps} into a {@code ArrayList<Step>} and set it to the {@code Recipe} that we are building.
     */
    public RecipeBuilder withSteps(String... steps) {
        this.steps = SampleDataUtil.getStepList(steps);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Recipe} that we are building.
     */
    public RecipeBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Recipe build() {
        return new Recipe(name, completionTime, servingSize, ingredients, steps, tags);
    }

}
