package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.UniqueRecipeList;

/**
 * Wraps all data at the recipe-book level <br>
 * Duplicates are not allowed (by .isSameRecipe comparison).
 */
public class RecipeBook implements ReadOnlyRecipeBook {

    private final UniqueRecipeList recipes;

    public RecipeBook() {
        recipes = new UniqueRecipeList();
    }

    // ------ List Operations ------
    /**
     * Creates a RecipeBook using the Recipes in the {@code toBeCopied}
     */
    public RecipeBook(ReadOnlyRecipeBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the recipe list with {@code recipes}. <br>
     * {@code recipes} must not contain duplicate persons.
     */
    public void setRecipes(List<Recipe> recipes) {
        this.recipes.setRecipes(recipes);
    }

    /**
     * Resets the existing data of this {@code RecipeBook} with {@code newData}.
     */
    public void resetData(ReadOnlyRecipeBook newData) {
        requireNonNull(newData);
        setRecipes(newData.getRecipeList());
    }

    // ------ Recipe Operations ------

    /** Returns true if a recipe shares the same name as {@code recipe} exists in the recipe book. */
    public boolean hasRecipe(Recipe recipe) {
        return this.getRecipeList().stream()
                .anyMatch(recipe::isSameRecipe);
    }

    public void addRecipe(Recipe toAdd) {
        recipes.add(toAdd);
    }

    public void setRecipe(Recipe target, Recipe editedRecipe) {
        requireAllNonNull(target, editedRecipe);
        recipes.setRecipe(target, editedRecipe);
    }

    public void removeRecipe(Recipe toRemove) {
        recipes.remove(toRemove);
    }

    // ------ Utils ------
    @Override
    public String toString() {
        return String.format("%d recipe(s)", recipes.asUnmodifiableObservableList().size());
    }

    @Override
    public ObservableList<Recipe> getRecipeList() {
        return recipes.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RecipeBook)) {
            return false;
        }

        if (o == this) {
            return true;
        }

        RecipeBook other = (RecipeBook) o;
        return this.recipes.equals(other.recipes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipes);
    }
}
