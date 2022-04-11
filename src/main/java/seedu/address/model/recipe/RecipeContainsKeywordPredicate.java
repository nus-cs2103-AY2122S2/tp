package seedu.address.model.recipe;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Recipe}'s {@code Name} matches any of the keywords given.
 */
public class RecipeContainsKeywordPredicate implements Predicate<Recipe> {
    private final List<String> keywords;

    public RecipeContainsKeywordPredicate(List<String> keywords) {
        this.keywords = List.of(keywords.toArray(new String[]{}));
    }

    public List<String> getKeywords() {
        return keywords;
    }

    @Override
    public boolean test(Recipe recipe) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.recipeContainsIgnoreCase(recipe.getSearchSet().toString(), keyword));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keywords.size(); i++) {
            if (i == keywords.size() - 1) {
                sb.append(String.format("\"%s\"", keywords.get(i)));
            } else {
                sb.append(String.format("\"%s\", ", keywords.get(i)));
            }
        }
        return sb.toString().trim();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RecipeContainsKeywordPredicate)) {
            return false;
        }

        if (this == o) {
            return true;
        }

        RecipeContainsKeywordPredicate other = (RecipeContainsKeywordPredicate) o;
        return this.getKeywords().equals(other.getKeywords());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getKeywords());
    }
}
