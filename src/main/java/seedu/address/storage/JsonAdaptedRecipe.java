package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.recipe.CompletionTime;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.ServingSize;
import seedu.address.model.recipe.Step;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Recipe}.
 */
public class JsonAdaptedRecipe {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Recipe's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedIngredient> ingredients = new ArrayList<>();
    private final Integer completionTime;
    private final Integer servingSize;
    private final List<JsonAdaptedStep> steps = new ArrayList<>();
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedRecipe} with the given recipe details.
     */
    @JsonCreator
    public JsonAdaptedRecipe(@JsonProperty("name") String name,
                             @JsonProperty("ingredients") List<JsonAdaptedIngredient> ingredients,
                             @JsonProperty("completionTime") Integer completionTime,
                             @JsonProperty("servingSize") Integer servingSize,
                             @JsonProperty("steps") List<JsonAdaptedStep> steps,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.servingSize = servingSize;
        this.completionTime = completionTime;
        if (ingredients != null) {
            this.ingredients.addAll(ingredients);
        }
        if (steps != null) {
            this.steps.addAll(steps);
        }
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Recipe} into this class for Jackson use.
     */
    public JsonAdaptedRecipe(Recipe source) {
        name = source.getName().fullName;
        servingSize = source.getServingSize().value;
        completionTime = source.getCompletionTime().value;
        tags.addAll(source.getTags().stream()
                .map(seedu.address.storage.JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        steps.addAll(source.getSteps().stream()
                .map(seedu.address.storage.JsonAdaptedStep::new)
                .collect(Collectors.toList()));
        ingredients.addAll(source.getIngredients().stream()
                .map(seedu.address.storage.JsonAdaptedIngredient::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted recipe object into the model's {@code Recipe} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted recipe.
     */
    public Recipe toModelType() throws IllegalValueException {
        final List<Tag> recipeTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            recipeTags.add(tag.toModelType());
        }

        final List<Ingredient> recipeIngredients = new ArrayList<>();
        for (JsonAdaptedIngredient ingredient: ingredients) {
            recipeIngredients.add(ingredient.toModelType());
        }

        final List<Step> recipeSteps = new ArrayList<>();
        for (JsonAdaptedStep step: steps) {
            recipeSteps.add(step.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (servingSize == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ServingSize.class.getSimpleName()));
        }
        if (!ServingSize.isValidServingSize(servingSize)) {
            throw new IllegalValueException(ServingSize.MESSAGE_CONSTRAINTS);
        }
        if (completionTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompletionTime.class.getSimpleName()));
        }
        if (!CompletionTime.isValidCompletionTime(completionTime)) {
            throw new IllegalValueException(CompletionTime.MESSAGE_CONSTRAINTS);
        }
        final CompletionTime modelCompletionTime = new CompletionTime(completionTime);
        final ServingSize modelServingSize = new ServingSize(servingSize);
        final List<Step> modelSteps = new ArrayList<>(recipeSteps);
        final List<Ingredient> modelIngredients = new ArrayList<>(recipeIngredients);
        final Set<Tag> modelTags = new HashSet<>(recipeTags);
        return new Recipe(modelName, modelCompletionTime, modelServingSize, modelIngredients, modelSteps, modelTags);
    }

}
