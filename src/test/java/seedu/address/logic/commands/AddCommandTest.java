package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyRecipeBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.RecipeBook;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.RecipeBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_recipeAcceptedByModel_addSuccessful() throws Exception {
        // create empty model for command to be executed on
        ModelStubAcceptingRecipeAdded modelStub = new ModelStubAcceptingRecipeAdded();
        Recipe validRecipe = new RecipeBuilder().build();

        // create model containing recipe
        ModelStubAcceptingRecipeAdded modelStubAdded = new ModelStubAcceptingRecipeAdded();
        modelStubAdded.addRecipe(validRecipe);

        AddCommand addCommand = new AddCommand(validRecipe);

        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS, validRecipe);

        // check that empty model now contains recipe added from AddCommand
        assertCommandSuccess(addCommand, modelStub, expectedMessage, modelStubAdded);
        assertEquals(Arrays.asList(validRecipe), modelStub.recipesAdded);
    }

    @Test
    public void execute_duplicateRecipe_throwsCommandException() {
        Recipe validRecipe = new RecipeBuilder().build();
        AddCommand addCommand = new AddCommand(validRecipe);
        ModelStub modelStub = new ModelStubWithRecipe(validRecipe);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_RECIPE, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Recipe aglioOlio = new RecipeBuilder().withName("Aglio Olio").build();
        Recipe chickenChop = new RecipeBuilder().withName("Chicken Chop").build();
        AddCommand addAglioOlioCommand = new AddCommand(aglioOlio);
        AddCommand addChickenChopCommand = new AddCommand(chickenChop);

        // same object -> returns true
        assertTrue(addAglioOlioCommand.equals(addAglioOlioCommand));

        // same values -> returns true
        AddCommand addAglioOlioCommandCopy = new AddCommand(aglioOlio);
        assertTrue(addAglioOlioCommand.equals(addAglioOlioCommandCopy));

        // different types -> returns false
        assertFalse(addAglioOlioCommand.equals(1));

        // null -> returns false
        assertFalse(addAglioOlioCommand.equals(null));

        // different recipe -> returns false
        assertFalse(addAglioOlioCommand.equals(addChickenChopCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getRecipeBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRecipeBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRecipe(Recipe recipe) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRecipeBook(ReadOnlyRecipeBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyRecipeBook getRecipeBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRecipe(Recipe recipe) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRecipe(Recipe target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRecipe(Recipe target, Recipe editedRecipe) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Recipe> getFilteredRecipeList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredRecipeList(Predicate<Recipe> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single recipe.
     */
    private class ModelStubWithRecipe extends ModelStub {
        private final Recipe recipe;

        ModelStubWithRecipe(Recipe recipe) {
            requireNonNull(recipe);
            this.recipe = recipe;
        }

        @Override
        public boolean hasRecipe(Recipe recipe) {
            requireNonNull(recipe);
            return this.recipe.isSameRecipe(recipe);
        }
    }

    /**
     * A Model stub that always accept the recipe being added.
     */
    private class ModelStubAcceptingRecipeAdded extends ModelStub {
        final ArrayList<Recipe> recipesAdded = new ArrayList<>();

        @Override
        public boolean hasRecipe(Recipe recipe) {
            requireNonNull(recipe);
            return recipesAdded.stream().anyMatch(recipe::isSameRecipe);
        }

        @Override
        public void addRecipe(Recipe recipe) {
            requireNonNull(recipe);
            recipesAdded.add(recipe);
        }

        @Override
        public ReadOnlyRecipeBook getRecipeBook() {
            return new RecipeBook();
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof ModelStubAcceptingRecipeAdded)) {
                return false;
            }

            if (this == o) {
                return true;
            }

            ModelStubAcceptingRecipeAdded that = (ModelStubAcceptingRecipeAdded) o;
            return recipesAdded.equals(that.recipesAdded);
        }

        @Override
        public int hashCode() {
            return Objects.hash(recipesAdded);
        }

        @Override
        public String toString() {
            return "ModelStubAcceptingRecipeAdded{"
                    + "recipesAdded="
                    + recipesAdded
                    + '}';
        }
    }
}
