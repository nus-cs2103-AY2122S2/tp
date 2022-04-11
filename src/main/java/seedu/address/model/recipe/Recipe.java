package seedu.address.model.recipe;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;


/**
 * Represents a Recipe in the recipe book. <br>
 * Guarantees: details are present and not null, field values are validated and immutable.
 */
public class Recipe {

    // Identity fields
    private final Name name;
    private final List<Ingredient> ingredients = new ArrayList<>();

    // Data fields
    private final CompletionTime completionTime;
    private final ServingSize servingSize;
    private final List<Step> steps = new ArrayList<>();
    private final Set<Tag> tags = new HashSet<>();

    private SearchSet searchValues;
    /**
     * Constructs a {@code Recipe}. <br>
     *
     * Every field must be present a not null, otherwise it throws a NullPointerException.
     */
    public Recipe(Name name, CompletionTime completionTime, ServingSize servingSize,
                  List<Ingredient> ingredients, List<Step> steps, Set<Tag> tags) {

        requireAllNonNull(name, completionTime, servingSize, ingredients, steps, tags);
        this.name = name;
        this.completionTime = completionTime;
        this.servingSize = servingSize;
        this.ingredients.addAll(ingredients);
        this.steps.addAll(steps);
        this.tags.addAll(tags);
        this.initSearchSet();
    }

    public Name getName() {
        return name;
    }

    public CompletionTime getCompletionTime() {
        return completionTime;
    }

    public ServingSize getServingSize() {
        return servingSize;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both recipes have the same name. <br>
     * This defines a weaker notion of equality between two recipes.
     *
     * @param otherRecipe another {@code Recipe} object
     * @return true if both recipes have the same name.
     */
    public boolean isSameRecipe(Recipe otherRecipe) {
        if (otherRecipe == null) {
            return false;
        }

        if (this == otherRecipe) {
            return true;
        }

        return otherRecipe.getName().equals(this.getName());
    }

    public SearchSet getSearchSet() {
        return this.searchValues;
    }

    private void initSearchSet() {
        this.searchValues = new SearchSet();
        this.searchValues.add(this.name.fullName.toLowerCase());
        this.ingredients.forEach(i -> searchValues.add(i.ingredientName.toLowerCase()));
        this.tags.forEach(t -> searchValues.add(t.tagName.toLowerCase()));
    }

    /**
     * Returns true if both recipes have the same field values. <br>
     * This defines a stronger notion of equality between two recipes.
     *
     * @param o another {@code Recipe} object
     * @return true if both recipes have the same field values.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Recipe)) {
            return false;
        }

        if (o == this) {
            return true;
        }

        Recipe other = (Recipe) o;
        return this.getName().equals(other.getName())
                && this.getCompletionTime().equals(other.getCompletionTime())
                && this.getServingSize().equals(other.getServingSize())
                && this.getIngredients().equals(other.getIngredients())
                && this.getSteps().equals(other.getSteps())
                && this.getTags().equals(other.getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, completionTime, servingSize, ingredients, steps, tags);
    }

    @Override
    public String toString() {
        // use this to display a short version of the recipe in the default/list view
        StringBuilder sb = new StringBuilder();
        sb.append(getName())
                .append(String.format("; Completion time: %s, Serving size: %s",
                        getCompletionTime(), getServingSize()));

        List<Ingredient> ingredients = getIngredients();
        if (!ingredients.isEmpty()) {
            sb.append("; Ingredients: ");

            // return ingredients with commas separating each ingredient
            // substring function removes leading and trailing brackets
            sb.append(String.join(",", ingredients.toString().substring(1,
                    ingredients.toString().length() - 1)));
        }

        List<Step> steps = getSteps();
        if (!steps.isEmpty()) {
            sb.append("; Steps: ");

            // return steps with commas separating each step
            // substring function removes leading and trailing brackets
            sb.append(String.join(",", steps.toString().substring(1,
                    steps.toString().length() - 1)));
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            sb.append("; Tags: ");
            tags.forEach(sb::append);
        }

        return sb.toString();
    }
}
