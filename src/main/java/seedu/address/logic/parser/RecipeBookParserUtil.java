package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.CompletionTime;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.ServingSize;
import seedu.address.model.recipe.Step;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class RecipeBookParserUtil {

    /**
     * Parses a {@code String oneBasedIndex} into an {@code Index} and returns it. <br>
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the index is invalid (not a non-zero positive integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(Messages.MESSAGE_INVALID_RECIPE_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name} and returns it. <br>
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String time} into an {@code CompletionTime} and returns it. <br>
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the time is invalid (not a non-zero positive integer).
     */
    public static CompletionTime parseCompletionTime(String time) throws ParseException {
        requireNonNull(time);

        String trimmedTime = time.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedTime)) {
            throw new ParseException(CompletionTime.MESSAGE_CONSTRAINTS);
        }
        return new CompletionTime(Integer.parseInt(trimmedTime));
    }

    /**
     * Parses a {@code String servingSize} into a {@code ServingSize} and returns it. <br>
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the ServingSize is invalid (not a non-zero positive number).
     */
    public static ServingSize parseServingSize(String servingSize) throws ParseException {
        String trimmedSize = servingSize.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedSize)) {
            throw new ParseException(ServingSize.MESSAGE_CONSTRAINTS);
        }
        return new ServingSize(Integer.parseInt(servingSize));
    }


    /**
     * Parses a {@code String ingredient} into a {@code Ingredient}. <br>
     * Input string is checked for validity and trimmed before returning an {@code Ingredient}.
     *
     * @throws ParseException if any of the fields given in {@code ingredient} is invalid.
     */
    public static Ingredient parseIngredient(String ingredient) throws ParseException {
        requireNonNull(ingredient);
        Pattern numberPattern = Pattern.compile(Ingredient.QUANTITY_VALIDATION_REGEX);
        Matcher numberMatcher = numberPattern.matcher(ingredient);

        // ------ Guard Clauses ------
        // Quantity not detected
        if (!numberMatcher.find()) {
            throw new ParseException(Ingredient.QUANTITY_CONSTRAINTS);
        }

        String[] splitIngredient = ingredient.split(Ingredient.QUANTITY_VALIDATION_REGEX);

        // String split should be [ingredient, quantifier]

        String name;
        String quantity;
        String quantifier;

        switch (splitIngredient.length) {
        case 1: // (name, quantity) case
            name = splitIngredient[0].trim();
            quantity = numberMatcher.group(0).trim();

            isValidIngredientInput(name, quantity);

            return new Ingredient(name, Double.parseDouble(quantity));

        case 2: // (name, quantity, quantifier) case
            name = splitIngredient[0].trim();
            quantity = numberMatcher.group(0).trim();
            quantifier = splitIngredient[1].trim();

            isValidIngredientInput(name, quantity);

            return new Ingredient(name, Double.parseDouble(quantity), quantifier);

        default:
            throw new ParseException(Messages.MESSAGE_MISSING_INGREDIENT_FIELDS);
        }
    }

    /**
     * Parses {@code Collection<String> ingredients} into a {@code List<Ingredient>}.
     */
    public static List<Ingredient> parseIngredients(Collection<String> ingredients) throws ParseException {
        requireNonNull(ingredients);

        final List<Ingredient> ingredientList = new ArrayList<>();
        for (String ingredient : ingredients) {
            ingredientList.add(parseIngredient(ingredient));
        }

        return ingredientList;
    }

    /**
     * Parses a {@code String step} into a {@code Step} and returns it. <br>
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code step} is invalid.
     */
    public static Step parseStep(String step) throws ParseException {
        requireNonNull(step);
        String trimmedStep = step.trim();
        if (!Step.isValidStep(trimmedStep)) {
            throw new ParseException(Step.MESSAGE_CONSTRAINTS);
        }
        return new Step(trimmedStep);
    }

    /**
     * Parses {@code Collection<String> steps} into a List.
     */
    public static List<Step> parseSteps(Collection<String> steps) throws ParseException {
        requireNonNull(steps);

        final List<Step> stepList = new ArrayList<>();
        for (String step : steps) {
            stepList.add(parseStep(step));
        }
        return stepList;
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    private static boolean isReset(List<String> tags) {
        boolean containsOneTag = tags.size() == 1;
        boolean containsEmptyTag = tags.get(0).trim().equals("");
        return (containsOneTag && containsEmptyTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(List<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();

        if (tags.isEmpty()) {
            return tagSet;
        }

        if (isReset(tags)) {
            return tagSet;
        }

        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }

        return tagSet;
    }

    private static void isValidIngredientInput(String name, String quantity) throws ParseException {
        if (!Ingredient.isValidIngredientName(name)) {
            throw new ParseException(Ingredient.NAME_CONSTRAINTS);
        }

        if (!StringUtil.isNonZeroPositiveDouble(quantity)) {
            throw new ParseException(Ingredient.QUANTITY_CONSTRAINTS);
        }
    }

    /**
     * Compares 2 recipe name values and returns if
     * both are equal or not (non-case-sensitivity)
     *
     * @param firstRecipeName the recipe name of the first recipe
     * @param secondRecipeName the recipe name of the second recipe
     * @return whether the recipes names are equal (non-case-sensitive)
     */
    public static boolean isRecipeNamesEqual(Name firstRecipeName, Name secondRecipeName) {
        String lowerFirstRecipeName = firstRecipeName.fullName.toLowerCase().trim();
        String lowerSecondRecipeName = secondRecipeName.fullName.toLowerCase().trim();
        return lowerFirstRecipeName.equals(lowerSecondRecipeName);
    }
}
