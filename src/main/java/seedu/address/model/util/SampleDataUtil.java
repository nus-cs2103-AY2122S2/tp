package seedu.address.model.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.parser.RecipeBookParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyRecipeBook;
import seedu.address.model.RecipeBook;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.Step;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code RecipeBook} with sample data.
 */
public class SampleDataUtil {
    private static Recipe[] getSampleRecipes() {
        return RecipeList.getListOfSampleRecipes();
    }

    public static ReadOnlyRecipeBook getSampleRecipeBook() {
        RecipeBook sampleRb = new RecipeBook();
        for (Recipe sampleRecipe : SampleDataUtil.getSampleRecipes()) {
            sampleRb.addRecipe(sampleRecipe);
            System.out.println(sampleRb);
        }
        return sampleRb;
    }

    /**
       * Returns an ingredient list containing the list of strings given.
       *
       * @param strings ingredients that are to the list of ingredients.
       * @return the list of ingredients containing the newly added ingredients.
       * @throws ParseException if any of the fields given in {@code strings} is invalid.
       */
    public static List<Ingredient> getIngredientList(String... strings) throws ParseException {
        Collection<String> toBeParsed = List.of(strings);
        return RecipeBookParserUtil.parseIngredients(toBeParsed);
    }

    /**
     * Returns a step list containing the list of strings given.
     *
     * @param strings list of steps to be added to the list of step.
     * @return List of Steps containing the newly added list of step.
     */
    public static List<Step> getStepList(String... strings) {
        return Arrays.stream(strings).map(Step::new).collect(Collectors.toList());
    }

    /**
     * Returns a tag set containing the list of strings given.
     *
     * @param strings list of tag to be added to the list of tag.
     * @return list of tags containing the newly added list of tags.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }


}
