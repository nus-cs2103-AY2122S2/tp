package seedu.address.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.recipe.exceptions.DuplicateRecipeException;
import seedu.address.model.recipe.exceptions.RecipeNotFoundException;

/**
 * A list of recipes that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Recipe#isSameRecipe(Recipe)}. As such, adding and updating of
 * recipes uses Recipe#isSameRecipe(Recipe) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniqueRecipeList. However, the removal of a person uses Recipe#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Recipe#isSameRecipe(Recipe)
 */
public class UniqueRecipeList implements Iterable<Recipe> {

    private final ObservableList<Recipe> internalList = FXCollections.observableArrayList();
    private final ObservableList<Recipe> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent recipe as the given argument.
     */
    public boolean contains(Recipe toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameRecipe);
    }

    /**
     * Adds a recipe to the list.
     * The recipe must not already exist in the list.
     */
    public void add(Recipe toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateRecipeException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the recipe {@code target} in the list with {@code editedRecipe}.
     * {@code target} must exist in the list.
     * The recipe identity of {@code editedRecipe} must not be the same as another existing recipe in the list.
     */
    public void setRecipe(Recipe target, Recipe editedRecipe) {
        requireAllNonNull(target, editedRecipe);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new RecipeNotFoundException();
        }

        if (!target.isSameRecipe(editedRecipe) && contains(editedRecipe)) {
            throw new DuplicateRecipeException();
        }

        internalList.set(index, editedRecipe);
    }

    /**
     * Removes the equivalent recipe from the list.
     * The recipe must exist in the list.
     */
    public void remove(Recipe toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new RecipeNotFoundException();
        }
    }

    public void setRecipes(UniqueRecipeList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code recipes}.
     * {@code recipes} must not contain duplicate recipes.
     */
    public void setRecipes(List<Recipe> recipes) {
        requireAllNonNull(recipes);
        if (!recipesAreUnique(recipes)) {
            throw new DuplicateRecipeException();
        }

        internalList.setAll(recipes);
    }

    public ObservableList<Recipe> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Recipe> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UniqueRecipeList)) {
            return false;
        }

        if (o == this) {
            return true;
        }
        UniqueRecipeList other = (UniqueRecipeList) o;
        return this.internalList.equals(other.internalList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalList);
    }

    /**
     * Returns true if {@code recipes} only contain unique entries. <br>
     * Uniqueness here uses the weak notation of equality (same name).
     *
     * @param recipes list of {@code Recipe} that needs to be checked for unique entries.
     * @return true if {@code recipes} does not contain any duplicates.
     */
    private boolean recipesAreUnique(List<Recipe> recipes) {
        Set<String> duplicateCheck = new HashSet<>();
        recipes.forEach(recipe -> duplicateCheck.add(recipe.getName().fullName));

        // if size are equal, recipe list has unique entries (weak notation)
        return recipes.size() == duplicateCheck.size();
    }
}
